package AepOds.Cadastro.repository;

import AepOds.Cadastro.Ong;

public interface RepositórioDeCadastroOng extends AutoCloseable {
    void incluirOng(Ong novo);
}
