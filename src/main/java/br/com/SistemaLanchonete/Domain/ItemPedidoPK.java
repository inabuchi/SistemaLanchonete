package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemPedidoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "cd_item_pedido")
	private int cdItemPedido;
	
	@ManyToOne
	@JoinColumn(name="cd_pedido")
	private PedidoBean pedido = new PedidoBean();
	
	@ManyToOne
	@JoinColumn(name="cd_produto")
	private ProdutoBean produto = new ProdutoBean();
	
	public PedidoBean getPedido() {
		return pedido;
	}
	public void setPedido(PedidoBean pedido) {
		this.pedido = pedido;
	}
	public ProdutoBean getProduto() {
		return produto;
	}
	public void setProduto(ProdutoBean produto) {
		this.produto = produto;
	}
	
	public int getCdItemPedido() {
		return cdItemPedido;
	}
	public void setCdItemPedido(int cdItemPedido) {
		this.cdItemPedido = cdItemPedido;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + cdItemPedido;
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
		if (((ItemPedidoPK) obj).cdItemPedido != cdItemPedido)
			return false;
		ItemPedidoPK other = (ItemPedidoPK) obj;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	
	
}
