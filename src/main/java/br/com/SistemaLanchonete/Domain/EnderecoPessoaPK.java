	package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Column;

/**
 * Chave composta da classe EnderecoPessoaBean
 * 
 * @author Yago
 */

@Embeddable
public class EnderecoPessoaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "cd_endereco", nullable = false)	
	private int cdEndereco;
	
	@Column(name = "cd_pessoa", nullable = false)	
	private int cdPessoa;

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
	 * Captura o valor contido no parametro cdPessoa
	 * 
	 * @return cdPessoa
	 */
	public int getCdPessoa() {
		return cdPessoa;
	}

	/**
	 * Setar o valor para o parametro cdPessoa
	 * 
	 * @param cdPessoa
	 */
	public void setCdPessoa(int cdPessoa) {
		this.cdPessoa = cdPessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdEndereco;
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
		EnderecoPessoaPK other = (EnderecoPessoaPK) obj;
		if (cdEndereco != other.cdEndereco)
			return false;
		if (cdPessoa != other.cdPessoa)
			return false;
		return true;
	}
}
