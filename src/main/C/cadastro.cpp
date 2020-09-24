#include <stdio.h>
#include <conio.h>
#include <stdlib.h>
#include <string.h>

typedef struct tipoElementoEndereco
{
	char estado[30];
	char cidade[30];
	char bairro[30];
	char lougradoro[50];
	char numero[10];
	char complemento[80];
}TElementoEndereco;

typedef struct tipoElementoUsuario
{
	int IdUsuario = 0;
	char nomeUsuario[30];
	int diaNascimento;
	int mesNascimento;
	int anoNascimento;
	char CpfUsuario[15];
	TElementoEndereco enderecoUsuario;
	char emailUsuario[50];
	char senhaUsuario[15];
	char confirmarSenhaUsuario[15];
}TElementoUsuario;

typedef struct tipoElementoOng
{
	int IdOng = 0;
	char nomeFantasiaOng[30];
	char cnpjOng[20];
	char descricaoOng[100];
	char produtosOng[10][50];
	TElementoEndereco enderecoOng;
	int telefoneOng;
	char emailOng[50];
	char senhaOng[15];
	char confirmarSenhaOng[15];
}TElementoOng;

typedef struct tipoElementoMercado
{
	int IdMercado = 0;
	char nomeFantasiaMercado[30];
	char cnpjMercado[20];
	TElementoEndereco enderecoMercado;
	int telefone;
	char urlMercado[100];
}TElementoMercado;

typedef struct tipoNoUsuario
{
	TElementoUsuario Usuario;
	struct tipoNoUsuario *esq;
	struct tipoNoUsuario *dir;
	int fatorBalanceamento;
}TNoUsuario;

typedef struct tipoNoOng
{
	TElementoOng Ong;
	struct tipoNoOng *esq;
	struct tipoNoOng *dir;
	int fatorBalanceamento;
}TNoOng;

typedef struct tipoNoMercado
{
	TElementoMercado mercado;
	struct tipoNoMercado *esq;
	struct tipoNoMercado *dir;
	int fatorBalanceamento;
}TNoMercado;

bool validarIdade(int idade)
{
	if (idade <2002)
	{
		return true;
	}
	else
	{
		printf("\n Idade precisa ser maior de 18 anos!");
		return false;
	}
}

bool validarMes(int idade)
{
	if(idade <= 12 && idade > 0)
	{
		return true;
	}
	else
	{
		printf("\n Mes inexistente!");
		return false;
	}
}

bool validarDia(int idade)
{
	if(idade <= 31 && idade > 0)
	{
		return true;
	}
	else
	{
		printf("\n Dia inexistente!");
		return false;
	}
}

bool validarSenha(char* senha, char* conSenha)
{
	if(strcmp(senha, conSenha)==0)
	{
		return true;
	}
	else
	{
		printf("\n Senha incorreta!");
		return false;
	}
}

bool validarCpf(char* cpf)
{  
	int icpf[12];  
	int i,somador=0,digito1,result1,result2,digito2,valor;    
  
	for(i=0;i<11;i++)  
	{  
		icpf[i]=cpf[i]-48;  
	}  
	for(i=0;i<9;i++)  
	{  
		somador+=icpf[i]*(10-i);  
	}  
	result1=somador%11;  
	if( (result1==0) || (result1==1) )  
	{  
		digito1=0;  
	}  
	else  
	{  
		digito1 = 11-result1;  
	}  
	somador=0;  
	for(i=0;i<10;i++)  
	{  
		somador+=icpf[i]*(11-i);  
	}  
	valor=(somador/11)*11;  
	result2=somador-valor;  
	if( (result2==0) || (result2==1) )  
	{  
		digito2=0;  
	}  
	else  
	{  
		digito2=11-result2;  
	}  
	if((digito1==icpf[9]) && (digito2==icpf[10]))  
	{  
		return true;  
	}  
	else  
	{  
		printf("\n CPF invalido!");
		return false;  
	} 
}  

TElementoEndereco lerEndereco ()
{
	TElementoEndereco novo;
	printf("\n-----------------------------------------");
	printf("\n Dados de Endereco");
	printf("\n-----------------------------------------");
	printf("\n Digite seu estado: ");
	fflush(stdin);
	gets(novo.estado);
 	printf("\n Digite sua cidade: ");
	fflush(stdin);
	gets(novo.cidade);
 	printf("\n Digite seu bairro: ");
	fflush(stdin);
	gets(novo.bairro);
 	printf("\n Digite o lougradouro: ");
	fflush(stdin);
	gets(novo.lougradoro);
 	printf("\n Digite o numero: ");
	fflush(stdin);
	gets(novo.numero);
 	printf("\n Complemento: ");
	fflush(stdin);
	gets(novo.complemento);
 	printf("\n -----------------------------------------");
	return novo;
}

void lerValorUsuario(TElementoUsuario *novoUsuario, int contador)
{
	printf("\n Digite seu Nome: ");
	fflush(stdin);
	gets(novoUsuario->nomeUsuario);
	do
	{
		printf("\n Digite o dia do seu nascimento: ");
		fflush(stdin);
		scanf("%d", &novoUsuario->diaNascimento);
	}while(!validarDia(novoUsuario->diaNascimento));
	do
	{
		printf("\n Digite o mes do seu nascimento: ");
		fflush(stdin);
		scanf("%d", &novoUsuario->mesNascimento);
	}while(!validarMes(novoUsuario->mesNascimento));
	do
	{
		printf("\n Digite o ano do seu nascimento: ");
		fflush(stdin);
		scanf("%d", &novoUsuario->anoNascimento);
	}while(!validarIdade(novoUsuario->anoNascimento));
	do
	{
		printf("\n Digite o seu CPF: ");
		fflush(stdin);
		scanf("%s", novoUsuario->CpfUsuario);
	}while(!validarCpf(novoUsuario->CpfUsuario));
	novoUsuario->enderecoUsuario = lerEndereco();
	printf("\n Digite seu E-mail: ");
	scanf("%s", novoUsuario->emailUsuario);
	printf("\n Digite uma senha: ");
	fflush(stdin);
	gets(novoUsuario->senhaUsuario);
	do
	{
		printf("\n Confirme a senha digitada: ");
		fflush(stdin);
		gets(novoUsuario->confirmarSenhaUsuario);
	}while(!validarSenha(novoUsuario->senhaUsuario, novoUsuario->confirmarSenhaUsuario));
	novoUsuario->IdUsuario = contador;
}

void lerValorOng(TElementoOng *novaOng, int contador)
{
	printf("\n Digite o nome fantasia: ");
	fflush(stdin);
	gets(novaOng->nomeFantasiaOng);
	printf("\n Digite o CNPJ: ");
	fflush(stdin);
	scanf("%s", novaOng->cnpjOng);
	novaOng->enderecoOng = lerEndereco();
	printf("\n Digite o telefone: ");
	fflush(stdin);
	scanf("%d", &novaOng->telefoneOng);
	printf("\n Digite o E-mail: ");
	fflush(stdin);
	scanf("%s", novaOng->emailOng);
	printf("\n Digite uma senha: ");
	fflush(stdin);
	gets(novaOng->senhaOng);
	do
	{
		printf("\n Confirme a senha digitada: ");
		fflush(stdin);
		gets(novaOng->confirmarSenhaOng);
	}while(!validarSenha(novaOng->senhaOng, novaOng->confirmarSenhaOng));
	printf("\n Digite uma descricao sobre a ONG: ");
	fflush(stdin);
	gets(novaOng->descricaoOng);
	printf("\n Produtos que deseja ser doados");
	for(int aux=0; aux<=9; aux++)
	{
		printf("\n digite o %d produto: ", aux+1);
		fflush(stdin);
		gets(novaOng->produtosOng[aux]);
	}
	novaOng->IdOng = contador;
}

void lerValorMercado(TElementoMercado *novoMercado, int contador)
{
	printf("\n Digite o nome fantasia: ");
	fflush(stdin);
	gets(novoMercado->nomeFantasiaMercado);
	printf("\n Digite o CNPJ: ");
	fflush(stdin);
	scanf("%s", novoMercado->cnpjMercado);
	novoMercado->enderecoMercado = lerEndereco();
	printf("\n Digite o telefone: ");
	fflush(stdin);
	scanf("%d", &novoMercado->telefone);
	printf("\n Digite a URL do site do %s: ", novoMercado->nomeFantasiaMercado);
	fflush(stdin);
	scanf("%s", novoMercado->urlMercado);
	novoMercado->IdMercado = contador;
}

TNoUsuario* inserirUsuario (TNoUsuario *raizUsuario, TElementoUsuario *novoUsuario)
{
	int inseriu = 0;
	TNoUsuario *novoNo;
	novoNo = new TNoUsuario;
	novoNo->esq = NULL;
	novoNo->dir = NULL;
	novoNo->fatorBalanceamento = 0;
	novoNo->Usuario.IdUsuario = novoUsuario->IdUsuario;
	strcpy(novoNo->Usuario.nomeUsuario, novoUsuario->nomeUsuario);
	strcpy(novoNo->Usuario.CpfUsuario, novoUsuario->CpfUsuario);
	novoNo->Usuario.diaNascimento = novoUsuario->diaNascimento;
	novoNo->Usuario.mesNascimento = novoUsuario->mesNascimento;
	novoNo->Usuario.anoNascimento = novoUsuario->anoNascimento;
	novoNo->Usuario.enderecoUsuario = novoUsuario->enderecoUsuario;
	strcpy(novoNo->Usuario.emailUsuario, novoUsuario->emailUsuario);
	strcpy(novoNo->Usuario.senhaUsuario, novoUsuario->senhaUsuario);
	strcpy(novoNo->Usuario.confirmarSenhaUsuario, novoUsuario->confirmarSenhaUsuario);
	if (raizUsuario == NULL)
	{
		raizUsuario = novoNo;
	}
	else
	{
		TNoUsuario *avo, *pai;
		avo = NULL;
		pai = raizUsuario;
		while (inseriu == 0)
		{
			if (novoNo->Usuario.IdUsuario < pai->Usuario.IdUsuario)
			{
				if (pai->esq == NULL)
				{
					pai->esq = novoNo;
					pai->fatorBalanceamento--;
					inseriu = 1;	
				}
				else
				{
					avo = pai;
					pai = pai->esq;
				}
			}
			else
			{
				if (pai->dir == NULL)
				{
					pai->dir = novoNo;
					pai->fatorBalanceamento++;
					inseriu = 1;
				}
				else
				{
					avo = pai;
					pai = pai->dir;
				}
			}
		}
		if (avo != NULL)
		{
			if (pai->fatorBalanceamento == 1 && avo->fatorBalanceamento == 1) 
			{
				avo->dir = pai->esq;
				pai->esq = avo;
				raizUsuario = pai;
				pai->fatorBalanceamento--;
				avo->fatorBalanceamento--;
			}
			else
			{
				avo->dir = novoNo;
				novoNo->dir = pai;
				pai->esq = NULL;
				
				novoNo->esq = avo;
				avo->dir = NULL;
				raizUsuario = novoNo;
				pai->fatorBalanceamento++;
				avo->fatorBalanceamento--;
			}
		}
	}
	return raizUsuario;
}

TNoOng* inserirOng (TNoOng *raizOng, TElementoOng *novaOng)
{
	int inseriu = 0;
	TNoOng *novoNo;
	novoNo = new TNoOng;
	novoNo->esq = NULL;
	novoNo->dir = NULL;
	novoNo->fatorBalanceamento = 0;
	novoNo->Ong.IdOng = novaOng->IdOng;
	strcpy(novoNo->Ong.nomeFantasiaOng, novaOng->nomeFantasiaOng);
	strcpy(novoNo->Ong.cnpjOng, novaOng->cnpjOng);
	novoNo->Ong.telefoneOng = novaOng->telefoneOng;
	novoNo->Ong.enderecoOng = novaOng->enderecoOng;
	strcpy(novoNo->Ong.descricaoOng, novaOng->descricaoOng);
	strcpy(novoNo->Ong.emailOng, novaOng->emailOng);
	strcpy(novoNo->Ong.senhaOng, novaOng->senhaOng);
	strcpy(novoNo->Ong.confirmarSenhaOng, novaOng->confirmarSenhaOng);
	for(int aux=0; aux<=9; aux++)
	{
		strcpy(novoNo->Ong.produtosOng[aux], novaOng->produtosOng[aux]);
	}
	if (raizOng == NULL)
	{
		raizOng = novoNo;
	}
	else
	{
		TNoOng *avo, *pai;
		avo = NULL;
		pai = raizOng;
		while (inseriu == 0)
		{
			if (novoNo->Ong.IdOng < pai->Ong.IdOng)
			{
				if (pai->esq == NULL)
				{
					pai->esq = novoNo;
					pai->fatorBalanceamento--;
					inseriu = 1;	
				}
				else
				{
					avo = pai;
					pai = pai->esq;
				}
			}
			else
			{
				if (pai->dir == NULL)
				{
					pai->dir = novoNo;
					pai->fatorBalanceamento++;
					inseriu = 1;
				}
				else
				{
					avo = pai;
					pai = pai->dir;
				}
			}
		}
		if (avo != NULL)
		{
			if (pai->fatorBalanceamento == 1 && avo->fatorBalanceamento == 1) 
			{
				avo->dir = pai->esq;
				pai->esq = avo;
				raizOng = pai;
				pai->fatorBalanceamento--;
				avo->fatorBalanceamento--;
			}
			else
			{
				avo->dir = novoNo;
				novoNo->dir = pai;
				pai->esq = NULL;
				
				novoNo->esq = avo;
				avo->dir = NULL;
				raizOng = novoNo;
				pai->fatorBalanceamento++;
				avo->fatorBalanceamento--;
			}
		}
	}
	return raizOng;
}

TNoMercado* inserirMercado (TNoMercado *raizMercado, TElementoMercado *novoMercado)
{
	int inseriu = 0;
	TNoMercado *novoNo;
	novoNo = new TNoMercado;
	novoNo->esq = NULL;
	novoNo->dir = NULL;
	novoNo->fatorBalanceamento = 0;
	novoNo->mercado.IdMercado = novoMercado->IdMercado;
	strcpy(novoNo->mercado.nomeFantasiaMercado, novoMercado->nomeFantasiaMercado);
	strcpy(novoNo->mercado.cnpjMercado, novoMercado->cnpjMercado);
	novoNo->mercado.telefone = novoMercado->telefone;
	novoNo->mercado.enderecoMercado = novoMercado->enderecoMercado;
	strcpy(novoNo->mercado.urlMercado, novoMercado->urlMercado);
	if (raizMercado == NULL)
	{
		raizMercado = novoNo;
	}
	else
	{
		TNoMercado *avo, *pai;
		avo = NULL;
		pai = raizMercado;
		while (inseriu == 0)
		{
			if (novoNo->mercado.IdMercado < pai->mercado.IdMercado)
			{
				if (pai->esq == NULL)
				{
					pai->esq = novoNo;
					pai->fatorBalanceamento--;
					inseriu = 1;	
				}
				else
				{
					avo = pai;
					pai = pai->esq;
				}
			}
			else
			{
				if (pai->dir == NULL)
				{
					pai->dir = novoNo;
					pai->fatorBalanceamento++;
					inseriu = 1;
				}
				else
				{
					avo = pai;
					pai = pai->dir;
				}
			}
		}
		if (avo != NULL)
		{
			if (pai->fatorBalanceamento == 1 && avo->fatorBalanceamento == 1) 
			{
				avo->dir = pai->esq;
				pai->esq = avo;
				raizMercado = pai;
				pai->fatorBalanceamento--;
				avo->fatorBalanceamento--;
			}
			else
			{
				avo->dir = novoNo;
				novoNo->dir = pai;
				pai->esq = NULL;
				
				novoNo->esq = avo;
				avo->dir = NULL;
				raizMercado = novoNo;
				pai->fatorBalanceamento++;
				avo->fatorBalanceamento--;
			}
		}
	}
	return raizMercado;
}

void apresentarEndereco(TElementoEndereco raiz)
{
	printf("\n\nEstado: %s                     cidade: %s", raiz.estado, raiz.cidade);
	printf("\n logradouro: %s                  Numero: %s", raiz.lougradoro, raiz.numero);
	printf("\n Complemento: %s", raiz.complemento);
}

void preOrdemUsuario (TNoUsuario *raiz)
{
	if (raiz != NULL)
	{
		printf("\n-----------------------------------------------------------");
		printf("\n---------------------Usuario-%d----------------------------", raiz->Usuario.IdUsuario);
		printf("\n Nome: %s", raiz->Usuario.nomeUsuario);
		printf("\n CPF: %s                   Nascimento: %d/%d/%d", raiz->Usuario.CpfUsuario, raiz->Usuario.diaNascimento, raiz->Usuario.mesNascimento, raiz->Usuario.anoNascimento);
		printf("\n E-mail: %s", raiz->Usuario.emailUsuario);
		apresentarEndereco(raiz->Usuario.enderecoUsuario);
		preOrdemUsuario (raiz->esq);
		preOrdemUsuario (raiz->dir);
	}
}

void emOrdemUsuario (TNoUsuario *raiz)
{
	if (raiz != NULL)
	{
		emOrdemUsuario (raiz->esq);
		printf("\n-----------------------------------------------------------");
		printf("\n---------------------Usuario-%d----------------------------", raiz->Usuario.IdUsuario);
		printf("\n Nome: %s", raiz->Usuario.nomeUsuario);
		printf("\n CPF: %s                   Nascimento: %d/%d/%d", raiz->Usuario.CpfUsuario, raiz->Usuario.diaNascimento, raiz->Usuario.mesNascimento, raiz->Usuario.anoNascimento);
		printf("\n E-mail: %s", raiz->Usuario.emailUsuario);
		apresentarEndereco(raiz->Usuario.enderecoUsuario);
		emOrdemUsuario (raiz->dir);
	}
}

void posOrdemUsuario (TNoUsuario *raiz)
{
	if (raiz != NULL)
	{
		posOrdemUsuario (raiz->esq);
		posOrdemUsuario (raiz->dir);
		printf("\n-----------------------------------------------------------");
		printf("\n---------------------Usuario-%d----------------------------", raiz->Usuario.IdUsuario);
		printf("\n Nome: %s", raiz->Usuario.nomeUsuario);
		printf("\n CPF: %s                   Nascimento: %d/%d/%d", raiz->Usuario.CpfUsuario, raiz->Usuario.diaNascimento, raiz->Usuario.mesNascimento, raiz->Usuario.anoNascimento);
		printf("\n E-mail: %s", raiz->Usuario.emailUsuario);
		apresentarEndereco(raiz->Usuario.enderecoUsuario);
	}
}

void preOrdemOng (TNoOng *raiz)
{
	if (raiz != NULL)
	{
		printf("\n-----------------------------------------------------------");
		printf("\n---------------------Ong-%d----------------------------", raiz->Ong.IdOng);
		printf("\n Nome fantasia: %s", raiz->Ong.nomeFantasiaOng);
		printf("\n CNPJ: %s", raiz->Ong.cnpjOng);
		printf("\n Telefone: %d", raiz->Ong.telefoneOng);
		printf("\n E-mail: %s", raiz->Ong.emailOng);
		apresentarEndereco(raiz->Ong.enderecoOng);
		printf("\n Descricao: %s", raiz->Ong.descricaoOng);
		for(int aux=0; aux<=9; aux++)
		{	
			printf("\n %d Produto: %s", aux + 1, raiz->Ong.produtosOng[aux]);
		}
		preOrdemOng (raiz->esq);
		preOrdemOng (raiz->dir);
	}
}

void emOrdemOng (TNoOng *raiz)
{
	if (raiz != NULL)
	{
		emOrdemOng (raiz->esq);
		printf("\n-----------------------------------------------------------");
		printf("\n---------------------Ong-%d----------------------------", raiz->Ong.IdOng);
		printf("\n Nome fantasia: %s", raiz->Ong.nomeFantasiaOng);
		printf("\n CNPJ: %s", raiz->Ong.cnpjOng);
		printf("\n Telefone: %d", raiz->Ong.telefoneOng);
		printf("\n E-mail: %s", raiz->Ong.emailOng);
		apresentarEndereco(raiz->Ong.enderecoOng);
		printf("\n Descricao: %s", raiz->Ong.descricaoOng);
		for(int aux=0; aux<=9; aux++)
		{	
			printf("\n %d Produto: %s", aux + 1, raiz->Ong.produtosOng[aux]);
		}
		emOrdemOng (raiz->dir);
	}
}

void posOrdemOng (TNoOng *raiz)
{
	if (raiz != NULL)
	{
		posOrdemOng (raiz->esq);
		posOrdemOng (raiz->dir);
		printf("\n-----------------------------------------------------------");
		printf("\n---------------------Ong-%d----------------------------", raiz->Ong.IdOng);
		printf("\n Nome fantasia: %s", raiz->Ong.nomeFantasiaOng);
		printf("\n CNPJ: %s", raiz->Ong.cnpjOng);
		printf("\n Telefone: %d", raiz->Ong.telefoneOng);
		printf("\n E-mail: %s", raiz->Ong.emailOng);
		apresentarEndereco(raiz->Ong.enderecoOng);
		printf("\n Descricao: %s", raiz->Ong.descricaoOng);
		for(int aux=0; aux<=9; aux++)
		{	
			printf("\n %d Produto: %s", aux + 1, raiz->Ong.produtosOng[aux]);
		}
	}
}

void preOrdemMercado (TNoMercado *raiz)
{
	if (raiz != NULL)
	{
		printf("\n-----------------------------------------------------------");
		printf("\n---------------------Mercado-%d----------------------------", raiz->mercado.IdMercado);
		printf("\n Nome fantasia: %s", raiz->mercado.nomeFantasiaMercado);
		printf("\n CNPJ: %s", raiz->mercado.cnpjMercado);
		printf("\n Telefone: %d", raiz->mercado.telefone);
		printf("\n URL: %s", raiz->mercado.urlMercado);
		apresentarEndereco(raiz->mercado.enderecoMercado);
		preOrdemMercado (raiz->esq);
		preOrdemMercado (raiz->dir);
	}
}

void emOrdemMercado (TNoMercado *raiz)
{
	if (raiz != NULL)
	{
		emOrdemMercado (raiz->esq);
		printf("\n-----------------------------------------------------------");
		printf("\n---------------------Mercado-%d----------------------------", raiz->mercado.IdMercado);
		printf("\n Nome fantasia: %s", raiz->mercado.nomeFantasiaMercado);
		printf("\n CNPJ: %s", raiz->mercado.cnpjMercado);
		printf("\n Telefone: %d", raiz->mercado.telefone);
		printf("\n URL: %s", raiz->mercado.urlMercado);
		apresentarEndereco(raiz->mercado.enderecoMercado);
		emOrdemMercado (raiz->dir);
	}
}

void posOrdemMercado (TNoMercado *raiz)
{
	if (raiz != NULL)
	{
		posOrdemMercado (raiz->esq);
		posOrdemMercado (raiz->dir);
		printf("\n-----------------------------------------------------------");
		printf("\n---------------------Mercado-%d----------------------------", raiz->mercado.IdMercado);
		printf("\n Nome fantasia: %s", raiz->mercado.nomeFantasiaMercado);
		printf("\n CNPJ: %s", raiz->mercado.cnpjMercado);
		printf("\n Telefone: %d", raiz->mercado.telefone);
		printf("\n URL: %s", raiz->mercado.urlMercado);
		apresentarEndereco(raiz->mercado.enderecoMercado);
	}
}

int sobeMaiorDireitaUsuario (TNoUsuario *aux)
{
	while (aux->dir != NULL)
	{
		aux = aux->dir;
	}
	return (aux->Usuario.IdUsuario);
}

TNoUsuario* removerUsuario ( TNoUsuario *auxRaiz, int auxValor)
{
	if (auxRaiz == NULL)
	{
		printf("\n Elemento nao encontrado!");
	}
	else
	{
		if (auxValor < auxRaiz->Usuario.IdUsuario)
		{
			auxRaiz->esq = removerUsuario (auxRaiz->esq, auxValor);
		}
		else
		{
			if (auxValor > auxRaiz->Usuario.IdUsuario)
			{
				auxRaiz->dir = removerUsuario (auxRaiz->dir, auxValor);
			}
			else
			{
				if (auxRaiz->dir == NULL)
				{
					TNoUsuario *aux;
					aux = auxRaiz;
					auxRaiz = auxRaiz->esq;
					free(aux);
				}
				else
				{
					if (auxRaiz->esq == NULL)
					{
						TNoUsuario *aux;
						aux = auxRaiz;
						auxRaiz = auxRaiz->dir;
						free(aux);
					}
					else
					{
						auxRaiz->Usuario.IdUsuario = sobeMaiorDireitaUsuario(auxRaiz->esq);
						auxRaiz->esq = removerUsuario (auxRaiz->esq, auxRaiz->Usuario.IdUsuario);
					}
				}
			}
		}
	}
	return auxRaiz;
}

int sobeMaiorDireitaOng (TNoOng *aux)
{
	while (aux->dir != NULL)
	{
		aux = aux->dir;
	}
	return (aux->Ong.IdOng);
}

TNoOng* removerOng ( TNoOng *auxRaiz, int auxValor)
{
	if (auxRaiz == NULL)
	{
		printf("\n Elemento nao encontrado!");
	}
	else
	{
		if (auxValor < auxRaiz->Ong.IdOng)
		{
			auxRaiz->esq = removerOng (auxRaiz->esq, auxValor);
		}
		else
		{
			if (auxValor > auxRaiz->Ong.IdOng)
			{
				auxRaiz->dir = removerOng (auxRaiz->dir, auxValor);
			}
			else
			{
				if (auxRaiz->dir == NULL)
				{
					TNoOng *aux;
					aux = auxRaiz;
					auxRaiz = auxRaiz->esq;
					free(aux);
				}
				else
				{
					if (auxRaiz->esq == NULL)
					{
						TNoOng *aux;
						aux = auxRaiz;
						auxRaiz = auxRaiz->dir;
						free(aux);
					}
					else
					{
						auxRaiz->Ong.IdOng = sobeMaiorDireitaOng(auxRaiz->esq);
						auxRaiz->esq = removerOng (auxRaiz->esq, auxRaiz->Ong.IdOng);
					}
				}
			}
		}
	}
	return auxRaiz;
}

int sobeMaiorDireitaMercado (TNoMercado *aux)
{
	while (aux->dir != NULL)
	{
		aux = aux->dir;
	}
	return (aux->mercado.IdMercado);
}

TNoMercado* removerMercado ( TNoMercado *auxRaiz, int auxValor)
{
	if (auxRaiz == NULL)
	{
		printf("\n Elemento nao encontrado!");
	}
	else
	{
		if (auxValor < auxRaiz->mercado.IdMercado)
		{
			auxRaiz->esq = removerMercado (auxRaiz->esq, auxValor);
		}
		else
		{
			if (auxValor > auxRaiz->mercado.IdMercado)
			{
				auxRaiz->dir = removerMercado (auxRaiz->dir, auxValor);
			}
			else
			{
				if (auxRaiz->dir == NULL)
				{
					TNoMercado *aux;
					aux = auxRaiz;
					auxRaiz = auxRaiz->esq;
					free(aux);
				}
				else
				{
					if (auxRaiz->esq == NULL)
					{
						TNoMercado *aux;
						aux = auxRaiz;
						auxRaiz = auxRaiz->dir;
						free(aux);
					}
					else
					{
						auxRaiz->mercado.IdMercado = sobeMaiorDireitaMercado(auxRaiz->esq);
						auxRaiz->esq = removerMercado (auxRaiz->esq, auxRaiz->mercado.IdMercado);
					}
				}
			}
		}
	}
	return auxRaiz;
}

int consultarRecursivoUsuario(TNoUsuario *auxRaiz, int auxValor){
	if(auxRaiz == NULL){
		return 0;
	}else{
		if(auxRaiz->Usuario.IdUsuario == auxValor){
			return 1;
		}else{
			if(auxValor < auxRaiz->Usuario.IdUsuario){
				return consultarRecursivoUsuario(auxRaiz->esq, auxValor);
			}else{
				return consultarRecursivoUsuario(auxRaiz->dir, auxValor);
			}
		}
	}
}

int consultarRecursivoOng(TNoOng *auxRaiz, int auxValor){
	if(auxRaiz == NULL){
		return 0;
	}else{
		if(auxRaiz->Ong.IdOng == auxValor){
			return 1;
		}else{
			if(auxValor < auxRaiz->Ong.IdOng){
				return consultarRecursivoOng(auxRaiz->esq, auxValor);
			}else{
				return consultarRecursivoOng(auxRaiz->dir, auxValor);
			}
		}
	}
}

int consultarRecursivoMercado(TNoMercado *auxRaiz, int auxValor){
	if(auxRaiz == NULL){
		return 0;
	}else{
		if(auxRaiz->mercado.IdMercado == auxValor){
			return 1;
		}else{
			if(auxValor < auxRaiz->mercado.IdMercado){
				return consultarRecursivoMercado(auxRaiz->esq, auxValor);
			}else{
				return consultarRecursivoMercado(auxRaiz->dir, auxValor);
			}
		}
	}
}

int main()
{
	int opcao, opcao2, opcao1;
	int contadorUsuario = 0;
	int contadorOng = 0;
	int contadorMercado = 0;
	TNoUsuario *raizUsuario = NULL;
	TNoOng *raizOng = NULL;
	TNoMercado *raizMercado = NULL;
	
	do
	{
		printf("\n 1 - Cadastro");
		printf("\n 2 - Relatorio");
		printf("\n 3 - Exclusao");
		printf("\n 4 - Consultar");
		printf("\n 0 - Sair");
		printf("\n Escolha uma opcao: ");
		fflush(stdin);
		scanf("%d", &opcao);
		if(opcao == 1)
		{
			printf("\n 1 - Cadastro de Usuario");
			printf("\n 2 - Cadastro de Ong");
			printf("\n 3 - Cadastro de Mercado");
			printf("\n 0 - Sair");
			printf("\n Escolha uma opcao: ");
			fflush(stdin);
			scanf("%d", &opcao2);
			switch(opcao2)
			{
				case 1:
					{
						TElementoUsuario novoUsuario;
						contadorUsuario++;
						lerValorUsuario(&novoUsuario, contadorUsuario);
						raizUsuario = inserirUsuario(raizUsuario, &novoUsuario);
						break;
					}
				case 2:
					{
						TElementoOng novaOng;
						contadorOng++;
						lerValorOng(&novaOng, contadorOng);
						raizOng = inserirOng(raizOng, &novaOng);
						break;
					}
				case 3:
					{
						TElementoMercado novoMercado;
						contadorMercado++;
						lerValorMercado(&novoMercado, contadorMercado);
						raizMercado = inserirMercado(raizMercado, &novoMercado);
						break;
					}
			}
		}else if (opcao == 2)
		{
			printf("\n 1 - Usuario");
			printf("\n 2 - Ong");
			printf("\n 3 - Mercado");
			printf("\n 0 - Sair");
			printf("\n Escolha uma opcao: ");
			fflush(stdin);
			scanf("%d", &opcao2);
			printf("\n 1 - Pre-Ordem");
			printf("\n 2 - Pos-Ordem");
			printf("\n 3 - Em-Ordem");
			printf("\n 0 - Sair");
			printf("\n Escolha uma opcao: ");
			fflush(stdin);
			scanf("%d", &opcao1);
			switch(opcao2)
			{
				case 1:
					{
						switch(opcao1)
						{
							case 1: preOrdemUsuario(raizUsuario); break;
							case 2: posOrdemUsuario(raizUsuario); break;
							case 3: emOrdemUsuario(raizUsuario); break;
							case 0: opcao = 0; break;
						}
						break;
					}
				case 2:
					{
						switch(opcao1)
						{
							case 1: preOrdemOng(raizOng); break;
							case 2: posOrdemOng(raizOng); break;
							case 3: emOrdemOng(raizOng); break;
							case 0: opcao = 0; break;
						}
						break;
					}
				case 3:
					{
						switch(opcao1)
						{
							case 1: preOrdemMercado(raizMercado); break;
							case 2: posOrdemMercado(raizMercado); break;
							case 3: emOrdemMercado(raizMercado); break;
							case 0: opcao = 0; break;
						}
						break;
					}
					case 0: opcao = 0; break;
			}
		}
		else if (opcao == 3)
		{
			printf("\n 1 - Usuario");
			printf("\n 2 - Ong");
			printf("\n 3 - Mercado");
			printf("\n 0 - Sair");
			printf("\n Escolha qual opcao voce deseja escluir: ");
			fflush(stdin);
			scanf("%d", &opcao2);
			switch(opcao2)
			{
				case 1:
					{
						int auxValor;
						printf("\n Informe o ID do usuario para ser excluido: ");
						scanf("%d", &auxValor);
						raizUsuario = removerUsuario (raizUsuario, auxValor);
						break;
					}
				case 2:
					{
						int auxValor;
						printf("\n Informe o ID da ONG para ser excluida: ");
						scanf("%d", &auxValor);
						raizOng = removerOng (raizOng, auxValor);
						break;
					}
				case 3:
					{
						int auxValor;
						printf("\n Informe o ID do mercado para ser excluido: ");
						scanf("%d", &auxValor);
						raizMercado = removerMercado (raizMercado, auxValor);
						break;
					}
				case 0: opcao = 0; break;
			}
		}
		else if (opcao == 4)
		{
			printf("\n 1 - Usuario");
			printf("\n 2 - Ong");
			printf("\n 3 - Mercado");
			printf("\n 0 - Sair");
			printf("\n Escolha qual opcao voce deseja consultar: ");
			fflush(stdin);
			scanf("%d", &opcao2);
			switch(opcao2)
			{
				case 1:
					{
						int auxValor;
						int encontrou;
						printf("\n Digite o ID que deseja ser consultado: ");
						scanf("%d",&auxValor);
						encontrou = consultarRecursivoUsuario(raizUsuario, auxValor);
						if(encontrou == 1)
						{
							printf("\n ID encontrado!");
						}
						else
						{
							printf("\n ID nao encontrado!");
						}
						break;
					}
				case 2:
					{
						int auxValor;
						int encontrou;
						printf("\n Digite o ID que deseja ser consultado: ");
						scanf("%d",&auxValor);
						encontrou = consultarRecursivoOng(raizOng, auxValor);
						if(encontrou == 1)
						{
							printf("\n ID encontrado!");
						}
						else
						{
							printf("\n ID nao encontrado!");
						}
						break;	
					}
				case 3:
					{
						int auxValor;
						int encontrou;
						printf("\n Digite o ID que deseja ser consultado: ");
						scanf("%d",&auxValor);
						encontrou = consultarRecursivoMercado(raizMercado, auxValor);
						if(encontrou == 1)
						{
							printf("\n ID encontrado!");
						}
						else
						{
							printf("\n ID nao encontrado!");
						}
						break;
					}
				case 0: opcao = 0; break;
			}
		}
	}while (opcao != 0);
}
