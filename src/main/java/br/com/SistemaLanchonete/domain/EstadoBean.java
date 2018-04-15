package br.com.SistemaLanchonete.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
/**
 * Classe de Estados cadastrados no sistema
 * 
 * @author Yago
 */
@Entity
@Table(name = "estado")
public class EstadoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_estado")
	private int cdEstado;
	@Column(name = "ds_estado")
	private String dsEstado;
	@Column(name = "ds_sigla")
	private String dsSigla;
	@OneToMany(mappedBy = "estado", targetEntity = MunicipioBean.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MunicipioBean> municipios;

	/**
	 * Construtor padrão da classe
	 */
	public EstadoBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdEstado
	 * @param dsEstado
	 * @param dsSigla
	 */
	public EstadoBean(int cdEstado, String dsEstado, String dsSigla) {
		this.cdEstado = cdEstado;
		this.dsEstado = dsEstado;
		this.dsSigla = dsSigla;
	}

	/**
	 * Captura o valor contido no parametro id
	 * 
	 * @return cdEstado
	 */
	public int getCdEstado() {
		return cdEstado;
	}

	/**
	 * Setar o valor para o parametro id
	 * 
	 * @param cdPessoa
	 */
	public void setCdEstado(int cdEstado) {
		this.cdEstado = cdEstado;
	}

	/**
	 * Captura o valor contido no parametro dsEstado
	 * 
	 * @return dsEstado
	 */
	public String getDsEstado() {
		return dsEstado;
	}

	/**
	 * Setar o valor para o parametro dsEstado
	 * 
	 * @param dsEstado
	 */
	public void setDsEstado(String dsEstado) {
		this.dsEstado = dsEstado;
	}

	/**
	 * Captura o valor contido no parametro dsSigla
	 * 
	 * @return dsSigla
	 */
	public String getDsSigla() {
		return dsSigla;
	}

	/**
	 * Setar o valor para o parametro dsSigla
	 * 
	 * @param dsSigla
	 */
	public void setDsSigla(String dsSigla) {
		this.dsSigla = dsSigla;
	}

	/**
	 * Retorna a lista de municipios desse estado
	 * 
	 *	@return municipio
	 */
	public List<MunicipioBean> getMunicipios() {
		return municipios;
	}

	/**
	 * Setar o valor para o parametro municipios
	 * 
	 * @param municipios
	 */
	public void setMunicipios(List<MunicipioBean> municipios) {
		this.municipios = municipios;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdEstado;
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
		EstadoBean other = (EstadoBean) obj;
		if (cdEstado != other.cdEstado)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nClasse .................: " + getClass().getSimpleName() + //
				"\nIdentificador do Estado: " + getDsSigla() + //
				"\nNome...................: " + getDsEstado();//
	}

}
