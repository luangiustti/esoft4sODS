package AepOds.Cadastro.repository;

import AepOds.Cadastro.Mercado;

public interface RepositórioDeCadastroMercado extends AutoCloseable {
    void incluirMercado(Mercado novo);
}
