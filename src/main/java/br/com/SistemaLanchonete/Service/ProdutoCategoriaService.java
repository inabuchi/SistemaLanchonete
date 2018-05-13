package br.com.SistemaLanchonete.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;

import br.com.SistemaLanchonete.Domain.ProdutoCategoriaBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;
import br.com.SistemaLanchonete.Validacao.Validacao;

// TODO tem de verificar se essa classe está ok
public class ProdutoCategoriaService {

	private static String retorno = "";
	GenericDAO<ProdutoCategoriaBean> produtoDAO = new GenericDAO<ProdutoCategoriaBean>();
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
				produtoDAO.save(categoria, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				produtoDAO.save(categoria, categoria.getCdProdutoCategoria());
			} catch (BDException e) {
				throw new BDException("Erro ao atualizar Produto " + categoria.getCdProdutoCategoria() + e.getMessage(),
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
		return produtoDAO.findLike(produtoCategoriaClasse, produtoCategoria);
	}

	public boolean validaCategoria(ProdutoCategoriaBean categoria) {
		// Valida objeto categoria nulo
		if (!Validacao.validaNulo(categoria)) {
			retorno = "Categoria Nula, não pode ser inserido no banco de dados";
			return false;
		}
		// Validar descrição Obrigatório
		categoria.setDsCategoria(categoria.getDsCategoria().trim());
		try {
			Validacao.validaAtributoNulo(categoria, CAMPOS[2]);
			Validacao.validaAtributoVazio(categoria, CAMPOS[2]);
		} catch (Exception e) {
			retorno = "Campo descrição está vazio e é obrigatório";
		}
		// Validar categoria duplicada
		ArrayList<ProdutoCategoriaBean> lista = findLike(categoria);
		if (!lista.isEmpty()) {
			retorno = "Categoria com descrição já cadastrada";
			return false;
		}
		return true;
	}

}
