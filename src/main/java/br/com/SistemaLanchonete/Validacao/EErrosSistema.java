package br.com.SistemaLanchonete.Validacao;

public enum EErrosSistema {
	CODIGO_NULO("C�digo � nulo ou num formato inv�lido! Somente inteiros s�o v�lidos."), //
	CODIGO_INVALIDO("O c�digo informado n�o � v�lido!"), //
	DATA_INVALIDA("Digite uma data valida no formato DD/MM/YYYY"), //
	VALOR_DECIMAL_INVALIDO("Digite um n�mero v�lido"), //
	VALOR_INTEIRO_INVALIDO("Digite um n�mero Inteiro"), //
	FORNECEDOR_INVALIDO("Fornecedor n�o cadastrado, tente novamente"), //
	CLIENTE_INVALIDO("Cliente n�o cadastrado, tente novamente"), //
	PESQUISA_INVALIDA("N�o foi possivel realizar a pesquisa solicitada"), //
	COMPRA_NAO_LOCALIZADA("Pedido de Compra n�o existe"), //
	COMPRA_INCOMPLETA("Pedido de compra n�o foi encerrado corretamente"), //
	PRODUTO_NAO_LOCALIZADO("Produto n�o localizado no cadastro"),//
	OPCAO_INVALIDA("Parametro escolhido incorreto");
	private final String erro;

	public String getErro() {
		return erro;
	}

	private EErrosSistema(String erro) {
		this.erro = erro;
	}
}
