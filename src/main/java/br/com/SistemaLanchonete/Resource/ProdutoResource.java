package br.com.SistemaLanchonete.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.SistemaLanchonete.Domain.ProdutoBean;
import br.com.SistemaLanchonete.Service.ProdutoService;

@Path("/produtos")
public class ProdutoResource {
	private static int id = 0;
	
	@POST
	@Path("/produto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(ProdutoBean produto) {
		try {
			new ProdutoService().save(produto, id);
			return Response.status(200).entity("Produto " + produto.getDescProduto() + " adicionado com sucesso!").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("/produto/{cdProduto}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response remove(ProdutoBean produto) {
		try {
			new ProdutoService().remove(produto);
			return Response.status(200).entity("Produto " + produto.getCodProduto() + " - " + produto.getDescProduto() + " removido com sucesso!").build();

		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	@GET
	@Path("/produto/{cdProduto}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response select(ProdutoBean produto) {
		try {
			new ProdutoService().findById(produto);
			return Response.status(200).entity("Produtos listados com sucesso!").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
