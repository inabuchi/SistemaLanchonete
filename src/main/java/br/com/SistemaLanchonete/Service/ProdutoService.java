package br.com.SistemaLanchonete.Service;

import br.com.SistemaLanchonete.Domain.ProdutoBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class ProdutoService {

	private static String retorno = "";
	GenericDAO<ProdutoBean> produtoDAO = new GenericDAO<ProdutoBean>();
	Class<ProdutoBean> produtoBean;

	public String save(ProdutoBean produto, int id) throws BDException {
		if (id == 0) {
			try {
				produtoDAO.save(produto, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				produtoDAO.save(produto, id);
			} catch (BDException e) {
				throw new BDException("Erro ao atualizar Produto " + produto.getcdProduto() + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(ProdutoBean produto) throws BDException {
		ProdutoBean produtoRetorna = produtoDAO.findById(produtoBean, produto.getcdProduto());
		try {;
			produtoDAO.remove(produtoBean, produtoRetorna.getcdProduto());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro ao remover : " + produto.getcdProduto()  + e.getMessage(), EErrosBD.EXCLUI_DADO);
		}
		return retorno;
	}

	public ProdutoBean findById(ProdutoBean produto) {
		return produtoDAO.findById(produtoBean, produto.getcdProduto());

	}
}
