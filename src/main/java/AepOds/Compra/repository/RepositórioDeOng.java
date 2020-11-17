package AepOds.Compra.repository;

import java.util.List;

import AepOds.Compra.ValueObject.DadosOng;

public interface RepositórioDeOng extends AutoCloseable{
    List<DadosOng> obterTodos();
}
