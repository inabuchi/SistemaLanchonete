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

/**
 * * Classe REST para manipulação de objetos do tipo Caixa<br>
 * <p>
 * URI para acesso: http://localhost:8080/SistemaLanchonete/services/caixa
 * 
 * @author Lino Pegoretti
 *
 */
@Path("/caixa")
public class CaixaResource {
	/**
	 * Recurso REST para fazer a abertura do caixa diário
	 * <p>
	 * URI para acesso: http://localhost:8080/SistemaLanchonete/services/caixa/caixa
	 * <p>
	 * Arquivo JSON para modelo de caixa:
	 * {@link br.com.SistemaLanchonete.ExemplosJSON.Caixa.json}
	 * 
	 * @param caixa
	 *            - Um objeto do tipo CaixaBean para ser inserido no BD,<br>
	 *            efetuando a abertura do caixa diário
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando inserido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@POST
	@Path("/caixa")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response abreCaixa(CaixaBean caixa) {
		try {
			new CaixaService().save(caixa);
			return Response.status(200).entity("Caixa Aberto com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/{cdCaixa}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response fechaCaixa(@PathParam("cdCaixa") int cdCaixa) {
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
