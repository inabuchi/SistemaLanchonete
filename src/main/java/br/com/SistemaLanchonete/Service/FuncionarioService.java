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

	public String save(FuncionarioBean funcionario, int id) throws BDException {
		if (id == 0) {
			try {
				funcionarioDao.save(funcionario, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				funcionarioDao.save(funcionario, id);
			} catch (BDException e) {
				throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(FuncionarioBean funcionario) {
		FuncionarioBean funcionarioRetorno = funcionarioDao.findById(funcionarioBean, funcionario.getCdFuncionario());
		try {
			funcionarioDao.remove(funcionarioBean, funcionarioRetorno.getCdPessoa());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			// throw new BDException("Erro na remoï¿½ï¿½o de dados:" + e.getMessage(),
			// EErrosBD.EXCLUI_DADO);

		}
		return retorno;
	}

	public FuncionarioBean findById(FuncionarioBean funcionario) {
		/*
		 * o metodo find busca por chave primaria, mas como nao tenho a anotaï¿½ï¿½o @ID
		 * no fucionario so retorna o funcinario que for igual na classe pessoa
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
}