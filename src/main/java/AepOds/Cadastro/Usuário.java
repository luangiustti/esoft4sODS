package AepOds.Cadastro;

import AepOds.Cadastro.ValueObject.Cpf;
import AepOds.Cadastro.ValueObject.Data;
import AepOds.Cadastro.ValueObject.Email;
import AepOds.Cadastro.ValueObject.Nome;
import AepOds.Cadastro.ValueObject.Senha;

public class Usuário {
    private Nome nome;
    private String email;
    private Senha senha;
    private Cpf cpf;
    private Data data;
    private Endereço endereço;

    public Usuário(Nome nome, Cpf cpf, Data data, Endereço endereço,String email, Senha senha){
        this.nome = nome;
        this.cpf = cpf;
        this.data = data;
        this.endereço = endereço;
        this.email = email;
        this.senha = senha;
    }
    public Cpf getCpf() {
        return cpf;
    }
    public Data getData() {
        return data;
    }
    public String getEmail() {
        return email;
    }
    public Endereço getEndereço() {
        return endereço;
    }
    public Nome getNome() {
        return nome;
    }
    public Senha getSenha() {
        return senha;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
