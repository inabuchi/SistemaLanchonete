package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.SistemaLanchonete.Domain.CaixaBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class CaixaService {
	private String retorno = "";
	GenericDAO<CaixaBean> caixaDao = new GenericDAO<CaixaBean>();
	Class<CaixaBean> caixaBean;

	public String save(CaixaBean caixa) throws BDException {
		/*
		 * TODO Fazer métodos auxiliares de validações de caixa
		 */

		if (caixa.getCdCaixa() == 0) {
			try {
				caixaDao.save(caixa, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				caixaDao.save(caixa, caixa.getCdCaixa());
			} catch (BDException e) {
				throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(CaixaBean caixaRetorno) throws BDException {
		caixaRetorno = caixaDao.findById(caixaBean, caixaRetorno.getCdCaixa());
		try {
			caixaDao.remove(caixaBean, caixaRetorno.getCdCaixa());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro na remoção de dados:" + e.getMessage(), EErrosBD.EXCLUI_DADO);

		}
		return retorno;
	}

	public CaixaBean findById(CaixaBean cliente) {
		/*
		 * o metodo find busca por chave primaria, mas como nao tenho a anotaï¿½ï¿½o @ID
		 * no fucionario so retorna o funcinario que for igual na classe pessoa tem
		 * ainda de fazer as mensagens de retorno se nao foi encontrado o cliente
		 * precisa fazer uma query nao da para usar o mï¿½todo find do hibernate
		 */
		return caixaDao.findById(caixaBean, cliente.getCdCaixa());

	}

	public ArrayList<CaixaBean> findLike(CaixaBean caixa) {
		ArrayList<CaixaBean> listaCaixa = new ArrayList<CaixaBean>();
		/*
		 * Esse metodo precisa ou retornar uma lista completa do banco ou entao uma
		 * lista aproximada utilizadno o parametro LIKE Tanto faz se for List ou
		 * ArrayList, ou qq outra collection
		 * 
		 * precisa ver como se faz para pegar os campos de pesquisa na tela e tambem os
		 * valores deles
		 */
		List<CaixaBean> lista2 = caixaDao.findLike(caixaBean, caixa);
		for (CaixaBean model2 : lista2) {
			listaCaixa.add(model2);
		}
		return listaCaixa;

	}

}
