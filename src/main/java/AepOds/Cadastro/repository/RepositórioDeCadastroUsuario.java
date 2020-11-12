package AepOds.Cadastro.repository;

import AepOds.Cadastro.Usuário;

public interface RepositórioDeCadastroUsuario extends AutoCloseable {
    void incluirUsuario(Usuário novo);
}
