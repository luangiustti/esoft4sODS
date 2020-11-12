package AepOds.Login.Respository;

import java.util.List;

import AepOds.Login.Login;

public interface RepositórioDeLoginOng extends AutoCloseable {
    List<Login> obterTodas();
}
