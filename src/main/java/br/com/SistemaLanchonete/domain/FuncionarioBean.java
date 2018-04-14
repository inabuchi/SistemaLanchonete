package br.com.SistemaLanchonete.Domain;

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
 * Classe Modelo para os Funcionários da empresa
 * 
 * @author Douglas
 */

@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "cd_pessoa")
public class FuncionarioBean extends PessoaBean {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_funcionario")
	private int cdFuncionario;
	@ManyToOne
	@JoinColumn(name = "cd_cargo")
	private CargoBean cargo;

	@Column(name = "dt_admissao")
	private Date dtAdmissao;
	@Column(name = "ds_login")
	private String dsLogin;
	@Column(name = "ds_senha")
	private String dsSenha;
	@Column(name = "cd_nivel")
	private int cdNivel;

	/**
	 * Contrutor padrão da classe
	 */
	public FuncionarioBean() {
	}

	/**
	 * Construtor da classe
	 *
	 * @param cdPessoa
	 * @param dsNome
	 * @param dsTelefone1
	 * @param dsTelefone2
	 * @param dtCadastro
	 * @param isAtivo
	 * @param cdFuncionario
	 * @param cargo
	 * @param dtAdmissao
	 * @param dsLogin
	 * @param dsSenha
	 * @param cdNivel
	 */
	public FuncionarioBean(int cdPessoa, String dsNome, String dsTelefone1, String dsTelefone2, Date dtCadastro,
			boolean isAtivo, int cdFuncionario, CargoBean cargo, Date dtAdmissao, String dsLogin, String dsSenha,
			int cdNivel) {
		super(cdPessoa, dsNome, dsTelefone1, dsTelefone2, dtCadastro, isAtivo);
		this.cdFuncionario = cdFuncionario;
		this.cargo = cargo;
		this.dtAdmissao = dtAdmissao;
		this.dsLogin = dsLogin;
		this.dsSenha = dsSenha;
		this.cdNivel = cdNivel;
	}

	/**
	 * Captura o valor contido no parametro cdFuncionario
	 * 
	 * @return cdFuncionario
	 */
	public int getCdFuncionario() {
		return cdFuncionario;
	}

	/**
	 * Setar o valor para o parametro cdFuncionario
	 * 
	 * @param cdFuncionario
	 */
	public void setCdFuncionario(int cdFuncionario) {
		this.cdFuncionario = cdFuncionario;
	}

	/**
	 * Captura o valor contido no parametro cargo
	 * 
	 * @return cargo
	 */
	public CargoBean getCargo() {
		return cargo;
	}

	/**
	 * Setar o valor para o parametro cargo
	 * 
	 * @param cargo
	 */
	public void setCargo(CargoBean cargo) {
		this.cargo = cargo;
	}

	/**
	 * Captura o valor contido no parametro dtAdmissao
	 * 
	 * @return dtAdmissao
	 */
	public Date getDtAdmissao() {
		return dtAdmissao;
	}

	/**
	 * Setar o valor para o parametro dtAdmissao
	 * 
	 * @param dtAdmissao
	 */
	public void setDtAdmissao(Date dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	/**
	 * Captura o valor contido no parametro dsLogin
	 * 
	 * @return dsLogin
	 */
	public String getDsLogin() {
		return dsLogin;
	}

	/**
	 * Setar o valor para o parametro dsLogin
	 * 
	 * @param dsLogin
	 */
	public void setDsLogin(String dsLogin) {
		this.dsLogin = dsLogin;
	}

	/**
	 * Captura o valor contido no parametro dsSenha
	 * 
	 * @return dsSenha
	 */
	public String getDsSenha() {
		return dsSenha;
	}

	/**
	 * Setar o valor para o parametro dsSenha
	 * 
	 * @param dsSenha
	 */
	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}

	/**
	 * Captura o valor contido no parametro cdNivel
	 * 
	 * @return cdNivel
	 */
	public int getCdNivel() {
		return cdNivel;
	}

	/**
	 * Setar o valor para o parametro cdNivel
	 * 
	 * @param cdNivel
	 */
	public void setCdNivel(int cdNivel) {
		this.cdNivel = cdNivel;
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
		result = prime * result + cdFuncionario;
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioBean other = (FuncionarioBean) obj;
		if (cdFuncionario != other.cdFuncionario)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + //
				"\nClasse ................: " + getClass().getSimpleName() + //
				"\nIdentif. do Funcionário: " + getCdFuncionario() + //
				"\nCargo..................: " + getCargo() + //
				"\nApelido................: " + getDsLogin() + //
				"\nTelefone...............: " + getDsSenha() + //
				"\nNível Permissão Sist...: " + getCdNivel() + //
				"\nData Admissão..........: " + getDtAdmissao(); //
	}
}