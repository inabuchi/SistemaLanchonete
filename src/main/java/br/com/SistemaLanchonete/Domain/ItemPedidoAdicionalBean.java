package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe Modelo para os Adicionais do item de pedido
 * 
 * @author Yago
 */

@Entity
@Table(name = "item_pedido_adicional")
public class ItemPedidoAdicionalBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemPedidoAdicionalPK pk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cd_item_pedido", insertable = false, updatable = false)
	private ItemPedidoBean itemPedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cd_adicional", insertable = false, updatable = false)
	private ProdutoAdicionalBean produtoAdicional;

	/**
	 * Contrutor padrão da classe
	 */
	public ItemPedidoAdicionalBean() {
		pk = new ItemPedidoAdicionalPK();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor da classe
	 *
	 * @param itemPedido
	 * @param produtoAdicional
	 */
	public ItemPedidoAdicionalBean(ItemPedidoBean itemPedido, ProdutoAdicionalBean produtoAdicional) {
		this();
		this.itemPedido = itemPedido;
		this.produtoAdicional = produtoAdicional;
		pk.setCdItemCompra(itemPedido != null ? itemPedido.getCdItemPedido() : 0);
		pk.setCdAdicional(produtoAdicional != null ? produtoAdicional.getCdProdutoAdicional() : 0);
	}

	/**
	 * Captura o valor contido no parametro itemPedido
	 * 
	 * @return itemPedido
	 */
	public ItemPedidoBean getItemPedido() {
		return itemPedido;
	}

	/**
	 * Setar o valor para o parametro itemPedido
	 * 
	 * @param itemPedido
	 */
	public void setItemPedido(ItemPedidoBean itemPedido) {
		this.itemPedido = itemPedido;
		pk.setCdItemCompra(itemPedido != null ? itemPedido.getCdItemPedido() : 0);
	}

	/**
	 * Captura o valor contido no parametro produtoAdicional
	 * 
	 * @return produtoAdicional
	 */
	public ProdutoAdicionalBean getPedidoAdicional() {
		return produtoAdicional;
	}

	/**
	 * Setar o valor para o parametro produtoAdicional
	 * 
	 * @param produtoAdicional
	 */
	public void setPedidoAdicional(ProdutoAdicionalBean produtoAdicional) {
		this.produtoAdicional = produtoAdicional;
		pk.setCdAdicional(produtoAdicional != null ? produtoAdicional.getCdProdutoAdicional() : 0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		ItemPedidoAdicionalBean other = (ItemPedidoAdicionalBean) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemPedidoAdicionalBean [itemPedido=" + itemPedido + ", produtoAdicional=" + produtoAdicional + "]";
	}
	
	
}
