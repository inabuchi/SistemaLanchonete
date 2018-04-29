package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.SistemaLanchonete.Domain.FuncionarioBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class FuncionarioService {
	private String retorno = "";
	GenericDAO<FuncionarioBean> funcionarioDao = new GenericDAO<FuncionarioBean>();
	Class<FuncionarioBean> funcionarioBean;

	public String save(FuncionarioBean funcionario) throws BDException {
		/*
		 * Valida o telefone do funcionario
		 */
		boolean validado = validarTelefoneFuncionario(funcionario.getDsTelefone1(), funcionario.getDsTelefone1());
		if (!validado) {
			retorno = "Funcionário com telefones inválidos";
		}
		if (funcionario.getCdPessoa() == 0) {
			try {
				funcionarioDao.save(funcionario, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				funcionarioDao.save(funcionario, funcionario.getCdPessoa());
			} catch (BDException e) {
				throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(FuncionarioBean funcionario) throws BDException {
		FuncionarioBean funcionarioRetorno = funcionarioDao.findById(funcionarioBean, funcionario.getCdFuncionario());
		try {
			funcionarioDao.remove(funcionarioBean, funcionarioRetorno.getCdPessoa());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro na remoção de dados:" + e.getMessage(), EErrosBD.EXCLUI_DADO);

		}
		return retorno;
	}

	public FuncionarioBean findById(FuncionarioBean funcionario) {
		/*
		 * o metodo find busca por chave primaria, mas como nao tenho a anotaï¿½ï¿½o
		 * 
		 * @ID no fucionario so retorna o funcinario que for igual na classe pessoa
		 *
		 * precisa fazer uma query nao da para usar o mï¿½todo find do hibernate
		 */
		return funcionarioDao.findById(funcionarioBean, funcionario.getCdFuncionario());

	}

	public ArrayList<FuncionarioBean> findLike(FuncionarioBean funcionario) {
		ArrayList<FuncionarioBean> lista = new ArrayList<FuncionarioBean>();
		/*
		 * Esse metodo precisa ou retornar uma lista completa do banco ou entao uma
		 * lista aproximada utilizadno o parametro LIKE Tanto faz se for List ou
		 * ArrayList, ou qq outra collection
		 */
		List<FuncionarioBean> lista2 = funcionarioDao.findLike(funcionarioBean, funcionario);
		for (FuncionarioBean model2 : lista2) {
			lista.add(model2);
		}
		return lista;
	}

	/**
	 * Validar se Telefone do Funcionario já existe
	 *
	 * @param dsTelefone1
	 * @param dsTelefone2
	 *
	 * @return Boolean
	 */
	public Boolean validarTelefoneFuncionario(String telefone1, String telefone2) {
		// Instancia um objeto funcionário
		FuncionarioBean funcionario = new FuncionarioBean();

		// Busca todos os registros (mais lento)
		List<FuncionarioBean> listaTelefone = findLike(funcionario);

		for (FuncionarioBean item : listaTelefone) {
			if (item.getDsTelefone1() == telefone1)
				return false;
			if (item.getDsTelefone1() == telefone2)
				return false;
			if (telefone2 == null || telefone2.trim().equals(""))
				continue;
			else {
				if (item.getDsTelefone1() == telefone1)
					return false;
				if (item.getDsTelefone1() == telefone2)
					return false;
			}
		}
		return true;
	}

	/**
	 * Validar Login é valido
	 *
	 * @param login
	 * @param senha
	 *
	 * @return int
	 */
	public int validarLogin(String login, String senha) {
		// Instancia um objeto funcionario
		FuncionarioBean funcionario = new FuncionarioBean();

		// Carregar o objeto funcionário
		funcionario.setDsLogin(login);
		funcionario.setDsSenha(senha);

		// Buscar na tabela pelo usuário e senha
		// Deve retornar o nível se deu certo, caso contrário retorna nulo
		// funcionario = funcionarioDao.ValidarLogin(funcionario);

		// Se objeto retornar nulo não foi encontrado.
		if (funcionario == null)
			return 0;
		return funcionario.getCdNivel();

	}
}