package br.com.SistemaLanchonete.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe de cargos Cadastrados no Sistema
 * 
 * @author Douglas
 */
@Entity
@Table(name = "cargo")
public class CargoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_cargo")
	private int cdCargo;

	@Column(name = "ds_cargo")
	private String dsCargo;
	@OneToMany(mappedBy = "cargo", targetEntity = FuncionarioBean.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<FuncionarioBean> funcionarios;

	public CargoBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdCargo
	 * @param dsCargo
	 */
	public CargoBean(int cdCargo, String dsCargo) {
		this.cdCargo = cdCargo;
		this.dsCargo = dsCargo;
	}

	/**
	 * Captura o valor contido no parametro cdCargo
	 * 
	 * @return cdCargo
	 */
	public int getCdCargo() {
		return cdCargo;
	}

	/**
	 * Setar o valor para o parametro cdCargo
	 * 
	 * @param cdCargo
	 */
	public void setCdCargo(int cdCargo) {
		this.cdCargo = cdCargo;
	}

	/**
	 * Captura o valor contido no parametro dsCargo
	 * 
	 * @return dsCargo
	 */
	public String getDsCargo() {
		return dsCargo;
	}

	/**
	 * Setar o valor para o parametro dsCargo
	 * 
	 * @param dsCargo
	 */
	public void setDsCargo(String dsCargo) {
		this.dsCargo = dsCargo;
	}

	/**
	 * Retorna a lista de funcionarios cadastrados com determinado cargo
	 * 
	 *	@return funcionarios
	 */
	public List<FuncionarioBean> getFuncionarios() {
		return funcionarios;
	}

	/**
	 * Setar o valor para o parametro funcionarios
	 * 
	 * @param funcionarios
	 */
	public void setFuncionarios(List<FuncionarioBean> funcionarios) {
		this.funcionarios = funcionarios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdCargo;
		result = prime * result + ((dsCargo == null) ? 0 : dsCargo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CargoBean other = (CargoBean) obj;
		if (cdCargo != other.cdCargo)
			return false;
		if (dsCargo == null) {
			if (other.dsCargo != null)
				return false;
		} else if (!dsCargo.equals(other.dsCargo))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nClasse.................: " + getClass().getSimpleName() + //
				"\nIdentificação do Cargo.: " + getCdCargo() + //
				"\nDescrição do Cargo.....: " + getDsCargo();
	}

}
