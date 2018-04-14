/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import beans.ClienteBean;
import controllers.ClienteControllers;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pc1
 */
@Path("/usuarios")
public class UsuarioApi {
    
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCustomer(ClienteBean cliente) {
        try {
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    
    @GET
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(ClienteBean queryData) {
        try {
            return Response.status(200).entity("teste").build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    
    @DELETE
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeCustomer(ClienteBean queryData) {
        try {
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    
    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(ClienteBean queryData) {
        try {
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
}
