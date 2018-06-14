package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe da tabela auxiliar endereco_pessoa
 * 
 * @author Yago
 */
@Entity
@Table(name = "endereco_pessoa")
public class EnderecoPessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private EnderecoPessoaPK pk = new EnderecoPessoaPK();

	@Column(name = "dt_alteracao")
	private Date dtAlteracao= new Date();

	@Column(name = "is_endereco_padrao")
	private boolean isEnderecoPadrao;

	/**
	 * Construtor padr�o da classe
	 */
	public EnderecoPessoaBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param endereco
	 * @param pessoa
	 * @param dtAlteracao
	 * @param isEnderecoPadrao
	 */
	public EnderecoPessoaBean(PessoaBean pessoa, EnderecoBean endereco, Date dtAlteracao, boolean isEnderecoPadrao) {
		super();
		pk.setPessoa(pessoa);
		pk.setEndereco(endereco);
		this.dtAlteracao = dtAlteracao;
		this.isEnderecoPadrao = isEnderecoPadrao;
	}

	
	/**
	 * Captura o valor contido no parametro pessoa
	 * 
	 * @return pessoa
	 */
	@JsonIgnore
	public PessoaBean getPessoa() {
		return pk.getPessoa();
	}

	/**
	 * Setar o valor para o parametro pessoa
	 * 
	 * @param pessoa
	 */
	public void setPessoa(PessoaBean pessoa) {
		pk.setPessoa(pessoa);
	}

	/**
	 * Captura o valor contido no parametro pk
	 * 
	 * @return pk
	 */

	/**
	 * Captura o valor contido no parametro endereco
	 * 
	 * @return endereco
	 */
	public EnderecoBean getEndereco() {
		return pk.getEndereco();
	}

	/**
	 * Setar o valor para o parametro endereco
	 * 
	 * @param endereco
	 */
	public void setEndereco(EnderecoBean endereco) {
		pk.setEndereco(endereco);
	}

	public EnderecoPessoaPK getPk() {
		return pk;
	}

	/**
	 * Setar o valor para o parametro pk
	 * 
	 * @param pk
	 */
	public void setPk(EnderecoPessoaPK pk) {
		this.pk = pk;
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
						"\nData altera��o............: " + getDtAlteracao() + //
						"\n� padr�o?.................: " + (isEnderecoPadrao() ? "Sim" : "N�o");//
	}
}
