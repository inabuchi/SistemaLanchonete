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

import br.com.SistemaLanchonete.Domain.ProdutoAdicionalBean;
import br.com.SistemaLanchonete.Service.ProdutoAdicionalService;

@Path("/produtoAdicional")
public class ProdutoAdicionalResource {

	@POST
	@Path("/produtoAdicional")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(ProdutoAdicionalBean produtoAdicional) {
		try {
			new ProdutoAdicionalService().save(produtoAdicional);
			return Response.status(201).entity("ProdutoAdicional Inserido com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/{cdProdutoAdicional}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cdProdutoAdicional") int cdProdutoAdicional) {
		try {
			ProdutoAdicionalBean produtoAdicional = new ProdutoAdicionalBean();
			produtoAdicional.setCdProdutoAdicional(cdProdutoAdicional);
			new ProdutoAdicionalService().save(produtoAdicional);
			return Response.status(200).entity("ProdutoAdicional alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdProdutoAdicional}")
	public Response delete(@PathParam("cdProdutoAdicional") int cdProdutoAdicional) {
		try {
			ProdutoAdicionalBean produtoAdicional = new ProdutoAdicionalBean();
			produtoAdicional.setCdProdutoAdicional(cdProdutoAdicional);
			new ProdutoAdicionalService().remove(cdProdutoAdicional);
			return Response.status(200).entity("ProdutoAdicional excluído com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/{cdProdutoAdicional}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoAdicionalBean select(@PathParam("cdProdutoAdicional") int cdProdutoAdicional) {
		try {
			ProdutoAdicionalBean produtoAdicional = new ProdutoAdicionalBean();
			produtoAdicional.setCdProdutoAdicional(cdProdutoAdicional);
			return new ProdutoAdicionalService().findById(produtoAdicional);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/produtoAdicionais")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ProdutoAdicionalBean> findLike(ProdutoAdicionalBean produtoAdicional) {
		try {
			ArrayList<ProdutoAdicionalBean> produtoAdicionais = new ProdutoAdicionalService()
					.findLike(produtoAdicional);
			return produtoAdicionais;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
