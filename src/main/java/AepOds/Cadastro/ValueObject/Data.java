package AepOds.Cadastro.ValueObject;

public class Data {
    private String valor;
    public Data (String valor){
        this.valor = valor;
    }
    public String getValor() {
        return valor;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return valor;
    }
}
