package AepOds.Compra.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AepOds.Compra.ValueObject.DadosMercado;

public class RepositórioDeMercadoJDBC implements RepositórioDeMercado{
    private Connection conexão;

    public RepositórioDeMercadoJDBC(Connection conexão){
        this.conexão = conexão;
    }

    @Override
    public List<DadosMercado> obterTodos() {
        List<DadosMercado> todos = new ArrayList<>();
        try (Statement select = conexão.createStatement();
            ResultSet resultado = select.executeQuery("select id_mercado, cnpj_mercado, url_mercado from mercado")){
            while (resultado.next()) {
                DadosMercado recuperado = new DadosMercado(
                    resultado.getString("id_mercado"),
                    resultado.getString("cnpj_mercado"),
                    resultado.getString("url_mercado"));
                todos.add(recuperado);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return todos;
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub

    }
}
