package AepOds.Cadastro.repository;

import java.sql.Connection;
import java.sql.Statement;


import AepOds.Cadastro.Mercado;

import java.sql.PreparedStatement;

public class RepositórioDeCadastroJDBCMercado implements RepositórioDeCadastroMercado {
    private Connection conexão;
    private PreparedStatement insert;

    public RepositórioDeCadastroJDBCMercado(Connection conexão){
        this.conexão = conexão;
        criarTabela();
    }

    private void criarTabela(){
        try (Statement createTable = conexão.createStatement()){    
            createTable.executeUpdate("create table if not exists mercado("+
                "ID_MERCADO CHAR(36) CONSTRAINT ID_MERCADO_NN NOT NULL,"+
                "CNPJ_MERCADO CHAR(14) CONSTRAINT CNPJ_MERCADO_NN NOT NULL,"+
                "NOME_FANTASIA_MERCADO VARCHAR(30),"+
                "TELEFONE_MERCADO CHAR(11),"+
                "URL_MERCADO VARCHAR(100) CONSTRAINT URL_MERCADO_NN NOT NULL,"+
                "ID_ENDERECO_MERCADO CHAR(36) CONSTRAINT ID_ENDERECO_MERCADO_NN NOT NULL,"+
            
                "CONSTRAINT ID_MERCADO_PK  PRIMARY KEY (ID_MERCADO, CNPJ_MERCADO, URL_MERCADO),"+
                "CONSTRAINT ID_ENDERECO_MERCADO_FK FOREIGN KEY (ID_ENDERECO_MERCADO) REFERENCES endereco)");
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
                " values (?,?,?,?,?,?)");
            }
            insert.setString(1, novo.getId());
            insert.setString(2, novo.getCnpj().getValor());
            insert.setString(3, novo.getNomeFantasia().getValor());
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
    public void close() throws Exception {
        System.out.println("RepositórioDeMercadoJDBC fechando!!! :)");
        if (insert != null && !insert.isClosed()) {
            insert.close();
        }
    }

}
