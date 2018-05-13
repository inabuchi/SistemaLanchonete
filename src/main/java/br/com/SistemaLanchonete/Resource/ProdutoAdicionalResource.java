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

import br.com.SistemaLanchonete.Domain.ProdutoAdicionalBean;
import br.com.SistemaLanchonete.Service.ProdutoAdicionalService;

/**
 * * Classe REST para manipulação de objetos do tipo ProdutoAdicional<br>
 * <p>
 * URI para acesso:
 * http://localhost:8080/SistemaLanchonete/services/produtoAdicional
 * 
 * @author Lino Pegoretti
 *
 */
@Path("/produtoAdicional")
public class ProdutoAdicionalResource {
	/**
	 * Recurso REST para inserção de um novo adicional de produto
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produtoAdicional/produtoAdicional
	 * <p>
	 * Arquivo JSON para modelo de adicional de produto:
	 * {@link br.com.SistemaLanchonete.ExemplosJSON.ProdutoAdicional.json}
	 * 
	 * @param produtoAdicional
	 *            - Um objeto do tipo ProdutoAdicionalBean para ser inserido no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando inserido com sucesso<br>
	 *         500 quando houver erro interno
	 */
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

	/**
	 * Recurso REST para atualização de um novo adicional de produto
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produtoAdicional/cdProdutoAdicional
	 * <p>
	 * 
	 * @param cdProdutoAdicional
	 *            - Um id de um ProdutoAdicionalBean para ser atualizado no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando atualizado com sucesso<br>
	 *         500 quando houver erro interno
	 */
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

	/**
	 * Recurso REST para remoção de uma categoria de produto no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produtoAdicional/cdProdutoAdicional
	 * <p>
	 * 
	 * @param cdProdutoAdicional
	 *            - Um id de um ProdutoAdicionalBean para ser removido do BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando removido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdProdutoAdicional}")
	public Response delete(@PathParam("cdProdutoAdicional") int cdProdutoAdicional) {
		try {
			ProdutoAdicionalBean produtoAdicional = new ProdutoAdicionalBean();
			produtoAdicional.setCdProdutoAdicional(cdProdutoAdicional);
			new ProdutoAdicionalService().remove(produtoAdicional);
			return Response.status(200).entity("ProdutoAdicional excluído com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para busca de um único adicional de produto no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produtoAdicional/cdProdutoAdicional
	 * <p>
	 * 
	 * @param cdProdutoAdicional
	 *            - Um id de um ProdutoAdicionalBean para ser buscado no BD
	 * 
	 * @return ProdutoAdicionalBean - Um adicional de produto localizado no banco de
	 *         dados<br>
	 *         500 quando houver erro interno
	 */
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

	/**
	 * Recurso REST para fazer uma busca de vários adicionais de produto no BD de o
	 * valor passado por parametro na URL
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produtoAdicional/produtoAdicionais?dsAdicional=
	 * <p>
	 * 
	 * @param dsAdicional
	 *            - descrição do adicional de produto para pesquisa
	 * @return ArrayList ProdutoAdicionalBean - Uma lista de adicionais de produto
	 *         de acordo com os parametros enviados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/produtoAdicionais")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ProdutoAdicionalBean> findLike(@QueryParam("dsAdicional") String dsAdicional) {
		ProdutoAdicionalBean produtoAdicional = new ProdutoAdicionalBean(0, dsAdicional, 0);
		try {
			ArrayList<ProdutoAdicionalBean> produtoAdicionais = new ProdutoAdicionalService()
					.findLike(produtoAdicional);
			return produtoAdicionais;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
