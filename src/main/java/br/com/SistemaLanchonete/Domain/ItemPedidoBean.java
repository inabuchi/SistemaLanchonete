package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe Modelo para os itens do pedido
 * 
 * @author Diogo
 */

@Entity
@Table(name = "item_pedido")
public class ItemPedidoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@JsonIgnore
	private ItemPedidoPK pk = new ItemPedidoPK();

	@Column(name = "qt_unitaria")
	private float qtUnitaria;
	@Column(name = "vl_unitario")
	private float vlUnitario;
	@Column(name = "vl_desconto")
	private float vlDesconto;
	@OneToMany(mappedBy = "pk.itemPedido", targetEntity = ItemPedidoAdicionalBean.class, fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH })
	private List<ItemPedidoAdicionalBean> itemPedidoAdicionais = new ArrayList<ItemPedidoAdicionalBean>();

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
	public ItemPedidoBean(int cdItemPedido, PedidoBean pedido, ProdutoBean produto, float qtUnitaria, float vlUnitario,
			float vlDesconto) {
		
		this.pk.setPedido(pedido);
		this.pk.setProduto(produto);
		this.qtUnitaria = qtUnitaria;
		this.vlUnitario = vlUnitario;
		this.vlDesconto = vlDesconto;
		this.pk.setCdItemPedido(cdItemPedido);
	}

	/**
	 * Captura o valor contido no parametro cdItemPedido
	 * 
	 * @return cdItemPedido
	 */
	public int getCdItemPedido() {
		return pk.getCdItemPedido();
	}

	/**
	 * Setar o valor para o parametro cdItemPedido
	 * 
	 * @param cdItemPedido
	 */
	public void setCdItemPedido(int cdItemPedido) {
		this.pk.setCdItemPedido(cdItemPedido);
	}

	/**
	 * Captura o valor contido no parametro pedido
	 * 
	 * @return pedido
	 */
	public PedidoBean getPedido() {
		return pk.getPedido();
	}

	/**
	 * Setar o valor para o parametro pedido
	 * 
	 * @param pedido
	 */
	public void setPedido(PedidoBean pedido) {
		this.pk.setPedido(pedido);
	}

	/**
	 * Captura o valor contido no parametro produto
	 * 
	 * @return produto
	 */
	public ProdutoBean getProduto() {
		return pk.getProduto();
	}

	/**
	 * Setar o valor para o parametro produto
	 * 
	 * @param produto
	 */
	public void setProduto(ProdutoBean produto) {
		this.pk.setProduto(produto);
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

	/**
	 * Captura o valor contido no parametro itemPedidoAdicionais
	 * 
	 * @return itemPedidoAdicionais
	 */
	public List<ItemPedidoAdicionalBean> getAdicionais() {
		return itemPedidoAdicionais;
	}

	/**
	 * Setar o valor para o parametro itemPedidoAdicionais
	 * 
	 * @param itemPedidoAdicionais
	 */
	public void addAdicional(ItemPedidoAdicionalBean adicional) {
		adicional.setItemPedido(this);
		this.itemPedidoAdicionais.add(adicional);
	}

	/**
	 * Captura o sub total do item de pedido
	 * 
	 * @return vlTotal
	 */
	public double getSubTotal() {

		double vlTotal = 0;

		for (ItemPedidoAdicionalBean itemPedidoAdicionalBean : itemPedidoAdicionais) {
			vlTotal += itemPedidoAdicionalBean.getPedidoAdicional().getVlAdicional();
		}

		vlTotal += (qtUnitaria * vlUnitario);

		vlTotal -= vlDesconto;

		return vlTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pk.getCdItemPedido();
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
		if (pk.getCdItemPedido() != other.pk.getCdItemPedido())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + //
				"\nClasse ..........: " + getClass().getSimpleName() + //
				"\nCód. Item Pedido.: " + getCdItemPedido() + //
				"\nPedido...........: " + (getPedido() != null ? getPedido().getCdPedido() : 0) + //
				"\nCód. Produto.....: " + (getProduto() != null ? getProduto().getCdProduto() : 0) + //
				"\nQT Unitária......: " + getQtUnitaria() + //
				"\nValor Unitário...: " + getVlUnitario() + //
				"\nValor Desconto...: " + getVlDesconto(); //
	}
}