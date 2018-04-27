package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;

import br.com.SistemaLanchonete.Domain.ProdutoBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class ProdutoService {

	private static String retorno = "";
	GenericDAO<ProdutoBean> produtoDAO = new GenericDAO<ProdutoBean>();
	Class<ProdutoBean> produtoBean;

	public String save(ProdutoBean produto) throws BDException {
		if (produto.getCdProduto() == 0) {
			try {
				produtoDAO.save(produto, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				produtoDAO.save(produto, produto.getCdProduto());
			} catch (BDException e) {
				throw new BDException("Erro ao atualizar Produto " + produto.getCdProduto() + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(ProdutoBean produto) throws BDException {
		ProdutoBean produtoRetorna = produtoDAO.findById(produtoBean, produto.getCdProduto());
		try {;
			produtoDAO.remove(produtoBean, produtoRetorna.getCdProduto());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro ao remover : " + produto.getCdProduto()  + e.getMessage(), EErrosBD.EXCLUI_DADO);
		}
		return retorno;
	}

	public ProdutoBean findById(ProdutoBean produto) {
		return produtoDAO.findById(produtoBean, produto.getCdProduto());

	}

	public ArrayList<ProdutoBean> findLike(ProdutoBean produto) {
		// TODO Auto-generated method stub
		return null;
	}
}
