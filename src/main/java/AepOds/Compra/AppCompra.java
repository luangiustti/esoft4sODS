package AepOds.Compra;

import AepOds.Cadastro.repository.GerenciadorDeConexão;
import AepOds.Compra.ValueObject.DadosMercado;
import AepOds.Compra.ValueObject.DadosOng;
import AepOds.Compra.ValueObject.DadosUsuario;
import AepOds.Compra.repository.RepositórioDeCarrinho;
import AepOds.Compra.repository.RepositórioDeCarrinhoJDBC;
import AepOds.Compra.repository.RepositórioDeDoacao;
import AepOds.Compra.repository.RepositórioDeDoacaoJDBC;
import AepOds.Compra.repository.RepositórioDeMercado;
import AepOds.Compra.repository.RepositórioDeMercadoJDBC;
import AepOds.Compra.repository.RepositórioDeOng;
import AepOds.Compra.repository.RepositórioDeOngJDBC;
import AepOds.Compra.repository.RepositórioDeProduto;
import AepOds.Compra.repository.RepositórioDeProdutoJDBC;
import AepOds.Compra.repository.RepositórioDeUsuairoJDBC;
import AepOds.Compra.repository.RepositórioDeUsuario;

public class AppCompra {
    public static void main(String[] args) {
        try (GerenciadorDeConexão gerenciadorDeConexão = new GerenciadorDeConexão();
            RepositórioDeProduto repoProduto = new RepositórioDeProdutoJDBC(gerenciadorDeConexão.getConexão());
            RepositórioDeCarrinho repoCarrinho = new RepositórioDeCarrinhoJDBC(gerenciadorDeConexão.getConexão());
            RepositórioDeDoacao repoDoacao = new RepositórioDeDoacaoJDBC(gerenciadorDeConexão.getConexão());
            RepositórioDeMercado repoMercado = new RepositórioDeMercadoJDBC(gerenciadorDeConexão.getConexão());
            RepositórioDeUsuario repoUsuario = new RepositórioDeUsuairoJDBC(gerenciadorDeConexão.getConexão());
            RepositórioDeOng repoOng = new RepositórioDeOngJDBC(gerenciadorDeConexão.getConexão());
            ){
                Produto arroz = new Produto("arroz", "5kg Prato fino", 26.99);
                Produto feijao = new Produto("feijao", "1kg são joão", 30.99);
                Produto oleo = new Produto("óleo", "1l cocamar", 35.99);
                Produto leite = new Produto("leite", "1l lider", 100.99);

                repoProduto.incluir(arroz);
                repoProduto.incluir(feijao);
                repoProduto.incluir(oleo);
                repoProduto.incluir(leite);

                DadosUsuario teste = null;
                for (DadosUsuario d : repoUsuario.obterTodos()) {
                    teste = new DadosUsuario(d.getId(), d.getCpf());
                }
                DadosMercado testeMercado = null;
                for (DadosMercado d : repoMercado.obterTodos()) {
                    testeMercado = new DadosMercado(d.getId(), d.getCnpj(), d.getUrl());
                }

                Carrinho carrinhoJoao = new Carrinho(testeMercado, teste);
                carrinhoJoao.setProdutos(arroz);
                carrinhoJoao.setProdutos(feijao);
                carrinhoJoao.setProdutos(oleo);
                carrinhoJoao.setProdutos(leite);

                repoCarrinho.incluir(carrinhoJoao);
                DadosOng testeOng = null;
                for (DadosOng o : repoOng.obterTodos()) {
                    testeOng = new DadosOng(o.getId(), o.getCnpj());
                }

                Doacao doacaoJoao = new Doacao(testeOng, carrinhoJoao);
                repoDoacao.incluir(doacaoJoao);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
