package AepOds.Compra;

import java.util.ArrayList;
import java.util.List;

import AepOds.Cadastro.Papel;
import AepOds.Compra.ValueObject.DadosMercado;
import AepOds.Compra.ValueObject.DadosUsuario;

public class Carrinho extends Papel {
    private DadosMercado mercado;
    private DadosUsuario usuario;
    private List<Produto> produtos = new ArrayList<>();

    public Carrinho(DadosMercado mercado, DadosUsuario usuario) {
        super();
        this.mercado = mercado;
        this.usuario = usuario;
    }

    public void setMercado(DadosMercado mercado) {
        this.mercado = mercado;
    }

    public void setProdutos(Produto produto) {
        this.produtos.add(produto);
    }

    public DadosMercado getMercado() {
        return mercado;
    }
    
    public DadosUsuario getUsuario() {
        return usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
