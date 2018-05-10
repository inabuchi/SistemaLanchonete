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

	public String save(ProdutoBean produto, int id) throws BDException {
		boolean resultado = verificaDescricaoECategoria(produto);
		if(resultado) {
			if(id == 0 ) {
				try {
					produtoDAO.save(produto, 0);
				} catch (Exception e) {
					throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
				}
				
				retorno = "Produto " + produto.getCdProduto() + " - " + produto.getDsProduto() + " adicionado com sucesso!";
				
			} else {
				try {
					produtoDAO.save(produto, id);
				} catch (BDException e) {
					throw new BDException("Erro ao atualizar Produto " + produto.getCdProduto() + e.getMessage(), EErrosBD.ATUALIZA_DADO);

				}
				
				retorno = "Produto " + produto.getCdProduto() + " - " + produto.getDsProduto() + " atualizado com sucesso!";
			}
		}
		return retorno = "Descrição de Produto & Categoria não preenchidos!";
	}
				
	public String remove(ProdutoBean produto) throws BDException {
		ProdutoBean produtoRetorna = produtoDAO.findById(produtoBean, produto.getCdProduto());
		try {
			produtoDAO.remove(produtoBean, produtoRetorna.getCdProduto());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro ao remover : " + produto.getCdProduto()  + e.getMessage(), EErrosBD.EXCLUI_DADO);
		}
		return retorno;
	}
	
	public ProdutoBean findById(ProdutoBean produto) throws BDException {
		try {
			return produtoDAO.findById(produtoBean, produto.getCdProduto());
		} catch (Exception e) {
			throw new BDException("Erro ao buscar produto " + produto.getCdProduto() + " - " + produto.getDsProduto() + e.getMessage(), EErrosBD.CONSULTA_DADO);
		}
	}
	
	public ArrayList<ProdutoBean> findLike(ProdutoBean produto) throws BDException {
		try {
			ArrayList<ProdutoBean> listaProdutos = new ArrayList<ProdutoBean>();
			return listaProdutos;
		} catch (Exception e) {
			throw new BDException("Erro ao buscar produto " + produto.getCdProduto() + " - " + produto.getDsProduto() + e.getMessage(), EErrosBD.CONSULTA_DADO);
		}
	}
	
	/**
	 * 
	 * @param produtos
	 * @return true se descri��o de produto e categoria foram preenchidos. Caso contr�rio, falso.
	 */
	public boolean verificaDescricaoECategoria(ProdutoBean produtos) {
		if(produtos.getDsProduto() != null && produtos.getDsProduto().trim().length() > 0 &&
		   produtos.getCategoria() != null && produtos.getCategoria().toString().trim().length() > 0) {
			return true;
		}
		return false;
	}
}

