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
 * Classe Modelo para os pedidos
 * 
 * @author Diogo
 */

@Entity
@Table(name = "pedido")
public class PedidoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_pedido")
	@Id
	private int cdPedido;
	@Column(name = "cd_cliente")
	private int cdCliente;
	@Column(name = "cd_funcionario")
	private int cdFuncionario;
	@Column(name = "cd_funcionarioEntrega")
	private int cdFuncionarioEntrega;
	@Column(name = "cd_forma_pagamento")
	private int cdFormaPagamento;
	@Column(name = "cd_num_pedido")
	private int cdNumPedido;
	@Column(name = "dt_emissao")
	private Date dtEmissao;
	@Column(name = "vl_entrega")
	private float vlEntrega;
	@Column(name = "vl_total_compra")
	private float vlTotalCompra;
	@Column(name = "vl_pago")
	private float vlPago;
	@Column(name = "dt_entrega")
	private Date dtEntrega;
	@Column(name = "vl_troco")
	private float vlTroco;
	
	/**
	 * Contrutor padrão da classe
	 */
	public PedidoBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdPedido
	 * @param cdCliente
	 * @param cdFuncionario
	 * @param cdFuncionarioEntrega
	 * @param cdFormaPagamento
	 * @param cdNumPedido
	 * @param dtEmissao
	 * @param vlEntrega
	 * @param vlTotalCompra
	 * @param vlPago
	 * @param dtEntrega
	 * @param vlTroco
	 */
	public PedidoBean(int cdPedido, int cdCliente, int cdFuncionario, int cdFuncionarioEntrega, int cdFormaPagamento,
			int cdNumPedido, Date dtEmissao, float vlEntrega, float vlTotalCompra, float vlPago, Date dtEntrega, 
			float vlTroco) {
		this.cdPedido = cdPedido;
		this.cdCliente = cdCliente;
		this.cdFuncionario = cdFuncionario;
		this.cdFuncionarioEntrega = cdFuncionarioEntrega;
		this.cdFormaPagamento = cdFormaPagamento;
		this.cdNumPedido = cdNumPedido;
		this.dtEmissao = dtEmissao;
		this.vlEntrega = vlEntrega;
		this.vlTotalCompra = vlTotalCompra;
		this.vlPago = vlPago;
		this.dtEntrega = dtEntrega;
		this.vlTroco = vlTroco;
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
	 * Captura o valor contido no parametro cdCliente
	 * 
	 * @return cdCliente
	 */
	public int getCdCliente() {
		return cdCliente;
	}

	/**
	 * Setar o valor para o parametro cdCliente
	 * 
	 * @param cdCliente
	 */
	public void setCdCliente(int cdCliente) {
		this.cdCliente = cdCliente;
	}

	/**
	 * Captura o valor contido no parametro cdFuncionario
	 * 
	 * @return cdFuncionario
	 */
	public int getCdFuncionario() {
		return cdFuncionario;
	}

	/**
	 * Setar o valor para o parametro cdFuncionario
	 * 
	 * @param cdFuncionario
	 */
	public void setCdFuncionario(int cdFuncionario) {
		this.cdFuncionario = cdFuncionario;
	}

	/**
	 * Captura o valor contido no parametro cdFuncionarioEntrega
	 * 
	 * @return cdFuncionarioEntrega
	 */
	public int getCdFuncionarioEntrega() {
		return cdFuncionarioEntrega;
	}

	/**
	 * Setar o valor para o parametro cdFuncionarioEntrega
	 * 
	 * @param cdFuncionarioEntrega
	 */
	public void setCdFuncionarioEntrega(int cdFuncionarioEntrega) {
		this.cdFuncionarioEntrega = cdFuncionarioEntrega;
	}

	/**
	 * Captura o valor contido no parametro cdFormaPagamento
	 * 
	 * @return cdFormaPagamento
	 */
	public int getCdFormaPagamento() {
		return cdFormaPagamento;
	}

	/**
	 * Setar o valor para o parametro cdFormaPagamento
	 * 
	 * @param cdFormaPagamento
	 */
	public void setCdFormaPagamento(int cdFormaPagamento) {
		this.cdFormaPagamento = cdFormaPagamento;
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
				"\nNúmero Pedido.......: " + getCdNumPedido() + //
				"\nData Emissão........: " + getDtEmissao() + //
				"\nValor Entrega.......: " + getVlEntrega() + //
				"\nValor Total Compra..: " + getVlTotalCompra() + //
				"\nValor Pago..........: " + getVlPago() + //
				"\nData Entrega........: " + getDtEntrega() + //
				"\nTroco...............: " + getVlTroco(); //
	}
}