package JoaoCarvalho.aula20200903.valueObjects;


public class Cpf {
    private String valor;
    public Cpf (String valor){
        if(validar(valor) == false){
            throw new RuntimeException("CPF invalido");
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
    
    public static boolean validar(String valor){
        if (valor.length() != 11){
            return false;
        }else{
            if(valor.equals("00000000000")||valor.equals("11111111111")||valor.equals("22222222222")||valor.equals("33333333333")||
            valor.equals("44444444444")||valor.equals("55555555555")||valor.equals("66666666666")||valor.equals("77777777777")||
            valor.equals("88888888888")||valor.equals("99999999999")){
                return false;
            }else{
                    int codVerificador1, codVerificador2;
                    int validacao = 10;
                    int total = 0;
                    for (int aux = 0; aux < 9; aux++) {
                        int aux1 = (valor.charAt(aux) - 48);
                        total = total + (validacao * aux1);
                        validacao = validacao - 1;
                    }
                    int confirmacao = 11 - (total % 11);
                    codVerificador1 = (valor.charAt(9) - 48);
                    codVerificador2 = (valor.charAt(10) - 48);
                    if ((confirmacao == 10)||(confirmacao == 11)){
                        confirmacao = 0;
                    }
                    if(confirmacao != codVerificador1){
                        return false;
                    }else{
                        validacao = 11;
                        total = 0;
                        for (int aux = 0; aux < 10; aux++) {
                            int aux1 = (valor.charAt(aux) - 48);
                            total = total + (validacao * aux1);
                        }
                        confirmacao = 11 - (total % 11);
                        if ((confirmacao == 10)||(confirmacao == 11)){
                            confirmacao = 0;
                        }
                        if (confirmacao != codVerificador2){
                            return false;
                        }else{
                            return true;
                        }
                    }
            }
        }
    }
}
