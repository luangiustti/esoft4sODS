package AepOds.Login.Respository;

import java.util.List;

import AepOds.Login.ValueObject.DadosDeLogin;

public interface RepositórioDeLoginUsuario extends AutoCloseable {
    List<DadosDeLogin> obterTodas();
}
