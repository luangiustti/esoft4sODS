package AepOds.Compra.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import AepOds.Compra.Doacao;

public class RepositórioDeDoacaoJDBC implements RepositórioDeDoacao {
    private Connection conexão;
    private PreparedStatement insert;

    public RepositórioDeDoacaoJDBC(Connection conexão) {
        this.conexão = conexão;
        criarTabela();
    }

    private void criarTabela() {
        try (Statement createTable = conexão.createStatement()){
            createTable.executeUpdate("create table if not exists doacao ("+
                "ID_DOACAO CHAR(36) CONSTRAINT ID_DOACAO_NN NOT NULL,"+
                "VALID_DOACAO CHAR(1),"+
                "ID_ONG_DOACAO CHAR(36) CONSTRAINT ID_ONG_DOACAO_NN NOT NULL,"+
                "CNPJ_ONG_DOACAO CHAR(14) CONSTRAINT CNPJ_ONG_DOACAO_NN NOT NULL,"+
                "ID_CARRINHO_DOACAO CHAR(36) CONSTRAINT ID_CARRINHO_DOACAO_NN NOT NULL,"+
                
                "CONSTRAINT ID_DOACAO_PK PRIMARY KEY (ID_DOACAO),"+
                "CONSTRAINT ID_ONG_DOACAO_FK FOREIGN KEY (ID_ONG_DOACAO, CNPJ_ONG_DOACAO) REFERENCES ong,"+
                "CONSTRAINT ID_CARRINHO_DOACAO_FK FOREIGN KEY (ID_CARRINHO_DOACAO) REFERENCES carrinho)");
                conexão.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void incluir(Doacao novo) {
        try {
            if (insert == null) {
                insert = conexão.prepareStatement("insert into doacao ("+
                "ID_DOACAO, VALID_DOACAO, ID_ONG_DOACAO, CNPJ_ONG_DOACAO, ID_CARRINHO_DOACAO) values (?,?,?,?,?)");
            }
            insert.setString(1, novo.getId());
            insert.setString(2, novo.getValidacao());
            insert.setString(3, novo.getOng().getId());
            insert.setString(4, novo.getOng().getCnpj());
            insert.setString(5, novo.getCarrinho().getId());
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
