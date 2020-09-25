package AepOds.Cadastro.ValueObject;

public class NomeFantasia {
    private String valor;
    public NomeFantasia (String valor){
        if(validar(valor)){
            this.valor = valor;
        }
    }
    public String getValor() {
        return valor;
    }
    private static boolean validar(String valor) {
        if (null == valor || valor.trim().length() == 0 || valor.trim().split(" ").length < 2) {
            throw new RuntimeException("O nome deve conter duas palavras!");
        }else{
            return true;
        }
    }
}
