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

import br.com.SistemaLanchonete.Domain.ProdutoCategoriaBean;
import br.com.SistemaLanchonete.Service.ProdutoCategoriaService;

@Path("/produtoCategoria")
public class ProdutoCategoriaResource {

	@POST
	@Path("/produtoCategoria")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(ProdutoCategoriaBean produtoCategoria) {
		try {
			new ProdutoCategoriaService().save(produtoCategoria);
			return Response.status(201).entity("Categoria de Produto Inserida com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/{cdProdutoCategoria}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cdProdutoCategoria") int cdProdutoCategoria) {
		try {
			ProdutoCategoriaBean produtoCategoria = new ProdutoCategoriaBean();
			produtoCategoria.setCdProdutoCategoria(cdProdutoCategoria);
			new ProdutoCategoriaService().save(produtoCategoria);
			return Response.status(200).entity("Categoria de Produto alterada com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdProdutoCategoria}")
	public Response delete(@PathParam("cdProdutoCategoria") int cdProdutoCategoria) {
		try {
			ProdutoCategoriaBean produtoCategoria = new ProdutoCategoriaBean();
			produtoCategoria.setCdProdutoCategoria(cdProdutoCategoria);
			new ProdutoCategoriaService().remove(produtoCategoria);
			return Response.status(200).entity("ProdutoCategoria excluída com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/{cdProdutoCategoria}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoCategoriaBean select(@PathParam("cdProdutoCategoria") int cdProdutoCategoria) {
		try {
			ProdutoCategoriaBean produtoCategoria = new ProdutoCategoriaBean();
			produtoCategoria.setCdProdutoCategoria(cdProdutoCategoria);
			return new ProdutoCategoriaService().findById(produtoCategoria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/produtoCategorias/{campo}={valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ProdutoCategoriaBean> findLike(@PathParam("campo") String campo,
			@PathParam("valor") String valor) {
		try {
			ProdutoCategoriaBean produtoCategoria = new ProdutoCategoriaBean();
			produtoCategoria.setDsCategoria(valor);
			ArrayList<ProdutoCategoriaBean> produtoCategorias = new ProdutoCategoriaService()
					.findLike(produtoCategoria);
			return produtoCategorias;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
