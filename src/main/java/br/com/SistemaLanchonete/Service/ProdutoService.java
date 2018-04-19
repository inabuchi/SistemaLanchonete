package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;

import br.com.SistemaLanchonete.Domain.ProdutoBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class ProdutoService {

	private GenericDAO<ProdutoBean> genericDAO = new GenericDAO<>();
	
	public boolean insert(ProdutoBean produto) {
		try {
			genericDAO.save(produto, produto.getCodProduto());
		} catch (BDException e) {
			return false;
		}
		return true;
	}

	public ProdutoBean findById(int id) {
		return genericDAO.findById(ProdutoBean.class, id);
	}

	public ArrayList<ProdutoBean> find(ProdutoBean produto) {
		return genericDAO.findLike(ProdutoBean.class, produto);
	}

	public String delete(int id) {
		try {
			return genericDAO.remove(ProdutoBean.class, id);
		} catch (BDException e) {
			return null;
		}
	}
}
