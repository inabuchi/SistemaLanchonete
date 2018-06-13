package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;

import br.com.SistemaLanchonete.Domain.BairroBean;
import br.com.SistemaLanchonete.Domain.EnderecoBean;
import br.com.SistemaLanchonete.Domain.EnderecoPessoaBean;
import br.com.SistemaLanchonete.Domain.EstadoBean;
import br.com.SistemaLanchonete.Domain.LogradouroBean;
import br.com.SistemaLanchonete.Domain.MunicipioBean;
import br.com.SistemaLanchonete.Domain.PessoaBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class EnderecoService {
	private String retorno = "";
	GenericDAO<EnderecoPessoaBean> enderecoPessoaDao = new GenericDAO<EnderecoPessoaBean>();

	public String save(EnderecoPessoaBean enderecoPessoa, PessoaBean pessoa) throws BDException {
		SalvarEndereco(enderecoPessoa.getEndereco());
//aqui tive de mudar
	 //mas voce esta setando na classe o endereco que ja estava nela
		//ok, mas como tem de fazer entao? taa assima ntes: setEnderecoPK(enderecopessoa, pessoa)
		enderecoPessoa.setEndereco(enderecoPessoa.getEndereco());
		enderecoPessoa.setPessoa(pessoa);
		enderecoPessoaDao.save(enderecoPessoa, /*enderecoPessoa.getEndereco().getCdEndereco()*/0);

		return retorno;
	}

	private String SalvarEndereco(EnderecoBean endereco) throws BDException {
		SalvarLogradouro(endereco.getLogradouro());

		GenericDAO<EnderecoBean> enderecoDao = new GenericDAO<EnderecoBean>();

		EnderecoBean enderecoCon = new EnderecoBean(0, null, 0, endereco.getDsComplemento(), "");
		GenericDAO<EnderecoBean> endDao = new GenericDAO<EnderecoBean>();

		ArrayList<EnderecoBean> lista = endDao.findLike(EnderecoBean.class, enderecoCon);

		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				if (endereco.getLogradouro().getCdLogradouro() == lista.get(i).getLogradouro().getCdLogradouro()) {
					endereco.setCdEndereco(lista.get(i).getCdEndereco());
					break;
				}
			}
		}

		if (endereco.getCdEndereco() == 0) {
			try {
				enderecoDao.save(endereco, 0);

				lista = endDao.findLike(EnderecoBean.class, enderecoCon);

				if (!lista.isEmpty()) {
					for (int i = 0; i < lista.size(); i++) {
						if (endereco.getLogradouro().getCdLogradouro() == lista.get(i).getLogradouro()
								.getCdLogradouro()) {
							endereco.setCdEndereco(lista.get(i).getCdEndereco());
							break;
						}
					}
				}
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				enderecoDao.save(endereco, endereco.getCdEndereco());
			} catch (BDException e) {
				throw new BDException("Erro na atualiza��o de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	/*
	 * private String SalvarEnderecoPessoa(EnderecoPessoaBean enderecoPessoa) throws
	 * BDException { return retorno = "Salvo com Sucesso"; }
	 */

	private String SalvarBairro(BairroBean bairro) throws BDException {
		SalvarMunicipio(bairro.getMunicipio());

		BairroBean bairroCon = new BairroBean(0, null, bairro.getDsBairro());
		GenericDAO<BairroBean> bairroDao = new GenericDAO<BairroBean>();

		ArrayList<BairroBean> lista = bairroDao.findLike(BairroBean.class, bairroCon);

		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getMunicipio().getCdMunicipio() == bairro.getMunicipio().getCdMunicipio()) {
					bairro.setCdBairro(lista.get(i).getCdBairro());
					break;
				}
			}
		}

		if (bairro.getCdBairro() == 0) {
			try {
				bairroDao.save(bairro, 0);

				lista = bairroDao.findLike(BairroBean.class, bairroCon);

				if (!lista.isEmpty()) {
					for (int i = 0; i < lista.size(); i++) {
						if (lista.get(i).getMunicipio().getCdMunicipio() == bairro.getMunicipio().getCdMunicipio()) {
							bairro.setCdBairro(lista.get(i).getCdBairro());
							break;
						}
					}
				}
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				bairroDao.save(bairro, bairro.getCdBairro());
			} catch (BDException e) {
				throw new BDException("Erro na atualiza��o de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	private String SalvarEstado(EstadoBean estado) throws BDException {
		EstadoBean estCon = new EstadoBean(0, "", estado.getDsSigla());
		GenericDAO<EstadoBean> estDao = new GenericDAO<EstadoBean>();

		GenericDAO<EstadoBean> estadoDao = new GenericDAO<EstadoBean>();
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
				throw new BDException("Erro na atualiza��o de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	private String SalvarLogradouro(LogradouroBean logradouro) throws BDException {
		SalvarBairro(logradouro.getBairro());

		GenericDAO<LogradouroBean> logradouroDao = new GenericDAO<LogradouroBean>();
		LogradouroBean logCon = new LogradouroBean(0, null, 0, logradouro.getDsLogradouro());
		GenericDAO<LogradouroBean> logDao = new GenericDAO<LogradouroBean>();

		ArrayList<LogradouroBean> lista = logDao.findLike(LogradouroBean.class, logCon);

		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getBairro().getCdBairro() == logradouro.getBairro().getCdBairro()) {
					logradouro.setCdLogradouro(lista.get(i).getCdLogradouro());
					break;
				}
			}
		}

		if (logradouro.getCdLogradouro() == 0) {
			try {
				logradouroDao.save(logradouro, 0);

				lista = logDao.findLike(LogradouroBean.class, logCon);

				if (!lista.isEmpty()) {
					for (int i = 0; i < lista.size(); i++) {
						if (lista.get(i).getBairro().getCdBairro() == logradouro.getBairro().getCdBairro()) {
							logradouro.setCdLogradouro(lista.get(i).getCdLogradouro());
							break;
						}
					}
				}
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				logradouroDao.save(logradouro, logradouro.getCdLogradouro());
			} catch (BDException e) {
				throw new BDException("Erro na atualiza��o de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	private String SalvarMunicipio(MunicipioBean municipio) throws BDException {
		SalvarEstado(municipio.getEstado());

		GenericDAO<MunicipioBean> municipioDao = new GenericDAO<MunicipioBean>();
		MunicipioBean munCon = new MunicipioBean(0, municipio.getEstado(), municipio.getDsMunicipio());
		GenericDAO<MunicipioBean> munDao = new GenericDAO<MunicipioBean>();

		ArrayList<MunicipioBean> lista = munDao.findLike(MunicipioBean.class, munCon);

		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getEstado().getCdEstado() == municipio.getEstado().getCdEstado()) {
					municipio.setCdMunicipio(lista.get(i).getCdMunicipio());
					break;
				}
			}
		}

		if (municipio.getCdMunicipio() == 0) {
			try {
				municipioDao.save(municipio, 0);

				lista = munDao.findLike(MunicipioBean.class, munCon);

				if (!lista.isEmpty()) {
					for (int i = 0; i < lista.size(); i++) {
						if (lista.get(i).getEstado().getCdEstado() == municipio.getEstado().getCdEstado()) {
							municipio.setCdMunicipio(lista.get(i).getCdMunicipio());
							break;
						}
					}
				}
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				municipioDao.save(municipio, municipio.getCdMunicipio());
			} catch (BDException e) {
				throw new BDException("Erro na atualiza��o de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}
}
