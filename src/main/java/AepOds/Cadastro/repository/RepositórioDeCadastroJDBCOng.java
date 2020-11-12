package AepOds.Cadastro.repository;

import java.sql.Connection;
import java.sql.Statement;

import AepOds.Cadastro.Ong;
import AepOds.Cadastro.Produto;


import java.sql.PreparedStatement;

public class RepositórioDeCadastroJDBCOng implements RepositórioDeCadastroOng {
    private Connection conexão;
    private PreparedStatement insert;

    public RepositórioDeCadastroJDBCOng(Connection conexão){
        this.conexão = conexão;
        criarTabela();
    }

    private void criarTabela(){
        try (Statement createTable = conexão.createStatement()){
            createTable.executeUpdate("create table if not exists ong("+
                "ID_ONG CHAR(36) CONSTRAINT ID_ONG_NN NOT NULL,"+
                "CNPJ_ONG CHAR(14) CONSTRAINT CNPJ_ONG_NN NOT NULL,"+
                "NOME_FANTASIA_ONG VARCHAR(30),"+
                "DESC_ONG VARCHAR(150),"+
                "TELEFONE_ONG CHAR(11),"+
                "EMAIL_ONG VARCHAR(50) CONSTRAINT EMAIL_ONG NOT NULL,"+
                "SENHA_ONG VARCHAR(30),"+
                "ID_ENDERECO_ONG CHAR(36) CONSTRAINT ID_ENDERECO_ONG_NN NOT NULL,"+
            
                "CONSTRAINT ID_ONG_PK PRIMARY KEY (ID_ONG, CNPJ_ONG),"+
                "CONSTRAINT EMAIL_ONG_UK UNIQUE (EMAIL_ONG),"+
                "CONSTRAINT ID_ENDERECO_ONG_FK FOREIGN KEY (ID_ENDERECO_ONG) REFERENCES endereco)");

            conexão.commit();
            createTable.executeUpdate("create table if not exists ong_produto("+
                "ID_FILTRO_PRODUTO_ONG CHAR(36) CONSTRAINT ID_FILTRO_PRODUTO_ONG_NN NOT NULL,"+
                "ID_ONG_ONG_PRODUTO CHAR(36) CONSTRAINT ID_ONG_ONG_PRODUTO_NN NOT NULL,"+
                "CNPJ_ONG_PRODUTO CHAR(14) CONSTRAINT CNPJ_ONG_PRODUTO NOT NULL,"+
            
                "CONSTRAINT ID_FILTRO_PRODUTO_ONG_FK FOREIGN KEY (ID_FILTRO_PRODUTO_ONG) REFERENCES filtro_produtos_ong,"+
                "CONSTRAINT ID_ONG_ONG_PRODUTO_FK FOREIGN KEY (ID_ONG_ONG_PRODUTO, CNPJ_ONG_PRODUTO) REFERENCES ong)");
            
            conexão.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  

    @Override
    public void incluirOng(Ong novo) {
        try {
            if (insert == null) {
                insert = conexão.prepareStatement("insert into ong("+
                "ID_ONG, CNPJ_ONG, NOME_FANTASIA_ONG, DESC_ONG, TELEFONE_ONG, EMAIL_ONG, SENHA_ONG, ID_ENDERECO_ONG)"+
                "values (?,?,?,?,?,?,?,?)");
            }
            insert.setString(1, novo.getId());
            insert.setString(2, novo.getCnpj().toString());
            insert.setString(3, novo.getNomeFantasia().toString());
            insert.setString(4, novo.getDescrição());
            insert.setString(5, novo.getTelefone());
            insert.setString(6, novo.getEmail());
            insert.setString(7, novo.getSenha().toString());
            insert.setString(8, novo.getEndereço().getId());

            insert.executeUpdate();
            conexão.commit();
            
            insert.clearParameters();
            insert = conexão.prepareStatement("insert into ong_produto("+
            "ID_FILTRO_PRODUTO_ONG, ID_ONG_ONG_PRODUTO, CNPJ_ONG_PRODUTO)"+
            "values (?,?,?)");
            for (Produto p : novo.getProdutos()) {
                insert.setString(1, p.getId());
                insert.setString(2, novo.getId());
                insert.setString(3, novo.getCnpj().toString());
                insert.executeUpdate();   
            }
            conexão.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public void close() throws Exception {
		System.out.println("RepositórioDeOngJDBC fechando!!! :)");
        if (insert != null && !insert.isClosed()) {
            insert.close();
        }
		
	}
}
