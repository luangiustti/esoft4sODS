package AepOds.Cadastro.repository;

import java.sql.Connection;
import java.sql.Statement;

import AepOds.Cadastro.Usuário;

import java.sql.PreparedStatement;

public class RepositórioDeCadastroJDBCUsuario implements RepositórioDeCadastroUsuario {
    private Connection conexão;
    private PreparedStatement insert;

    public RepositórioDeCadastroJDBCUsuario(Connection conexão){
        this.conexão = conexão;
        criarTabela();
    }

    private void criarTabela(){
        try (Statement createTable = conexão.createStatement()){
            createTable.executeUpdate("create table if not exists usuario("+
                "ID_USUARIO CHAR(36) CONSTRAINT ID_USUARIO_NN NOT NULL,"+
                "NOME_USUARIO VARCHAR(30),"+
                "CPF_USUARIO CHAR(11) CONSTRAINT CPF_USUARIO_NN NOT NULL,"+
                "DATA_NASC_USUARIO VARCHAR(50),"+
                "TELEFONE_USUARIO CHAR(11),"+
                "EMAIL_USUARIO VARCHAR(50) CONSTRAINT EMAIL_USUARIO_NN NOT NULL,"+
                "SENHA_USUARIO VARCHAR(30),"+
                "ID_ENDERECO_USUARIO CHAR(36) CONSTRAINT ID_ENDERECO_USUARIO_NN NOT NULL,"+
            
                "CONSTRAINT ID_USUARIO_PK PRIMARY KEY (ID_USUARIO, CPF_USUARIO),"+
                "CONSTRAINT ID_ENDERECO_USUARIO_FK FOREIGN KEY (ID_ENDERECO_USUARIO) REFERENCES endereco,"+
                "CONSTRAINT EMAIL_USUARIO_UK UNIQUE (EMAIL_USUARIO))");
            conexão.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void incluirUsuario(Usuário novo) {
        try {
            if (insert == null) {
                insert = conexão.prepareStatement("insert into usuario ("+
                "ID_USUARIO, NOME_USUARIO, CPF_USUARIO, DATA_NASC_USUARIO, TELEFONE_USUARIO, EMAIL_USUARIO, SENHA_USUARIO, ID_ENDERECO_USUARIO)"+
                " values (?,?,?,?,?,?,?,?)");
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
	public void close() throws Exception {
		System.out.println("RepositórioDeUsuarioJDBC fechando!!! :)");
        if (insert != null && !insert.isClosed()) {
            insert.close();
        }
		
	}
}
