package AepOds.Login.Respository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AepOds.Login.Login;

public class RepositórioDeLoginJDBCOng implements RepositórioDeLoginOng {
    private Connection conexão;

    public RepositórioDeLoginJDBCOng(Connection conexão){
        this.conexão = conexão;
    }

    @Override
    public List<Login> obterTodas() {
        List<Login> todas = new ArrayList<>();
        try (Statement select = conexão.createStatement();
            ResultSet resultado = select.executeQuery("select EMAIL_ONG, SENHA_ONG from ong")){
            while (resultado.next()) {
                Login recuperado = new Login(
                    resultado.getString("EMAIL"),
                    resultado.getString("SENHA"));
                todas.add(recuperado);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return todas;
    }

    @Override
    public void close() throws Exception {

    }
}
