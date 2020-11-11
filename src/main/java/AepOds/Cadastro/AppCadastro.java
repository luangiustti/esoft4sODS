package AepOds.Cadastro;

import java.util.Date;

import AepOds.Cadastro.ValueObject.Cnpj;
import AepOds.Cadastro.ValueObject.Cpf;
import AepOds.Cadastro.ValueObject.Data;
import AepOds.Cadastro.ValueObject.Email;
import AepOds.Cadastro.ValueObject.Nome;
import AepOds.Cadastro.ValueObject.NomeFantasia;
import AepOds.Cadastro.ValueObject.Senha;
import AepOds.Cadastro.repository.GerenciadorDeConexão;
import AepOds.Cadastro.repository.RepositórioDeCadastroJDBC;
import AepOds.Cadastro.repository.RepositórioDeCadastro;

public class AppCadastro {
    public static void main(String[] args) {
        try (GerenciadorDeConexão gerenciadorConexão = new GerenciadorDeConexão();
            RepositórioDeCadastro cadastro = new RepositórioDeCadastroJDBC(gerenciadorConexão.getConexão());
            ){
            
            Endereço unicesumarEndereço = new Endereço("pr", "Maringá", "Jardim Aclimação", "av. guedner", "1610", "faculdade");
            Endereço supermercadoCoEndereço = new Endereço("pr", "Maingá", "zona 7", "av. Paraná", "1600", "mercado");
            Endereço ongMundoMelhEndereço = new Endereço("pr", "Maringá", "jardim imperial 2", "chihiro nakatani", "610", "ong");
            
            cadastro.incluirEndereco(unicesumarEndereço);
            cadastro.incluirEndereco(supermercadoCoEndereço);
            cadastro.incluirEndereco(ongMundoMelhEndereço);

            Produto arroz = new Produto("Arroz");
            Produto feijao = new Produto("Feijão");
            Produto oleo = new Produto("Oléo");

            cadastro.incluirProduto(arroz);
            cadastro.incluirProduto(feijao);
            cadastro.incluirProduto(oleo);

            Mercado condorMercado = new Mercado(new NomeFantasia("Supermercados condor"), new Cnpj("47409319000139"), "https://www.condor.com.br/", "0800416655", supermercadoCoEndereço);
            
            cadastro.incluirMercado(condorMercado);
            
            Ong mundoMelhorOng = new Ong("mundomelhor@mundo.com", new Senha("ongong@1234"), new Cnpj("72470628000161"), new NomeFantasia("Mundo melhor"), " É UMA ORGANIZAÇÃO NÃO GOVERNAMENTAL DESTINADA A AJUDAR PESSOAS M SITUAÇÃO DE FOME", ongMundoMelhEndereço, "44999986950");
            mundoMelhorOng.addprodutos(arroz);
            mundoMelhorOng.addprodutos(feijao);
            mundoMelhorOng.addprodutos(oleo);
            
            cadastro.incluirOng(mundoMelhorOng);
            
            Usuário uncesumarUsuário = new Usuário(new Nome("Wilson mattos"), new Cpf("54694022075"), new Data("19/05/1998"), "44998989898",  unicesumarEndereço, new Email("wilson@unicesumar"), new Senha("12345unicesumar"));        
                
            cadastro.incluirUsuario(uncesumarUsuário);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        /*Endereço unicesumarEndereço = new Endereço("pr", "Maringá", "Jardim Aclimação", "av. guedner", "1610", "faculdade");
        Endereço supermercadoCoEndereço = new Endereço("pr", "Maingá", "zona 7", "av. Paraná", "1600", "mercado");
        Endereço ongMundoMelhEndereço = new Endereço("pr", "Maringá", "jardim imperial 2", "chihiro nakatani", "610", "ong");
        Produto arroz = new Produto("Arroz", "5kg prato fino");
        Produto feijao = new Produto("Feijão", "1kg feijão preto");
        Produto oleo = new Produto("Oléo", "girasol 1 litro");
        Mercado condorMercado = new Mercado(new NomeFantasia("Supermercados condor"), new Cnpj("47409319000139"), "https://www.condor.com.br/", "0800416655", supermercadoCoEndereço);
        Ong mundoMelhorOng = new Ong("mundomelhor@mundo.com", new Senha("ongong@1234"), new Cnpj("72470628000161"), new NomeFantasia("Mundo melhor"), " É UMA ORGANIZAÇÃO NÃO GOVERNAMENTAL DESTINADA A AJUDAR PESSOAS M SITUAÇÃO DE FOME", ongMundoMelhEndereço, "44999986950");
        mundoMelhorOng.addprodutos(arroz);
        mundoMelhorOng.addprodutos(feijao);
        mundoMelhorOng.addprodutos(oleo);
        Usuário uncesumarUsuário = new Usuário(new Nome("Wilson mattos"), new Cpf("54694022075"), new Data("05/10/1950"), unicesumarEndereço, new Email("wilson@unicesumar"), new Senha("12345unicesumar"));
        System.out.println(uncesumarUsuário.getNome() + " " + uncesumarUsuário.getEndereço());*/
    }
}
