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
 * Classe de Bairros cadastrados no sistema
 * 
 * @author Patrick
 */
@Entity
@Table(name = "bairro")
public class BairroBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_bairro")
	private int cdBairro;

	@ManyToOne
	@JoinColumn(name = "cd_municipio", referencedColumnName = "cd_municipio")
	private MunicipioBean municipio;

	@Column(name = "ds_bairro")
	private String dsBairro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bairro", targetEntity = LogradouroBean.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LogradouroBean> logradouros = new ArrayList<LogradouroBean>();

	/**
	 * Construtor padrão da classe
	 */
	public BairroBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdBairro
	 * @param municipio
	 * @param dsBairro
	 */
	public BairroBean(int cdBairro, MunicipioBean municipio, String dsBairro) {
		this.cdBairro = cdBairro;
		this.municipio = municipio;
		this.dsBairro = dsBairro;
	}

	/**
	 * Captura o valor contido no parametro id
	 * 
	 * @return cdBairro
	 */

	public int getCdBairro() {
		return cdBairro;
	}

	/**
	 * Setar o valor para o parametro cdBairro
	 * 
	 * @param cdBairro
	 */
	public void setCdBairro(int cdBairro) {
		this.cdBairro = cdBairro;
	}

	/**
	 * Captura o valor contido no parametro municipio
	 * 
	 * @return municipio
	 */
	public MunicipioBean getMunicipio() {
		return municipio;
	}

	/**
	 * Setar o valor para o parametro municipio
	 * 
	 * @param municipio
	 */
	public void setMunicipio(MunicipioBean municipio) {
		this.municipio = municipio;
	}

	/**
	 * Captura o valor contido no parametro dsBairro
	 * 
	 * @return dsBairro
	 */
	public String getDsBairro() {
		return dsBairro;
	}

	/**
	 * Setar o valor para o parametro dsBairro
	 * 
	 * @param dsBairro
	 */
	public void setDsBairro(String dsBairro) {
		this.dsBairro = dsBairro;
	}

	/**
	 * Retorna a lista de logradouros desse bairros
	 * 
	 * @return logradouros
	 */
	public List<LogradouroBean> getLogradouros() {
		return logradouros;
	}

	/**
	 * Setar o valor para o parametro logradouros
	 * 
	 * @param logradouros
	 */
	public void setLogradouros(List<LogradouroBean> logradouros) {
		this.logradouros = logradouros;
	}

	@Override
	public String toString() {
		return "\nClasse ....................: " + getClass().getSimpleName() + //
				"\nNome......................: " + getDsBairro() + //
				"\nMunicipio.................: " + getMunicipio() != null ? getMunicipio().getDsMunicipio()
						: "Sem munic�pio.";//
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdBairro;
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
		BairroBean other = (BairroBean) obj;
		if (cdBairro != other.cdBairro)
			return false;
		return true;
	}
}
