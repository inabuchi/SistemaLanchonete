package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe modelo para ProdutoAdicional
 * 
 * @author Jonatan José Soares
 *
 */
@Entity
@Table(name = "adicional")
public class ProdutoAdicionalBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "cd_adicional")
	private int cdProdutoAdicional;

	@Column(name = "ds_adicional")
	private String dsAdicional;

	@Column(name = "vl_adicional")
	private float vlAdicional;

	@OneToMany(mappedBy = "pk.adicional", targetEntity = ItemPedidoAdicionalBean.class, fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH })
	private List<ItemPedidoAdicionalBean> itemPedidoAdicionais = new ArrayList<ItemPedidoAdicionalBean>();
	
	/**
	 * Contrutor padr�o da classe
	 */
	public ProdutoAdicionalBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdProdutoAdicional
	 * @param dsAdicional
	 * @param vlAdicional
	 */
	public ProdutoAdicionalBean(int cdProdutoAdicional, String dsAdicional, float vlAdicional) {
		this.cdProdutoAdicional = cdProdutoAdicional;
		this.dsAdicional = dsAdicional;
		this.vlAdicional = vlAdicional;
	}

	/**
	 * 
	 * @return codigo ProdutoAdicional
	 */
	public int getCdProdutoAdicional() {
		return cdProdutoAdicional;
	}

	/**
	 * 
	 * @param Seta
	 *            codProdutoAdicional
	 */
	public void setCdProdutoAdicional(int cdProdutoAdicional) {
		this.cdProdutoAdicional = cdProdutoAdicional;

	}

	/**
	 * 
	 * @return dsAdicional ProdutoAdicional
	 */
	public String getDsAdicional() {
		return dsAdicional;
	}

	/**
	 * 
	 * @param Seta
	 *            dsAdicional
	 */
	public void setDsAdicional(String dsAdicional) {
		this.dsAdicional = dsAdicional;

	}

	/**
	 * 
	 * @return vlAdicional ProdutoAdicional
	 */
	public float getVlAdicional() {
		return vlAdicional;
	}

	/**
	 * 
	 * @param Seta
	 *            vlAdicional
	 */
	public void setVlAdicional(float vlAdicional) {
		this.vlAdicional = vlAdicional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdProdutoAdicional;
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
		ProdutoAdicionalBean other = (ProdutoAdicionalBean) obj;
		if (cdProdutoAdicional != other.cdProdutoAdicional)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProdutoAdicionalBean [cdProdutoAdicional=" + cdProdutoAdicional + ", dsAdicional=" + dsAdicional
				+ ", vlAdicional=" + vlAdicional + "]";
	}

}
