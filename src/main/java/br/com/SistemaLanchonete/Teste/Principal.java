package br.com.SistemaLanchonete.Teste;

import java.util.Date;

import br.com.SistemaLanchonete.Domain.BairroBean;
import br.com.SistemaLanchonete.Domain.EnderecoBean;
import br.com.SistemaLanchonete.Domain.EnderecoPessoaBean;
import br.com.SistemaLanchonete.Domain.EstadoBean;
import br.com.SistemaLanchonete.Domain.LogradouroBean;
import br.com.SistemaLanchonete.Domain.MunicipioBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Service.EnderecoService;

public class Principal {

	public static void main(String[] args) throws BDException {
		EstadoBean est = new EstadoBean(0, "Exterior", "EX");
		
		MunicipioBean mun = new MunicipioBean(0, est, "Rowekseoes");
		
		BairroBean bairro = new BairroBean(0, mun, "Adsrewqw");
		
		LogradouroBean log = new LogradouroBean(0, bairro, 89010600, "Rua woeasudwi");
		
		EnderecoBean end = new EnderecoBean(0, log, 799, "SCI Sistemas contábeis", "");
		
		EnderecoPessoaBean endPessoa = new EnderecoPessoaBean(end, null, new Date(), true);
				
		EnderecoService endSer = new EnderecoService();
		
		endSer.save(endPessoa);
	}

}
