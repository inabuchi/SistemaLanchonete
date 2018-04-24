package br.com.SistemaLanchonete.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * Classe Genérica de Acesso ao banco de dados
 * 
 * @author Lino Pegoretti
 *
 * @param <MODEL>
 */
public class GenericDAO<MODEL> implements IDAO<MODEL> {
	private static EntityManager manager = ConectionFactory.getEntityManager();
	private String retorno = null;

	/**
	 * Salva um objeto da classe passsada como parametro no banco
	 * 
	 * @param model
	 *            - Objeto a ser salvo no banco
	 * @param id
	 *            - Identificação do registro no banco 0 = novo registro
	 * @return String - Mensagem de retorno
	 * @throws BDException
	 */
	public String save(MODEL model, int id) throws BDException {
		try {
			manager.getTransaction().begin();
			if (id == 0) {
				manager.persist(model);
				retorno = "Dados salvos com sucesso na tabela";
			} else {
				manager.merge(model);
				retorno = "Dados atualizados com sucesso na tabela";
			}
			manager.getTransaction().commit();
			manager.clear();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new BDException("Erro na atualiza��o de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		return retorno;
	}

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
	public String remove(Class<MODEL> classe, int id) throws BDException {
		MODEL modelR = findById(classe, id);
		try {
			manager.getTransaction().begin();
			manager.remove(modelR);
			manager.getTransaction().commit();
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw new BDException("Erro na remo��o de dados:" + e.getMessage(), EErrosBD.EXCLUI_DADO);

		}
		return retorno;
	}

	/**
	 * Retorna um objeto da classe passada como parametro de acordo com o id
	 * 
	 * @param classe
	 *            - Classe a ser manipulada
	 * @param id
	 *            - id do objeto a ser buscado
	 * @return MODEL - Objeto da classe de acordo com o id
	 */
	public MODEL findById(Class<MODEL> classe, int id) {
		/*
		 * o metodo find busca por chave primaria, mas como nao tenho a 
		 * anotação @ID no fucionario e no cliente so retorna o funcionário 
		 * que for igual na classe pessoa
		 * 
		 * precisa fazer uma query nao da para usar o método find do hibernate
		 */
		return manager.find(classe, id);
	}

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
	public ArrayList<MODEL> findLike(Class<MODEL> classe, MODEL model) {
		ArrayList<MODEL> lista = new ArrayList<MODEL>();
		/*
		 * Esse metodo precisa ou retornar uma lista completa do banco ou entao uma
		 * lista aproximada utilizadno o parametro LIKE Tanto faz se for List ou
		 * ArrayList, ou qq outra collection
		 */
		List<MODEL> lista2 = manager.createQuery("FROM " + classe.getSimpleName()).getResultList();
		for (MODEL model2 : lista2) {
			lista.add(model2);
		}
		return lista;

	}

}
