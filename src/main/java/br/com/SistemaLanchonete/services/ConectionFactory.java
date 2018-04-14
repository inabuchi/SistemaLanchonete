package br.com.SistemaLanchonete.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConectionFactory {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Sistema-Lanchonete");

	/**
	 * Cria uma conexão com o banco de dados
	 * 
	 * @return EntityManager - uma conexão com o banco de dados
	 */
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	

}
