package AepOds.Cadastro.repository;

import AepOds.Cadastro.Endereço;

public interface RepositórioDeCadastroEndereco extends AutoCloseable {
    
    void incluirEndereco(Endereço novo);
}