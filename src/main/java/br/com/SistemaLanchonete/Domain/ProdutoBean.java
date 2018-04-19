package br.com.SistemaLanchonete.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Classe modelo ProdutoBean para popula��o dos produtos
 * @author Jonatan Jos� Soares
 *
 */

@Entity
@Table(name = "produto")
@PrimaryKeyJoinColumn(name = "cd_produto")
public class ProdutoBean {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name= "cd_produto")
	private int codProduto;
	
	@Column (name = "ds_ref_produto")
	private String descRefProduto;
	
	@Column(name = "ds_produto")
	private String descProduto;
	
	@Column(name = "is_ativo")
	private boolean isAtivo;
	
	/**
	 * Constructor padr�o da Classe ProdutoBean;
	 */
	
	public ProdutoBean() {
	}
	
	/**
	 * Constructor par�metrizado da Classe ProdutoBean
	 * @param codProduto -> C�digo do Produto a ser inserido;
	 * @param descRefProduto -> Descri��o da Refer�ncia do Produto a ser Inserido;
	 * @param descProduto -> Descri��o do Produto a ser Inserido;
	 * @param isAtivo -> Status Produto Ativo ou Inativo;
	 */
	
	ProdutoBean(int codProduto, String descRefProduto, String descProduto, boolean isAtivo) {
		this.codProduto = codProduto;
		this.descRefProduto = descRefProduto;
		this.descProduto = descProduto;
		this.isAtivo = isAtivo;
	}
	
	/**
	 * 
	 * @return c�digo do Produto
	 */
	public int getCodProduto() {
		return codProduto;
	}
	
	/**
	 * 
	 * @param Setar valor para codProduto
	 */
	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
	
	/**
	 * 
	 * @return Retorna a descri��o da refer�ncia do Produto
	 */
	public String getDescRefProduto() {
		return descRefProduto;
	}
	/**
	 * 
	 * @param Setar valor para descRefProduto 
	 */
	public void setDescRefProduto(String descRefProduto) {
		this.descRefProduto = descRefProduto;
	}
	
	/**
	 * 
	 * @return Retorna a descri��o do Produto
	 */
	public String getDescProduto() {
		return descProduto;
	}
	
	/**
	 * 
	 * @param Setar valor para descProduto
	 */
	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}
	
	/**
	 * 
	 * @return Retorna se produto est� ativo ou n�o
	 */
	public boolean getIsAtivo() {
		return isAtivo;
	}
	
	/**
	 * 
	 * @param Setar valor para isAtivo (True or False)
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
		if (codProduto!= other.codProduto)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() +
				"\nClasse: " + ProdutoBean.class.getName() +
				"\nC�digo do Produto: " + getCodProduto() +
				"\nDescri��o da Refer�ncia do Produto " +  getDescRefProduto() +
				"\nDescri��o do Produto " + getDescProduto() +
				"\nIsAtivo: " + getIsAtivo();
	}
	
	
	
	

}
