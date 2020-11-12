package AepOds.Login.Respository;

import java.util.List;

import AepOds.Login.Login;

public interface RepositórioDeLoginUsuario extends AutoCloseable {
    List<Login> obterTodas();
}
