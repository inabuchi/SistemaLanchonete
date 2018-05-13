package br.com.SistemaLanchonete.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe modelo ProdutoBean para população dos produtos
 * 
 * @author Jonatan José Soares
 *
 */

@Entity
@Table(name = "produto")
public class ProdutoBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_produto")
	private int cdProduto;

	@ManyToOne
	@JoinColumn(name = "cd_categoria", referencedColumnName = "cd_categoria")
	private ProdutoCategoriaBean categoria;

	@Column(name = "ds_ref_produto")
	private String dsRefProduto;

	@Column(name = "ds_produto")
	private String dsProduto;

	@Column(name = "is_ativo")
	private boolean isAtivo;

	/**
	 * Constructor padrão da Classe ProdutoBean;
	 */

	public ProdutoBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdProduto
	 * @param categoria
	 * @param dsRefProduto
	 * @param dsProduto
	 * @param isAtivo
	 */

	public ProdutoBean(int cdProduto, ProdutoCategoriaBean categoria, String dsRefProduto, String dsProduto,
			boolean isAtivo) {
		super();
		this.cdProduto = cdProduto;
		this.categoria = categoria;
		this.dsRefProduto = dsRefProduto;
		this.dsProduto = dsProduto;
		this.isAtivo = isAtivo;
	}

	/**
	 * 
	 * @return Retorna a categproa do Produto
	 */
	public ProdutoCategoriaBean getCategoria() {
		return categoria;
	}

	/**
	 * 
	 * @param Setar
	 *            valor para categoria
	 */
	public void setCategoria(ProdutoCategoriaBean categoria) {
		this.categoria = categoria;
	}

	/**
	 * 
	 * @return Retorna a descrição da referência do Produto
	 */
	public int getCdProduto() {
		return cdProduto;
	}

	/**
	 * 
	 * @param Setar
	 *            valor para cdProduto
	 */
	public void setCdProduto(int cdProduto) {
		this.cdProduto = cdProduto;
	}

	/**
	 * 
	 * @return Retorna a descrição da referência do Produto
	 */
	public String getDsRefProduto() {
		return dsRefProduto;
	}

	/**
	 * 
	 * @param Setar
	 *            valor para dsRefProduto
	 */
	public void setDsRefProduto(String dsRefProduto) {
		this.dsRefProduto = dsRefProduto;
	}

	/**
	 * 
	 * @return Retorna a descrição do Produto
	 */
	public String getDsProduto() {
		return dsProduto;
	}

	/**
	 * 
	 * @param Setar
	 *            valor para dsProduto
	 */
	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	/**
	 * 
	 * @return Retorna se produto está ativo ou não
	 */
	public boolean getIsAtivo() {
		return isAtivo;
	}

	/**
	 * 
	 * @param Setar
	 *            valor para isAtivo (True or False)
	 */
	public void setIsAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoBean other = (ProdutoBean) obj;
		if (cdProduto != other.cdProduto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nClasse ................: " + getClass().getSimpleName() + //
				"\nCódigo do Produto......: " + getCdProduto() + //
				"\nRef. Interna...........: " + getDsRefProduto() + //
				"\nDescrição do Produto...: " + getDsProduto() + //
				"\nAtivo..................: " + (getIsAtivo() ? "Sim" : "Não");//
	}
}
