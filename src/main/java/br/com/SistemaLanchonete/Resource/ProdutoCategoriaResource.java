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

import br.com.SistemaLanchonete.Domain.ProdutoCategoriaBean;
import br.com.SistemaLanchonete.Service.ProdutoCategoriaService;

/**
 * * Classe REST para manipulação de objetos do tipo ProdutoCategoria<br>
 * <p>
 * URI para acesso:
 * http://localhost:8080/SistemaLanchonete/services/produtoCategoria
 * 
 * @author Lino Pegoretti
 *
 */
@Path("/produtoCategoria")
public class ProdutoCategoriaResource {
	/**
	 * Recurso REST para inserção de uma nova categoria de produto
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produtoCategoria/produtoCategoria
	 * <p>
	 * Arquivo JSON para modelo de categoria de produto:
	 * {@link br.com.SistemaLanchonete.ExemplosJSON.ProdutoCategoria.json}
	 * 
	 * @param produtoCategoria
	 *            - Um objeto do tipo ProdutoCategoriaBean para ser inserido no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando inserido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@POST
	@Path("/produtoCategoria")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(ProdutoCategoriaBean produtoCategoria) {
		try {
			new ProdutoCategoriaService().save(produtoCategoria);
			return Response.status(200).entity("Categoria de Produto Inserida com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para atualização de uma categoria de produto no BD *
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produtoCategoria/cdProdutoCategoria
	 * <p>
	 * 
	 * @param cdProdutoCategoria
	 *            - Um id de um ProdutoCategoriaBean para ser atualizado no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando atualizado com sucesso<br>
	 *         500 quando houver erro interno
	 */
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

	/**
	 * Recurso REST para remoção de uma categoria de produto no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produtoCategoria/cdProdutoCategoria
	 * <p>
	 * 
	 * @param cdProdutoCategoria
	 *            - Um id de um ProdutoCategoriaBean para ser removido do BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando removido com sucesso<br>
	 *         500 quando houver erro interno
	 */
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

	/**
	 * Recurso REST para busca de uma única categoria de produto no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produtoCategoria/cdProdutoCategoria
	 * <p>
	 * 
	 * @param cdProdutoCategoria
	 *            - Um id de um ProdutoCategoriaBean para ser buscado no BD
	 * 
	 * @return ProdutoCategoriaBean - Uma categoria de produto localizado no banco
	 *         de dados<br>
	 *         500 quando houver erro interno
	 */
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

	/**
	 * Recurso REST para fazer uma busca de várias categorias de produto no BD de
	 * acordo com o valor passado por parametro na URL
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produtoCategoria/produtoCategorias?dsCategoria=
	 * <p>
	 * 
	 * @param dsCategoria
	 *            - Uma categoria para pesquisa
	 * @return ArrayList ProdutoCategoriaBean - Uma lista de categorias de produto
	 *         de acordo com os parametros enviados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/produtoCategorias")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ProdutoCategoriaBean> findLike(@QueryParam("dsCategoria") String dsCategoria) {
		ProdutoCategoriaBean produtoCategoria = new ProdutoCategoriaBean(0, dsCategoria);
		try {
			ArrayList<ProdutoCategoriaBean> produtoCategorias = new ProdutoCategoriaService()
					.findLike(produtoCategoria);
			return produtoCategorias;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
