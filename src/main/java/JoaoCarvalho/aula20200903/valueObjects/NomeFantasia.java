package JoaoCarvalho.aula20200903.valueObjects;

public class NomeFantasia {
    private String valor;

    public NomeFantasia(String valor) {
        if (null == valor || valor.trim().length() == 0) {
            throw new RuntimeException("O nome fantasia não pode ser nulo!");
        }
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    @Override
    public String toString() {
        return valor;
    }
}
