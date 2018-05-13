package br.com.SistemaLanchonete.Service;

import br.com.SistemaLanchonete.Domain.BairroBean;
import br.com.SistemaLanchonete.Domain.EnderecoBean;
import br.com.SistemaLanchonete.Domain.EnderecoPessoaBean;
import br.com.SistemaLanchonete.Domain.EstadoBean;
import br.com.SistemaLanchonete.Domain.LogradouroBean;
import br.com.SistemaLanchonete.Domain.MunicipioBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class EnderecoService {
	private String retorno = "";
	GenericDAO<EnderecoBean> enderecoDao = new GenericDAO<EnderecoBean>();
	GenericDAO<EnderecoPessoaBean> enderecoPessoaDao = new GenericDAO<EnderecoPessoaBean>();
	GenericDAO<BairroBean> bairroDao = new GenericDAO<BairroBean>();
	GenericDAO<EstadoBean> estadoDao = new GenericDAO<EstadoBean>();
	GenericDAO<LogradouroBean> logradouroDao = new GenericDAO<LogradouroBean>();
	GenericDAO<MunicipioBean> municipioDao = new GenericDAO<MunicipioBean>();
	
	Class<EnderecoBean> enderecoBean;
	Class<EnderecoPessoaBean> enderecoPessoaBean;
	Class<BairroBean> bairroBean;
	Class<EstadoBean> estadoBean;
	Class<LogradouroBean> logradouroBean;
	Class<MunicipioBean> municipioBean;

	public String save(EnderecoBean endereco, 
						EnderecoPessoaBean enderecoPessoa, 
						BairroBean bairro, 
						EstadoBean estado,
						LogradouroBean logradouro,
						MunicipioBean municipio) throws BDException {
		
		SalvarEndereco(endereco);
		SalvarEnderecoPessoa(enderecoPessoa);
		SalvarBairro(bairro);
		SalvarEstado(estado);
		SalvarLogradouro(logradouro);
		SalvarMunicipio(municipio);
		
		return retorno;
	}
	
	private String SalvarEndereco(EnderecoBean endereco) throws BDException {
		if (endereco.getCdEndereco() == 0 && endereco.getCdNumero() == 0) {
			try {
				enderecoDao.save(endereco, 0);
		} catch (BDException e) {
			throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados salvos com sucesso na tabela";
	} else {
		try {
			enderecoDao.save(endereco, endereco.getCdEndereco());
		} catch (BDException e) {
			throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados atualizados com sucesso na tabela";
	}
	return retorno;
	}
	
	private String SalvarEnderecoPessoa(EnderecoPessoaBean enderecoPessoa) throws BDException {
		return retorno = "Salvo com Sucesso";
	}
	
	private String SalvarBairro(BairroBean bairro) throws BDException {
		if (bairro.getCdBairro() == 0) {
			try {
				bairroDao.save(bairro, 0);
		} catch (BDException e) {
			throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados salvos com sucesso na tabela";
	} else {
		try {
			bairroDao.save(bairro, bairro.getCdBairro());
		} catch (BDException e) {
			throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados atualizados com sucesso na tabela";
	}
	return retorno;
	}
	
	private String SalvarEstado(EstadoBean estado) throws BDException {
		if (estado.getCdEstado() == 0) {
			try {
				estadoDao.save(estado, 0);
		} catch (BDException e) {
			throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados salvos com sucesso na tabela";
	} else {
		try {
			estadoDao.save(estado, estado.getCdEstado());
		} catch (BDException e) {
			throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados atualizados com sucesso na tabela";
	}
	return retorno;
	}
	
	private String SalvarLogradouro(LogradouroBean logradouro) throws BDException {
		if (logradouro.getCdCep() == 0 && logradouro.getCdLogradouro() == 0) {
			try {
				logradouroDao.save(logradouro, 0);
		} catch (BDException e) {
			throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados salvos com sucesso na tabela";
	} else {
		try {
			logradouroDao.save(logradouro, logradouro.getCdCep());
		} catch (BDException e) {
			throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados atualizados com sucesso na tabela";
	}
	return retorno;
	}
	
	private String SalvarMunicipio(MunicipioBean municipio) throws BDException {
		if (municipio.getCdMunicipio() == 0) {
			try {
				municipioDao.save(municipio, 0);
		} catch (BDException e) {
			throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados salvos com sucesso na tabela";
	} else {
		try {
			municipioDao.save(municipio, municipio.getCdMunicipio());
		} catch (BDException e) {
			throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados atualizados com sucesso na tabela";
	}
	return retorno;
	}
}
