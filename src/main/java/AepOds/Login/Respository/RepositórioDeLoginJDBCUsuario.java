package AepOds.Login.Respository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AepOds.Login.ValueObject.DadosDeLogin;

public class RepositórioDeLoginJDBCUsuario implements RepositórioDeLoginUsuario{
    private Connection conexão;

    public RepositórioDeLoginJDBCUsuario(Connection conexão){
        this.conexão = conexão;
    }

    @Override
    public List<DadosDeLogin> obterTodas() {
        List<DadosDeLogin> todas = new ArrayList<>();
        try (Statement select = conexão.createStatement();
            ResultSet resultado = select.executeQuery("select email_usuario, senha_usuario from usuario")){
            while (resultado.next()) {
                DadosDeLogin recuperado = new DadosDeLogin(
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
