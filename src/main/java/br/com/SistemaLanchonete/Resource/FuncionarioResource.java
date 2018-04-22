package br.com.SistemaLanchonete.Resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.SistemaLanchonete.Domain.FuncionarioBean;
import br.com.SistemaLanchonete.Service.FuncionarioService;
import br.com.SistemaLanchonete.Validacao.Validacao;

@Path("/funcionario")
public class FuncionarioResource {

	@POST
	@Path("/funcionario")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response insert(FuncionarioBean funcionario) {
		System.out.println("Cheguei Aqui");
		System.out.println("--> " + funcionario);
		try {
			new FuncionarioService().save(funcionario, 0);			
			return Response.status(200).entity(Validacao.removerCaracteresEspeciais("Funcionario inserido com Sucesso")).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/funcionario")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(FuncionarioBean funcionario) {
	try {
		new FuncionarioService().save(funcionario, 0);
			return Response.status(200).entity("Funcionario alterado com sucesso").build();
		
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("/funcionario")
	@Produces(MediaType.APPLICATION_JSON)
	public FuncionarioBean select(FuncionarioBean funcionario) {
		try {
			return new FuncionarioService().findById(funcionario);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	//
	@DELETE
	@Path("/funcionario/{cdPessoa}")
	public Response delete(FuncionarioBean funcionario) {
		try {
			new FuncionarioService().remove(funcionario);
		
			return Response.status(200).entity(Validacao.removerCaracteresEspeciais("Funcionario excluído.")).build();
		} catch (Exception e) {
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
