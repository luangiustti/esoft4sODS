package AepOds.Cadastro.repository;

import java.sql.Connection;
import java.sql.Statement;

import AepOds.Cadastro.Endereço;

import java.sql.PreparedStatement;

public class RepositórioDeCadastroJDBCEndereco implements RepositórioDeCadastroEndereco{
    private Connection conexão;
    private PreparedStatement insert;

    public RepositórioDeCadastroJDBCEndereco(Connection conexão){
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
	public void close() throws Exception {
		System.out.println("RepositórioDeEnderecoJDBC fechando!!! :)");
        if (insert != null && !insert.isClosed()) {
            insert.close();
        }
		
	}
}