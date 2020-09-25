package AepOds.Cadastro;

import AepOds.Cadastro.ValueObject.Cnpj;
import AepOds.Cadastro.ValueObject.NomeFantasia;
import AepOds.Cadastro.ValueObject.Senha;

public class Ong {
    private String email;
    private Senha senha;
    private Cnpj cnpj;
    private NomeFantasia nomeFantasia;
    private String descrição;
    private Endereço endereço;
    private String telefone;
    
    public Ong(String email, Senha senha, Cnpj cnpj, NomeFantasia nomeFantasia, String descrição, Endereço endereço, String telefone){
        this.telefone = telefone;
        this.email = email;
        this.senha.getValor();
        this.cnpj.getValor();
        this.nomeFantasia.getValor();
        this.descrição = descrição;
        this.endereço.toString();
    }
    public Cnpj getCnpj() {
        return cnpj;
    }
    public String getDescrição() {
        return descrição;
    }
    public String getEmail() {
        return email;
    }
    public Endereço getEndereço() {
        return endereço;
    }
    public NomeFantasia getNomeFantasia() {
        return nomeFantasia;
    }
    public Senha getSenha() {
        return senha;
    }
    public String getTelefone() {
        return telefone;
    }
}
