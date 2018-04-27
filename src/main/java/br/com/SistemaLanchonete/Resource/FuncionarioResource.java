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

@Path("/funcionario")
public class FuncionarioResource {

	@POST
	@Path("/funcionario")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insert(FuncionarioBean funcionario) {
		try {
			new FuncionarioService().save(funcionario);
			return Response.status(201).entity("Funcionario Inserido com Sucesso").build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/{cdPessoa}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("cdPessoa") int codigo) {
		try {
			FuncionarioBean funcionario = new FuncionarioBean();
			funcionario.setCdPessoa(codigo);
			new FuncionarioService().save(funcionario);
			return Response.status(200).entity("Funcionario alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cdPessoa}")
	public Response delete(@PathParam("cdPessoa") int codigo) {
		try {
			FuncionarioBean funcionario = new FuncionarioBean();
			funcionario.setCdPessoa(codigo);
			new FuncionarioService().remove(funcionario);
			return Response.status(200).entity("Funcionario excluído com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/{cdPessoa}")
	@Produces(MediaType.APPLICATION_JSON)
	public FuncionarioBean select(@PathParam("cdPessoa") int codigo) {
		try {
			FuncionarioBean funcionario = new FuncionarioBean();
			funcionario.setCdPessoa(codigo);
			return new FuncionarioService().findById(funcionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/funcionarios")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<FuncionarioBean> findLike(FuncionarioBean funcionario) {
		try {
			ArrayList<FuncionarioBean> funcionarios = new FuncionarioService().findLike(funcionario);
			return funcionarios;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
