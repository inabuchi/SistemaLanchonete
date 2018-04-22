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

import br.com.SistemaLanchonete.Domain.ClienteBean;
import br.com.SistemaLanchonete.Service.ClienteService;
import br.com.SistemaLanchonete.Validacao.Validacao;

@Path("/cliente")
public class ClienteResource {

	@POST
	@Path("/cliente")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response insert(ClienteBean cliente) {
		System.out.println("Cheguei Aqui");
		System.out.println("--> " + cliente);
		try {
			new ClienteService().save(cliente, 0);			
			return Response.status(200).entity(Validacao.removerCaracteresEspeciais("Cliente inserido com Sucesso")).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/cliente")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(ClienteBean cliente) {
		try {
			new ClienteService().save(cliente, 0);
			return Response.status(200).entity("Cliente alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("/cliente")
	@Produces(MediaType.APPLICATION_JSON)
	public ClienteBean select(ClienteBean cliente) {
		try {
			return new ClienteService().findById(cliente);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@DELETE
	@Path("/cliente/{cdPessoa}")
	public Response delete(ClienteBean cliente) {
		try {
			new ClienteService().remove(cliente);
		
			return Response.status(200).entity(Validacao.removerCaracteresEspeciais("Cadastro exclu�do.")).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("/clientes")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ClienteBean> findLike(ClienteBean cliente) {
		try {
			ArrayList<ClienteBean> clientes = new ClienteService().findLike(cliente);
			return clientes;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
