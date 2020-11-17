package AepOds.Compra.repository;

import AepOds.Compra.Produto;

public interface RepositórioDeProduto extends AutoCloseable {
 
    void incluir(Produto novo);
}
