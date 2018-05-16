package br.com.SistemaLanchonete.Repository;

public enum ETipoCampo {
	INT("int"), //
	STRING("String"), //
	DOUBLE("double"), //
	BOOLEAN("boolean"), //
	DATE("Date");

	private final String descricao;

	/**
	 * Captura o valor contido no parametro descricao
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Construtor da Classe
	 *
	 * @param descricao
	 */
	private ETipoCampo(String descricao) {
		this.descricao = descricao;
	}

}
