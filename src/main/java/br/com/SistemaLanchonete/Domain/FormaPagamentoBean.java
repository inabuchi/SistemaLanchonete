package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Classe Modelo para as formas de pagamento
 * 
 * @author Diogo
 */

@Entity
@Table(name = "forma_pagamento")
public class FormaPagamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_forma_pagamento")
	private int cdFormaPagamento;
	@Column(name = "ds_forma_pagamento")
	private String dsFormaPagamento;
	
	/**
	 * Contrutor padrão da classe
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
				"\nCód. Forma Pagamento.: " + getCdFormaPagamento() + //
				"\nForma Pagamento......: " + getDsFormaPagamento(); //
	}
}