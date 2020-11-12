package AepOds.Login;

public class Login {
    private String email;
    private String senha;

    public Login(String email, String senha){
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
