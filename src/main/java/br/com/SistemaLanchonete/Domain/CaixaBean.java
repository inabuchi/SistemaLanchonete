package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * Classe de caixas cadastrados no sistema
 * 
 * @author Patrick
 */
@Entity
@Table(name = "caixa")
public class CaixaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_caixa")
	private int cdCaixa;

	@ManyToOne
	@JoinColumn(name = "cd_funcionario", referencedColumnName = "cd_funcionario")
	private FuncionarioBean funcionario;

	@Column(name = "dt_abertura")
	private Date dtAbertura;

	@Column(name = "dt_fechamento")
	private Date dtFechamento;

	@Column(name = "vl_troco_inicial")
	private float vlTrocoInicial;

	/**
	 * Construtor padr�o da classe
	 */
	public CaixaBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdCaixa
	 * @param funcionario
	 * @param dtAbertura
	 * @param dtFechamento
	 * @param vlTrocoInicial
	 */
	public CaixaBean(int cdCaixa, FuncionarioBean funcionario, Date dtAbertura, Date dtFechamento,
			float vlTrocoInicial) {
		this.cdCaixa = cdCaixa;
		this.funcionario = funcionario;
		this.dtAbertura = dtAbertura;
		this.dtFechamento = dtFechamento;
		this.vlTrocoInicial = vlTrocoInicial;
	}

	/**
	 * Captura o valor contido no parametro cdCaixa
	 * 
	 * @return cdCaixa
	 */
	public int getCdCaixa() {
		return cdCaixa;
	}

	/**
	 * Setar o valor para o parametro cdCaixa
	 * 
	 * @param cdCaixa
	 */
	public void setCdCaixa(int cdCaixa) {
		this.cdCaixa = cdCaixa;
	}

	/**
	 * Captura o valor contido no parametro funcionario
	 *
	 * @return funcionario
	 */
	public FuncionarioBean getFuncionario() {
		return funcionario;
	}

	/**
	 * Setar o valor para o parametro funcionario
	 *
	 * @param funcionario
	 */
	public void setFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * Captura o valor contido no parametro dtAbertura
	 * 
	 * @return dtAbertura
	 */
	public Date getDtAbertura() {
		return dtAbertura;
	}

	/**
	 * Setar o valor para o parametro dtAbertura
	 * 
	 * @param dtAbertura
	 */
	public void setDtAbertura(Date dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	/**
	 * Captura o valor contido no parametro dtFechamento
	 * 
	 * @return dtFechamento
	 */
	public Date getDtFechamento() {
		return dtFechamento;
	}

	/**
	 * Setar o valor para o parametro dtFechamento
	 * 
	 * @param dtFechamento
	 */
	public void setDtFechamento(Date dtFechamento) {
		this.dtFechamento = dtFechamento;
	}

	/**
	 * Captura o valor contido no parametro vlTrocoInicial
	 * 
	 * @return vlTrocoInicial
	 */
	public float getVlTrocoInicial() {
		return vlTrocoInicial;
	}

	/**
	 * Setar o valor para o parametro vlTrocoInicial
	 * 
	 * @param vlTrocoInicial
	 */
	public void setVlTrocoInicial(float vlTrocoInicial) {
		this.vlTrocoInicial = vlTrocoInicial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdCaixa;
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
		CaixaBean other = (CaixaBean) obj;
		if (cdCaixa != other.cdCaixa)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nClasse .................: " + getClass().getSimpleName() + //
				"\nIdentificador do caixa.: " + getCdCaixa() + //
				"\nNome funcion�rio.......: " + // getFuncionario() != null ? getFuncionario().getDsNome() : "Sem
												// funcionario" +//
				"\nData abertura..........: " + getDtAbertura() + //
				"\nData fechamento........: " + getDtFechamento() + //
				"\nValor Troco Inicial....: " + getVlTrocoInicial();//
	}

}
