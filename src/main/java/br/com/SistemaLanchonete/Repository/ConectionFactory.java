package br.com.SistemaLanchonete.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConectionFactory {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Sistema-Lanchonete");

	/**
	 * Cria uma conex�o com o banco de dados
	 * 
	 * @return EntityManager - uma conexÃ£o com o banco de dados
	 */
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	

}
