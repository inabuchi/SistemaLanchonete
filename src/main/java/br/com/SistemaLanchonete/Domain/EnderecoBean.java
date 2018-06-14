package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.ArrayList;
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
 * Classe de Endere�os cadastrados no sistema
 * 
 * @author Yago
 */
@Entity
@Table(name = "endereco")
public class EnderecoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_endereco")
	private int cdEndereco;

	@ManyToOne
	@JoinColumn(name = "cd_logradouro", referencedColumnName = "cd_logradouro")
	private LogradouroBean logradouro;

	@Column(name = "cd_numero")
	private int cdNumero;

	@Column(name = "ds_complemento")
	private String dsComplemento;

	@Column(name = "ds_observacao")
	private String dsObservacao;

	@JsonIgnore
	@OneToMany(mappedBy = "pk.endereco", //
			targetEntity = EnderecoPessoaBean.class, //
			fetch = FetchType.LAZY, //
			cascade = CascadeType.ALL)
	private List<EnderecoPessoaBean> enderecoPessoas = new ArrayList<EnderecoPessoaBean>();

	/**
	 * Construtor padr�o da classe
	 */
	public EnderecoBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdEndereco
	 * @param logradouro
	 * @param cdNumero
	 * @param dsComplemento
	 * @param dsObservacao
	 */
	public EnderecoBean(int cdEndereco, LogradouroBean logradouro, int cdNumero, String dsComplemento,
			String dsObservacao) {
		super();
		this.cdEndereco = cdEndereco;
		this.logradouro = logradouro;
		this.cdNumero = cdNumero;
		this.dsComplemento = dsComplemento;
		this.dsObservacao = dsObservacao;
	}

	/**
	 * Captura o valor contido no parametro cdEndereco
	 * 
	 * @return cdEndereco
	 */
	public int getCdEndereco() {
		return cdEndereco;
	}

	/**
	 * Setar o valor para o parametro cdEndereco
	 * 
	 * @param cdEndereco
	 */
	public void setCdEndereco(int cdEndereco) {
		this.cdEndereco = cdEndereco;
	}

	/**
	 * Captura o valor contido no parametro logradouro
	 * 
	 * @return logradouro
	 */
	public LogradouroBean getLogradouro() {
		return logradouro;
	}

	/**
	 * Setar o valor para o parametro logradouro
	 * 
	 * @param logradouro
	 */
	public void setLogradouro(LogradouroBean logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * Captura o valor contido no parametro idcdNumero
	 * 
	 * @return cdNumero
	 */
	public int getCdNumero() {
		return cdNumero;
	}

	/**
	 * Setar o valor para o parametro cdNumero
	 * 
	 * @param cdNumero
	 */
	public void setCdNumero(int cdNumero) {
		this.cdNumero = cdNumero;
	}

	/**
	 * Captura o valor contido no parametro dsComplemento
	 * 
	 * @return dsComplemento
	 */
	public String getDsComplemento() {
		return dsComplemento;
	}

	/**
	 * Setar o valor para o parametro dsComplemento
	 * 
	 * @param dsComplemento
	 */
	public void setDsComplemento(String dsComplemento) {
		this.dsComplemento = dsComplemento;
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
	 * Retorna a lista de pessoas desse endere�o
	 * 
	 * @return enderecoPessoas
	 */
	public List<EnderecoPessoaBean> getEnderecoPessoas() {
		return enderecoPessoas;
	}

	/**
	 * Setar o valor para o parametro enderecoPessoas
	 * 
	 * @param enderecoPessoas
	 */
	public void setEnderecoPessoas(List<EnderecoPessoaBean> enderecoPessoas) {
		this.enderecoPessoas = enderecoPessoas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdEndereco;
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
		EnderecoBean other = (EnderecoBean) obj;
		if (cdEndereco != other.cdEndereco)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nClasse ....................: " + getClass().getSimpleName() + //
				"\nNumero....................: " + getCdNumero() + //
				"\nLogradouro................: " + getLogradouro() != null ? getLogradouro().getDsLogradouro()
						: "Sem logradouro." + //
								"\nComplemento...............: " + getDsComplemento() + //
								"\nObservacao................: " + getDsObservacao();//
	}
}
