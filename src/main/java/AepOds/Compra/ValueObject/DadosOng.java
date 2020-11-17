package AepOds.Compra.ValueObject;

public class DadosOng {
    private String id;
    private String cnpj;

    public DadosOng(String id, String cnpj){
        this.id = id;
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getId() {
        return id;
    }
}
