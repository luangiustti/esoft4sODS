package AepOds.Compra.repository;

import AepOds.Compra.Carrinho;

public interface RepositórioDeCarrinho extends AutoCloseable {
    
    void incluir(Carrinho novo);
}
