package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;
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
	Class<CaixaBean> caixaClasse = CaixaBean.class;
	GenericDAO<PedidoBean> pedidoDao = new GenericDAO<PedidoBean>();
	Class<PedidoBean> pedidoClasse = PedidoBean.class;
	GenericDAO<FormaPagamentoBean> formaPagamentoDao = new GenericDAO<FormaPagamentoBean>();
	Class<FormaPagamentoBean> formaPagamentoClasse = FormaPagamentoBean.class;

	/**
	 * Salva ou atualiza um caixa no banco de acordo com o objeto passado se id do
	 * objeto = 0 salva senao update
	 * 
	 * @param object: caixa
	 * @return String: Mensagem de retorno informando a situação
	 * @throws BDException
	 */
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

	/**
	 * Remove um caixa no banco de acordo com o objeto passado
	 * 
	 * @param Object: caixa
	 * @return String: Mensagem de retorno informando a situação
	 * @throws BDException
	 */
	public String remove(CaixaBean caixaRetorno) throws BDException {
		caixaRetorno = caixaDao.findById(caixaClasse, caixaRetorno.getCdCaixa());
		try {
			caixaDao.remove(caixaClasse, caixaRetorno.getCdCaixa());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro na remoção de dados:" + e.getMessage(), EErrosBD.EXCLUI_DADO);

		}
		return retorno;
	}

	/**
	 * Pesquisa um caixa pelo seu código de acordo com o objeto vindo da tela
	 * 
	 * @param Object: caixa
	 * @return Object: um caixa gravado no Banco de acordo com o cd do objeto passado
	 */
	public CaixaBean findById(CaixaBean cliente) {
		/*
		 * o metodo find busca por chave primaria, mas como nao tenho a anotaï¿½ï¿½o @ID
		 * no fucionario so retorna o funcinario que for igual na classe pessoa tem
		 * ainda de fazer as mensagens de retorno se nao foi encontrado o cliente
		 * precisa fazer uma query nao da para usar o mï¿½todo find do hibernate
		 */
		return caixaDao.findById(caixaClasse, cliente.getCdCaixa());

	}

	/**
	 * Retorna uma lista de caixa cadastrados de acordo com o atributo e valor
	 * passado por parametro da tela
	 * 
	 * @param Object: caixa
	 * @return ArrayList<Object>: lista de caixa
	 */
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
		List<CaixaBean> lista2 = caixaDao.findLike(caixaClasse, caixa);
		for (CaixaBean model2 : lista2) {
			listaCaixa.add(model2);
		}
		return listaCaixa;

	}
	
	/**
     * Método para fechamento de caixa
     * Obrigatório informar funcionário
     *
     * @param Object: Caixa
     * @return Double: Valor do caixa fechado
     */
	public Double fecharCaixa(CaixaBean caixa) throws Exception {
		try {
			PedidoBean pedidoBean = new PedidoBean();
			pedidoBean.setDtEmissao(Validacao.formatarData(11, new Date()));
			pedidoBean.setCdFuncionario(caixa.getFuncionario());
			List<PedidoBean> pedidoCaixa = pedidoDao.findLike(pedidoClasse, pedidoBean);
			Double result = 0.0;
			for (PedidoBean item : pedidoCaixa) {
				result += Validacao.validaDouble(item.getVlPago());
			}
			CaixaBean caixaBean = new CaixaBean();
			caixaBean.setDtAbertura(Validacao.formatarData(11, new Date()));
			List<CaixaBean> listaCaixa = caixaDao.findLike(caixaClasse, caixaBean);
			for (CaixaBean item : listaCaixa) {
				result += Validacao.validaDouble(item.getVlTrocoInicial());
			}
			return result;
		} catch (Exception e) {
			throw new BDException("Erro ao fechar caixa:" + e.getMessage(), EErrosBD.CONSULTA_DADO);
		}
	}
	
	/**
     * Método para gerar relatório de pedidos
     * separado por forma de pagamento
     *
     * @param Object: caixa
     * @return ArrayList<Object>: Retorna array de object com todos os dados do pedido,
     * separado por forma de pagamento.
	 * @throws Exception 
     */	
	public ArrayList<Object> gerarRelFechaCaixa(CaixaBean caixa) throws Exception {
		try {
			ArrayList<Object> result = new ArrayList<Object>();
			PedidoBean pedidoBean = new PedidoBean();
			pedidoBean.setDtEmissao(Validacao.formatarData(11, new Date()));			
			List<PedidoBean> pedidoCaixa = pedidoDao.findLike(pedidoClasse, pedidoBean);
			
			CaixaBean caixaBean = new CaixaBean();
			caixaBean.setDtAbertura(Validacao.formatarData(11, new Date()));
			List<CaixaBean> listaCaixa = caixaDao.findLike(caixaClasse, caixaBean);
			
			FormaPagamentoBean formaPagamentoBean = new FormaPagamentoBean();
			List<FormaPagamentoBean> listaForma = formaPagamentoDao.findLike(formaPagamentoClasse, formaPagamentoBean);
			for (CaixaBean model : listaCaixa) {
				result.add(model);
				for (FormaPagamentoBean model2 : listaForma) {
					result.add(model2);
					for (PedidoBean model3 : pedidoCaixa) {
						if (Validacao.validaInteger(model2.getCdFormaPagamento()) == Validacao.validaInteger(model3.getCdFormaPagamento())) {
							result.add(model3);
						}
					}
				}
			}
			return result;
		} catch (Exception e) {
			throw new BDException("Erro ao gerar relatório de fechamento de caixa:" + e.getMessage(), EErrosBD.CONSULTA_DADO);
		}
	}
	
	/**
     * Método para verificar se o caixa está aberto
     *
     * @return boolean: Retorna true para aberto e false para fechado
     * 
	 * @throws Exception 
     */	
	public boolean verificaCaixaAberto() throws Exception {
		try {
			String nomeCampo = "dt_fechamento";
			CaixaBean caixaBean = new CaixaBean();
			Date data = Validacao.formatarData(11, new Date());
			ArrayList<CaixaBean> listaCaixa = caixaDao.findDateNull(caixaClasse, nomeCampo);
			Boolean result = false;
			for (CaixaBean model : listaCaixa) {
				if ("".equalsIgnoreCase(Validacao.validaString(model.getDtFechamento()))) {
					result = Validacao.validaInteger(model.getCdCaixa()) == 0 ? false : true;
				}				
			}
			return result;
		} catch (Exception e) {
			throw new BDException("Erro ao verificar se o caixa está aberto:" + e.getMessage(), EErrosBD.CONSULTA_DADO);
		}
	}
	
	/**
     * Método para gerar relatório de caixa fechado ou aberto entre duas datas
     *
     * @param Object: caixa, data inicial, data final, aberturaFechamento
     * 
     * @param aberturaFechamento
     * parâmetro de aberturaFechamento recebe 1 para abertura e qualquer
     * outro numero para fechamento 
     * 
     * @return ArrayList<Object>: Retorna um ArrayList de object
     * com todos os dados do caixa
     * 
	 * @throws Exception 
     */	
	public ArrayList<Object> gerarRelCaixa(CaixaBean caixa, Date data1, Date data2, int value) throws Exception {
		try {
			String nomeCampo = (value == 1) ? "dt_abertura" : "dt_fechamento";
			
			CaixaBean caixaBean = new CaixaBean();
			caixaBean.setDtAbertura(Validacao.formatarData(11, new Date()));
			ArrayList<CaixaBean> listaCaixa = caixaDao.findDate(caixaClasse, data1, data2, nomeCampo);
			ArrayList<Object> result = new ArrayList<Object>();
			for (CaixaBean model : listaCaixa) {
				result.add(model);
			}
			return result;
		} catch (Exception e) {
			throw new BDException("Erro ao gerar relatório de Caixa:" + e.getMessage(), EErrosBD.CONSULTA_DADO);
		}
	}

}
