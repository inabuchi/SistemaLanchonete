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
 * Classe de Munic�pios cadastrados no sistema
 * 
 * @author Patrick
 */
@Entity
@Table(name = "municipio")
public class MunicipioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_municipio")
	private int cdMunicipio;

	@ManyToOne
	@JoinColumn(name = "cd_estado", referencedColumnName = "cd_estado")
	private EstadoBean estado;

	@Column(name = "ds_municipio")
	private String dsMunicipio;

	@JsonIgnore
	@OneToMany(mappedBy = "municipio", targetEntity = BairroBean.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BairroBean> bairros = new ArrayList<BairroBean>();

	/**
	 * Construtor padrão da classe
	 */
	public MunicipioBean() {

	}

	/**
	 * Construtor da classe
	 *
	 * @param cdMunicipio
	 * @param estado
	 * @param dsMunicipio
	 */
	public MunicipioBean(int cdMunicipio, EstadoBean estado, String dsMunicipio) {
		super();
		this.cdMunicipio = cdMunicipio;
		this.estado = estado;
		this.dsMunicipio = dsMunicipio;
	}

	/**
	 * Captura o valor contido no parametro id
	 * 
	 * @return cdMunicipio
	 */

	public int getCdMunicipio() {
		return cdMunicipio;
	}

	/**
	 * Setar o valor para o parametro cdMunicipio
	 * 
	 * @param cdMunicipio
	 */
	public void setCdMunicipio(int cdMunicipio) {
		this.cdMunicipio = cdMunicipio;
	}

	/**
	 * Captura o valor contido no parametro estado
	 * 
	 * @return estado
	 */
	public EstadoBean getEstado() {
		return estado;
	}

	/**
	 * Setar o valor para o parametro estado
	 * 
	 * @param estado
	 */
	public void setEstado(EstadoBean estado) {
		this.estado = estado;
	}

	/**
	 * Captura o valor contido no parametro dsMunicipio
	 * 
	 * @return dsMunicipio
	 */
	public String getDsMunicipio() {
		return dsMunicipio;
	}

	/**
	 * Setar o valor para o parametro dsMunicipio
	 * 
	 * @param dsMunicipio
	 */
	public void setDsMunicipio(String dsMunicipio) {
		this.dsMunicipio = dsMunicipio;
	}

	/**
	 * Retorna a lista de bairros desse municipio
	 * 
	 * @return bairros
	 */
	public List<BairroBean> getBairros() {
		return bairros;
	}

	/**
	 * Setar o valor para o parametro bairros
	 * 
	 * @param bairros
	 */
	public void setBairros(List<BairroBean> bairros) {
		this.bairros = bairros;
	}

	@Override
	public String toString() {
		return "\nClasse ....................: " + getClass().getSimpleName() + //
				"\nNome......................: " + getDsMunicipio() + //
				"\nEstado....................: " + getEstado() != null ? getEstado().getDsSigla() : "Sem estado.";//
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdMunicipio;
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
		MunicipioBean other = (MunicipioBean) obj;
		if (cdMunicipio != other.cdMunicipio)
			return false;
		return true;
	}
}
