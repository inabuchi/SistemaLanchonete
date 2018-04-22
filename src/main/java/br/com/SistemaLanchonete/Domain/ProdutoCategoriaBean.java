package br.com.SistemaLanchonete.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Classe modelo para ProdutoCategoria
 * @author Jonatan José Soares
 *
 */
@Entity
@Table(name = "categoria")
@PrimaryKeyJoinColumn(name = "cd_categoria")
public class ProdutoCategoriaBean {
	
	@Column (name = "cd_categoria")
	private int codProdutoCategoria;

	/**
	 * 
	 * @return código da categoria do Produto
	 */
	public int getCodProdutoCategoria() {
		return codProdutoCategoria;
	}
	
	/**
	 * 
	 * @param codProdutoCategoria
	 */
	public void setCodProdutoCategoria(int codProdutoCategoria) {
		this.codProdutoCategoria = codProdutoCategoria;
	}
	
	

}
