package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Chave composta da classe EnderecoPessoaBean
 * 
 * @author Yago
 */

@Embeddable
public class EnderecoPessoaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "cd_endereco", nullable = false)
	private EnderecoBean endereco;
	
	@ManyToOne
	@JoinColumn(name = "cd_pessoa", nullable = false)
	private PessoaBean pessoa;


	public EnderecoBean getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoBean endereco) {
		this.endereco = endereco;
	}

	public PessoaBean getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaBean pessoa) {
		this.pessoa = pessoa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}

}
