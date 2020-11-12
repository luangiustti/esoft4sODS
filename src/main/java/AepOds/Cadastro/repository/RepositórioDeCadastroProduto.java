package AepOds.Cadastro.repository;

import AepOds.Cadastro.Produto;

public interface RepositórioDeCadastroProduto extends AutoCloseable {
    void incluirProduto(Produto novo);
}
