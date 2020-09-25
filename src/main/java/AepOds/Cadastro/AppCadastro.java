package AepOds.Cadastro;

import AepOds.Cadastro.ValueObject.Cnpj;
import AepOds.Cadastro.ValueObject.Cpf;
import AepOds.Cadastro.ValueObject.Data;
import AepOds.Cadastro.ValueObject.Nome;
import AepOds.Cadastro.ValueObject.NomeFantasia;
import AepOds.Cadastro.ValueObject.Senha;

public class AppCadastro {
    public static void main(String[] args) {
        Endereço unicesumarEndereço = new Endereço("paraná", "Maringá", "Jardim Aclimação", "av. guedner", "1610", "faculdade");
        Endereço supermercadoCoEndereço = new Endereço("paraná", "Maingá", "zona 7", "av. Paraná", "1600", "mercado");
        Endereço ongMundoMelhEndereço = new Endereço("paraná", "Maringá", "jardim imperial 2", "chihiro nakatani", "610", "ong");
        Produto arroz = new Produto("Arroz", "5kg prato fino");
        Produto feijao = new Produto("Feijão", "1kg feijão preto");
        Produto oleo = new Produto("Oléo", "girasol 1 litro");
        Mercado condorMercado = new Mercado(new NomeFantasia("Supermercados condor"), new Cnpj("47409319000139"), "https://www.condor.com.br/", "0800416655", supermercadoCoEndereço);
        Ong mundoMelhorOng = new Ong("mundomelhor@mundo.com", new Senha("ongong@1234"), new Cnpj("72470628000161"), new NomeFantasia("Mundo melhor"), " É UMA ORGANIZAÇÃO NÃO GOVERNAMENTAL DESTINADA A AJUDAR PESSOAS M SITUAÇÃO DE FOME", ongMundoMelhEndereço, "44999986950");
        mundoMelhorOng.addprodutos(arroz);
        mundoMelhorOng.addprodutos(feijao);
        mundoMelhorOng.addprodutos(oleo);
        Usuário uncesumarUsuário = new Usuário(new Nome("Wilson mattos"), new Cpf("54694022075"), new Data("05/10/1950"), unicesumarEndereço, "wilson@unicesumar", new Senha("12345unicesumar"));
        System.out.println(uncesumarUsuário.getNome() + " " + uncesumarUsuário.getEndereço());
    }
}
