package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.SistemaLanchonete.Domain.FuncionarioBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

/*
 * TODO Essa classe tem de ser refeita de acordo com a classe ClienteService
 * Criar um m�todo somente para validar login, temos de definir como vai ser feito
 * talvez fazer um novo rest de Login e mandar para um metodo aqui
 * esperar o Oldenburg colcoar o controle de sess�o antes de acertar isso
 */
public class FuncionarioService {
	private String retorno = "";
	GenericDAO<FuncionarioBean> funcionarioDao = new GenericDAO<FuncionarioBean>();
	Class<FuncionarioBean> funcionarioClasse = FuncionarioBean.class;

	public String save(FuncionarioBean funcionario) throws BDException {
		/*
		 * Valida o telefone do funcionario
		 */
		// Validar Nome Obrigat�rio
		if (funcionario.getDsNome().trim() == null || funcionario.getDsNome().trim().length() == 0)
			return (retorno = "Campo nome � obrigat�rio");
		// Validar Login Obrigat�rio
		if (funcionario.getDsLogin().trim() == null || funcionario.getDsLogin().trim().length() == 0)
			return (retorno = "Campo Login � obrigat�rio");
		// Validar Senha Obrigat�rio
		if (funcionario.getDsSenha().trim() == null || funcionario.getDsSenha().trim().length() == 0)
			return (retorno = "Campo Senha � obrigat�rio");

		boolean validado = validarTelefoneFuncionario(funcionario.getDsTelefone1(), funcionario.getDsTelefone1());
		if (!validado) {
			retorno = "Funcion�rio com telefones inv�lidos";
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
				throw new BDException("Erro na atualiza��o de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(FuncionarioBean funcionario) throws BDException {
		FuncionarioBean funcionarioRetorno = funcionarioDao.findById(funcionarioClasse, funcionario.getCdFuncionario());
		try {
			funcionarioDao.remove(funcionarioClasse, funcionarioRetorno.getCdPessoa());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro na remo��o de dados:" + e.getMessage(), EErrosBD.EXCLUI_DADO);

		}
		return retorno;
	}

	public FuncionarioBean findById(FuncionarioBean funcionario) {
		return funcionarioDao.findById(funcionarioClasse, funcionario.getCdPessoa());
	}

	public ArrayList<FuncionarioBean> findLike(FuncionarioBean funcionario) {
		ArrayList<FuncionarioBean> lista = new ArrayList<FuncionarioBean>();
		/*
		 * Esse metodo precisa ou retornar uma lista completa do banco ou entao uma
		 * lista aproximada utilizadno o parametro LIKE Tanto faz se for List ou
		 * ArrayList, ou qq outra collection
		 */
		List<FuncionarioBean> lista2 = funcionarioDao.findLike(funcionarioClasse, funcionario);
		for (FuncionarioBean model2 : lista2) {
			lista.add(model2);
		}
		return lista;
	}

	/**
	 * Validar se Telefone do Funcionario j� existe
	 *
	 * @param dsTelefone1
	 * @param dsTelefone2
	 *
	 * @return Boolean
	 */
	public Boolean validarTelefoneFuncionario(String telefone1, String telefone2) {
		// Instancia um objeto funcion�rio
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
	 * Validar Login � valido
	 *
	 * @param login
	 * @param senha
	 *
	 * @return int
	 */
	public int validarLogin(String login, String senha) {
		// Instancia um objeto funcionario
		FuncionarioBean funcionario = new FuncionarioBean();

		// Carregar o objeto funcion�rio
		funcionario.setDsLogin(login);
		funcionario.setDsSenha(senha);

		// Buscar na tabela pelo usu�rio e senha
		// Deve retornar o n�vel se deu certo, caso contr�rio retorna nulo
		// funcionario = funcionarioDao.ValidarLogin(funcionario);

		// Se objeto retornar nulo n�o foi encontrado.
		// if (funcionario == null)
		//	return 0;
		return funcionario.getCdNivel();

	}
}