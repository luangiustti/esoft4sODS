package AepOds.Compra.repository;

import AepOds.Compra.Doacao;

public interface RepositórioDeDoacao extends AutoCloseable {
    
    void incluir(Doacao novo);
}
