package br.com.SistemaLanchonete.Domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe modelo PrecoProdutoBean para popula��o das tabelas de pre�o
 * 
 * @author Yago Setter
 *
 */

@Entity
@Table(name = "preco_produto")
public class PrecoProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_preco_produto")
	private int cdPrecoProduto;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cd_produto", referencedColumnName = "cd_produto")
	private ProdutoBean produto;

	@Column(name = "vl_preco_atual")
	private float vlPrecoAtual;

	@Column(name = "dt_inicial")
	private Date dtInicial = new Date();

	@Column(name = "dt_final")
	private Date dtFinal = new Date();

	@Column(name = "is_ativo")
	private boolean isAtivo;

	/**
	 * 
	 */
	public PrecoProdutoBean() {

	}

	/**
	 * @param cdPrecoProduto
	 * @param produto
	 * @param vlPrecoAtual
	 * @param dtInicial
	 * @param dtFinal
	 * @param isAtivo
	 */
	public PrecoProdutoBean(int cdPrecoProduto, ProdutoBean produto, float vlPrecoAtual, Date dtInicial, Date dtFinal,
			boolean isAtivo) {
		super();
		this.cdPrecoProduto = cdPrecoProduto;
		this.produto = produto;
		this.vlPrecoAtual = vlPrecoAtual;
		this.dtInicial = dtInicial;
		this.dtFinal = dtFinal;
		this.isAtivo = isAtivo;
	}

	/**
	 * Captura o valor contido no parametro cdPrecoProduto
	 * 
	 * @return cdPrecoProduto
	 */

	public int getCdPrecoProduto() {
		return cdPrecoProduto;
	}

	/**
	 * Setar o valor para o parametro cdPrecoProduto
	 * 
	 * @param cdPrecoProduto
	 */
	public void setCdPrecoProduto(int cdPrecoProduto) {
		this.cdPrecoProduto = cdPrecoProduto;
	}

	/**
	 * Captura o valor contido no parametro produto
	 * 
	 * @return produto
	 */

	public ProdutoBean getProduto() {
		return produto;
	}

	/**
	 * Setar o valor para o parametro produto
	 * 
	 * @param produto
	 */
	public void setProduto(ProdutoBean produto) {
		this.produto = produto;
	}

	/**
	 * Captura o valor contido no parametro vlPrecoAtual
	 * 
	 * @return vlPrecoAtual
	 */

	public float getVlPrecoAtual() {
		return vlPrecoAtual;
	}

	/**
	 * Setar o valor para o parametro vlPrecoAtual
	 * 
	 * @param vlPrecoAtual
	 */
	public void setVlPrecoAtual(float vlPrecoAtual) {
		this.vlPrecoAtual = vlPrecoAtual;
	}

	/**
	 * Captura o valor contido no parametro dtInicial
	 * 
	 * @return dtInicial
	 */

	public Date getDtInicial() {
		return dtInicial;
	}

	/**
	 * Setar o valor para o parametro dtInicial
	 * 
	 * @param dtInicial
	 */
	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	/**
	 * Captura o valor contido no parametro dtFinal
	 * 
	 * @return dtFinal
	 */

	public Date getDtFinal() {
		return dtFinal;
	}

	/**
	 * Setar o valor para o parametro dtFinal
	 * 
	 * @param dtFinal
	 */
	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdPrecoProduto;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PrecoProdutoBean)) {
			return false;
		}
		PrecoProdutoBean other = (PrecoProdutoBean) obj;
		if (cdPrecoProduto != other.cdPrecoProduto) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PrecoProdutoBean [cdPrecoProduto=" + cdPrecoProduto + ", produto=" + produto + ", vlPrecoAtual="
				+ vlPrecoAtual + ", dtInicial=" + dtInicial + ", dtFinal=" + dtFinal + ", isAtivo=" + isAtivo + "]";
	}
}
