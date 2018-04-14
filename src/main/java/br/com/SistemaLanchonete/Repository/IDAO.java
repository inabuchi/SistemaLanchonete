package br.com.SistemaLanchonete.Repository;

import java.util.List;

public interface IDAO<MODEL> {
	/**
	 * Salva um objeto da classe passsada como parametro no banco
	 * 
	 * @param model
	 *            - Objeto a ser salvo no banco
	 * @param id
	 *            - Identifica��o do registro no banco 0 = novo registro
	 * @return String - Mensagem de retorno
	 * @throws BDException
	 */
	public abstract String save(MODEL model, int id) throws BDException;

	/**
	 * Apaga um objeto da classe passada como parametro no banco
	 * 
	 * @param classe
	 *            - Classe a ser manipulada
	 * @param id
	 *            - id do objeto a ser deletado
	 * @return String - Mensagem de retorno
	 * @throws BDException
	 */
	public abstract String remove(Class<MODEL> classe, int id) throws BDException;

	/**
	 * Retorna um objeto da classe passada como parametro de acordo com o id
	 * 
	 * @param classe
	 *            - Classe a ser manipulada
	 * @param id
	 *            - id do objeto a ser buscado
	 * @return MODEL - Objeto da classe de acordo com o id
	 */
	public abstract MODEL findById(Class<MODEL> classe, int id);

	/**
	 * Retorna uma lista de objetos da classe passada como parametro de acordo com o
	 * objeto passado como parametro
	 * 
	 * @param classe
	 *            - Classe a ser manipulada
	 * @param model
	 *            - Objeto para pesquisa no banco
	 * @return List - Lista de objetos achados com o objeto passado como parametro
	 */
	public abstract List<MODEL> findLike(Class<MODEL> classe, MODEL model);
}
