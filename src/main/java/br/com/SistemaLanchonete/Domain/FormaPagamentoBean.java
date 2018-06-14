package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe Modelo para as formas de pagamento
 * 
 * @author Diogo
 */

@Entity
@Table(name = "forma_pagamento")
public class FormaPagamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_forma_pagamento")
	private int cdFormaPagamento;

	@Column(name = "ds_forma_pagamento")
	private String dsFormaPagamento;
	@JsonIgnore
	@OneToMany(mappedBy = "formaPagto")
	private List<PedidoBean> pedidos = new ArrayList<PedidoBean>();

	/**
	 * Contrutor padr�o da classe
	 */
	public FormaPagamentoBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdFormaPagamento
	 * @param dsFormaPagamento
	 */
	public FormaPagamentoBean(int cdFormaPagamento, String dsFormaPagamento) {
		this.cdFormaPagamento = cdFormaPagamento;
		this.dsFormaPagamento = dsFormaPagamento;
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
	 * Captura o valor contido no parametro dsFormaPagamento
	 * 
	 * @return dsFormaPagamento
	 */
	public String getDsFormaPagamento() {
		return dsFormaPagamento;
	}

	/**
	 * Setar o valor para o parametro dsFormaPagamento
	 * 
	 * @param dsFormaPagamento
	 */
	public void setDsFormaPagamento(String dsFormaPagamento) {
		this.dsFormaPagamento = dsFormaPagamento;
	}

	/**
	 * Captura o valor contido no parametro pedidos
	 * 
	 * @return pedidos
	 */

	public List<PedidoBean> getPedidos() {
		return pedidos;
	}

	/**
	 * Setar o valor para o parametro pedidos
	 * 
	 * @param pedidos
	 */
	public void setPedidos(List<PedidoBean> pedidos) {
		this.pedidos = pedidos;
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
		result = prime * result + cdFormaPagamento;
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
		FormaPagamentoBean other = (FormaPagamentoBean) obj;
		if (cdFormaPagamento != other.cdFormaPagamento)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + //
				"\nClasse ..............: " + getClass().getSimpleName() + //
				"\nC�d. Forma Pagamento.: " + getCdFormaPagamento() + //
				"\nForma Pagamento......: " + getDsFormaPagamento(); //
	}
}