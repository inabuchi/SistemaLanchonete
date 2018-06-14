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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.authz.annotation.RequiresAuthentication;

import br.com.SistemaLanchonete.Domain.ClienteBean;
import br.com.SistemaLanchonete.Service.ClienteService;

/**
 * Classe REST para manipula��o de objetos do tipo cliente<br>
 * <p>
 * URI para acesso: http://localhost:8080/SistemaLanchonete/services/cliente
 * 
 * @author Lino Pegoretti
 *
 */
@Path("/cliente")
public class ClienteResource {

	/**
	 * Recurso REST para inser��o de um novo cliente
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
	 * Recurso REST para atualiza��o de um cliente no BD *
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
	 * Recurso REST para remo��o de um cliente no BD
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
			return Response.status(200).entity("Cliente exclu�do com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para busca de um �nico cliente no BD
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
	 * Recurso REST para fazer uma busca de v�rios clientes no BD de acordo com o
	 * valor passado por parametro na URL
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/cliente/clientes?dsNome=&dsTelefone1=
	 * <p>
	 * 
	 * @param dsNome
	 *            - Valor do campo nome para pesquisa
	 * @param dsTelefone1
	 *            - Valor do campo telefone1 para a pesquisa
	 * 
	 * @return ArrayList ClienteBean - Uma lista de clientes de acordo com os
	 *         parametros enviados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/clientes")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ClienteBean> findLike(@QueryParam("dsNome") String dsNome,
			@QueryParam("dsTelefone1") String dsTelefone1) {
		ClienteBean cliente = new ClienteBean(0, dsNome, dsTelefone1, null, null, true, 0, null);
		try {
			ArrayList<ClienteBean> clientes = new ClienteService().findLike(cliente);
			return clientes;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
