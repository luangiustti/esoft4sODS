package AepOds.Compra.ValueObject;

public class DadosUsuario {
    private String id;
    private String cpf;

    public DadosUsuario(String id, String cpf){
        this.cpf = cpf;
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }
    
    public String getId() {
        return id;
    }
    
}

