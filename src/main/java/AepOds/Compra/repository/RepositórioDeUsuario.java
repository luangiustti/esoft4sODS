package AepOds.Compra.repository;

import java.util.List;

import AepOds.Compra.ValueObject.DadosUsuario;

public interface RepositórioDeUsuario extends AutoCloseable{
    List<DadosUsuario> obterTodos();
}
