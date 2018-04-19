package br.com.SistemaLanchonete.Resource;

import java.util.ArrayList;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.SistemaLanchonete.Domain.ProdutoBean;
import br.com.SistemaLanchonete.Service.ProdutoService;

@Path("/produtos")
public class ProdutoResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(ProdutoBean produto) {
		return new ProdutoService().insert(produto)
				? Response.status(200).entity("Transação executada com sucesso").build()
						: Response.status(500).entity("houve um erro ao tentar salvar").build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(ProdutoBean produto) {
		return new ProdutoService().insert(produto)
				? Response.status(200).entity("Transação executada com sucesso").build()
						: Response.status(500).entity("houve um erro ao tentar salvar").build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ProdutoBean> find(ProdutoBean produto) {
		return new ProdutoService().find(produto);
	}
	
	@GET
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoBean getProdutoById(@PathParam("id") int id) {
		return new ProdutoService().findById(id);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		String msg = new ProdutoService().delete(id);
		int status = msg == null ? 500 : 200;
		return Response.status(status).entity(Optional.ofNullable(msg).orElse("houve um erro ao tentar excluir")).build();
	}
}
