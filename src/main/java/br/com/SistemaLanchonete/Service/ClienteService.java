package br.com.SistemaLanchonete.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.SistemaLanchonete.Domain.ClienteBean;
import br.com.SistemaLanchonete.Domain.PessoaBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;
import br.com.SistemaLanchonete.Validacao.Validacao;

// TODO testar toda a classe se esta cumprindo com os requisitos de analise
public class ClienteService {
	private static String retorno = "";
	GenericDAO<ClienteBean> clienteDao = new GenericDAO<ClienteBean>();
	Class<ClienteBean> clienteClasse = ClienteBean.class;
	Field[] CAMPOSPESSOA = PessoaBean.class.getDeclaredFields();
	// CAMPOSPESSOA[0] --> PessoaBean --> serialVersionUID
	// CAMPOSPESSOA[1] --> PessoaBean --> cdPessoa
	// CAMPOSPESSOA[2] --> PessoaBean --> dsNome
	// CAMPOSPESSOA[3] --> PessoaBean --> dsTelefone1
	// CAMPOSPESSOA[4] --> PessoaBean --> dsTelefone2
	// CAMPOSPESSOA[5] --> PessoaBean --> dtCadastro
	// CAMPOSPESSOA[6] --> PessoaBean --> isAtivo
	// CAMPOSPESSOA[7] --> PessoaBean --> enderecoPessoas
	Field[] CAMPOSCLIENTE = ClienteBean.class.getDeclaredFields();
	// CAMPOSCLIENTE[0] --> ClienteBean --> serialVersionUID
	// CAMPOSCLIENTE[1] --> ClienteBean --> cdCliente
	// CAMPOSCLIENTE[2] --> ClienteBean --> dsObservacao
	// CAMPOSCLIENTE[3] --> ClienteBean --> pedidos

	/**
	 * Salva ou atualiza um cliente no banco de acordo com o objeto passado por
	 * parametro<br>
	 * se id do objeto = 0 salva um novo registro senao faz update
	 * 
	 * @param cliente
	 * @return Mensagem de retorno informando a situação
	 * @throws BDException
	 */
	public String save(ClienteBean cliente) throws BDException {
		//Se tiver esse if, nunca vai atualizar o telefone de um cliente que já existe,
		//pois sempre que existe o telefone ele já cai no return
		//if (!validaCliente(cliente)) {
		//	return retorno;
		//}
		if (!validaCliente(cliente) && (cliente.getCdPessoa() == 0)) {
			return retorno;
		}
		
		if (cliente.getCdPessoa() == 0) {
			try {
				clienteDao.save(cliente, 0);
				
				ValidarTelefoneCliente(cliente); //Busca o código em que foi inserido o cliente

				SaveEndereco(cliente);		
				
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				//ERRO AQUI
				clienteDao.save(cliente, cliente.getCdPessoa());
				
				SaveEndereco(cliente);	
				
			} catch (BDException e) {
				throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
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
			throw new BDException("Erro na remoção de dados:" + e.getMessage(), EErrosBD.EXCLUI_DADO);

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
		return clienteDao.findLike(clienteClasse, cliente);
	}

	/**
	 * Validações do objeto cliente para utilizar nos metodos de DAO
	 * 
	 * @param cliente
	 * @return
	 * @throws Exception
	 */
	public boolean validaCliente(ClienteBean cliente) {
		// Valida objeto cliente nulo
		if (!Validacao.validaNulo(cliente)) {
			retorno = "Cliente Nulo, não pode ser inserido no banco de dados";
			return false;
		}
		// Validar Nome Obrigatório
		try {
			Validacao.validaAtributoNulo(cliente, CAMPOSPESSOA[2]);
			Validacao.validaAtributoVazio(cliente, CAMPOSPESSOA[2]);
		} catch (Exception e) {
			retorno = "Campo nome está vazio e é obrigatório";
		}
		// Validar Telefone Obrigatório
		try {
			Validacao.validaAtributoNulo(cliente, CAMPOSPESSOA[3]);
			Validacao.validaAtributoVazio(cliente, CAMPOSPESSOA[3]);
		} catch (Exception e) {
			retorno = "Campo Telefone esta vazio e é obrigatório";
		}
		// Validar se os Telefones já existem na tabela de Cliente
		if (!ValidarTelefoneCliente(cliente)) {
			retorno = "Telefone já existente";
			return false;
		}
		return true;
	}

	/**
	 * Validar se Telefone do Cliente já existe
	 * 
	 * @param cliente
	 *            - um cliente para testar os telefones
	 *
	 * @return boolean - true se nao for achado duplicado no banco
	 */
	private boolean ValidarTelefoneCliente(ClienteBean cliente) {
		/*
		 * Instancia um novo cliente somente com o telefone passado por parametro e faz
		 * a pesquisa no bd
		 */
		List<ClienteBean> listaTelefone = findLike(
				new ClienteBean(0, null, cliente.getDsTelefone1(), null, null, true, 0, null));
		if (!listaTelefone.isEmpty()) {
			/*
			 * faz as validações somente se a lista tiver algum conteudo tem de testar se eh
			 * so isso que precisa no telefone
			 */
			for (ClienteBean clienteLista : listaTelefone) {
				if (clienteLista.getDsTelefone1().equals(cliente.getDsTelefone1())) {
					cliente.setCdPessoa(clienteLista.getCdPessoa());
					return false;
				}else
				if (clienteLista.getDsTelefone1().equals(cliente.getDsTelefone2())) {
					cliente.setCdPessoa(clienteLista.getCdPessoa());					
					return false;
				}else
				if (cliente.getDsTelefone1() == null || cliente.getDsTelefone2().trim().equals(""))
					continue;
				else {
					if (clienteLista.getDsTelefone2().equals(cliente.getDsTelefone1())) {
						cliente.setCdPessoa(clienteLista.getCdPessoa());						
						return false;
					}else
					if (clienteLista.getDsTelefone2().equals(cliente.getDsTelefone2())) {
						cliente.setCdPessoa(clienteLista.getCdPessoa());						
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private boolean SaveEndereco(ClienteBean cliente) {
		EnderecoService endSer = new EnderecoService();
		
		for (int i = 0; i < cliente.getEnderecoPessoas().size(); i++) {
			try {
				endSer.save(cliente.getEnderecoPessoas().get(i), cliente);				
				
			} catch(Exception e) {
				return false;
			}			
		}	
		
		return true;
	}
}
