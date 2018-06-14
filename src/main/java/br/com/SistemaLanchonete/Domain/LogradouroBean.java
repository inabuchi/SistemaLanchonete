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
 * Classe de Logradouros cadastrados no sistema
 * 
 * @author Patrick
 */
@Entity
@Table(name = "logradouro")
public class LogradouroBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_logradouro")
	private int cdLogradouro;

	@ManyToOne
	@JoinColumn(name = "cd_bairro", referencedColumnName = "cd_bairro")
	private BairroBean bairro;

	@Column(name = "cd_cep")
	private int cdCep;

	@Column(name = "ds_logradouro")
	private String dsLogradouro;
	@JsonIgnore
	@OneToMany(mappedBy = "logradouro", targetEntity = EnderecoBean.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EnderecoBean> enderecos = new ArrayList<EnderecoBean>();

	/**
	 * Construtor padrão da classe
	 */
	public LogradouroBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdLogradouro
	 * @param bairro
	 * @param cdCep
	 * @param dsLogradouro
	 */
	public LogradouroBean(int cdLogradouro, BairroBean bairro, int cdCep, String dsLogradouro) {
		super();
		this.cdLogradouro = cdLogradouro;
		this.bairro = bairro;
		this.cdCep = cdCep;
		this.dsLogradouro = dsLogradouro;
	}

	/**
	 * Captura o valor contido no parametro id
	 * 
	 * @return cdLogradouro
	 */

	public int getCdLogradouro() {
		return cdLogradouro;
	}

	/**
	 * Setar o valor para o parametro cdLogradouro
	 * 
	 * @param cdLogradouro
	 */
	public void setCdLogradouro(int cdLogradouro) {
		this.cdLogradouro = cdLogradouro;
	}

	/**
	 * Captura o valor contido no parametro bairro
	 * 
	 * @return bairro
	 */
	public BairroBean getBairro() {
		return bairro;
	}

	/**
	 * Setar o valor para o parametro bairro
	 * 
	 * @param bairro
	 */
	public void setBairro(BairroBean bairro) {
		this.bairro = bairro;
	}

	/**
	 * Captura o valor contido no parametro cdCep
	 * 
	 * @return cdCep
	 */
	public int getCdCep() {
		return cdCep;
	}

	/**
	 * Setar o valor para o parametro cdCep
	 * 
	 * @param cdCep
	 */
	public void setCdCep(int cdCep) {
		this.cdCep = cdCep;
	}

	/**
	 * Captura o valor contido no parametro dsLogradouro
	 * 
	 * @return dsLogradouro
	 */
	public String getDsLogradouro() {
		return dsLogradouro;
	}

	/**
	 * Setar o valor para o parametro dsLogradouro
	 * 
	 * @param dsLogradouro
	 */
	public void setDsLogradouro(String dsLogradouro) {
		this.dsLogradouro = dsLogradouro;
	}

	/**
	 * Retorna a lista de endere�os desse logradouro
	 * 
	 * @return enderecos
	 */
	public List<EnderecoBean> getEnderecos() {
		return enderecos;
	}

	/**
	 * Setar o valor para o parametro enderecos
	 * 
	 * @param enderecos
	 */
	public void setEndereco(List<EnderecoBean> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public String toString() {
		return "\nClasse ....................: " + getClass().getSimpleName() + //
				"\nNome......................: " + getDsLogradouro() + //
				"\nBairro....................: " + getBairro() != null ? getBairro().getDsBairro() : "Sem bairro." + //
						"\nCep.......................: " + getCdCep();//
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdLogradouro;
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
		LogradouroBean other = (LogradouroBean) obj;
		if (cdLogradouro != other.cdLogradouro)
			return false;
		return true;
	}
}
