package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.SistemaLanchonete.Domain.ProdutoCategoriaBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class ProdutoCategoriaService {

	private static String retorno = "";
	GenericDAO<ProdutoCategoriaBean> produtoDAO = new GenericDAO<ProdutoCategoriaBean>();
	Class<ProdutoCategoriaBean> produtoCategoriaClasse = ProdutoCategoriaBean.class;

	public String save(ProdutoCategoriaBean produto) throws BDException {
		if (produto.getCdProdutoCategoria() == 0) {
			try {
				produtoDAO.save(produto, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				produtoDAO.save(produto, produto.getCdProdutoCategoria());
			} catch (BDException e) {
				throw new BDException("Erro ao atualizar Produto " + produto.getCdProdutoCategoria() + e.getMessage(),
						EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(ProdutoCategoriaBean produtoCategoria) throws BDException {
		ProdutoCategoriaBean produtoCategoriaRetorna = produtoDAO.findById(produtoCategoriaClasse,
				produtoCategoria.getCdProdutoCategoria());
		try {
			;
			produtoDAO.remove(produtoCategoriaClasse, produtoCategoriaRetorna.getCdProdutoCategoria());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro ao remover : " + produtoCategoria.getCdProdutoCategoria() + e.getMessage(),
					EErrosBD.EXCLUI_DADO);
		}
		return retorno;
	}

	public ProdutoCategoriaBean findById(ProdutoCategoriaBean produtoCategoria) {
		return produtoDAO.findById(produtoCategoriaClasse, produtoCategoria.getCdProdutoCategoria());

	}

	public ArrayList<ProdutoCategoriaBean> findLike(ProdutoCategoriaBean produtoCategoria) {
		ArrayList<ProdutoCategoriaBean> lista = new ArrayList<ProdutoCategoriaBean>();
		List<ProdutoCategoriaBean> lista2 = produtoDAO.findLike(produtoCategoriaClasse, produtoCategoria);
		for (ProdutoCategoriaBean model2 : lista2) {
			lista.add(model2);
		}
		return lista;
	}

}
