package br.com.SistemaLanchonete.Validacao;

public class SistemaException extends Exception {

	private static final long serialVersionUID = 1L;

	public SistemaException(String msgErro, EErrosSistema erro) {
		super(msgErro + " <--> " + erro.getErro());
	}
}
