package AepOds.Login.Respository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AepOds.Login.DadosDeLogin;

public class RepositórioDeLoginJDBCOng implements RepositórioDeLoginOng {
    private Connection conexão;

    public RepositórioDeLoginJDBCOng(Connection conexão){
        this.conexão = conexão;
    }

    @Override
    public List<DadosDeLogin> obterTodas() {
        List<DadosDeLogin> todas = new ArrayList<>();
        try (Statement select = conexão.createStatement();
            ResultSet resultado = select.executeQuery("select email_ong, senha_ong from ong")){
            while (resultado.next()) {
                DadosDeLogin recuperado = new DadosDeLogin(
                    resultado.getString("email_ong"),
                    resultado.getString("senha_ong"));
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
