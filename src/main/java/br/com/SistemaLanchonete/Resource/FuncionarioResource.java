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

import br.com.SistemaLanchonete.Domain.FuncionarioBean;
import br.com.SistemaLanchonete.Service.FuncionarioService;

/**
 * Classe REST para manipulação de objetos do tipo funcionario<br>
 * <p>
 * URI para acesso: http://localhost:8080/SistemaLanchonete/services/funcionario
 * 
 * @author Lino Pegoretti
 *
 */
@Path("/funcionario")
public class FuncionarioResource {
	/**
	 * Recurso REST para inserção de um novo funcionario
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/funcionario/funcionario
	 * <p>
	 * Arquivo JSON para modelo de funcionario:
	 * {@link br.com.SistemaLanchonete.ExemplosJSON.Funcionario.json}
	 * 
	 * @param funcionario
	 *            - Um objeto do tipo FuncionarioBean para ser inserido no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando inserido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@POST
	@Path("/funcionario")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(FuncionarioBean funcionario) {
		try {
			new FuncionarioService().save(funcionario);
			return Response.status(200).entity("Funcionario Inserido com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para atualização de um funcionario no BD *
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/funcionario/cdPessoa
	 * <p>
	 * 
	 * @param cdPessoa
	 *            - Um id de um FuncionarioBean para ser atualizado no BD
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
			FuncionarioBean funcionario = new FuncionarioBean();
			funcionario.setCdPessoa(cdPessoa);
			new FuncionarioService().save(funcionario);
			return Response.status(200).entity("Funcionario alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para remoção de um funcionario no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/funcionario/cdPessoa
	 * <p>
	 * 
	 * @param cdPessoa
	 *            - Um id de um FuncionarioBean para ser removido do BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando removido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdPessoa}")
	public Response delete(@PathParam("cdPessoa") int cdPessoa) {
		try {
			FuncionarioBean funcionario = new FuncionarioBean();
			funcionario.setCdPessoa(cdPessoa);
			new FuncionarioService().remove(funcionario);
			return Response.status(200).entity("Funcionario excluído com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para busca de um único funcionario no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/funcionario/cdPessoa
	 * <p>
	 * 
	 * @param cdPessoa
	 *            - Um id de um FuncionarioBean para ser buscado no BD
	 * 
	 * @return FuncionarioBean - Um funcionario localizado no banco de dados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/{cdPessoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public FuncionarioBean select(@PathParam("cdPessoa") int cdPessoa) {
		try {
			FuncionarioBean funcionario = new FuncionarioBean();
			funcionario.setCdPessoa(cdPessoa);
			return new FuncionarioService().findById(funcionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para fazer uma busca de vários funcionarios no BD de acordo com
	 * o campo e valor passado por parametro na URL
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/funcionario/funcionarios/campo=valor
	 * <p>
	 * 
	 * @param campo
	 *            - Campo para pesquisa, implementado Telefone e Nome
	 * @param valor
	 *            - Valor do campo para a pesquisa
	 * 
	 * @return ArrayList FuncionarioBean - Uma lista de funcionarios de acordo com
	 *         os parametros enviados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/funcionarios/{campo}={valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FuncionarioBean> findLike(@PathParam("campo") String campo, @PathParam("valor") String valor) {
		try {
			FuncionarioBean funcionario = new FuncionarioBean();
			funcionario.setDsNome(valor);
			ArrayList<FuncionarioBean> funcionarios = new FuncionarioService().findLike(funcionario);
			return funcionarios;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
