package AepOds.Cadastro.ValueObject;

public class Email {
    private String valor;
    public Email (String valor){
        if(validar(valor)){
            this.valor = valor;
        }
    }
    public String getValor() {
        return valor;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return valor;
    }
    private static boolean validar(String valor) {
        if (null == valor || valor.trim().length() == 0 || valor.trim().split("@").length < 2) {
            throw new RuntimeException("O email invalido!");
        }else{
            return true;
        }
    }
}
