package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
 * Classe Modelo para os pedidos
 * 
 * @author Diogo
 */

@Entity
@Table(name = "pedido")
public class PedidoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_pedido")
	private int cdPedido;
	
	@ManyToOne
	@JoinColumn(name = "cd_cliente", referencedColumnName = "cd_cliente")
	private ClienteBean cliente;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cd_funcionario", referencedColumnName = "cd_funcionario")
	private FuncionarioBean funcionario;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cd_funcionarioEntrega", referencedColumnName = "cd_funcionario")
	private FuncionarioBean funcionarioEntrega;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cd_forma_pagamento", referencedColumnName = "cd_forma_pagamento")
	private FormaPagamentoBean formaPagto;
	
	@Column(name = "cd_num_pedido")
	private int cdNumPedido;
	
	@Column(name = "dt_emissao")
	private Date dtEmissao;
	
	@Column(name = "vl_entrega")
	private float vlEntrega;
	
	@Column(name = "vl_total_compra")
	private float vlTotalCompra;
	
	@Column(name = "vl_desconto")
	private float vlDesconto;
	
	@Column(name = "vl_pago")
	private float vlPago;
	
	@Column(name = "dt_entrega")
	private Date dtEntrega;
	
	@Column(name = "vl_troco")
	private float vlTroco;
	
	@Column(name = "ds_observacao")
	private String dsObservacao;
	
	@OneToMany(mappedBy = "pk.pedido", targetEntity = ItemPedidoBean.class, fetch = FetchType.LAZY, cascade = {
			CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH })
	private List<ItemPedidoBean> itensPedido = new ArrayList<ItemPedidoBean>();

	/**
	 * Contrutor padr�o da classe
	 */
	public PedidoBean() {

	}

	/**
	 * Construtor da classe
	 *
	 * @param cdPedido
	 * @param cliente
	 * @param funcionario
	 * @param funcionarioEntrega
	 * @param formaPagto
	 * @param cdNumPedido
	 * @param dtEmissao
	 * @param vlEntrega
	 * @param vlTotalCompra
	 * @param vlPago
	 * @param dtEntrega
	 * @param vlTroco
	 * @param dsObservacao
	 */
	public PedidoBean(int cdPedido, ClienteBean cliente, FuncionarioBean funcionario,
			FuncionarioBean funcionarioEntrega, FormaPagamentoBean formaPagto, int cdNumPedido, Date dtEmissao,
			float vlEntrega, float vlTotalCompra, float vlDesconto, float vlPago, Date dtEntrega, float vlTroco,
			String dsObservacao) {
		this();
		this.cdPedido = cdPedido;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.funcionarioEntrega = funcionarioEntrega;
		this.formaPagto = formaPagto;
		this.cdNumPedido = cdNumPedido;
		this.dtEmissao = dtEmissao;
		this.vlEntrega = vlEntrega;
		this.vlTotalCompra = vlTotalCompra;
		this.vlPago = vlPago;
		this.dtEntrega = dtEntrega;
		this.vlTroco = vlTroco;
		this.dsObservacao = dsObservacao;
		this.vlDesconto = vlDesconto;
	}

	public float getVlDesconto() {
		return vlDesconto;
	}

	public void setVlDesconto(float vlDesconto) {
		this.vlDesconto = vlDesconto;
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
	 * Captura o valor contido no parametro cliente
	 * 
	 * @return cliente
	 */
	public ClienteBean getCliente() {
		return cliente;
	}

	/**
	 * Setar o valor para o parametro cliente
	 * 
	 * @param cliente
	 */
	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	/**
	 * Captura o valor contido no parametro funcionario
	 *
	 * @return funcionario
	 */
	public FuncionarioBean getCdFuncionario() {
		return funcionario;
	}

	/**
	 * Setar o valor para o parametro funcionario
	 *
	 * @param funcionario
	 */
	public void setCdFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * Captura o valor contido no parametro funcionarioEntrega
	 *
	 * @return funcionarioEntrega
	 */
	public FuncionarioBean getCdFuncionarioEntrega() {
		return funcionarioEntrega;
	}

	/**
	 * Setar o valor para o parametro funcionarioEntrega
	 *
	 * @param funcionarioEntrega
	 */
	public void setCdFuncionarioEntrega(FuncionarioBean funcionarioEntrega) {
		this.funcionarioEntrega = funcionarioEntrega;
	}

	/**
	 * Captura o valor contido no parametro formaPagamento
	 * 
	 * @return formaPagamento
	 */
	public FormaPagamentoBean getCdFormaPagamento() {
		return formaPagto;
	}

	/**
	 * Setar o valor para o parametro formaPagamento
	 * 
	 * @param formaPagamento
	 */
	public void setCdFormaPagamento(FormaPagamentoBean formaPagto) {
		this.formaPagto = formaPagto;
	}

	/**
	 * Captura o valor contido no parametro cdNumPedido
	 * 
	 * @return cdNumPedido
	 */
	public int getCdNumPedido() {
		return cdNumPedido;
	}

	/**
	 * Setar o valor para o parametro cdNumPedido
	 * 
	 * @param cdNumPedido
	 */
	public void setCdNumPedido(int cdNumPedido) {
		this.cdNumPedido = cdNumPedido;
	}

	/**
	 * Captura o valor contido no parametro dtEmissao
	 * 
	 * @return dtEmissao
	 */
	public Date getDtEmissao() {
		return dtEmissao;
	}

	/**
	 * Setar o valor para o parametro dtEmissao
	 * 
	 * @param dtEmissao
	 */
	public void setDtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}

	/**
	 * Captura o valor contido no parametro vlEntrega
	 * 
	 * @return vlEntrega
	 */
	public float getVlEntrega() {
		return vlEntrega;
	}

	/**
	 * Setar o valor para o parametro vlEntrega
	 * 
	 * @param vlEntrega
	 */
	public void setVlEntrega(float vlEntrega) {
		this.vlEntrega = vlEntrega;
	}

	/**
	 * Captura o valor contido no parametro vlTotalCompra
	 * 
	 * @return vlTotalCompra
	 */
	public float getVlTotalCompra() {
		return vlTotalCompra;
	}

	/**
	 * Setar o valor para o parametro vlTotalCompra
	 * 
	 * @param vlTotalCompra
	 */
	public void setVlTotalCompra(float vlTotalCompra) {
		this.vlTotalCompra = vlTotalCompra;
	}

	/**
	 * Captura o valor contido no parametro vlPago
	 * 
	 * @return vlPago
	 */
	public float getVlPago() {
		return vlPago;
	}

	/**
	 * Setar o valor para o parametro vlPago
	 * 
	 * @param vlPago
	 */
	public void setVlPago(float vlPago) {
		this.vlPago = vlPago;
	}

	/**
	 * Captura o valor contido no parametro dtEntrega
	 * 
	 * @return dtEntrega
	 */
	public Date getDtEntrega() {
		return dtEntrega;
	}

	/**
	 * Setar o valor para o parametro dtEntrega
	 * 
	 * @param dtEntrega
	 */
	public void setDtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	/**
	 * Captura o valor contido no parametro vlTroco
	 * 
	 * @return vlTroco
	 */
	public float getVlTroco() {
		return vlTroco;
	}

	/**
	 * Setar o valor para o parametro vlTroco
	 * 
	 * @param vlTroco
	 */
	public void setVlTroco(float vlTroco) {
		this.vlTroco = vlTroco;
	}

	/**
	 * Captura o valor contido no parametro dsObservacao
	 * 
	 * @return dsObservacao
	 */
	public String getDsObservacao() {
		return dsObservacao;
	}

	/**
	 * Setar o valor para o parametro dsObservacao
	 * 
	 * @param dsObservacao
	 */
	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	/**
	 * Captura o valor contido no parametro itensPedido
	 * 
	 * @return itensPedido
	 */
	public List<ItemPedidoBean> getItens() {
		return itensPedido;
	}

	/**
	 * Setar o valor para o parametro itensPedido
	 * 
	 * @param itensPedido
	 */
	public void addItem(ItemPedidoBean itemPedido) {
		itemPedido.setPedido(this);
		this.itensPedido.add(itemPedido);
	}

	/**
	 * Captura o valor total do pedido
	 * 
	 * @return vlTotal
	 */
	public double getValorTotal() {
		double soma = 0.0;
		for (ItemPedidoBean ip : itensPedido) {
			soma += ip.getSubTotal();
		}
		return soma;
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
		result = prime * result + cdPedido;
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
		PedidoBean other = (PedidoBean) obj;
		if (cdPedido != other.cdPedido)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + //
				"\nClasse .............: " + getClass().getSimpleName() + //
				"\nPedido..............: " + getCdPedido() + //
				"\nFuncionario.........: " + getCdFuncionario() + //
				"\nFuncionario Entrega.: " + getCdFuncionarioEntrega() + //
				"\nForma de Pagamento..: " + getCdFormaPagamento() + //
				"\nN�mero Pedido.......: " + getCdNumPedido() + //
				"\nData Emiss�o........: " + getDtEmissao() + //
				"\nValor Entrega.......: " + getVlEntrega() + //
				"\nValor Total Compra..: " + getVlTotalCompra() + //
				"\nValor Pago..........: " + getVlPago() + //
				"\nData Entrega........: " + getDtEntrega() + //
				"\nTroco...............: " + getVlTroco(); //
	}
}