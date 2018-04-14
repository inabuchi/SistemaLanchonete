package br.com.SistemaLanchonete.Repository;

public enum EErrosBD {
	ABRE_CONEXAO("Erro ao abrir conex�o com o banco de dados"), //
	CRIA_CONEXAO("Erro ao criar a conex�o com o banco de dados"), //
	FECHA_CONEXAO("Erro ao fechar a conex�o com o banco de dados"), //
	ACESSA_DRIVER("Erro ao carregar o driver do banco de dados."), ACESSO_BANCO(
			"Erro ao acessar o banco de dados do sistema"), //
	MONTA_SQL("Erro na prepara��o do banco de dados do sistema"), //
	CRIA_TABELA("Erro ao criar a tabela especificada"), //
	DROPA_TABELA("Erro ao eliminar a tabela especificada"), //
	INSERE_DADO("Erro ao inserir dados na tabela especificada"), //
	ROLLBACK("Erro ao desfazer as altera��es no banco. Entre em contato com o DBA"), //
	CONSULTA_DADO("Erro ao consultar dados na tabela especificada"), //
	ATUALIZA_DADO("Erro ao atualizar os dados na tabela especificada"), //
	EXCLUI_DADO("Erro ao excluir os dados da tabela especificada"); //
	private final String erro;

	public String getErro() {
		return erro;
	}

	private EErrosBD(String erro) {
		this.erro = erro;
	}
}
