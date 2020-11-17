package AepOds.Compra.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import AepOds.Compra.Carrinho;
import AepOds.Compra.Produto;

public class RepositórioDeCarrinhoJDBC implements RepositórioDeCarrinho {
    private Connection conexão;
    private PreparedStatement insert;

    public RepositórioDeCarrinhoJDBC(Connection conexão) {
        this.conexão = conexão;
        criarTabela();
    }

    private void criarTabela() {
        try (Statement createTable = conexão.createStatement()){
            createTable.executeUpdate("create table if not exists carrinho ("+
                "ID_CARRINHO CHAR(36) CONSTRAINT ID_CARRINHO_NN NOT NULL,"+
                "ID_MERCADO_CARRINHO CHAR(36) CONSTRAINT ID_MERCADO_CARRINHO_NN NOT NULL,"+
                "CNPJ_MERCADO_CARRINHO CHAR(14) CONSTRAINT CNPJ_MERCADO_CARRINHO_NN NOT NULL,"+
                "URL_MERCADO_CARRINHO VARCHAR(100) CONSTRAINT URL_MERCADO_CARRINHO_NN NOT NULL,"+
                "ID_USUARIO_CARRINHO CHAR(36) CONSTRAINT ID_USUARIO_CARRINHO_NN NOT NULL,"+
                "CPF_USUARIO_CARRINHO CHAR(11) CONSTRAINT CPF_USUARIO_CARRINHO_NN NOT NULL,"+
            
                "CONSTRAINT ID_CARRINHO_PK PRIMARY KEY (ID_CARRINHO),"+
                "CONSTRAINT ID_MERCADO_CARRINHO_FK FOREIGN KEY (ID_MERCADO_CARRINHO, CNPJ_MERCADO_CARRINHO, URL_MERCADO_CARRINHO) REFERENCES mercado,"+
                "CONSTRAINT ID_USUARIO_CARRINHO_FK FOREIGN KEY (ID_USUARIO_CARRINHO, CPF_USUARIO_CARRINHO) REFERENCES usuario)");
                conexão.commit();
            
            createTable.executeUpdate("create table if not exists produto_carrinho ("+
                "ID_PRODUTO_CARRINHO CHAR(36) CONSTRAINT ID_PRODUTO_CARRINHO_NN NOT NULL,"+
                "ID_CARRINHO_PRODUTO CHAR(36) CONSTRAINT ID_CARRINHO_PRODUTO_NN NOT NULL,"+
            
                "CONSTRAINT ID_PRODUTO_CARRINHO_FK FOREIGN KEY (ID_PRODUTO_CARRINHO) REFERENCES produtos,"+
                "CONSTRAINT ID_CARRINHO_PRODUTO_FK FOREIGN KEY (ID_CARRINHO_PRODUTO) REFERENCES carrinho)");
            conexão.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void incluir(Carrinho novo) {
        try {
            if (insert == null) {
                insert = conexão.prepareStatement("insert into carrinho ("+
                "ID_CARRINHO, ID_MERCADO_CARRINHO, CNPJ_MERCADO_CARRINHO, URL_MERCADO_CARRINHO, ID_USUARIO_CARRINHO, CPF_USUARIO_CARRINHO)"+
                " values (?,?,?,?,?,?)");
            }
            insert.setString(1, novo.getId());
            insert.setString(2, novo.getMercado().getId());
            insert.setString(3, novo.getMercado().getCnpj());
            insert.setString(4, novo.getMercado().getUrl());
            insert.setString(5, novo.getUsuario().getId());
            insert.setString(6, novo.getUsuario().getCpf());
            insert.executeUpdate();
            conexão.commit();

            insert.clearParameters();
            insert = conexão.prepareStatement("insert into produto_carrinho("+
            "ID_PRODUTO_CARRINHO, ID_CARRINHO_PRODUTO)"+
            " values (?,?)");
            for (Produto p : novo.getProdutos()) {
                insert.setString(1, p.getId());
                insert.setString(2, novo.getId());
                insert.executeUpdate();   
            }
            conexão.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("RepositórioDeProdutosJDBC fechando!");
        if (insert != null && !insert.isClosed()) {
            insert.close();
        }
    }
}
