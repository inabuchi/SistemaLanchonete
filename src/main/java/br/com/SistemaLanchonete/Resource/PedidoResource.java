package br.com.SistemaLanchonete.Resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.SistemaLanchonete.Domain.PedidoBean;
import br.com.SistemaLanchonete.Service.PedidoService;
import br.com.SistemaLanchonete.Validacao.Validacao;

@Path("/pedido")
public class PedidoResource {

	@POST
	@Path("/pedido")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(PedidoBean pedido) {
		try {
			new PedidoService().save(pedido);
			return Response.status(201).entity("Pedido Inserido com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

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

	@GET
	@Path("/pedidos/{campo}={valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PedidoBean> findLike(@PathParam("campo") String campo, @PathParam("valor") String valor) {
		try {
			PedidoBean pedido = new PedidoBean();
			pedido.setDtEmissao(Validacao.stringToDate(valor));
			ArrayList<PedidoBean> pedidos = new PedidoService().findLike(pedido);
			return pedidos;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
