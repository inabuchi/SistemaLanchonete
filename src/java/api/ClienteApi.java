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
 * @author Jonathan Oldenburg
 */
@Path("/clientes")
public class ClienteApi {
    
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCustomer(ClienteBean cliente) {
        try {
            new ClienteControllers().Insert(cliente);
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
            List<ClienteBean> clientes = new ClienteControllers().DefaultSelect(queryData);
            return Response.status(200).entity(clientes).build();
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
            new ClienteControllers().Delete(queryData);
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
            new ClienteControllers().Update(queryData);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
}