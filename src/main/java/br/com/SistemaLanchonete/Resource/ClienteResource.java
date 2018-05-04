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

import br.com.SistemaLanchonete.Domain.ClienteBean;
import br.com.SistemaLanchonete.Service.ClienteService;

@Path("/cliente")
public class ClienteResource {

	@POST
	@Path("/cliente")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(ClienteBean cliente) {
		try {
			new ClienteService().save(cliente);
			return Response.status(200).entity("Cliente Inserido com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/{cdPessoa}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cdPessoa") int codigo) {
		try {
			ClienteBean cliente = new ClienteBean();
			cliente.setCdPessoa(codigo);
			new ClienteService().save(cliente);
			return Response.status(200).entity("Cliente alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdPessoa}")
	public Response delete(@PathParam("cdPessoa") int codigo) {
		try {
			ClienteBean cliente = new ClienteBean();
			cliente.setCdPessoa(codigo);
			new ClienteService().remove(cliente);
			return Response.status(200).entity("Cliente excluído com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/{cdPessoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClienteBean select(@PathParam("cdPessoa") int codigo) {
		try {
			ClienteBean cliente = new ClienteBean();
			cliente.setCdPessoa(codigo);
			return new ClienteService().findById(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/clientes/{campo}={valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ClienteBean> findLike(@PathParam("campo") String campo, @PathParam("valor") String valor) {
		try {
			ClienteBean cliente = new ClienteBean();
			cliente.setDsNome(valor);
			ArrayList<ClienteBean> clientes = new ClienteService().findLike(cliente);
			return clientes;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
