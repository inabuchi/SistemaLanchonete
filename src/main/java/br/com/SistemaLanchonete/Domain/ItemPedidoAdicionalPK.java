	package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * Chave composta da classe ItemPedidoAdicionalBean
 * 
 * @author Yago
 */

@Embeddable
public class ItemPedidoAdicionalPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false)
	  @JoinColumns(value = {
	          @JoinColumn(name = "cd_item_pedido", referencedColumnName = "cd_item_pedido"),
	          @JoinColumn(name = "cd_pedido", referencedColumnName = "cd_pedido"),
	          @JoinColumn(name = "cd_produto", referencedColumnName = "cd_produto") })
	
	//@Column(name = "cd_item_pedido")	
	private ItemPedidoBean itemPedido;
	
	//@Column(name = "cd_pedido")	
	//private PedidoBean pedido;
	
	//@Column(name = "cd_produto")	
	//private ProdutoBean produto;
	
	@Column(name = "cd_adicional")	
	private ProdutoAdicionalBean adicional;

	public ItemPedidoBean getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(ItemPedidoBean itemPedido) {
		this.itemPedido = itemPedido;
		//this.produto = itemPedido.getProduto();
		//this.pedido = itemPedido.getPedido();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adicional == null) ? 0 : adicional.hashCode());
		result = prime * result + ((itemPedido == null) ? 0 : itemPedido.hashCode());
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
		ItemPedidoAdicionalPK other = (ItemPedidoAdicionalPK) obj;
		if (adicional == null) {
			if (other.adicional != null)
				return false;
		} else if (!adicional.equals(other.adicional))
			return false;
		if (itemPedido == null) {
			if (other.itemPedido != null)
				return false;
		} else if (!itemPedido.equals(other.itemPedido))
			return false;
		return true;
	}

	public ProdutoAdicionalBean getAdicional() {
		return adicional;
	}

	public void setAdicional(ProdutoAdicionalBean adicional) {
		this.adicional = adicional;
	}

	
}
