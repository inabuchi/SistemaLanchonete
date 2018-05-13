package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;

import br.com.SistemaLanchonete.Domain.BairroBean;
import br.com.SistemaLanchonete.Domain.EnderecoBean;
import br.com.SistemaLanchonete.Domain.EnderecoPessoaBean;
import br.com.SistemaLanchonete.Domain.EstadoBean;
import br.com.SistemaLanchonete.Domain.LogradouroBean;
import br.com.SistemaLanchonete.Domain.MunicipioBean;
import br.com.SistemaLanchonete.Domain.PedidoBean;
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

	public String save(EnderecoPessoaBean enderecoPessoa) throws BDException {
				
		SalvarEndereco(enderecoPessoa.getEndereco());
		
		return retorno;
	}
	
	private String SalvarEndereco(EnderecoBean endereco) throws BDException {
		SalvarLogradouro(endereco.getLogradouro());
		
		EnderecoBean enderecoCon = new EnderecoBean(0, null, endereco.getCdNumero(), endereco.getDsComplemento(), "");
		GenericDAO<EnderecoBean> endDao = new GenericDAO<EnderecoBean>();
		
		//Est� dando erro aqui
		ArrayList<EnderecoBean> lista = endDao.findLike(EnderecoBean.class, enderecoCon);
		
		if (!lista.isEmpty()) {
			endereco.setCdEndereco(lista.get(0).getCdEndereco());
		}		
		
		if (endereco.getCdEndereco() == 0) {
			try {
				enderecoDao.save(endereco, 0);
				
				lista = endDao.findLike(EnderecoBean.class, enderecoCon);
				
				if (!lista.isEmpty()) {
					endereco.setCdEndereco(lista.get(0).getCdEndereco());
				}				
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
		SalvarMunicipio(bairro.getMunicipio());
		
		BairroBean bairroCon = new BairroBean(0, null, bairro.getDsBairro());
		GenericDAO<BairroBean> bairroDao = new GenericDAO<BairroBean>();
		
		ArrayList<BairroBean> lista = bairroDao.findLike(BairroBean.class, bairroCon);
		
		if (!lista.isEmpty()) {
			bairro.setCdBairro(lista.get(0).getCdBairro());
		}		
		
		if (bairro.getCdBairro() == 0) {
			try {
				bairroDao.save(bairro, 0);
				
				lista = bairroDao.findLike(BairroBean.class, bairroCon);
				if (!lista.isEmpty()) {
					bairro.setCdBairro(lista.get(0).getCdBairro());
				}				
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
		EstadoBean estCon = new EstadoBean(0, "", estado.getDsSigla());
		GenericDAO<EstadoBean> estDao = new GenericDAO<EstadoBean>();
		
		ArrayList<EstadoBean> lista = estDao.findLike(EstadoBean.class, estCon);
		
		if (!lista.isEmpty()) {
			estado.setCdEstado(lista.get(0).getCdEstado());
		}
		
		if (estado.getCdEstado() == 0) {
			try {
				estadoDao.save(estado, 0);
				
				lista = estDao.findLike(EstadoBean.class, estCon);
				if (!lista.isEmpty()) {
					estado.setCdEstado(lista.get(0).getCdEstado());
				}				
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
		SalvarBairro(logradouro.getBairro());
		
		LogradouroBean logCon = new LogradouroBean(0, null, 0, logradouro.getDsLogradouro());
		GenericDAO<LogradouroBean> logDao = new GenericDAO<LogradouroBean>();
		
		ArrayList<LogradouroBean> lista = logDao.findLike(LogradouroBean.class, logCon);
		
		if (!lista.isEmpty()) {
			logradouro.setCdLogradouro(lista.get(0).getCdLogradouro());
		}
		
		if (logradouro.getCdLogradouro() == 0) {
			try {
				logradouroDao.save(logradouro, 0);
				
				lista = logDao.findLike(LogradouroBean.class, logCon);
				
				if (!lista.isEmpty()) {
					logradouro.setCdLogradouro(lista.get(0).getCdLogradouro());
				}				
		} catch (BDException e) {
			throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados salvos com sucesso na tabela";
	} else {
		try {
			logradouroDao.save(logradouro, logradouro.getCdLogradouro());
		} catch (BDException e) {
			throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
		}
		retorno = "Dados atualizados com sucesso na tabela";
	}
	return retorno;
	}
	
	private String SalvarMunicipio(MunicipioBean municipio) throws BDException {
		SalvarEstado(municipio.getEstado());
		
		MunicipioBean munCon = new MunicipioBean(0, municipio.getEstado(), municipio.getDsMunicipio());
		GenericDAO<MunicipioBean> munDao = new GenericDAO<MunicipioBean>();
		
		ArrayList<MunicipioBean> lista = munDao.findLike(MunicipioBean.class, munCon);
		
		if (!lista.isEmpty()) {
			municipio.setCdMunicipio(lista.get(0).getCdMunicipio());
		}		
		
		if (municipio.getCdMunicipio() == 0) {
			try {
				municipioDao.save(municipio, 0);
				
				lista = munDao.findLike(MunicipioBean.class, munCon);
				
				if (!lista.isEmpty()) {
					municipio.setCdMunicipio(lista.get(0).getCdMunicipio());
				}						
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
