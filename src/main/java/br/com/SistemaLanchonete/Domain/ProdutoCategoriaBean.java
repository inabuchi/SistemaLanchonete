package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe modelo para ProdutoCategoria
 * 
 * @author Jonatan Jos� Soares
 *
 */
@Entity
@Table(name = "categoria")
public class ProdutoCategoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_categoria")
	private int cdProdutoCategoria;

	@Column(name = "ds_categoria")
	private String dsCategoria;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<ProdutoBean> produtos = new ArrayList<ProdutoBean>();

	/**
	 * Contrutor padr�o da classe
	 */
	public ProdutoCategoriaBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdProdutoCategoria
	 * @param dsCategoria
	 */
	public ProdutoCategoriaBean(int cdProdutoCategoria, String dsCategoria) {
		this.cdProdutoCategoria = cdProdutoCategoria;
		this.dsCategoria = dsCategoria;
	}

	/**
	 * Captura o valor contido no parametro dsCategoria
	 * 
	 * @return dsCategoria
	 */

	public String getDsCategoria() {
		return dsCategoria;
	}

	/**
	 * Setar o valor para o parametro dsCategoria
	 * 
	 * @param dsCategoria
	 */
	public void setDsCategoria(String dsCategoria) {
		this.dsCategoria = dsCategoria;
	}

	/**
	 * 
	 * @return c�digo da categoria do Produto
	 */
	public int getCdProdutoCategoria() {
		return cdProdutoCategoria;
	}

	/**
	 * 
	 * @param cdProdutoCategoria
	 */
	public void setCdProdutoCategoria(int cdProdutoCategoria) {
		this.cdProdutoCategoria = cdProdutoCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdProdutoCategoria;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoCategoriaBean other = (ProdutoCategoriaBean) obj;
		if (cdProdutoCategoria != other.cdProdutoCategoria)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProdutoCategoriaBean [cdProdutoCategoria=" + cdProdutoCategoria + ", dsCategoria=" + dsCategoria + "]";
	}

}
