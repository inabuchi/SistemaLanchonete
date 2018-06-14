package br.com.SistemaLanchonete.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe Modelo para os Funcion�rios da empresa
 * 
 * @author Patrick
 */

@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "cd_pessoa")
public class FuncionarioBean extends PessoaBean {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_funcionario")
	private int cdFuncionario;
	@Column(name = "ds_cargo")
	private String dsCargo;
	@Column(name = "ds_login")
	private String dsLogin;
	@Column(name = "ds_senha")
	private String dsSenha;
	@Column(name = "cd_nivel")
	private int cdNivel;
	@JsonIgnore
	@OneToMany(mappedBy = "funcionario", targetEntity = CaixaBean.class, fetch =
	FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CaixaBean> caixa = new ArrayList<CaixaBean>();

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
	 * @param dsCargo
	 * @param dtAdmissao
	 * @param dsLogin
	 * @param dsSenha
	 * @param cdNivel
	 */
	public FuncionarioBean(int cdPessoa, String dsNome, String dsTelefone1, String dsTelefone2, Date dtCadastro,
			boolean isAtivo, int cdFuncionario, String dsCargo, String dsLogin, String dsSenha, int cdNivel) {
		super(cdPessoa, dsNome, dsTelefone1, dsTelefone2, dtCadastro, isAtivo);
		this.cdFuncionario = cdFuncionario;
		this.dsCargo = dsCargo;
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
	public String getDsCargo() {
		return dsCargo;
	}

	/**
	 * Setar o valor para o parametro cargo
	 * 
	 * @param dsCargo
	 */
	public void setDsCargo(String dsCargo) {
		this.dsCargo = dsCargo;
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

	/**
	 * Retorna a lista de Caixas Desse Funcionario
	 *
	 * @return caixa
	 */
	public List<CaixaBean> getCaixa() {
		return caixa;
	}

	/**
	 * Setar o valor para o parametro caixa
	 *
	 * @param caixa
	 */
	public void setCaixa(List<CaixaBean> caixa) {
		this.caixa = caixa;
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
				"\nIdentif. do Funcion�rio: " + getCdFuncionario() + //
				"\nCargo..................: " + getDsCargo() + //
				"\nApelido................: " + getDsLogin() + //
				"\nTelefone...............: " + getDsSenha() + //
				"\nN�vel Permiss�o Sist...: " + getCdNivel();
	}
}