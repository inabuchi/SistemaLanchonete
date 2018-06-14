package br.com.SistemaLanchonete.Domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe modelo ProdutoBean para popula��o dos produtos
 * 
 * @author Jonatan Jos� Soares
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
	
	@OneToMany(mappedBy = "produto", targetEntity = PrecoProdutoBean.class, fetch = FetchType.LAZY, cascade = {
			CascadeType.ALL })
	private List<PrecoProdutoBean> precoProduto = new ArrayList<PrecoProdutoBean>();

	@Column(name = "is_ativo")
	private boolean isAtivo;

	@JsonIgnore
	@OneToMany(mappedBy = "pk.produto", targetEntity = ItemPedidoBean.class, fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH })
	private List<ItemPedidoBean> itensPedido = new ArrayList<ItemPedidoBean>();

	/**
	 * Constructor padr�o da Classe ProdutoBean;
	 */

	public ProdutoBean() {
	}

	/**
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
	 * Captura o valor contido no parametro cdProduto
	 * 
	 * @return cdProduto
	 */

	public int getCdProduto() {
		return cdProduto;
	}

	/**
	 * Setar o valor para o parametro cdProduto
	 * 
	 * @param cdProduto
	 */
	public void setCdProduto(int cdProduto) {
		this.cdProduto = cdProduto;
	}

	/**
	 * Captura o valor contido no parametro categoria
	 * 
	 * @return categoria
	 */

	public ProdutoCategoriaBean getCategoria() {
		return categoria;
	}

	/**
	 * Setar o valor para o parametro categoria
	 * 
	 * @param categoria
	 */
	public void setCategoria(ProdutoCategoriaBean categoria) {
		this.categoria = categoria;
	}

	/**
	 * Captura o valor contido no parametro dsRefProduto
	 * 
	 * @return dsRefProduto
	 */

	public String getDsRefProduto() {
		return dsRefProduto;
	}

	/**
	 * Setar o valor para o parametro dsRefProduto
	 * 
	 * @param dsRefProduto
	 */
	public void setDsRefProduto(String dsRefProduto) {
		this.dsRefProduto = dsRefProduto;
	}

	/**
	 * Captura o valor contido no parametro dsProduto
	 * 
	 * @return dsProduto
	 */

	public String getDsProduto() {
		return dsProduto;
	}

	/**
	 * Setar o valor para o parametro dsProduto
	 * 
	 * @param dsProduto
	 */
	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	/**
	 * Captura o valor contido no parametro precoProduto
	 * 
	 * @return precoProduto
	 */

	public List<PrecoProdutoBean> getPrecoProduto() {
		return precoProduto;
	}

	/**
	 * Setar o valor para o parametro precoProduto
	 * 
	 * @param precoProduto
	 */
	public void setPrecoProduto(List<PrecoProdutoBean> precoProduto) {
		this.precoProduto = precoProduto;
	}

	/**
	 * Captura o valor contido no parametro isAtivo
	 * 
	 * @return isAtivo
	 */

	public boolean isAtivo() {
		return isAtivo;
	}

	/**
	 * Setar o valor para o parametro isAtivo
	 * 
	 * @param isAtivo
	 */
	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	/**
	 * Captura o valor contido no parametro itensPedido
	 * 
	 * @return itensPedido
	 */

	public List<ItemPedidoBean> getItensPedido() {
		return itensPedido;
	}

	/**
	 * Setar o valor para o parametro itensPedido
	 * 
	 * @param itensPedido
	 */
	public void setItensPedido(List<ItemPedidoBean> itensPedido) {
		this.itensPedido = itensPedido;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdProduto;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ProdutoBean)) {
			return false;
		}
		ProdutoBean other = (ProdutoBean) obj;
		if (cdProduto != other.cdProduto) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "\nClasse ................: " + getClass().getSimpleName() + //
				"\nC�digo do Produto......: " + getCdProduto() + //
				"\nRef. Interna...........: " + getDsRefProduto() + //
				"\nDescri��o do Produto...: " + getDsProduto() + //
				"\nAtivo..................: " + (isAtivo() ? "Sim" : "N�o");//
	}
}
