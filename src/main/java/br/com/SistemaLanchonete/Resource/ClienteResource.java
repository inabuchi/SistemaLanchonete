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

@Path("/clientes")
public class ClienteResource {

	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(ClienteBean cliente) {
		try {
			String teste = "teste";
			//new ClienteService().save(cliente);
			return Response.status(200).entity(teste).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/pais")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(ClienteBean cliente) {
		try {
			new ClienteService().save(cliente);
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
			return new ClienteService().select(cliente.getCdPessoa());
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("/cliente/{cdPessoa}")
	public Response delete(ClienteBean cliente) {
		try {
			new ClienteService().delete(cliente.getCdPessoa());

			return Response.status(200).entity("cadastro excluído.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/clientes")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ClienteBean> selecWhere(ClienteBean cliente) {
		try {
			ArrayList<ClienteBean> clientes = new ClienteService().selectWhere(cliente);
			return clientes;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
