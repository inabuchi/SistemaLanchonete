package br.com.SistemaLanchonete.Teste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.SistemaLanchonete.Domain.BairroBean;
import br.com.SistemaLanchonete.Domain.ClienteBean;
import br.com.SistemaLanchonete.Domain.EnderecoBean;
import br.com.SistemaLanchonete.Domain.EnderecoPessoaBean;
import br.com.SistemaLanchonete.Domain.EstadoBean;
import br.com.SistemaLanchonete.Domain.FormaPagamentoBean;
import br.com.SistemaLanchonete.Domain.FuncionarioBean;
import br.com.SistemaLanchonete.Domain.LogradouroBean;
import br.com.SistemaLanchonete.Domain.MunicipioBean;
import br.com.SistemaLanchonete.Domain.PedidoBean;
import br.com.SistemaLanchonete.Domain.PrecoProdutoBean;
import br.com.SistemaLanchonete.Domain.ProdutoAdicionalBean;
import br.com.SistemaLanchonete.Domain.ProdutoBean;
import br.com.SistemaLanchonete.Domain.ProdutoCategoriaBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Resource.FormaPagamentoResource;
import br.com.SistemaLanchonete.Resource.PedidoResource;
import br.com.SistemaLanchonete.Resource.ProdutoAdicionalResource;
import br.com.SistemaLanchonete.Resource.ProdutoCategoriaResource;
import br.com.SistemaLanchonete.Resource.ProdutoResource;
import br.com.SistemaLanchonete.Service.ClienteService;
import br.com.SistemaLanchonete.Service.FuncionarioService;
import br.com.SistemaLanchonete.Validacao.Constantes;

public class Principal {
	/*
	 * Clientes para teste
	 * 
	 * 
	 * TODO precisa colocar aqui clientes com as mais variadas situaçoes:
	 * 
	 * espaços em branco, telefones duplicados, null nas string, etc
	 * 
	 * pelo menos uns 10 clientes e endercos diferentes, ja utilizando os valores
	 * inseridos de estado, municipio, bairro, logradouro e tambem criando novos
	 * desses
	 */
	private static ClienteBean cli0 = null;
	private static ClienteBean cli1 = new ClienteBean();
	private static ClienteBean cli2 = new ClienteBean(0, null, null, null, new Date(), true, 0, "");
	private static ClienteBean cli3 = new ClienteBean(0, "", "", "", new Date(), true, 0, "");
	private static ClienteBean cli4 = new ClienteBean(0, " ", " ", " ", new Date(), true, 0, "");
	private static ClienteBean cli5 = new ClienteBean(0, "Genivaldo", "(47) 3333-3333", "(47)4444-4444", new Date(),
			true, 0, "");
	private static ClienteBean[] clientes = { cli0, cli1, cli2, cli3, cli4, cli5 };

	/*
	 * Funcionarios para teste
	 * 
	 * TODO idem clientes
	 */
	private static FuncionarioBean func0 = null;
	private static FuncionarioBean func1 = new FuncionarioBean();
	private static FuncionarioBean func2 = new FuncionarioBean(0, null, null, null, new Date(), true, 0, null, null,
			null, 0);
	private static FuncionarioBean func3 = new FuncionarioBean(0, "", "", "", new Date(), true, 0, "cargo", "func3",
			"func3", 0);
	private static FuncionarioBean func4 = new FuncionarioBean(0, " ", " ", " ", new Date(), true, 0, "cargo", "func4",
			"func4", 3);
	private static FuncionarioBean[] funcionarios = { func0, func1, func2, func3, func4 };
	/*
	 * Categorias para teste
	 */
	private static ProdutoCategoriaBean cat0 = null;
	private static ProdutoCategoriaBean cat1 = new ProdutoCategoriaBean(0, null);
	private static ProdutoCategoriaBean cat2 = new ProdutoCategoriaBean(0, "");
	private static ProdutoCategoriaBean cat3 = new ProdutoCategoriaBean(0, " ");
	private static ProdutoCategoriaBean cat4 = new ProdutoCategoriaBean(0, "      categoria com espaco na frente");
	private static ProdutoCategoriaBean cat5 = new ProdutoCategoriaBean(0, "categoria com espaco atras          ");
	private static ProdutoCategoriaBean cat6 = new ProdutoCategoriaBean(0, "categoria sem espaco");
	private static ProdutoCategoriaBean cat7 = new ProdutoCategoriaBean(0, "123456");
	private static ProdutoCategoriaBean[] categorias = { cat0, cat1, cat2, cat3, cat4, cat5, cat6, cat7 };
	/*
	 * Adicionais para teste
	 */
	private static ProdutoAdicionalBean adi0 = null;
	private static ProdutoAdicionalBean adi1 = new ProdutoAdicionalBean(0, null, 1);
	private static ProdutoAdicionalBean adi2 = new ProdutoAdicionalBean(0, "", 2);
	private static ProdutoAdicionalBean adi3 = new ProdutoAdicionalBean(0, " ", 3);
	private static ProdutoAdicionalBean adi4 = new ProdutoAdicionalBean(0, "      adicional com espaco na frente", 4);
	private static ProdutoAdicionalBean adi5 = new ProdutoAdicionalBean(0, "adicional com espaco atras          ", 5);
	private static ProdutoAdicionalBean adi6 = new ProdutoAdicionalBean(0, "adicional sem espaco", 6);
	private static ProdutoAdicionalBean adi7 = new ProdutoAdicionalBean(0, "123456", 7);
	private static ProdutoAdicionalBean[] adicionais = { adi0, adi1, adi2, adi3, adi4, adi5, adi6, adi7 };
	/*
	 * Formas de pagamento para teste
	 */
	private static FormaPagamentoBean fpa0 = null;
	private static FormaPagamentoBean fpa1 = new FormaPagamentoBean(0, null);
	private static FormaPagamentoBean fpa2 = new FormaPagamentoBean(0, "");
	private static FormaPagamentoBean fpa3 = new FormaPagamentoBean(0, " ");
	private static FormaPagamentoBean fpa4 = new FormaPagamentoBean(0, "      formaPagto com espaco na frente");
	private static FormaPagamentoBean fpa5 = new FormaPagamentoBean(0, "formaPagto com espaco atras          ");
	private static FormaPagamentoBean fpa6 = new FormaPagamentoBean(0, "formaPagto sem espaco");
	private static FormaPagamentoBean fpa7 = new FormaPagamentoBean(0, "123456");
	private static FormaPagamentoBean[] formasPagamento = { fpa0, fpa1, fpa2, fpa3, fpa4, fpa5, fpa6, fpa7 };

	/*
	 * Produtos para teste
	 */
	private static ProdutoBean pro0 = null;
	private static ProdutoBean pro1 = new ProdutoBean(0, cat6, null, null, true);
	private static ProdutoBean pro2 = new ProdutoBean(0, cat6, "", "", true);
	private static ProdutoBean pro3 = new ProdutoBean(0, cat6, " ", " ", true);
	private static ProdutoBean pro4 = new ProdutoBean(0, cat6, "  99", "    produto com espaco na frente", true);
	private static ProdutoBean pro5 = new ProdutoBean(0, cat6, "99  ", "produto com espaco atras        ", true);
	private static ProdutoBean pro6 = new ProdutoBean(0, cat6, "99", "produto sem espaco", true);
	private static ProdutoBean pro7 = new ProdutoBean(0, cat6, "xxxx", "123456789", true);
	private static ProdutoBean[] produtos = { pro0, pro1, pro2, pro3, pro4, pro5, pro6, pro7 };

	/*
	 * Preços para teste
	 */
	private static PrecoProdutoBean pre0 = new PrecoProdutoBean(0, pro7, 10, null, null, true);
	private static List<PrecoProdutoBean> precoProduto = new ArrayList<PrecoProdutoBean>();

	private static PedidoBean ped1 = new PedidoBean(0, cli5, func4, func3, fpa7, 0, new Date(), 0, 0, 0, 0, new Date(),
			0, "");
	private static PedidoBean ped2;
	private static PedidoBean ped3;
	private static PedidoBean ped4;
	private static PedidoBean ped5;
	private static PedidoBean ped6;
	private static PedidoBean ped7;
	private static PedidoBean[] pedidos = { ped1, ped2, ped3, ped4, ped5, ped6, ped7 };
	private static String beanArray[] = { //
			"", //
			"Cliente", //
			"Funcionario", //
			"Categoria de Produto", //
			"Adicional de Produto", //
			"Forma de Pagamento", //
			"Produto", //
			"Pedido" };
	private static String operacaoArray[] = { //
			"", //
			Constantes.INSERE, //
			Constantes.ALTERA, //
			Constantes.DELETA, //
			Constantes.PESQUISA, //
			"TOD" };

	public static void main(String[] args) throws BDException {
		boolean testando = true;
		while (testando) {
			// testa conexao com baco

			// testa classe
			String beanManipulacao = (String) JOptionPane.showInputDialog(null, "Classe para manipulação", "Escolha: ",
					JOptionPane.QUESTION_MESSAGE, null, beanArray, "");
			// testa operacao
			String operacao = (String) JOptionPane.showInputDialog(null, "Operação a ser realizada", "Escolha: ",
					JOptionPane.QUESTION_MESSAGE, null, operacaoArray, "");

			switch (beanManipulacao) {
			case "Cliente":
				cliente(operacao);
				break;
			case "Fucionario":
				funcionario(operacao);
				break;
			case "Categoria de Produto":
				categoria(operacao);
				break;
			case "Adicional de Produto":
				adicional(operacao);
				break;
			case "Forma de Pagamento":
				formaPagamamento(operacao);
				break;
			case "Produto":
				produto(operacao);
				break;
			case "Pedido":
				pedido(operacao);
				break;
			default:
				break;
			}
			int test = JOptionPane.showConfirmDialog(null, "Deseja Continuar?: ", "Continuar o programa?",
					JOptionPane.YES_NO_OPTION);
			if (test == 0) {
				testando = true;
			} else {
				testando = false;
			}
		}

	}

	/**
	 * @param operacao
	 * @throws BDException
	 * 
	 */
	private static void cliente(String operacao) throws BDException {
		// vamos inserir um cliente completo para ver

		EstadoBean est = new EstadoBean(0, "Exterior", "EX");
		MunicipioBean mun = new MunicipioBean(0, est, "Rowekseoes");
		BairroBean bairro = new BairroBean(0, mun, "Adsrewqw");
		LogradouroBean log = new LogradouroBean(0, bairro, 89010600, "Rua woeasudwi");
		EnderecoBean end = new EnderecoBean(0, log, 799, "SCI Sistemas cont�beis", "");
		EnderecoPessoaBean endPessoa = new EnderecoPessoaBean(null, end, new Date(), true);
		/* EnderecoService endSer = new EnderecoService(); */
		cli5.addEnderecoPessoa(endPessoa);
		ClienteService cliSer = new ClienteService();
		cliSer.save(cli5);
		// for (ClienteBean clienteBean : clientes) {
		// try {
		// System.out.println(clienteBean + " <--> " + new
		// ClienteService().save(clienteBean));
		// } catch (BDException e) {
		//
		// e.printStackTrace();
		// }
		// }
		/* endSer.save(endPessoa); */
	}

	/**
	 * @param operacao
	 * 
	 */
	private static void funcionario(String operacao) {
		// TODOfazer a logica para insercao de funcionario e seu endereco
		for (FuncionarioBean funcionarioBean : funcionarios) {
			try {
				System.out.println(funcionarioBean + " <--> " + new FuncionarioService().save(funcionarioBean));
			} catch (BDException e) {
				e.printStackTrace();
			}
		}
	}

	public static void categoria(String operacao) {
		// JSON padrão de Categotia de produto

		// {//
		// "cdProdutoCategoria": 999,//
		// "dsCategoria": "XXXXXXXXXXX"//
		// }//
		List<ProdutoCategoriaBean> listaTotal = new ProdutoCategoriaResource().findLike(null);
		ProdutoCategoriaResource categoriaResource = new ProdutoCategoriaResource();
		switch (operacao) {
		case Constantes.INSERE:
			for (ProdutoCategoriaBean produtoCategoriaBean : categorias) {
				System.out.println(
						produtoCategoriaBean + " <--> " + categoriaResource.insert(produtoCategoriaBean).getEntity());
			}
			break;
		case Constantes.ALTERA:
			for (ProdutoCategoriaBean produtoCategoriaBean : listaTotal) {
				int cdProdutoCategoria = produtoCategoriaBean.getCdProdutoCategoria();
				String dsCategoria = "alteracao " + String.valueOf(cdProdutoCategoria);
				ProdutoCategoriaBean produtoCategoriaBeanNovo = new ProdutoCategoriaBean(cdProdutoCategoria,
						dsCategoria);
				Object retorno = categoriaResource.update(produtoCategoriaBeanNovo.getCdProdutoCategoria()).getEntity();
				System.out.println("Atual " + produtoCategoriaBean + " <--> Novo " + produtoCategoriaBeanNovo
						+ " Banco <--> " + retorno);
			}
			break;
		case Constantes.DELETA:
			for (int i = 0; i <= listaTotal.size(); i++) {
				System.out.println("Remocao Unica " + i + " <--> " + categoriaResource.delete(i));
			}
			break;
		case Constantes.PESQUISA:
			for (int i = 0; i <= listaTotal.size(); i++) {
				System.out.println("Pesquisa Unica " + i + " <--> " + categoriaResource.select(i));
			}
			break;
		default:
			System.out.println("Pesquisa Completa: ");
			for (ProdutoCategoriaBean produtoCategoriaBean : listaTotal) {
				System.out.println(produtoCategoriaBean);
			}
			break;
		}
	}

	public static void adicional(String operacao) {
		// JSON padrão de adicional de produto

		// {//
		// "cdProdutoAdicional": 9999,//
		// "dsAdicional": "XXXXXXX",//
		// "vlAdicional": 9999//
		// }//

		switch (operacao) {
		case Constantes.INSERE:
			for (ProdutoAdicionalBean produtoAdicionalBean : adicionais) {
				System.out.println(
						produtoAdicionalBean + " <--> " + new ProdutoAdicionalResource().insert(produtoAdicionalBean));
			}
			break;
		case Constantes.ALTERA:
			break;
		case Constantes.DELETA:
			break;
		case Constantes.PESQUISA:
			break;
		default:
			break;
		}
	}

	public static void formaPagamamento(String operacao) {

		// { //
		// "cdFormaPagamento": 9999, //
		// "dsFormaPagamento": "XXXXXXXXX" //
		// } //

		switch (operacao) {
		case Constantes.INSERE:
			for (FormaPagamentoBean formaPagamentoBean : formasPagamento) {
				System.out.println(
						formaPagamentoBean + " <--> " + new FormaPagamentoResource().insert(formaPagamentoBean));
			}
			break;
		case Constantes.ALTERA:
			break;
		case Constantes.DELETA:
			break;
		case Constantes.PESQUISA:
			break;
		default:
			break;
		}
	}

	/**
	 * @param operacao
	 * 
	 */
	private static void produto(String operacao) {

		// JSON padrão de Produto

		// { //
		// "cdProduto": 999, //
		// "categoria": { //
		// "cdProdutoCategoria": 9999, //
		// "dsCategoria": "XXXXXXXXXXX" //
		// }, //
		// "dsRefProduto": "XXXX", //
		// "dsProduto": "XXXXXXXXXXXXXXXXXXX",//
		// "isAtivo": false //
		// } //
		precoProduto.add(pre0);
		pro7.setPrecoProduto(precoProduto);
		List<ProdutoBean> listaProduto = new ProdutoResource().findLike(null, null);
		ProdutoResource produtoResource = new ProdutoResource();

		switch (operacao) {
		case Constantes.INSERE:
			for (ProdutoBean produtoBean : produtos) {
				System.out.println(produtoBean + " <--> " + produtoResource.insert(produtoBean));
			}
			break;
		case Constantes.ALTERA:
			break;
		case Constantes.DELETA:
			break;
		case Constantes.PESQUISA:
			for (int i = 0; i <= listaProduto.size(); i++) {
				System.out.println("Pesquisa Unica " + i + " <--> " + produtoResource.select(i));
			}
			break;
		default:
			System.out.println("Pesquisa Completa: ");
			for (ProdutoBean produtoBean : listaProduto) {
				System.out.println(produtoBean);
				System.out.println(produtoBean.getPrecoProduto());
			}
			break;
		}
	}

	/**
	 * @param operacao
	 * 
	 */
	private static void pedido(String operacao) {
		// TODO fazer os metodos do pedido
		switch (operacao) {
		case Constantes.INSERE:
			break;
		case Constantes.ALTERA:
			break;
		case Constantes.DELETA:
			break;
		case Constantes.PESQUISA:
			System.out.println("Pedidos Banco:  <--> " + new PedidoResource().select(1));

			break;
		default:
			break;
		}
	}
}
