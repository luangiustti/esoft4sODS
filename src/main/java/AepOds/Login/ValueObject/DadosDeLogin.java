package AepOds.Login.ValueObject;

public class DadosDeLogin {
    private String email;
    private String senha;

    public DadosDeLogin(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    @Override
    public String toString() {
        return "EMAIL: " + email + " - SENHA: " + senha;
    }
}
