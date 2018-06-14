package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe Modelo para os Adicionais do item de pedido
 * 
 * @author Yago
 */

@Entity
@Table(name = "item_pedido_adicional")
public class ItemPedidoAdicionalBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoAdicionalPK pk;

	/**
	 * Contrutor padr�o da classe
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
		pk.setItemPedido(itemPedido);
		pk.setAdicional(produtoAdicional);
	}

	/**
	 * Captura o valor contido no parametro itemPedido
	 * 
	 * @return itemPedido
	 */
	public ItemPedidoBean getItemPedido() {
		return pk.getItemPedido();
	}

	/**
	 * Setar o valor para o parametro itemPedido
	 * 
	 * @param itemPedido
	 */
	public void setItemPedido(ItemPedidoBean itemPedido) {
		pk.setItemPedido(itemPedido);
	}

	/**
	 * Captura o valor contido no parametro produtoAdicional
	 * 
	 * @return produtoAdicional
	 */
	public ProdutoAdicionalBean getPedidoAdicional() {
		return pk.getAdicional();
	}

	/**
	 * Setar o valor para o parametro produtoAdicional
	 * 
	 * @param produtoAdicional
	 */
	public void setPedidoAdicional(ProdutoAdicionalBean produtoAdicional) {
		pk.setAdicional(produtoAdicional);
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
		return "ItemPedidoAdicionalBean [itemPedido=" + pk.getItemPedido() + ", produtoAdicional=" + pk.getItemPedido()
				+ "]";
	}

}
