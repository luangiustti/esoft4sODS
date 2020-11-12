package AepOds.Cadastro.repository;

import java.sql.Connection;
import java.sql.Statement;

import AepOds.Cadastro.Produto;

import java.sql.PreparedStatement;

public class RepositórioDeCadastroJDBCProduto implements RepositórioDeCadastroProduto {
    private Connection conexão;
    private PreparedStatement insert;

    public RepositórioDeCadastroJDBCProduto(Connection conexão){
        this.conexão = conexão;
        criarTabela();
    }

    private void criarTabela(){
        try (Statement createTable = conexão.createStatement()){
            createTable.executeUpdate("create table if not exists filtro_produtos_ong("+
                "ID_FILTRO_PRODUTOS_ONG CHAR(36) CONSTRAINT ID_FILTRO_PRODUTOS_ONG_NN NOT NULL,"+
                "NOME_FILTRO_PRODUTOS_ONG VARCHAR(30),"+
                
                "CONSTRAINT ID_FILTRO_PRODUTOS_ONG_PK PRIMARY KEY (ID_FILTRO_PRODUTOS_ONG))");

            conexão.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void incluirProduto(Produto novo) {
        try {
            if (insert == null) {
                insert = conexão.prepareStatement("insert into filtro_produtos_ong("+
                "ID_FILTRO_PRODUTOS_ONG, NOME_FILTRO_PRODUTOS_ONG)"+
                "values (?,?)");
            }
            insert.setString(1, novo.getId());
            insert.setString(2, novo.getNome());

            insert.executeUpdate();
            conexão.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public void close() throws Exception {
		System.out.println("RepositórioDeProdutoJDBC fechando!!! :)");
        if (insert != null && !insert.isClosed()) {
            insert.close();
        }
		
	}
}
