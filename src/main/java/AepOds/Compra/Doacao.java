package AepOds.Compra;

import AepOds.Cadastro.Papel;
import AepOds.Compra.ValueObject.DadosOng;

public class Doacao extends Papel{
    private String validacao;
    private DadosOng ong;
    private Carrinho carrinho;

    public Doacao(DadosOng ong, Carrinho carrinho) {
        super();
        this.ong = ong;
        this.carrinho = carrinho;
    }

    public void setValidacao(String validacao) {
        this.validacao = validacao;
    }

    public String getValidacao() {
        return validacao;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public DadosOng getOng() {
        return ong;
    }

}
