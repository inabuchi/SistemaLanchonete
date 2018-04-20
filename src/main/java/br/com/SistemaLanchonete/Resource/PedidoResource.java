package br.com.SistemaLanchonete.Resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
	@Consumes({MediaType.APPLICATION_JSON})
	public Response insert(PedidoBean pedido) {
		System.out.println("Cheguei Aqui");
		System.out.println("--> " + pedido);
		try {
			new PedidoService().save(pedido, 0);			
			return Response.status(200).entity(Validacao.removerCaracteresEspeciais("Pedido inserido com Sucesso")).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/pedido")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(PedidoBean pedido) {
		try {
			new PedidoService().save(pedido, 0);
			return Response.status(200).entity("Pedido alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("/pedido")
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoBean select(PedidoBean pedido) {
		try {
			return new PedidoService().findById(pedido);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@DELETE
	@Path("/pedido/{cdPessoa}")
	public Response delete(PedidoBean pedido) {
		try {
			new PedidoService().remove(pedido);
		
			return Response.status(200).entity(Validacao.removerCaracteresEspeciais("Pedido excluído.")).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("/pedidos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PedidoBean> findLike(PedidoBean pedido) {
		try {
			ArrayList<PedidoBean> pedidos = new PedidoService().findLike(pedido);
			return pedidos;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
