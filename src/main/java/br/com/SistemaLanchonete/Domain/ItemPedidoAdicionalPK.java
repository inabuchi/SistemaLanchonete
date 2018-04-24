	package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Column;

/**
 * Chave composta da classe ItemPedidoAdicionalBean
 * 
 * @author Yago
 */

@Embeddable
public class ItemPedidoAdicionalPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "cd_item_pedido")	
	private int cdItemCompra;
	
	@Column(name = "cd_adicional")	
	private int cdAdicional;

	public int getCdItemCompra() {
		return cdItemCompra;
	}

	public void setCdItemCompra(int cdItemCompra) {
		this.cdItemCompra = cdItemCompra;
	}

	public int getCdAdicional() {
		return cdAdicional;
	}

	public void setCdAdicional(int cdAdicional) {
		this.cdAdicional = cdAdicional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdAdicional;
		result = prime * result + cdItemCompra;
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
		if (cdAdicional != other.cdAdicional)
			return false;
		if (cdItemCompra != other.cdItemCompra)
			return false;
		return true;
	}

	
}
