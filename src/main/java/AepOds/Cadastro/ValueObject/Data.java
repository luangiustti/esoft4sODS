package AepOds.Cadastro.ValueObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
    private Date valor;
    public Data (String valor){
        this.valor = criarData(valor);
    }
    
    public Date getValor() {
        return valor;
    }

    private Date criarData(String data) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String toString() {
        return valor.toString();
    }
}
