package AepOds.Cadastro;

import AepOds.Cadastro.ValueObject.Cnpj;
import AepOds.Cadastro.ValueObject.NomeFantasia;

public class Mercado {
    private NomeFantasia nomeFantasia;
    private Cnpj cnpj;
    private String url;
    private String telefone;
    private Endereço endereço;
    public Mercado(NomeFantasia nomeFantasia, Cnpj cnpj, String url, String telefone, Endereço endereço){
        this.telefone = telefone;
        this.nomeFantasia.getValor();
        this.cnpj.getValor();
        this.endereço.toString();
        this.url = url; 
    }
    public Cnpj getCnpj() {
        return cnpj;
    }
    public Endereço getEndereço() {
        return endereço;
    }
    public NomeFantasia getNomeFantasia() {
        return nomeFantasia;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getUrl() {
        return url;
    }
}
