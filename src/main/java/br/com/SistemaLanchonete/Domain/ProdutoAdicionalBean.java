package br.com.SistemaLanchonete.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Classe modelo para ProdutoAdicional
 * @author Jonatan José Soares
 *
 */
@Entity
@Table(name = "adicional")
@PrimaryKeyJoinColumn(name = "cd_adicional")
public class ProdutoAdicionalBean extends ProdutoBean {
	
	@Column(name = "cd_adicional")
	private int codProdutoAdicional;
	
	/**
	 * 
	 * @return codigo ProdutoAdicional
	 */
	public int getCodProdutoAdicional() {
		return codProdutoAdicional;
	}
	
	/**
	 * 
	 * @param Seta codProdutoAdicional
	 */
	public void setCodProdutoAdicional(int codProdutoAdicional) {
		this.codProdutoAdicional = codProdutoAdicional;
	
	}
}
