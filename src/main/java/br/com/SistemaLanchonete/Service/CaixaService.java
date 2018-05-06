package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.com.SistemaLanchonete.Domain.CaixaBean;
import br.com.SistemaLanchonete.Domain.FormaPagamentoBean;
import br.com.SistemaLanchonete.Domain.PedidoBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;
import br.com.SistemaLanchonete.Validacao.Validacao;

public class CaixaService {
	private String retorno = "";
	GenericDAO<CaixaBean> caixaDao = new GenericDAO<CaixaBean>();
	Class<CaixaBean> caixaBean;
	GenericDAO<PedidoBean> pedidoDao = new GenericDAO<PedidoBean>();
	Class<FormaPagamentoBean> formaPagamentoBean;
	GenericDAO<FormaPagamentoBean> formaPagamentoDao = new GenericDAO<FormaPagamentoBean>();

	public String save(CaixaBean caixa) throws BDException {
		if (caixa.getCdCaixa() == 0) {
			if (Validacao.validaDouble(caixa.getVlTrocoInicial()) == 0.0) {
				retorno = "Não é permitido inserir valor inicial igual a zero";
			} else {
				try {
					caixa.setDtAbertura(new Date());
					caixaDao.save(caixa, 0);
				} catch (BDException e) {
					throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
				}
				retorno = "Dados salvos com sucesso na tabela";
			}
		} else {
			try {
				caixa.setDtFechamento(new Date());
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
	
	/**
     * Método para fechamento de caixa
     * Obrigatório informar funcionário
     *
     * @param Objeto
     * @return Double
     */
	public Double fecharCaixa(CaixaBean caixa) throws Exception {
		try {
			PedidoBean pedidoBean = new PedidoBean();
			pedidoBean.setDtEmissao(Validacao.formatarData(2, new Date()));
			pedidoBean.setCdFuncionario(caixa.getFuncionario());
			List<PedidoBean> pedidoCaixa = pedidoDao.findLike(PedidoBean.class, pedidoBean);
			Double result = 0.0;
			for (PedidoBean item : pedidoCaixa) {
				result += Validacao.validaDouble(item.getVlPago());
			}
			CaixaBean caixaBean = new CaixaBean();
			caixaBean.setDtAbertura(Validacao.formatarData(2, new Date()));
			List<CaixaBean> listaCaixa = caixaDao.findLike(CaixaBean.class, caixaBean);
			for (CaixaBean item : listaCaixa) {
				result += Validacao.validaDouble(item.getVlTrocoInicial());
			}
			return result;
		} catch (Exception e) {
			throw new BDException("Erro no fechamento do caixa:" + e.getMessage(), EErrosBD.CONSULTA_DADO);
		}
	}
	
	/**
     * Método para gerar relatório
     *
     * @param Objeto
     * @return ArrayList<String>
	 * @throws Exception 
     */
	public ArrayList<String> gerarRelatorio(CaixaBean caixa) throws Exception {
		try {
			ArrayList<String> result = new ArrayList<String>();
			PedidoBean pedidoBean = new PedidoBean();
			pedidoBean.setDtEmissao(Validacao.formatarData(2, new Date()));
			List<PedidoBean> pedidoCaixa = pedidoDao.findLike(PedidoBean.class, pedidoBean);
			
			CaixaBean caixaBean = new CaixaBean();
			caixaBean.setDtAbertura(Validacao.formatarData(2, new Date()));
			Collection<CaixaBean> listaCaixa = caixaDao.findLike(CaixaBean.class, caixaBean);
			
			FormaPagamentoBean formaPagamentoBean = new FormaPagamentoBean();			
			for (CaixaBean model : listaCaixa) {			
				result.add(Validacao.validaString(model));
				for (PedidoBean model2 : pedidoCaixa) {
					result.add(Validacao.validaString(model2));
					formaPagamentoBean.setCdFormaPagamento(Validacao.validaInteger(pedidoBean.getCdFormaPagamento()));
					List<FormaPagamentoBean> formaPagameto = formaPagamentoDao.findLike(FormaPagamentoBean.class, formaPagamentoBean);
					for (FormaPagamentoBean model3 : formaPagameto) {						
						result.add(Validacao.validaString(model3.getDsFormaPagamento()));
					}
				}
			}			
			return result;
		} catch (Exception e) {
			throw new BDException("Erro ao gerar relatório:" + e.getMessage(), EErrosBD.CONSULTA_DADO);
		}
	}

}
