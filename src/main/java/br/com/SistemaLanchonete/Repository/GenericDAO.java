package br.com.SistemaLanchonete.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.SistemaLanchonete.Validacao.Validacao;

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
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<MODEL> cq = cb.createQuery(classe);
		Root<MODEL> root = cq.from(classe);
		
		List<Expression<Boolean>> predicados = new ArrayList<Expression<Boolean>>();
		
		Class<?> metaClass = model.getClass();
		
		List<Field> fields = findAllFields(metaClass);
		
		for (int i = 0; i < fields.size(); ++i) {
			
			fields.get(i).setAccessible(true); // You might want to set modifier to public first.
		    Object value = null;
			try {
				value = fields.get(i).get(model);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			if(value != null) {	
				String filtro = "";
				if(value instanceof Integer && ((Integer)value) != 0)
					filtro = Integer.toString((Integer)value) ;
				else if(value instanceof String && !Validacao.validaString(value).equalsIgnoreCase(""))
					filtro = Validacao.validaString(value) ;
				else if(value instanceof Double &&((Double)value) != 0)
					filtro = Double.toString((Double)value) ;
				else if(value instanceof Float && ((Float)value) != 0)
					filtro = Float.toString((Float)value) ;
				else if(value instanceof Date)
					predicados.add(cb.between(root.<Date>get(fields.get(i).getName()), 
							Validacao.getInicioDia(((Date) value)), Validacao.getFimDia((Date) value)));

				if (!filtro.equalsIgnoreCase(""))
					predicados.add(cb.like(root.get(fields.get(i).getName()), "%" + filtro + "%"));		
			}
			
		}

		cq.where( cb.and(predicados.toArray(new Predicate[0])));
		cq.select(root);	
		
		List<MODEL> lista2 = manager.createQuery(cq).getResultList();
		for (MODEL model2 : lista2) {
			lista.add(model2);
		}
		return lista;

	}
	
	private static List<Field> findAllFields(Class<?> metaClass) {
	    List<Field[]> fields = new ArrayList<Field[]>();
	    findFields(metaClass, fields);

	    List<Field> allFields = new ArrayList<Field>();
	    for(Field[] f : fields) {
	        List<Field> asList = Arrays.asList(f);
	        allFields.addAll(asList);
	    }
	    return allFields;
	}

	private static void findFields(Class<?> metaClass2, List<Field[]> fields) {
	    Class<?> next = metaClass2;
	    while(true) {
	        Field[] f = next.getDeclaredFields();
	        fields.add(f);
	        next = next.getSuperclass();
	        if(next.equals(Object.class))
	            return;
	    }
	}
	
	public ArrayList<MODEL> findDate(Class<MODEL> classe, Date data1, Date data2, String nomeCampo) throws Exception {
		ArrayList<MODEL> lista = new ArrayList<MODEL>();
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<MODEL> cq = cb.createQuery(classe);
		Root<MODEL> root = cq.from(classe);
		
		List<Expression<Boolean>> predicados = new ArrayList<Expression<Boolean>>();
		
		if ((!"".equalsIgnoreCase(Validacao.validaString(data1)))
				&& (!"".equalsIgnoreCase(Validacao.validaString(data2)))) {		
			predicados.add(cb.between(root.<Date>get(nomeCampo),
					Validacao.getInicioDia(Validacao.formatarData(11, data1)), Validacao.getFimDia(Validacao.formatarData(11, data2))));		
		}

		cq.where( cb.and(predicados.toArray(new Predicate[0])));
		cq.select(root);	
		
		List<MODEL> lista2 = manager.createQuery(cq).getResultList();
		for (MODEL model2 : lista2) {
			lista.add(model2);
		}
		return lista;
	}
	
	public ArrayList<MODEL> findDateNull(Class<MODEL> classe, String nomeCampo) throws Exception {
		ArrayList<MODEL> lista = new ArrayList<MODEL>();
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<MODEL> cq = cb.createQuery(classe);
		Root<MODEL> root = cq.from(classe);
		
		List<Expression<Boolean>> predicados = new ArrayList<Expression<Boolean>>();
		
		predicados.add(root.<Date>get(nomeCampo).isNull());

		cq.where( cb.and(predicados.toArray(new Predicate[0])));
		cq.select(root);	
		
		List<MODEL> lista2 = manager.createQuery(cq).getResultList();
		for (MODEL model2 : lista2) {
			lista.add(model2);
		}
		return lista;
	}
}
