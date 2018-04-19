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

@Path("/produto")
public class ProdutoResource {

	@POST
	@Path("/addProduto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(ProdutoBean produto) {
		try {
			new ProdutoService().save(produto);
			return Response.status(200).entity("Produto " + produto.getDescProduto() + " adicionado com sucesso!").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("/delProduto/{cdProduto}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(ProdutoBean produto) {
		try {
			new ProdutoService().delete(produto.getCodProduto());
			return Response.status(200).entity("Produto " + produto.getCodProduto() + " - " + produto.getDescProduto() + " removido com sucesso!").build();

		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("/listProduto/{cdProduto}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response select(ProdutoBean produto) {
		try {
			new ProdutoService().select(produto.getCodProduto());
			return Response.status(200).entity("Produtos listados com sucesso!").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
