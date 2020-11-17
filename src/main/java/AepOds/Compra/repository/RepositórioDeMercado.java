package AepOds.Compra.repository;

import java.util.List;

import AepOds.Compra.ValueObject.DadosMercado;

public interface RepositórioDeMercado extends AutoCloseable {
    List<DadosMercado> obterTodos();
}
