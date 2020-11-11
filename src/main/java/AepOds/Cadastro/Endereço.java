package AepOds.Cadastro;

public class Endereço extends Papel {
    private String estado;
    private String cidade;
    private String bairro;
    private String logradouro;
    private String número;
    private String complemento;

    public Endereço(String estado, String cidade, String bairro, String logradouro, String número, String complemento) {
        super();
        if(validar(estado)){
            this.estado = estado;
        }else{
            throw new RuntimeException("O campo não pode ser vazio!");
        }
        if(validar(cidade)){
            this.cidade = cidade;
        }else{
            throw new RuntimeException("O campo não pode ser vazio!");
        }
        if(validar(bairro)){
            this.bairro = bairro;
        }else{
            throw new RuntimeException("O campo não pode ser vazio!");
        }
        if(validar(logradouro)){
            this.logradouro = logradouro;
        }else{
            throw new RuntimeException("O campo não pode ser vazio!");
        }
        if(validar(número)){
            this.número = número;
        }else{
            throw new RuntimeException("O campo não pode ser vazio!");
        }
        this.complemento = complemento;
    }
    public String getEstado() {
        return estado;
    }
    public String getCidade() {
        return cidade;
    }
    public String getBairro() {
        return bairro;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public String getNúmero() {
        return número;
    }
    public String getComplemento() {
        return complemento;
    }
    @Override
    public String toString() {
        return "Endereço [estado=" + estado + ", cidade=" + cidade + ", bairro=" + bairro + ", logradouro=" + logradouro + ", número=" + número + ", complemento=" + complemento + "] ";
    }
    private static boolean validar(String valor){
        if (valor == null){
            return false;
        }else{
            return true;
        }
    }
}
