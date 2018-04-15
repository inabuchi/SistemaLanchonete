package br.com.SistemaLanchonete.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class EnderecoPessoaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name="cd_endereco")
	private int cdEndereco;
	
	@Column(name="cd_pessoa")
	private int cdPessoa;

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
