package br.com.SistemaLanchonete.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;

import br.com.SistemaLanchonete.Domain.ProdutoCategoriaBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;
import br.com.SistemaLanchonete.Validacao.Validacao;

// TODO tem de verificar se essa classe est� ok
public class ProdutoCategoriaService {

	private static String retorno = "";
	GenericDAO<ProdutoCategoriaBean> produtoCategoriaDAO = new GenericDAO<ProdutoCategoriaBean>();
	Class<ProdutoCategoriaBean> produtoCategoriaClasse = ProdutoCategoriaBean.class;
	static Field[] CAMPOS = ProdutoCategoriaBean.class.getDeclaredFields();

	// CAMPOS[0] --> ProdutoCategoriaBean --> serialVersionUID
	// CAMPOS[1] --> ProdutoCategoriaBean --> cdProdutoCategoria
	// CAMPOS[2] --> ProdutoCategoriaBean --> dsCategoria

	public String save(ProdutoCategoriaBean categoria) throws BDException {
		if (!validaCategoria(categoria)) {
			return retorno;
		}
		if (categoria.getCdProdutoCategoria() == 0) {
			try {
				produtoCategoriaDAO.save(categoria, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				produtoCategoriaDAO.save(categoria, categoria.getCdProdutoCategoria());
			} catch (BDException e) {
				throw new BDException("Erro ao atualizar Produto " + categoria.getCdProdutoCategoria() + e.getMessage(),
						EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(ProdutoCategoriaBean produtoCategoria) throws BDException {
		ProdutoCategoriaBean produtoCategoriaRetorna = produtoCategoriaDAO.findById(produtoCategoriaClasse,
				produtoCategoria.getCdProdutoCategoria());
		try {
			produtoCategoriaDAO.remove(produtoCategoriaClasse, produtoCategoriaRetorna.getCdProdutoCategoria());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			throw new BDException("Erro ao remover : " + produtoCategoria.getCdProdutoCategoria() + e.getMessage(),
					EErrosBD.EXCLUI_DADO);
		}
		return retorno;
	}

	public ProdutoCategoriaBean findById(ProdutoCategoriaBean produtoCategoria) {
		return produtoCategoriaDAO.findById(produtoCategoriaClasse, produtoCategoria.getCdProdutoCategoria());
	}

	public ArrayList<ProdutoCategoriaBean> findLike(ProdutoCategoriaBean produtoCategoria) {
		return produtoCategoriaDAO.findLike(produtoCategoriaClasse, produtoCategoria);
	}

	public boolean validaCategoria(ProdutoCategoriaBean categoria) {
		// Valida objeto categoria nulo
		if (!Validacao.validaNulo(categoria)) {
			retorno = "Categoria Nula, n�o pode ser inserido no banco de dados";
			return false;
		}
		// Validar descri��o Obrigat�rio
		categoria.setDsCategoria(categoria.getDsCategoria().trim());
		try {
			Validacao.validaAtributoNulo(categoria, CAMPOS[2]);
			Validacao.validaAtributoVazio(categoria, CAMPOS[2]);
		} catch (Exception e) {
			retorno = "Campo descri��o est� vazio e � obrigat�rio";
		}
		// Validar categoria duplicada
		ArrayList<ProdutoCategoriaBean> lista = findLike(categoria);
		if (!lista.isEmpty()) {
			retorno = "Categoria com descri��o j� cadastrada";
			return false;
		}
		return true;
	}

}
