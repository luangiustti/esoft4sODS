package AepOds.Login.Respository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AepOds.Login.Login;

public class RepositórioDeLoginJDBCUsuario implements RepositórioDeLoginUsuario{
    private Connection conexão;

    public RepositórioDeLoginJDBCUsuario(Connection conexão){
        this.conexão = conexão;
    }

    @Override
    public List<Login> obterTodas() {
        List<Login> todas = new ArrayList<>();
        try (Statement select = conexão.createStatement();
            ResultSet resultado = select.executeQuery("select email_usuario, senha_usuario from usuario")){
            while (resultado.next()) {
                Login recuperado = new Login(
                    resultado.getString("email_usuario"),
                    resultado.getString("senha_usuario"));
                todas.add(recuperado);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return todas;
    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub

    }
}
