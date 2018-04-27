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

import br.com.SistemaLanchonete.Domain.CaixaBean;
import br.com.SistemaLanchonete.Service.CaixaService;

@Path("/caixa")
public class CaixaResource {

	@POST
	@Path("/caixa")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(CaixaBean caixa) {
		try {
			new CaixaService().save(caixa);
			return Response.status(201).entity("Caixa Inserido com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/{cdCaixa}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cdCaixa") int cdCaixa) {
		try {
			CaixaBean caixa = new CaixaBean();
			caixa.setCdCaixa(cdCaixa);
			new CaixaService().save(caixa);
			return Response.status(200).entity("Caixa alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdCaixa}")
	public Response delete(@PathParam("cdCaixa") int cdCaixa) {
		try {
			CaixaBean caixa = new CaixaBean();
			caixa.setCdCaixa(cdCaixa);
			new CaixaService().remove(caixa);
			return Response.status(200).entity("Caixa excluído com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/{cdCaixa}")
	@Produces(MediaType.APPLICATION_JSON)
	public CaixaBean select(@PathParam("cdCaixa") int cdCaixa) {
		try {
			CaixaBean caixa = new CaixaBean();
			caixa.setCdCaixa(cdCaixa);
			return new CaixaService().findById(caixa);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/caixas")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CaixaBean> findLike(CaixaBean caixa) {
		try {
			ArrayList<CaixaBean> caixas = new CaixaService().findLike(caixa);
			return caixas;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
