package AepOds.Compra.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import AepOds.Compra.Produto;

public class RepositórioDeProdutoJDBC implements RepositórioDeProduto {
    private Connection conexão;
    private PreparedStatement insert;

    public RepositórioDeProdutoJDBC(Connection conexão) {
        this.conexão = conexão;
        criarTabela();
    }

    private void criarTabela() {
        try (Statement createTable = conexão.createStatement()){
            createTable.executeUpdate("create table if not exists produtos ("+
                "ID_PRODUTO char(36) CONSTRAINT ID_PRODUTO_NN NOT NULL,"+
                "NOME_PRODUTO VARCHAR(30),"+
                "DESC_PRODUTO VARCHAR(150),"+
                "VALOR_PRODUTO DOUBLE PRECISION,"+
            
                "CONSTRAINT ID_PRODUTO_PK PRIMARY KEY (ID_PRODUTO))");
                conexão.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void incluir(Produto novo) {
        try {
            if (insert == null) {
                insert = conexão.prepareStatement("insert into produtos (ID_PRODUTO, NOME_PRODUTO, DESC_PRODUTO, VALOR_PRODUTO) values (?,?,?,?)");
            }
            insert.setString(1, novo.getId());
            insert.setString(2, novo.getNome());
            insert.setString(3, novo.getDescricao());
            insert.setDouble(4, novo.getValor());
            insert.executeUpdate();
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
