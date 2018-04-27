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

import br.com.SistemaLanchonete.Domain.FormaPagamentoBean;
import br.com.SistemaLanchonete.Service.FormaPagamentoService;

@Path("/formaPagamento")
public class FormaPagamentoResource {

	@POST
	@Path("/formaPagamento")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(FormaPagamentoBean formaPagamento) {
		try {
			new FormaPagamentoService().save(formaPagamento);
			return Response.status(201).entity("Forma de Pagamento Inserida com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/{cdFormaPagamento}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cdFormaPagamento") int cdFormaPagamento) {
		try {
			FormaPagamentoBean formaPagamento = new FormaPagamentoBean();
			formaPagamento.setCdFormaPagamento(cdFormaPagamento);
			new FormaPagamentoService().save(formaPagamento);
			return Response.status(200).entity("Forma de Pagamento alterada com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdFormaPagamento}")
	public Response delete(@PathParam("cdFormaPagamento") int cdFormaPagamento) {
		try {
			FormaPagamentoBean formaPagamento = new FormaPagamentoBean();
			formaPagamento.setCdFormaPagamento(cdFormaPagamento);
			new FormaPagamentoService().remove(formaPagamento);
			return Response.status(200).entity("Forma de Pagamento excluída com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/{cdFormaPagamento}")
	@Produces(MediaType.APPLICATION_JSON)
	public FormaPagamentoBean select(@PathParam("cdFormaPagamento") int cdFormaPagamento) {
		try {
			FormaPagamentoBean formaPagamento = new FormaPagamentoBean();
			formaPagamento.setCdFormaPagamento(cdFormaPagamento);
			return new FormaPagamentoService().findById(formaPagamento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/formasPagamento")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FormaPagamentoBean> findLike(FormaPagamentoBean formaPagamento) {
		try {
			ArrayList<FormaPagamentoBean> formasPagamento = new FormaPagamentoService().findLike(formaPagamento);
			return formasPagamento;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
