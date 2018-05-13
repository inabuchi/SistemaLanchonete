package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;

import br.com.SistemaLanchonete.Domain.ProdutoAdicionalBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class ProdutoAdicionalService {

	private static String retorno = "";
	GenericDAO<ProdutoAdicionalBean> produtoDAO = new GenericDAO<ProdutoAdicionalBean>();
	Class<ProdutoAdicionalBean> produtoAdicionalClasse = ProdutoAdicionalBean.class;

	public String save(ProdutoAdicionalBean produtoAdicional) throws BDException {
		if (produtoAdicional.getCdProdutoAdicional() == 0) {
			try {
				produtoDAO.save(produtoAdicional, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				produtoDAO.save(produtoAdicional, produtoAdicional.getCdProdutoAdicional());
			} catch (BDException e) {
				throw new BDException(
						"Erro ao atualizar Produto " + produtoAdicional.getCdProdutoAdicional() + e.getMessage(),
						EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(ProdutoAdicionalBean produtoAdicional) throws BDException {
		ProdutoAdicionalBean produtoAdicionalRetorna = produtoDAO.findById(produtoAdicionalClasse,
				produtoAdicional.getCdProdutoAdicional());
		try {
			produtoDAO.remove(produtoAdicionalClasse, produtoAdicionalRetorna.getCdProdutoAdicional());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro ao remover : " + produtoAdicional.getCdProdutoAdicional() + e.getMessage(),
					EErrosBD.EXCLUI_DADO);
		}
		return retorno;
	}

	public ProdutoAdicionalBean findById(ProdutoAdicionalBean produtoAdicional) {
		return produtoDAO.findById(produtoAdicionalClasse, produtoAdicional.getCdProdutoAdicional());
	}

	public ArrayList<ProdutoAdicionalBean> findLike(ProdutoAdicionalBean produtoAdicional) {
		return produtoDAO.findLike(produtoAdicionalClasse, produtoAdicional);
	}

}
