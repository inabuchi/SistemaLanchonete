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

import br.com.SistemaLanchonete.Domain.ProdutoBean;
import br.com.SistemaLanchonete.Service.ProdutoService;

/**
 * * Classe REST para manipulação de objetos do tipo Produto<br>
 * <p>
 * URI para acesso: http://localhost:8080/SistemaLanchonete/services/produto
 * 
 * @author Lino Pegoretti
 *
 */
@Path("/produto")
public class ProdutoResource {

	/**
	 * Recurso REST para inserção de um novo produto
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produto/produto
	 * <p>
	 * Arquivo JSON para modelo de produto:
	 * {@link br.com.SistemaLanchonete.ExemplosJSON.Produto.json}
	 * 
	 * @param produto
	 *            - Um objeto do tipo ProdutoBean para ser inserido no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando inserido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@POST
	@Path("/produto")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(ProdutoBean produto) {
		try {
			new ProdutoService().save(produto);
			return Response.status(200).entity("Produto Inserido com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para atualização de um produto no BD *
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produto/cdProduto
	 * <p>
	 * 
	 * @param cdProduto
	 *            - Um id de um ProdutoBean para ser atualizado no BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando atualizado com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@PUT
	@Path("/{cdProduto}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cdProduto") int cdProduto) {
		try {
			ProdutoBean produto = new ProdutoBean();
			produto.setCdProduto(cdProduto);
			new ProdutoService().save(produto);
			return Response.status(200).entity("Produto alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para remoção de um produto no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produto/cdPessoa
	 * <p>
	 * 
	 * @param cdProduto
	 *            - Um id de um ProdutoBean para ser removido do BD
	 * 
	 * @return Response - Uma resposta do servidor principal<br>
	 *         200 quando removido com sucesso<br>
	 *         500 quando houver erro interno
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdProduto}")
	public Response delete(@PathParam("cdProduto") int cdProduto) {
		try {
			ProdutoBean produto = new ProdutoBean();
			produto.setCdProduto(cdProduto);
			new ProdutoService().remove(produto);
			return Response.status(200).entity("Produto excluído com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para busca de um único produto no BD
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produto/cdProduto
	 * <p>
	 * 
	 * @param cdProduto
	 *            - Um id de um ProdutoBean para ser buscado no BD
	 * 
	 * @return ProdutoBean - Um produto localizado no banco de dados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/{cdProduto}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoBean select(@PathParam("cdProduto") int cdProduto) {
		try {
			ProdutoBean produto = new ProdutoBean();
			produto.setCdProduto(cdProduto);
			return new ProdutoService().findById(produto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	/**
	 * Recurso REST para fazer uma busca de vários produtos no BD de acordo com o
	 * campo e valor passado por parametro na URL
	 * <p>
	 * URI para acesso:
	 * http://localhost:8080/SistemaLanchonete/services/produto/produtos?dsRefProduto=&dsProduto=
	 * <p>
	 * 
	 * @param dsRefProduto
	 *            - Referencia de um produto para pesquisa
	 * @param dsProduto
	 *            - Descrição de um produto para pesquisa
	 * @param valor
	 *            - Valor do campo para a pesquisa
	 * @return ArrayList ProdutoBean - Uma lista de produtos de acordo com os
	 *         parametros enviados<br>
	 *         500 quando houver erro interno
	 */
	@GET
	@Path("/produtos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ProdutoBean> findLike(@QueryParam("dsRefProduto") String dsRefProduto,
			@QueryParam("dsProduto") String dsProduto) {
		ProdutoBean produto = new ProdutoBean(0, null, dsRefProduto, dsProduto, true);
		try {
			ArrayList<ProdutoBean> produtos = new ProdutoService().findLike(produto);
			return produtos;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
