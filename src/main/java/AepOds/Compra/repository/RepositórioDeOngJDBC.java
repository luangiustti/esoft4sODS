package AepOds.Compra.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AepOds.Compra.ValueObject.DadosOng;

public class RepositórioDeOngJDBC implements RepositórioDeOng {
    private Connection conexão;
        

    public RepositórioDeOngJDBC(Connection conexão){
        this.conexão = conexão;
    }

    @Override
    public List<DadosOng> obterTodos() {
        List<DadosOng> todos = new ArrayList<>();
        try (Statement select = conexão.createStatement();
            ResultSet resultado = select.executeQuery("select id_ong, cnpj_ong from ong")){
            while (resultado.next()) {
                DadosOng recuperado = new DadosOng(
                    resultado.getString("id_ong"),
                    resultado.getString("cnpj_ong"));
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
