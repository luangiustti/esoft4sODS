package AepOds.Compra;

import AepOds.Cadastro.Papel;

public class Produto extends Papel {
    private String nome;
    private String descricao;
    private double valor;

    public Produto(String nome, String descricao, double valor) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "NOME DO PRODUTO: " + nome + ", Descrição: " + descricao + ", Valor: " + valor;
    }
}
