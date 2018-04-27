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

import br.com.SistemaLanchonete.Domain.ProdutoBean;
import br.com.SistemaLanchonete.Service.ProdutoService;

@Path("/produto")
public class ProdutoResource {

	@POST
	@Path("/produto")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(ProdutoBean produto) {
		try {
			new ProdutoService().save(produto);
			return Response.status(201).entity("Produto Inserido com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/{cdProduto}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cdProduto") int cdProduto) {
		try {
			ProdutoBean produto = new ProdutoBean();
			produto.setCdProduto(cdProduto);
			new ProdutoService().save(produto);
			return Response.status(200).entity("Produto alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdProduto}")
	public Response delete(@PathParam("cdProduto") int cdProduto) {
		try {
			ProdutoBean produto = new ProdutoBean();
			produto.setCdProduto(cdProduto);
			new ProdutoService().remove(produto);
			return Response.status(200).entity("Produto excluído com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/{cdProduto}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoBean select(@PathParam("cdProduto") int cdProduto) {
		try {
			ProdutoBean produto = new ProdutoBean();
			produto.setCdProduto(cdProduto);
			return new ProdutoService().findById(produto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/produtos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ProdutoBean> findLike(ProdutoBean produto) {
		try {
			ArrayList<ProdutoBean> produtos = new ProdutoService().findLike(produto);
			return produtos;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
