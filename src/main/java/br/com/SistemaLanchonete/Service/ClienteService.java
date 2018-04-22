package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.SistemaLanchonete.Domain.ClienteBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class ClienteService {
	private String retorno = "";
	GenericDAO<ClienteBean> clienteDao = new GenericDAO<ClienteBean>();
	Class<ClienteBean> clienteBean;

	public String save(ClienteBean cliente, int id) throws BDException {
		if (id == 0) {
			try {
				// aqui precisa validar os dados que vem da tela
				clienteDao.save(cliente, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				clienteDao.save(cliente, id);
			} catch (BDException e) {
				throw new BDException("Erro na atualiza��o de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(ClienteBean cliente) {
		ClienteBean clienteRetorno = clienteDao.findById(clienteBean, cliente.getCdPessoa());
		try {
			clienteDao.remove(clienteBean, clienteRetorno.getCdPessoa());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			// throw new BDException("Erro na remo��o de dados:" + e.getMessage(),
			// EErrosBD.EXCLUI_DADO);

		}
		return retorno;
	}

	public ClienteBean findById(ClienteBean cliente) {
		/*
		 * o metodo find busca por chave primaria, mas como nao tenho a anota��o @ID
		 * no fucionario so retorna o funcinario que for igual na classe pessoa
		 * 
		 * precisa fazer uma query nao da para usar o m�todo find do hibernate
		 */

		return clienteDao.findById(clienteBean, cliente.getCdPessoa());

	}

	public ArrayList<ClienteBean> findLike(ClienteBean cliente) {
		ArrayList<ClienteBean> lista = new ArrayList<ClienteBean>();
		/*
		 * Esse metodo precisa ou retornar uma lista completa do banco ou entao uma
		 * lista aproximada utilizadno o parametro LIKE Tanto faz se for List ou
		 * ArrayList, ou qq outra collection
		 */
		List<ClienteBean> lista2 = clienteDao.findLike(clienteBean, cliente);
		for (ClienteBean model2 : lista2) {
			lista.add(model2);
		}
		return lista;

	}

}
