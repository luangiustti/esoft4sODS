package AepOds.Compra.ValueObject;

public class DadosMercado {
    private String id;
    private String cnpj;
    private String url;

    public DadosMercado(String id, String cnpj, String url) {
        this.cnpj = cnpj;
        this.id = id;
        this.url = url;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
