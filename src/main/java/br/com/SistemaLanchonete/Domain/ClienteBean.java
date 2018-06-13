package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Classe Modelo para os Clientes Cadastrados no Sistema
 * 
 * @author Douglas
 */
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "cd_pessoa")
public class ClienteBean extends PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_cliente")
	private int cdCliente;
	
	@Column(name = "ds_observacao")
	private String dsObservacao;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "cliente")
//	private List<PedidoBean> pedidos = new ArrayList<PedidoBean>();

	public ClienteBean() {
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
	 * @param cdCliente
	 * @param dsObservacao
	 */
	public ClienteBean(int cdPessoa, String dsNome, String dsTelefone1, String dsTelefone2, Date dtCadastro,
			boolean isAtivo, int cdCliente, String dsObservacao) {
		super(cdPessoa, dsNome, dsTelefone1, dsTelefone2, dtCadastro, isAtivo);
		this.cdCliente = cdCliente;
		this.dsObservacao = dsObservacao;
	}

	/**
	 * Captura o valor contido no parametro id
	 * 
	 * @return id
	 */
	public int getCdCliente() {
		return cdCliente;
	}

	/**
	 * Setar o valor para o parametro id
	 * 
	 * @param id
	 */
	public void setCdCliente(int cdCliente) {
		this.cdCliente = cdCliente;
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

	// public List<PedidoBean> getPedidos() {
	// return pedidos;
	// }
	//
	// public void setPedidos(List<PedidoBean> pedidos) {
	// this.pedidos = pedidos;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + cdCliente;
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
		ClienteBean other = (ClienteBean) obj;
		if (cdCliente != other.cdCliente)
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
		return super.toString() + //
				"\nClasse ................: " + getClass().getSimpleName() + //
				"\nIdentif. do Cliente....: " + getCdCliente() + //
				"\nOberva��o..............: " + getDsObservacao();
	}

}