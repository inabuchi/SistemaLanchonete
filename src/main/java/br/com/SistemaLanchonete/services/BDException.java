package br.com.SistemaLanchonete.services;

public class BDException extends Exception {

	private static final long serialVersionUID = 1L;

	public BDException(String mensagem, EErrosBD erro) {
		super(erro.getErro() + "#" + mensagem);
	}
}