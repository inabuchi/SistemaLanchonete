package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.SistemaLanchonete.Domain.ClienteBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;
import br.com.SistemaLanchonete.Validacao.Validacao;

public class ClienteService {
	private String retorno = "";
	GenericDAO<ClienteBean> clienteDao = new GenericDAO<ClienteBean>();
	Class<ClienteBean> clienteClasse = ClienteBean.class;

	/**
	 * Salva ou atualiza um cliente no banco de acordo com o objeto passado se id do
	 * objeto = 0 salva senao update
	 * 
	 * @param cliente
	 * @return Mensagem de retorno informando a situaÃ§Ã£o
	 * @throws BDException
	 */
	public String save(ClienteBean cliente) throws BDException {
//		if (!validaCliente(cliente)) {
//			return retorno;
//		}
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
		if (!validaCliente(cliente)) {
			return retorno;
		}
		ClienteBean clienteRetorno = clienteDao.findById(clienteClasse, cliente.getCdPessoa());
		try {
			clienteDao.remove(clienteClasse, clienteRetorno.getCdPessoa());
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
		return clienteDao.findById(clienteClasse, cliente.getCdPessoa());

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
		List<ClienteBean> lista2 = clienteDao.findLike(clienteClasse, cliente);
		for (ClienteBean model2 : lista2) {
			lista.add(model2);
		}
		return lista;

	}

	/*
	 * Validações do objeto cliente para utilizar nos metodos de DAO
	 */
	private boolean validaCliente(ClienteBean cliente) {
		// Valida objeto cliente nulo
		if (!Validacao.validaNulo(cliente)) {
			retorno = "Cliente Nulo, não pode ser inserido no banco de dados";
			return false;
		}
		// Valida objeto cliente sem dados
		if (!Validacao.validaVazio(cliente)) {
			retorno = "Cliente sem dados, não pode ser inserido no banco de dados";
			return false;
		}
		// Validar Nome Obrigatório
		if (!Validacao.testaStringNula(cliente.getDsNome()) || !Validacao.testaStringVazia(cliente.getDsNome())) {
			retorno = "Campo nome está vazio e é obrigatório";
			return false;
		}
		// Validar Telefone Obrigatório
		if (Validacao.testaStringNula(cliente.getDsTelefone1())
				|| Validacao.testaStringVazia(cliente.getDsTelefone1())) {
			retorno = "Campo Telefone esta vázio e é obrigatório";
			return false;
		}
		// Validar se os Telefones já existem na tabela de Cliente
		if (!ValidarTelefoneCliente(cliente.getDsTelefone1(), cliente.getDsTelefone2())) {
			retorno = "Telefone já existente";
			return false;
		}
		return true;
	}

	/**
	 * Validar se Telefone do Cliente já existe
	 * 
	 * @param dsTelefone1
	 * @param dsTelefone2
	 *
	 * @return Boolean
	 */
	private boolean ValidarTelefoneCliente(String telefone1, String telefone2) {
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
