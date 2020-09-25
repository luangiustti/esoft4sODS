package AepOds.Cadastro;

public class Produto {
    private String Nome;
    private String Descrição;
    public Produto(String Nome, String Descrição){
        this.Nome = Nome;
        this.Descrição = Descrição;
    }
    public String getDescrição() {
        return Descrição;
    }
    public String getNome() {
        return Nome;
    }
    @Override
    public String toString() {
        return Nome + Descrição;
    }
}
