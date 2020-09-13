package JoaoCarvalho.aula20200903.valueObjects;

public class Cnpj {
    private String valor;
    public Cnpj(String valor){
        if(validar(valor)==false){
            throw new RuntimeException("CNPJ invalido");
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
        if(valor.length() != 14){
            return false;
        }else{
            if (valor.equals("00000000000000") || valor.equals("11111111111111") || valor.equals("22222222222222") || 
            valor.equals("33333333333333") || valor.equals("44444444444444") || valor.equals("55555555555555") ||
            valor.equals("66666666666666") || valor.equals("77777777777777") || valor.equals("88888888888888") || 
            valor.equals("99999999999999")){
                return false;
            }else{
                int codVerificador1 = (valor.charAt(12) - 48);
                int codVerificador2 = (valor.charAt(13) - 48);
                int calculoTotal = 0;
                int auxCalculo = 2;
                int digitoCnpj;
                for (int aux = 11; aux >= 0; aux--) {
                    digitoCnpj = (valor.charAt(aux) - 48);
                    calculoTotal = calculoTotal + (digitoCnpj*auxCalculo);
                    if (auxCalculo == 9){
                        auxCalculo = 2;
                    }else{
                        auxCalculo++;
                    }
                }
                calculoTotal = calculoTotal % 11;
                if (calculoTotal == 0 || calculoTotal == 1){
                    calculoTotal = 0;
                }else{
                    calculoTotal = 11 - calculoTotal;
                }
                if(calculoTotal != codVerificador1){
                    return false;
                }else{
                    calculoTotal = 0;
                    auxCalculo = 2;
                    for (int aux = 12; aux >= 0; aux--) {
                        digitoCnpj = (valor.charAt(aux) - 48);
                        calculoTotal = calculoTotal + (digitoCnpj*auxCalculo);
                        if (auxCalculo == 9){
                            auxCalculo = 2;
                        }else{
                            auxCalculo++;
                        }
                    }
                    calculoTotal = calculoTotal % 11;
                    if (calculoTotal == 0 || calculoTotal == 1){
                        calculoTotal = 0;
                    }else{
                        calculoTotal = 11 - calculoTotal;
                    }
                    if(calculoTotal != codVerificador2){
                        return false;
                    }else{
                        return true;
                    }
                }
            }
        }
    }
}