package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;
import java.util.List;

import br.com.SistemaLanchonete.Domain.CargoBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

public class CargoService {
	private String retorno = "";
	GenericDAO<CargoBean> cargoDao = new GenericDAO<CargoBean>();
	Class<CargoBean> cargoBean;
	
	public String save(CargoBean cargo, int id) throws BDException {
		if (id == 0) {
			try {
				cargoDao.save(cargo, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				cargoDao.save(cargo, id);
			} catch (BDException e) {
				throw new BDException("Erro na atualização de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);
			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(CargoBean cargo) {
		CargoBean cargoRetorno = cargoDao.findById(cargoBean, cargo.getCdCargo());
		try {
			cargoDao.remove(cargoBean, cargoRetorno.getCdCargo());
			retorno = "Dados removidos com sucesso na tabela";
		} catch (Exception e) {
			// throw new BDException("Erro na remoï¿½ï¿½o de dados:" + e.getMessage(),
			// EErrosBD.EXCLUI_DADO);

		}
		return retorno;
	}

	public CargoBean findById(CargoBean cargo) {
		/*
		 * o metodo find busca por chave primaria, mas como nao tenho a anotaï¿½ï¿½o @ID
		 * no fucionario so retorna o funcinario que for igual na classe pessoa
		 * 
		 * precisa fazer uma query nao da para usar o mï¿½todo find do hibernate
		 */
		return cargoDao.findById(cargoBean, cargo.getCdCargo());

	}

	public ArrayList<CargoBean> findLike(CargoBean cargo) {
		ArrayList<CargoBean> lista = new ArrayList<CargoBean>();
		/*
		 * Esse metodo precisa ou retornar uma lista completa do banco ou entao uma
		 * lista aproximada utilizadno o parametro LIKE Tanto faz se for List ou
		 * ArrayList, ou qq outra collection
		 */
		List<CargoBean> lista2 = cargoDao.findLike(cargoBean, cargo);
		for (CargoBean model2 : lista2) {
			lista.add(model2);
		}
		return lista;
	}
}
