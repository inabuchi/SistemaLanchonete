package br.com.SistemaLanchonete.Service;

import java.util.ArrayList;
import java.util.List;
import br.com.SistemaLanchonete.Domain.PedidoBean;
import br.com.SistemaLanchonete.Repository.BDException;
import br.com.SistemaLanchonete.Repository.EErrosBD;
import br.com.SistemaLanchonete.Repository.GenericDAO;

/**
 * Service de Pedidos
 * 
 * @author Erick
 */
public class PedidoService {
	private String retorno = "";
	GenericDAO<PedidoBean> pedidoDao = new GenericDAO<PedidoBean>();
	Class<PedidoBean> pedidoClasse = PedidoBean.class;

	public String save(PedidoBean pedido) throws BDException {
		if (pedido.getCdPedido() == 0) {
			try {
				// aqui precisa validar os dados que vem da tela
				pedidoDao.save(pedido, 0);
			} catch (BDException e) {
				throw new BDException("Erro ao Salvar dados no banco" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados salvos com sucesso na tabela";
		} else {
			try {
				pedidoDao.save(pedido, pedido.getCdPedido());
			} catch (BDException e) {
				throw new BDException("Erro na atualizacao de dados:" + e.getMessage(), EErrosBD.ATUALIZA_DADO);

			}
			retorno = "Dados atualizados com sucesso na tabela";
		}
		return retorno;
	}

	public String remove(PedidoBean pedido) throws Exception {
		PedidoBean pedidoRetorno = pedidoDao.findById(pedidoClasse, pedido.getCdPedido());
		try {
			pedidoDao.remove(pedidoClasse, pedidoRetorno.getCdPedido());
		} catch (BDException e) {
			throw new BDException("Erro na remoção de dados:" + e.getMessage(), EErrosBD.EXCLUI_DADO);
		}
		retorno = "Dados removidos com sucesso na tabela";

		return retorno;
	}

	public PedidoBean findById(PedidoBean pedido) {
		/*
		 * o metodo find busca por chave primaria, mas como nao tenho a anotaï¿½ï¿½o @ID
		 * no fucionario so retorna o funcinario que for igual na classe pessoa
		 * 
		 * precisa fazer uma query nao da para usar o mï¿½todo find do hibernate
		 */

		return pedidoDao.findById(pedidoClasse, pedido.getCdPedido());

	}

	public ArrayList<PedidoBean> findLike(PedidoBean pedido) {
		ArrayList<PedidoBean> lista = new ArrayList<PedidoBean>();
		/*
		 * Esse metodo precisa ou retornar uma lista completa do banco ou entao uma
		 * lista aproximada utilizadno o parametro LIKE Tanto faz se for List ou
		 * ArrayList, ou qq outra collection
		 */
		List<PedidoBean> lista2 = pedidoDao.findLike(pedidoClasse, pedido);
		for (PedidoBean model2 : lista2) {
			lista.add(model2);
		}
		return lista;

	}

}