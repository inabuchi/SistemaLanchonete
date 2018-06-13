package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Super Classe de Pessoas cadastradas no sistema
 * 
 * @author Douglas
 */
@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_pessoa")
	private int cdPessoa;
	
	@Column(name = "ds_nome")
	private String dsNome;
	
	@Column(name = "ds_telefone1")
	private String dsTelefone1;
	
	@Column(name = "ds_telefone2")
	private String dsTelefone2;
	
	@Column(name = "dt_cadastro")
	private Date dtCadastro = new Date();
	
	@Column(name = "is_ativo")
	private boolean isAtivo;
	
	@OneToMany(mappedBy = "pk.pessoa", //
			targetEntity = EnderecoPessoaBean.class, //
			fetch = FetchType.EAGER/*, //
			cascade = CascadeType.ALL*/)
	private List<EnderecoPessoaBean> enderecoPessoas = new ArrayList<EnderecoPessoaBean>();

	/**
	 * Construtor padr�o da classe
	 */
	public PessoaBean() {
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
	 */
	public PessoaBean(int cdPessoa, String dsNome, String dsTelefone1, String dsTelefone2, Date dtCadastro,
			boolean isAtivo) {
		this.cdPessoa = cdPessoa;
		this.dsNome = dsNome;
		this.dsTelefone1 = dsTelefone1;
		this.dsTelefone2 = dsTelefone2;
		this.dtCadastro = dtCadastro;
		this.isAtivo = isAtivo;
	}

	/**
	 * Captura o valor contido no parametro id
	 * 
	 * @return cdPessoa
	 */
	public int getCdPessoa() {
		return cdPessoa;
	}

	/**
	 * Setar o valor para o parametro id
	 * 
	 * @param cdPessoa
	 */
	public void setCdPessoa(int cdPessoa) {
		this.cdPessoa = cdPessoa;
	}

	/**
	 * Captura o valor contido no parametro dsNome
	 * 
	 * @return dsNome
	 */
	public String getDsNome() {
		return dsNome;
	}

	/**
	 * Setar o valor para o parametro dsNome
	 * 
	 * @param dsNome
	 */
	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	/**
	 * Captura o valor contido no parametro dsTelefone1
	 * 
	 * @return dsTelefone1
	 */
	public String getDsTelefone1() {
		return dsTelefone1;
	}

	/**
	 * Setar o valor para o parametro dsTelefone1
	 * 
	 * @param dsTelefone1
	 */
	public void setDsTelefone1(String dsTelefone1) {
		this.dsTelefone1 = dsTelefone1;
	}

	/**
	 * Captura o valor contido no parametro dsTelefone2
	 * 
	 * @return dsTelefone2
	 */
	public String getDsTelefone2() {
		return dsTelefone2;
	}

	/**
	 * Setar o valor para o parametro dsTelefone2
	 * 
	 * @param dsTelefone2
	 */
	public void setDsTelefone2(String dsTelefone2) {
		this.dsTelefone2 = dsTelefone2;
	}

	/**
	 * Captura o valor contido no parametro dtCadastro
	 * 
	 * @return dtCadastro
	 */
	public Date getDtCadastro() {
		return dtCadastro;
	}

	/**
	 * Setar o valor para o parametro dtCadastro
	 * 
	 * @param dtCadastro
	 */
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	/**
	 * Captura o valor contido no parametro isAtivo
	 * 
	 * @return isAtivo
	 */
	public boolean isAtivo() {
		return isAtivo;
	}

	/**
	 * Setar o valor para o parametro isAtivo
	 * 
	 * @param isAtivo
	 */
	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	/**
	 * Retorna a lista de enderecos dessa pesoa
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
	public void addEnderecoPessoa(EnderecoPessoaBean enderecoPessoa) {
		enderecoPessoa.setPessoa(this);
		this.enderecoPessoas.add(enderecoPessoa);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdPessoa;
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
		PessoaBean other = (PessoaBean) obj;
		if (cdPessoa != other.cdPessoa)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nClasse ................: " + getClass().getSimpleName() + //
				"\nIdentificador da Pessoa: " + getCdPessoa() + //
				"\nNome...................: " + getDsNome() + //
				"\nApelido................: " + getDsTelefone1() + //
				"\nTelefone...............: " + getDsTelefone2() + //
				"\nData Cadastro..........: " + getDtCadastro() + //
				"\nAtivo..................: " + (isAtivo() ? "Sim" : "N�o");//
	}

}
