package AepOds.Cadastro;

import AepOds.Cadastro.ValueObject.Cnpj;
import AepOds.Cadastro.ValueObject.Cpf;
import AepOds.Cadastro.ValueObject.Data;
import AepOds.Cadastro.ValueObject.Email;
import AepOds.Cadastro.ValueObject.Nome;
import AepOds.Cadastro.ValueObject.NomeFantasia;
import AepOds.Cadastro.ValueObject.Senha;
import AepOds.Cadastro.repository.GerenciadorDeConexão;
import AepOds.Cadastro.repository.RepositórioDeCadastroJDBCEndereco;
import AepOds.Cadastro.repository.RepositórioDeCadastroJDBCMercado;
import AepOds.Cadastro.repository.RepositórioDeCadastroJDBCOng;
import AepOds.Cadastro.repository.RepositórioDeCadastroJDBCProduto;
import AepOds.Cadastro.repository.RepositórioDeCadastroJDBCUsuario;
import AepOds.Cadastro.repository.RepositórioDeCadastroMercado;
import AepOds.Cadastro.repository.RepositórioDeCadastroOng;
import AepOds.Cadastro.repository.RepositórioDeCadastroProduto;
import AepOds.Cadastro.repository.RepositórioDeCadastroUsuario;
import AepOds.Cadastro.repository.RepositórioDeCadastroEndereco;

public class AppCadastro {
    public static void main(String[] args) {
        try (GerenciadorDeConexão gerenciadorConexão = new GerenciadorDeConexão();
            RepositórioDeCadastroEndereco cadastroEndereco = new RepositórioDeCadastroJDBCEndereco(gerenciadorConexão.getConexão());
            RepositórioDeCadastroMercado cadastroMercado = new RepositórioDeCadastroJDBCMercado(gerenciadorConexão.getConexão());
            RepositórioDeCadastroProduto cadastroProduto = new RepositórioDeCadastroJDBCProduto(gerenciadorConexão.getConexão());
            RepositórioDeCadastroUsuario cadastroUsuario = new RepositórioDeCadastroJDBCUsuario(gerenciadorConexão.getConexão());
            RepositórioDeCadastroOng cadastroOng = new RepositórioDeCadastroJDBCOng(gerenciadorConexão.getConexão());
            ){
            
            Endereço unicesumarEndereço = new Endereço("pr", "Maringá", "Jardim Aclimação", "av. guedner", "1610", "faculdade");
            Endereço supermercadoCoEndereço = new Endereço("pr", "Maingá", "zona 7", "av. Paraná", "1600", "mercado");
            Endereço ongMundoMelhEndereço = new Endereço("pr", "Maringá", "jardim imperial 2", "chihiro nakatani", "610", "ong");
            
            cadastroEndereco.incluirEndereco(unicesumarEndereço);
            cadastroEndereco.incluirEndereco(supermercadoCoEndereço);
            cadastroEndereco.incluirEndereco(ongMundoMelhEndereço);

            Produto arroz = new Produto("Arroz");
            Produto feijao = new Produto("Feijão");
            Produto oleo = new Produto("Oléo");

            cadastroProduto.incluirProduto(arroz);
            cadastroProduto.incluirProduto(feijao);
            cadastroProduto.incluirProduto(oleo);

            Mercado condorMercado = new Mercado(new NomeFantasia("Supermercados condor"), new Cnpj("47409319000139"), "https://www.condor.com.br/", "08004166551", supermercadoCoEndereço);
            
            //cadastroMercado.incluirMercado(condorMercado);

            Ong mundoMelhorOng = new Ong("mundomelhor@mundo.com", new Senha("ongong@1234"), new Cnpj("72470628000161"), new NomeFantasia("Mundo melhor"), " É UMA ORGANIZAÇÃO NÃO GOVERNAMENTAL DESTINADA A AJUDAR PESSOAS M SITUAÇÃO DE FOME", ongMundoMelhEndereço, "44999986950");
            mundoMelhorOng.addprodutos(arroz);
            mundoMelhorOng.addprodutos(feijao);
            mundoMelhorOng.addprodutos(oleo);
            
            //cadastroOng.incluirOng(mundoMelhorOng);
            
            Usuário uncesumarUsuário = new Usuário(new Nome("Wilson mattos"), new Cpf("17012213060"), new Data("19/05/1998"), "44998989898",  unicesumarEndereço, new Email("wilson@unicesumar"), new Senha("12345unicesumar"));        
            Usuário joaoPaulo = new Usuário(new Nome("joao paulo"), new Cpf("04529447910"), new Data("27/07/2001"), "44995959595", unicesumarEndereço, new Email("joaopaulo@gmail.com"), new Senha("123456789"));
            
            cadastroUsuario.incluirUsuario(uncesumarUsuário);
            //cadastroUsuario.incluirUsuario(joaoPaulo);

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
