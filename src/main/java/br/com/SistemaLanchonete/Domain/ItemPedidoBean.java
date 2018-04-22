package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Classe Modelo para os itens do pedido
 * 
 * @author Diogo
 */

@Entity
@Table(name = "item_pedido")
public class ItemPedidoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_item_compra")
	@Id
	private int cdItemPedido;
	@Column(name = "cd_pedido")
	private int cdPedido;
	@Column(name = "cd_produto")
	private int cdProduto;
	@Column(name = "qt_unitaria")
	private float qtUnitaria;
	@Column(name = "vl_unitario")
	private float vlUnitario;
	@Column(name = "vl_desconto")
	private float vlDesconto;
	
	/**
	 * Contrutor padrão da classe
	 */
	public ItemPedidoBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdItemPedido
	 * @param cdPedido
	 * @param cdProduto
	 * @param qtUnitaria
	 * @param vlUnitario
	 * @param vlDesconto
	 */
	public ItemPedidoBean(int cdItemPedido, int cdPedido, int cdProduto, float qtUnitaria, float vlUnitario,
			float vlDesconto) {
		this.cdItemPedido = cdItemPedido;
		this.cdPedido = cdPedido;
		this.cdProduto = cdProduto;
		this.qtUnitaria = qtUnitaria;
		this.vlUnitario = vlUnitario;
		this.vlDesconto = vlDesconto;
	}

	/**
	 * Captura o valor contido no parametro cdItemPedido
	 * 
	 * @return cdItemPedido
	 */
	public int getCdItemPedido() {
		return cdItemPedido;
	}

	/**
	 * Setar o valor para o parametro cdItemPedido
	 * 
	 * @param cdItemPedido
	 */
	public void setCdItemPedido(int cdItemPedido) {
		this.cdItemPedido = cdItemPedido;
	}

	/**
	 * Captura o valor contido no parametro cdPedido
	 * 
	 * @return cdPedido
	 */
	public int getCdPedido() {
		return cdPedido;
	}

	/**
	 * Setar o valor para o parametro cdPedido
	 * 
	 * @param cdPedido
	 */
	public void setCdPedido(int cdPedido) {
		this.cdPedido = cdPedido;
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
	 * Captura o valor contido no parametro qtUnitaria
	 * 
	 * @return qtUnitaria
	 */
	public float getQtUnitaria() {
		return qtUnitaria;
	}

	/**
	 * Setar o valor para o parametro qtUnitaria
	 * 
	 * @param qtUnitaria
	 */
	public void setQtUnitaria(float qtUnitaria) {
		this.qtUnitaria = qtUnitaria;
	}

	/**
	 * Captura o valor contido no parametro vlUnitario
	 * 
	 * @return vlUnitario
	 */
	public float getVlUnitario() {
		return vlUnitario;
	}

	/**
	 * Setar o valor para o parametro vlUnitario
	 * 
	 * @param vlUnitario
	 */
	public void setVlUnitario(float vlUnitario) {
		this.vlUnitario = vlUnitario;
	}

	/**
	 * Captura o valor contido no parametro vlDesconto
	 * 
	 * @return vlDesconto
	 */
	public float getVlDesconto() {
		return vlDesconto;
	}

	/**
	 * Setar o valor para o parametro vlDesconto
	 * 
	 * @param vlDesconto
	 */
	public void setVlDesconto(float vlDesconto) {
		this.vlDesconto = vlDesconto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		ItemPedidoBean other = (ItemPedidoBean) obj;
		if (cdItemPedido != other.cdItemPedido)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + //
				"\nClasse ..........: " + getClass().getSimpleName() + //
				"\nCód. Item Pedido.: " + getCdItemPedido() + //
				"\nPedido...........: " + getCdPedido() + //
				"\nCód. Produto.....: " + getCdProduto() + //
				"\nQT Unitária......: " + getQtUnitaria() + //
				"\nValor Unitário...: " + getVlUnitario() + //
				"\nValor Desconto...: " + getVlDesconto(); //
	}
}