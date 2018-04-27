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

	/**
	 * Salva ou atualiza um cliente no banco de acordo com o objeto passado se id do
	 * objeto = 0 salva senao update
	 * 
	 * @param cliente
	 * @return Mensagem de retorno informando a situaÃ§Ã£o
	 * @throws BDException
	 */
	public String save(ClienteBean cliente) throws BDException {
		/*
		 * TODO Fazer métodos auxiliares de validações de cliente
		 */

		if (cliente.getCdPessoa() == 0) {
			try {
				clienteDao.save(cliente, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				clienteDao.save(cliente, cliente.getCdPessoa());
			} catch (BDException e) {
				throw new BDException("Erro na atualizaÃ§Ã£o de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	/**
	 * Remove um Cliente no banco de acordo com o objeto passado
	 * 
	 * @param cliente
	 * @return Mensagem de retorno informando a situação
	 * @throws BDException
	 */
	public String remove(ClienteBean cliente) throws BDException {
		ClienteBean clienteRetorno = clienteDao.findById(clienteBean, cliente.getCdPessoa());
		try {
			clienteDao.remove(clienteBean, clienteRetorno.getCdPessoa());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro na remoÃ§o de dados:" + e.getMessage(), EErrosBD.EXCLUI_DADO);

		}
		return retorno;
	}

	/**
	 * Pesquisa um cliente pelo seu código de acordo com o objeto vindo da tela
	 * 
	 * @param cliente
	 * @return um cliente gravado no Banco de acordo com o cd do objeto passado
	 */
	public ClienteBean findById(ClienteBean cliente) {
		/*
		 * o metodo find busca por chave primaria, mas como nao tenho a anotaï¿½ï¿½o @ID
		 * no fucionario so retorna o funcinario que for igual na classe pessoa tem
		 * ainda de fazer as mensagens de retorno se nao foi encontrado o cliente
		 * precisa fazer uma query nao da para usar o mï¿½todo find do hibernate
		 */
		System.out.println(cliente);

		return clienteDao.findById(clienteBean, cliente.getCdPessoa());

	}

	/**
	 * Retorna uma lista de clientes cadastrados de acordo com o atributo e valor
	 * passado por parametro da tela
	 * 
	 * @param cliente
	 * @return lista de clientes
	 */
	public ArrayList<ClienteBean> findLike(ClienteBean cliente) {
		ArrayList<ClienteBean> lista = new ArrayList<ClienteBean>();
		/*
		 * Esse metodo precisa ou retornar uma lista completa do banco ou entao uma
		 * lista aproximada utilizadno o parametro LIKE Tanto faz se for List ou
		 * ArrayList, ou qq outra collection
		 * 
		 * precisa ver como se faz para pegar os campos de pesquisa na tela e tambem os
		 * valores deles
		 */
		List<ClienteBean> lista2 = clienteDao.findLike(clienteBean, cliente);
		for (ClienteBean model2 : lista2) {
			lista.add(model2);
		}
		return lista;

	}

	/**
	 * Validar se Telefone do Cliente já existe
	 * 
	 * @param dsTelefone1
	 * @param dsTelefone2
	 *
	 * @return Boolean
	 */
	public Boolean ValidarTelefoneCliente(String telefone1, String telefone2) {
		// Instancia um objeto cliente
		ClienteBean cliente = new ClienteBean();

		// Busca todos os registros (mais lento)
		List<ClienteBean> listaTelefone = findLike(cliente);

		for (ClienteBean item : listaTelefone) {
			if (item.getDsTelefone1() == telefone1)
				return false;
			if (item.getDsTelefone1() == telefone2)
				return false;
			if (telefone2 == null || telefone2.trim().equals(""))
				continue;
			else {
				if (item.getDsTelefone2() == telefone1)
					return false;
				if (item.getDsTelefone2() == telefone2)
					return false;
			}
		}
		return true;
	}

}
