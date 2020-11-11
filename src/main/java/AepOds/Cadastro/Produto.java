package AepOds.Cadastro;

public class Produto extends Papel {
    private String Nome;

    public Produto(String Nome){
        super();
        this.Nome = Nome;

    }
    public String getNome() {
        return Nome;
    }
    @Override
    public String toString() {
        return Nome;
    }
}
