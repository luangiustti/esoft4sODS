package JoaoCarvalho.aula20200903;

import javax.swing.JOptionPane;

import JoaoCarvalho.aula20200903.valueObjects.*;

public class AppValueObjects {
    public static void main(String[] args) {
        Nome nomeDigitado = new Nome(JOptionPane.showInputDialog(null, "Digite o nome: "));
        Cpf cpfDigitado = new Cpf(JOptionPane.showInputDialog(null, "Digite o CPF: "));
        Cnpj cnpjDigitado = new Cnpj(JOptionPane.showInputDialog(null, "Digite o CNPJ: "));

        System.out.println(nomeDigitado);
    }
    
}
