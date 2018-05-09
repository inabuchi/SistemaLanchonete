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

/**
 * * Classe REST para manipulação de objetos do tipo FormaPagamentoBean<br>
 * <p>
 * URI para acesso:
 * http://localhost:8080/SistemaLanchonete/services/formaPagamento
 * 
 * @author Lino Pegoretti
 *
 */
@Path("/formaPagamento")
public class FormaPagamentoResource {
	/**
	 * Recurso REST para inserção de uma nova forma de pagamento
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/formaPagamento/formaPagamento
	 * <p>
	 * Arquivo JSON para modelo de forma de pagamento:
	 * {@link br.com.SistemaLanchonete.ExemplosJSON.FormaPagamento.json}
	 * 
	 * @param formaPagamento
	 *            - Um objeto do tipo FormaPagamentoBean para ser inserido no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando inserido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@POST
	@Path("/formaPagamento")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(FormaPagamentoBean formaPagamento) {
		try {
			new FormaPagamentoService().save(formaPagamento);
			return Response.status(200).entity("Forma de Pagamento Inserida com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para atualização de uma nova forma de pagamento
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/formaPagamento/cdFormaPagamento
	 * <p>
	 * 
	 * @param cdFormaPagamento
	 *            - Um id de um FormaPagamentoBean para ser atualizado no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando atualizado com sucesso<br>
	 *         500 quando houver erro interno
	 */
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

	/**
	 * Recurso REST para remoção de uma categoria de produto no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/formaPagamento/cdFormaPagamento
	 * <p>
	 * 
	 * @param cdFormaPagamento
	 *            - Um id de um FormaPagamentoBean para ser removido do BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando removido com sucesso<br>
	 *         500 quando houver erro interno
	 */
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

	/**
	 * Recurso REST para busca de uma única forma de pagamento no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/formaPagamento/cdFormaPagamento
	 * <p>
	 * 
	 * @param cdFormaPagamento
	 *            - Um id de um FormaPagamentoBean para ser buscado no BD
	 * 
	 * @return FormaPagamentoBean - Um adicional de produto localizado no banco de
	 *         dados<br>
	 *         500 quando houver erro interno
	 */
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

	/**
	 * Recurso REST para fazer uma busca de várias formas de pagamento no BD de
	 * acordo com o campo e valor passado por parametro na URL
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/formaPagamento/formasPagamento/campo=valor
	 * <p>
	 * 
	 * @param campo
	 *            - Campo para pesquisa, implementado descrição
	 * @param valor
	 *            - Valor do campo para a pesquisa
	 * 
	 * @return ArrayList FormaPagamentoBean - Uma lista de formas de pagamento de
	 *         acordo com os parametros enviados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/formasPagamento/{campo}={valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FormaPagamentoBean> findLike(@PathParam("campo") String campo, @PathParam("valor") String valor) {
		FormaPagamentoBean formaPagamento = new FormaPagamentoBean();
		formaPagamento.setDsFormaPagamento(valor);
		try {
			ArrayList<FormaPagamentoBean> formasPagamento = new FormaPagamentoService().findLike(formaPagamento);
			return formasPagamento;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
