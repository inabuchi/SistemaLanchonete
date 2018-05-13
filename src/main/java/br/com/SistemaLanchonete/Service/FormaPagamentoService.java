package br.com.SistemaLanchonete.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;

import br.com.SistemaLanchonete.Domain.FormaPagamentoBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;
import br.com.SistemaLanchonete.Validacao.Validacao;

//TODO tem de verificar se essa classe está ok
public class FormaPagamentoService {

	private static String retorno = "";
	GenericDAO<FormaPagamentoBean> formaPagamentoDAO = new GenericDAO<FormaPagamentoBean>();
	Class<FormaPagamentoBean> formaPagamentoClasse = FormaPagamentoBean.class;
	Field[] CAMPOS = FormaPagamentoBean.class.getDeclaredFields();

	// CAMPOS[0] --> FormaPagamentoBean --> serialVersionUID
	// CAMPOS[1] --> FormaPagamentoBean --> cdFormaPagamento
	// CAMPOS[2] --> FormaPagamentoBean --> dsFormaPagamento

	public String save(FormaPagamentoBean formaPagamento) throws BDException {
		if (!validaCategoria(formaPagamento)) {
			return retorno;
		}
		if (formaPagamento.getCdFormaPagamento() == 0) {
			try {
				formaPagamentoDAO.save(formaPagamento, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				formaPagamentoDAO.save(formaPagamento, formaPagamento.getCdFormaPagamento());
			} catch (BDException e) {
				throw new BDException(
						"Erro ao atualizar Produto " + formaPagamento.getCdFormaPagamento() + e.getMessage(),
						EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(FormaPagamentoBean formaPagamento) throws BDException {
		FormaPagamentoBean formaPagamentoRetorna = formaPagamentoDAO.findById(formaPagamentoClasse,
				formaPagamento.getCdFormaPagamento());
		try {
			formaPagamentoDAO.remove(formaPagamentoClasse, formaPagamentoRetorna.getCdFormaPagamento());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro ao remover : " + formaPagamento.getCdFormaPagamento() + e.getMessage(),
					EErrosBD.EXCLUI_DADO);
		}
		return retorno;
	}

	public FormaPagamentoBean findById(FormaPagamentoBean formaPagamento) {
		return formaPagamentoDAO.findById(formaPagamentoClasse, formaPagamento.getCdFormaPagamento());
	}

	public ArrayList<FormaPagamentoBean> findLike(FormaPagamentoBean formaPagamento) {
		return formaPagamentoDAO.findLike(formaPagamentoClasse, formaPagamento);
	}

	public boolean validaCategoria(FormaPagamentoBean formaPagamento) {
		// Valida objeto formaPagamento nulo
		if (!Validacao.validaNulo(formaPagamento)) {
			retorno = "Categoria Nula, não pode ser inserido no banco de dados";
			return false;
		}
		// Validar descrição Obrigatório
		formaPagamento.setDsFormaPagamento(formaPagamento.getDsFormaPagamento().trim());
		try {
			Validacao.validaAtributoNulo(formaPagamento, CAMPOS[2]);
			Validacao.validaAtributoVazio(formaPagamento, CAMPOS[2]);
		} catch (Exception e) {
			retorno = "Campo descrição está vazio e é obrigatório";
		}
		// Validar forma de Pagamento duplicada
		ArrayList<FormaPagamentoBean> lista = findLike(formaPagamento);
		if (!lista.isEmpty()) {
			retorno = "Categoria com descrição já cadastrada";
			return false;
		}
		return true;
	}

}
