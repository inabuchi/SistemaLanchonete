package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.EmbeddedId;
/**
 * Classe da tabela auxiliar endereco_pessoa
 * 
 * @author Yago
 */
@Entity
@Table(name = "endereco_pessoa")
public class EnderecoPessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private EnderecoPessoaPK pk;
	
	@ManyToOne
	@JoinColumn(name="cd_endereco", referencedColumnName="cd_endereco")
	private EnderecoBean endereco;
	
	@ManyToOne
	@JoinColumn(name="cd_pessoa", referencedColumnName="cd_pessoa")
	private PessoaBean pessoa;
	
	@Column(name = "dt_alteracao")
	private Date dtAlteracao;
	
	@Column(name = "is_endereco_padrao")
	private boolean isEnderecoPadrao;

	/**
	 * Construtor padrão da classe
	 */
	public EnderecoPessoaBean() {
		pk = new EnderecoPessoaPK();
	}
	
	/**
	 * Construtor da classe
	 *
	 * @param endereco
	 * @param pessoa
	 * @param dtAlteracao
	 * @param isEnderecoPadrao
	 */
	public EnderecoPessoaBean(EnderecoBean endereco, PessoaBean pessoa, Date dtAlteracao, boolean isEnderecoPadrao) {
		this();
		this.endereco = endereco;
		this.pessoa = pessoa;
		this.dtAlteracao = dtAlteracao;
		this.isEnderecoPadrao = isEnderecoPadrao;
		
		pk.setCdEndereco(endereco != null ? endereco.getCdEndereco() : 0);
		pk.setCdPessoa(pessoa != null ? pessoa.getCdPessoa() : 0);
	}

	/**
	 * Captura o valor contido no parametro endereco
	 * 
	 * @return endereco
	 */
	public EnderecoBean getEndereco() {
		return endereco;
	}

	/**
	 * Setar o valor para o parametro endereco
	 * 
	 * @param endereco
	 */
	public void setEndereco(EnderecoBean endereco) {
		this.endereco = endereco;
		pk.setCdEndereco(endereco != null ? endereco.getCdEndereco() : 0);
	}

	/**
	 * Captura o valor contido no parametro pessoa
	 * 
	 * @return pessoa
	 */
	public PessoaBean getPessoa() {
		return pessoa;
	}

	/**
	 * Setar o valor para o parametro pessoa
	 * 
	 * @param pessoa
	 */
	public void setPessoa(PessoaBean pessoa) {
		this.pessoa = pessoa;
		pk.setCdPessoa(pessoa != null ? pessoa.getCdPessoa() : 0);
	}

	/**
	 * Captura o valor contido no parametro dtAlteracao
	 * 
	 * @return dtAlteracao
	 */
	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	/**
	 * Setar o valor para o parametro dtAlteracao
	 * 
	 * @param dtAlteracao
	 */
	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	/**
	 * Captura o valor contido no parametro isEnderecoPadrao
	 * 
	 * @return isEnderecoPadrao
	 */
	public boolean isEnderecoPadrao() {
		return isEnderecoPadrao;
	}

	/**
	 * Setar o valor para o parametro isEnderecoPadrao
	 * 
	 * @param isEnderecoPadrao
	 */
	public void setEnderecoPadrao(boolean isEnderecoPadrao) {
		this.isEnderecoPadrao = isEnderecoPadrao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pk.hashCode();
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
		EnderecoPessoaBean other = (EnderecoPessoaBean) obj;
		return pk.equals(other.pk);
	}

	@Override
	public String toString() {
		return "\nClasse ....................: " + getClass().getSimpleName() + //
				"\nPessoa....................: " + getPessoa() != null ? getPessoa().getDsNome() : "Sem pessoa" + //
				"\nData alteração............: " + getDtAlteracao() +//
				"\nÉ padrão?.................: " + (isEnderecoPadrao() ? "Sim" : "Não" );//
	}
}

