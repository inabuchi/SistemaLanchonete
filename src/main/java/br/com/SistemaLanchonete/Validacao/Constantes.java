package br.com.SistemaLanchonete.Validacao;

public class Constantes {
	// Erros de Login
	public static final int LOGIN_COM_ERRO = -1;
	public static final int LOGIN_SEM_ERRO = 0;
	public static final int LOGIN_INVALIDO = 1;
	public static final int SESSAO_EXPIRADA = 2;
	public static final int SESSAO_INVALIDA = 3;
	public static final int SESSAO_DESLOGADA = 4;
	// Ordem de Consulta ao Banco de Dados
	public static final String ORDEM_ASCENDENTE = "ASC";
	public static final String ORDEM_DESCENDENTE = "DESC";

	// Parametros de manuten��o de dados (CRUD)
	public static final String INSERE = "INS";
	public static final String DELETA = "DEL";
	public static final String ALTERA = "UPD";
	public static final String PESQUISA = "PES";
	public static final String SELECIONA = "SEL";

	// Parametros de Logout
	public static final String LOGOUT = "SAIR";

}
