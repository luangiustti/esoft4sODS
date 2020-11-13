package AepOds.Login;

import AepOds.Cadastro.repository.GerenciadorDeConexão;
import AepOds.Login.Respository.RepositórioDeLoginJDBCOng;
import AepOds.Login.Respository.RepositórioDeLoginJDBCUsuario;
import AepOds.Login.Respository.RepositórioDeLoginOng;
import AepOds.Login.Respository.RepositórioDeLoginUsuario;

public class AppLogin {
    public static void main(String[] args) {
        try (GerenciadorDeConexão gerenciadorDeConexão = new GerenciadorDeConexão();
            RepositórioDeLoginUsuario repoUsuario = new RepositórioDeLoginJDBCUsuario(gerenciadorDeConexão.getConexão());
            RepositórioDeLoginOng repoOng = new RepositórioDeLoginJDBCOng(gerenciadorDeConexão.getConexão());
            ){
                Login teste = new Login("joaopaulo@gmail.com", "123456789", repoUsuario.obterTodas(), repoOng.obterTodas());
                System.out.println(teste.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}