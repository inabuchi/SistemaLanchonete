package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;

import br.com.SistemaLanchonete.Domain.ProdutoBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class ProdutoService {

	private static String retorno = "";
	GenericDAO<ProdutoBean> produtoDAO = new GenericDAO<ProdutoBean>();
	Class<ProdutoBean> produtoBean = ProdutoBean.class;

	public String save(ProdutoBean produto) throws BDException {
		if (verificaDescricaoECategoria(produto)) {
			if (produto.getCdProduto() == 0) {
				try {
					produtoDAO.save(produto, 0);
				} catch (Exception e) {
					throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
				}
				retorno = "Produto " + produto.getCdProduto() + " - " + produto.getDsProduto()
						+ " adicionado com sucesso!";
			} else {
				try {
					produtoDAO.save(produto, produto.getCdProduto());
				} catch (BDException e) {
					throw new BDException("Erro ao atualizar Produto " + produto.getCdProduto() + e.getMessage(),
							EErrosBD.ATUALIZA_DADO);

				}

				retorno = "Produto " + produto.getCdProduto() + " - " + produto.getDsProduto()
						+ " atualizado com sucesso!";
			}
		}
		return retorno = "Descri��o de Produto & Categoria n�o preenchidos!";
	}

	public String remove(ProdutoBean produto) throws BDException {
		ProdutoBean produtoRetorna = produtoDAO.findById(produtoBean, produto.getCdProduto());
		try {
			produtoDAO.remove(produtoBean, produtoRetorna.getCdProduto());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro ao remover : " + produto.getCdProduto() + e.getMessage(), EErrosBD.EXCLUI_DADO);
		}
		return retorno;
	}

	public ProdutoBean findById(ProdutoBean produto) {
		return produtoDAO.findById(produtoBean, produto.getCdProduto());
	}

	public ArrayList<ProdutoBean> findLike(ProdutoBean produto) {
		return produtoDAO.findLike(produtoBean, produto);
	}

	/**
	 * 
	 * @param produtos
	 * @return true se descri��o de produto e categoria foram preenchidos. Caso
	 *         contr�rio, falso.
	 */
	public boolean verificaDescricaoECategoria(ProdutoBean produtos) {
//		if (produtos.getDsProduto() != null && produtos.getDsProduto().trim().length() > 0
//				&& produtos.getCategoria() != null && produtos.getCategoria().toString().trim().length() > 0) {
//			return true;
//		}
		return true;
	}
}
