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

import br.com.SistemaLanchonete.Domain.CargoBean;
import br.com.SistemaLanchonete.Service.CargoService;
import br.com.SistemaLanchonete.Validacao.Validacao;

@Path("/cargo")
public class CargoResource {

	@POST
	@Path("/cargo")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response insert(CargoBean cargo) {
		System.out.println("Cheguei Aqui");
		System.out.println("--> " + cargo);
		try {
			new CargoService().save(cargo, 0);			
			return Response.status(200).entity(Validacao.removerCaracteresEspeciais("Cargo inserido com Sucesso")).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/Cargo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(CargoBean cargo) {
		try {
			new CargoService().save(cargo, 0);
			return Response.status(200).entity("Cargo alterado com sucesso").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("/Cargo")
	@Produces(MediaType.APPLICATION_JSON)
	public CargoBean select(CargoBean cargo) {
		try {
			return new CargoService().findById(cargo);
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@DELETE
	@Path("/Cargo/{cdPessoa}")
	public Response delete(CargoBean cargo) {
		try {
			new CargoService().remove(cargo);
		
			return Response.status(200).entity(Validacao.removerCaracteresEspeciais("Cargo excluído.")).build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("/Cargos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CargoBean> findLike(CargoBean cargo) {
		try {
			ArrayList<CargoBean> Cargos = new CargoService().findLike(cargo);
			return Cargos;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
