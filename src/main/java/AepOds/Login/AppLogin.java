package AepOds.Login;

import AepOds.Cadastro.repository.GerenciadorDeConexão;
import AepOds.Login.Respository.RepositórioDeLoginJDBCUsuario;
import AepOds.Login.Respository.RepositórioDeLoginUsuario;

public class AppLogin {
    public static void main(String[] args) {
        try (GerenciadorDeConexão gerenciadorDeConexão = new GerenciadorDeConexão();
            RepositórioDeLoginUsuario usuario = new RepositórioDeLoginJDBCUsuario(gerenciadorDeConexão.getConexão());
            ){
                for (Login l : usuario.obterTodas()) {
                    System.out.println(l.toString());
                }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}