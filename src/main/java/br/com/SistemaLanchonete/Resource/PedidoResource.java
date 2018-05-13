package br.com.SistemaLanchonete.Resource;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.SistemaLanchonete.Domain.PedidoBean;
import br.com.SistemaLanchonete.Service.PedidoService;
import br.com.SistemaLanchonete.Validacao.SistemaException;
import br.com.SistemaLanchonete.Validacao.Validacao;

/**
 * * Classe REST para manipulação de objetos do tipo Pedido<br>
 * <p>
 * URI para acesso: http://localhost:8080/SistemaLanchonete/services/pedido
 * 
 * @author Lino Pegoretti
 *
 */
@Path("/pedido")
public class PedidoResource {
	/**
	 * Recurso REST para inserção de um novo pedido
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/pedido/pedido
	 * <p>
	 * Arquivo JSON para modelo de pedido:
	 * {@link br.com.SistemaLanchonete.ExemplosJSON.Pedido.json}
	 * 
	 * @param pedido
	 *            - Um objeto do tipo PedidoBean para ser inserido no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando inserido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@POST
	@Path("/pedido")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(PedidoBean pedido) {
		try {
			new PedidoService().save(pedido);
			return Response.status(200).entity("Pedido Inserido com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para atualização de pedido
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/pedido/cdPedido
	 * <p>
	 * 
	 * @param cdPedido
	 *            - Um id de um PedidoBean para ser atualizado no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando atualizado com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@PUT
	@Path("/{cdPedido}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cdPedido") int cdPedido) {
		try {
			PedidoBean pedido = new PedidoBean();
			pedido.setCdPedido(cdPedido);
			new PedidoService().save(pedido);
			return Response.status(200).entity("Pedido alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para remoção de um pedido no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/pedido/cdPedido
	 * <p>
	 * 
	 * @param cdPedido
	 *            - Um id de um PedidoBean para ser removido do BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando removido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdPedido}")
	public Response delete(@PathParam("cdPedido") int cdPedido) {
		try {
			PedidoBean pedido = new PedidoBean();
			pedido.setCdPedido(cdPedido);
			new PedidoService().remove(pedido);
			return Response.status(200).entity("Pedido excluído com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para busca de um único pedido no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/pedido/cdPedido
	 * <p>
	 * 
	 * @param cdPedido
	 *            - Um id de um PedidoBean para ser buscado no BD
	 * 
	 * @return PedidoBean - Um pedido para ser localizado no banco de dados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/{cdPedido}")
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoBean select(@PathParam("cdPedido") int cdPedido) {
		try {
			PedidoBean pedido = new PedidoBean();
			pedido.setCdPedido(cdPedido);
			return new PedidoService().findById(pedido);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para fazer uma busca de vários pedidos no BD de acordo com o o
	 * valor passado por parametro na URL
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/pedido/pedidos/cdNumPedido=&dtDataEmissao=
	 * <p>
	 * 
	 * @param cdNumPedido
	 *            - um numero interno de pedido para pesquisa, se vier numero
	 *            invalido pesquisa por numPedido=0
	 * @param dtEmissao
	 *            - uma data para pesquisa dos pedidos, se vier datainvalida,
	 *            pesquisa pela data atual
	 * 
	 * @return ArrayList PedidoBean - Uma lista de pedidos de acordo com os
	 *         parametros enviados <br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/pedidos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PedidoBean> findLike(@QueryParam("cdNumPedido") String cdNumPedido,
			@QueryParam("dtEmissao") String dtEmissao) {
		int numPedido;
		try {
			numPedido = Validacao.stringToInt(cdNumPedido);
		} catch (SistemaException e1) {
			numPedido = 0;
		}
		Date dataEmissao = null;
		try {
			dataEmissao = Validacao.stringToDate(dtEmissao);
		} catch (SistemaException e1) {
			dataEmissao = new Date();
		}
		PedidoBean pedido = new PedidoBean(0, null, null, null, null, numPedido, dataEmissao, 0, 0, 0, 0, null, 0,
				null);
		try {
			ArrayList<PedidoBean> pedidos = new PedidoService().findLike(pedido);
			return pedidos;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
