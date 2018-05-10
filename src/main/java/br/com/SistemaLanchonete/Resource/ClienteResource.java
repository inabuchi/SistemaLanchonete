package br.com.SistemaLanchonete.Resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.SistemaLanchonete.Domain.ClienteBean;
import br.com.SistemaLanchonete.Service.ClienteService;
import br.com.SistemaLanchonete.Validacao.SistemaException;
import br.com.SistemaLanchonete.Validacao.Validacao;

/**
 * Classe REST para manipulação de objetos do tipo cliente<br>
 * <p>
 * URI para acesso: http://localhost:8080/SistemaLanchonete/services/cliente
 * 
 * @author Lino Pegoretti
 *
 */
@Path("/cliente")
public class ClienteResource {

	/**
	 * Recurso REST para inserção de um novo cliente
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/cliente/cliente
	 * <p>
	 * Arquivo JSON para modelo de cliente:
	 * {@link br.com.SistemaLanchonete.ExemplosJSON.Cliente.json}
	 * 
	 * @param cliente
	 *            - Um objeto do tipo ClienteBean para ser inserido no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando inserido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@POST
	@Path("/cliente")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(ClienteBean cliente) {
		try {
			new ClienteService().save(cliente);
			return Response.status(200).entity("Cliente Inserido com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para atualização de um cliente no BD *
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/cliente/cdPessoa
	 * <p>
	 * 
	 * @param cdPessoa
	 *            - Um id de um ClienteBean para ser atualizado no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando atualizado com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@PUT
	@Path("/{cdPessoa}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cdPessoa") int cdPessoa) {
		try {
			ClienteBean cliente = new ClienteBean();
			cliente.setCdPessoa(cdPessoa);
			new ClienteService().save(cliente);
			return Response.status(200).entity("Cliente alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para remoção de um cliente no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/cliente/cdPessoa
	 * <p>
	 * 
	 * @param cdPessoa
	 *            - Um id de um ClienteBean para ser removido do BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando removido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@DELETE
	@Path("/{cdPessoa}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("cdPessoa") int cdPessoa) {
		try {
			ClienteBean cliente = new ClienteBean();
			cliente.setCdPessoa(cdPessoa);
			new ClienteService().remove(cliente);
			return Response.status(200).entity("Cliente excluído com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para busca de um único cliente no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/cliente/cdPessoa
	 * <p>
	 * 
	 * @param cdPessoa
	 *            - Um id de um ClienteBean para ser buscado no BD
	 * 
	 * @return ClienteBean - Um cliente localizado no banco de dados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/{cdPessoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClienteBean select(@PathParam("cdPessoa") int cdPessoa) {
		try {
			ClienteBean cliente = new ClienteBean();
			cliente.setCdPessoa(cdPessoa);
			return new ClienteService().findById(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para fazer uma busca de vários clientes no BD de acordo com o
	 * campo e valor passado por parametro na URL
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/cliente/clientes/campo=valor
	 * <p>
	 * 
	 * @param campo
	 *            - Campo para pesquisa, implementado Telefone e Nome
	 * @param valor
	 *            - Valor do campo para a pesquisa
	 * 
	 * @return ArrayList ClienteBean - Uma lista de clientes de acordo com os
	 *         parametros enviados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/clientes?campo={campo}&valor={valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ClienteBean> findLike(@QueryParam("campo") String campo, @QueryParam("valor") String valor) {
		System.out.println(campo + "--> " + valor);
		ClienteBean cliente = new ClienteBean();
		if (campo.equals("dsNome")) {
			cliente.setDsNome(valor);
		} else {
			cliente.setDsTelefone1(valor);
		}
		try {
			ArrayList<ClienteBean> clientes = new ClienteService().findLike(cliente);
			return clientes;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
