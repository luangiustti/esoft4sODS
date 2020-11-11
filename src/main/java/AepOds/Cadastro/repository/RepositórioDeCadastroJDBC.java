package AepOds.Cadastro.repository;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import AepOds.Cadastro.Endereço;
import AepOds.Cadastro.Mercado;
import AepOds.Cadastro.Ong;
import AepOds.Cadastro.Produto;
import AepOds.Cadastro.Usuário;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositórioDeCadastroJDBC implements RepositórioDeCadastro{
    private Connection conexão;
    private PreparedStatement insert;

    public RepositórioDeCadastroJDBC(Connection conexão){
        this.conexão = conexão;
        criarTabela();
    }

    private void criarTabela(){
        try (Statement createTable = conexão.createStatement()){
            createTable.executeUpdate("create table if not exists endereco("+
                "ID_ENDERECO CHAR(36) CONSTRAINT ID_ENDERECO_NN NOT NULL,"+
                "ESTADO_ENDERECO CHAR(2),"+
                "CIDADE_ENDERECO VARCHAR(30),"+
                "BAIRRO_ENDERECO VARCHAR(30),"+
                "LOGRADOURO_ENDERECO VARCHAR(30),"+
                "NUMERO_ENDERECO VARCHAR(10),"+
                "COMPLEMENTO_ENDERECO VARCHAR(150),"+

                "CONSTRAINT ID_ENDERECO_PK PRIMARY KEY (ID_ENDERECO))");
            conexão.commit();
            
            createTable.executeUpdate("create table if not exists usuario("+
                "ID_USUARIO CHAR(36) CONSTRAINT ID_USUARIO_NN NOT NULL,"+
                "NOME_USUARIO VARCHAR(30),"+
                "CPF_USUARIO CHAR(11) CONSTRAINT CPF_USUARIO_NN NOT NULL,"+
                "DATA_NASC_USUARIO DATE,"+
                "TELEFONE_USUARIO CHAR(11),"+
                "EMAIL_USUARIO VARCHAR(50) CONSTRAINT EMAIL_USUARIO_NN NOT NULL,"+
                "SENHA_USUARIO VARCHAR(30),"+
                "ID_ENDERECO_USUARIO CHAR(36) CONSTRAINT ID_ENDERECO_USUARIO_NN NOT NULL,"+
            
                "CONSTRAINT ID_USUARIO_PK PRIMARY KEY (ID_USUARIO, CPF_USUARIO),"+
                "CONSTRAINT ID_ENDERECO_USUARIO_FK FOREIGN KEY (ID_ENDERECO_USUARIO) REFERENCES endereco,"+
                "CONSTRAINT EMAIL_USUARIO_UK UNIQUE (EMAIL_USUARIO))");
    
            createTable.executeUpdate("create table if not exists mercado("+
                "ID_MERCADO CHAR(36) CONSTRAINT ID_MERCADO_NN NOT NULL,"+
                "CNPJ_MERCADO CHAR(14) CONSTRAINT CNPJ_MERCADO_NN NOT NULL,"+
                "NOME_FANTASIA_MERCADO VARCHAR(30),"+
                "TELEFONE_MERCADO CHAR(11),"+
                "URL_MERCADO VARCHAR(100) CONSTRAINT URL_MERCADO_NN NOT NULL,"+
                "ID_ENDERECO_MERCADO CHAR(36) CONSTRAINT ID_ENDERECO_MERCADO_NN NOT NULL,"+
            
                "CONSTRAINT ID_MERCADO_PK  PRIMARY KEY (ID_MERCADO, CNPJ_MERCADO, URL_MERCADO),"+
                "CONSTRAINT ID_ENDERECO_MERCADO_FK FOREIGN KEY (ID_ENDERECO_MERCADO) REFERENCES endereco)");
            
            createTable.executeUpdate("create table if not exists filtro_produtos_ong("+
                "ID_FILTRO_PRODUTOS_ONG CHAR(36) CONSTRAINT ID_FILTRO_PRODUTOS_ONG_NN NOT NULL,"+
                "NOME_FILTRO_PRODUTOS_ONG VARCHAR(30),"+
                
                "CONSTRAINT ID_FILTRO_PRODUTOS_ONG_PK PRIMARY KEY (ID_FILTRO_PRODUTOS_ONG))");

            conexão.commit();
            
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
    public void incluirEndereco (Endereço novo) {
        try {
            if (insert == null) {
                insert = conexão.prepareStatement("insert into endereco ("+
                    "ID_ENDERECO, ESTADO_ENDERECO, CIDADE_ENDERECO, BAIRRO_ENDERECO, LOGRADOURO_ENDERECO, NUMERO_ENDERECO, COMPLEMENTO_ENDERECO)"+
                    " values (?,?,?,?,?,?,?)");
            }
            insert.setString(1, novo.getId());
            insert.setString(2, novo.getEstado());
            insert.setString(3, novo.getCidade());
            insert.setString(4, novo.getBairro());
            insert.setString(5, novo.getLogradouro());
            insert.setString(6, novo.getNúmero());
            insert.setString(7, novo.getComplemento());

            insert.executeUpdate();
            conexão.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }    

    @Override
    public void incluirUsuario(Usuário novo) {
        try {
            if (insert == null) {
                insert = conexão.prepareStatement("insert into usuario("+
                "ID_USUARIO, NOME_USUARIO, CPF_USUARIO, DATA_NASC, TELEFONE_USUARIO, EMAIL_USUARIO, SENHA_USUARIO, ID_ENDERECO_USUARIO)"+
                "values (?,?,?,?,?,?,?,?)");
            }
            insert.setString(1, novo.getId());
            insert.setString(2, novo.getNome().toString());
            insert.setString(3, novo.getCpf().toString());
            insert.setString(4, novo.getData().toString());
            insert.setString(5, novo.getTelefone());
            insert.setString(6, novo.getEmail().toString());
            insert.setString(7, novo.getSenha().toString());
            insert.setString(8, novo.getEndereço().getId());

            insert.executeUpdate();
            conexão.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void incluirMercado(Mercado novo) {
        try {
            if (insert == null) {
                insert = conexão.prepareStatement("insert into mercado ("+
                "ID_MERCADO, CNPJ_MERCADO, NOME_FANTASIA_MERCADO, TELEFONE_MERCADO, URL_MERCADO, ID_ENDERECO_MERCADO)"+
                "values(?,?,?,?,?,?)");
            }
            insert.setString(1, novo.getId());
            insert.setString(2, novo.getCnpj().toString());
            insert.setString(3, novo.getNomeFantasia().toString());
            insert.setString(4, novo.getTelefone());
            insert.setString(5, novo.getUrl());
            insert.setString(6, novo.getEndereço().getId());

            insert.executeUpdate();
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
    public void incluirOng(Ong novo) {
        try {
            if (insert == null) {
                insert = conexão.prepareStatement("insert into ong("+
                "ID_MERCADO, CNPJ_ONG, NOME_FANTASIA_ONG, DESC_ONG, TELEFONE_ONG, EMAIL_ONG, SENHA_ONG, ID_ENDERECO_ONG)"+
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
            }
            insert.executeUpdate();
            conexão.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public void close() throws Exception {
		System.out.println("RepositórioDePilhaJDBC fechando!!! :)");
        if (insert != null && !insert.isClosed()) {
            insert.close();
        }
		
	}
}