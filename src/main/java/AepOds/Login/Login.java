package AepOds.Login;

import java.util.List;

import AepOds.Login.ValueObject.DadosDeLogin;

public class Login {
    private String email;
    private String senha;
    private String tipoDeLogin;

    public Login(String email, String senha, List<DadosDeLogin> usuario, List<DadosDeLogin> ong) {
        tipoDeLogin = validarLoginEmail(email, usuario, ong);
        if (tipoDeLogin == "I") {
            throw new RuntimeException("Email digitado invalido!");
        }
        this.email = email;
        if (tipoDeLogin == "VU") {
            if (!validarLoginSenha(senha, usuario)) {
                throw new RuntimeException("senha digitada invalida!");
            }
        }else if (tipoDeLogin == "VO"){
            if (!validarLoginSenha(senha, ong)) {
                throw new RuntimeException("senha digitada invalida!");
            }
        }
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }

    public String getTipoDeLogin() {
        return tipoDeLogin;
    }

    @Override
    public String toString() {
        if (tipoDeLogin == "VU") {
            return "Usuario doador";
        }
        if (tipoDeLogin == "VO") {
            return "Usuario ONG";
        }
        return null;
    }

    private static String validarLoginEmail (String email, List<DadosDeLogin> usuario, List<DadosDeLogin> ong) {
        for (DadosDeLogin u : usuario) {
            if (u.getEmail().equals(email)) {
                return "VU";
            }
        }
        for (DadosDeLogin o : ong) {
            if (o.getEmail().equals(email)) {
                return "VO";
            }
        }
        return "I";
    }
    private static boolean validarLoginSenha (String senha, List<DadosDeLogin> usuarioOng) { 
        for (DadosDeLogin u : usuarioOng) {
            if (u.getSenha().equals(senha)) {
                return true;
            }
        }
        
        return false;
    }
}
