package AepOds.Cadastro.repository;

import AepOds.Cadastro.Endereço;
import AepOds.Cadastro.Mercado;
import AepOds.Cadastro.Ong;
import AepOds.Cadastro.Produto;
import AepOds.Cadastro.Usuário;

public interface RepositórioDeCadastro extends AutoCloseable {
    
    //void incluirEndereco(Endereço novo);
   // void incluirUsuario(Usuário novo);
    void incluirMercado(Mercado novo);
    void incluirProduto(Produto novo);
    void incluirOng(Ong novo);
}