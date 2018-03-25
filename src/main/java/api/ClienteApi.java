package api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import beans.ClienteBean;
import controllers.ClienteControllers;

/**
 *
 * @author Jonathan Oldenburg
 */
@Path("cliente")
public class ClienteApi {
    
    @POST
    @Path("/addCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertCustomer(ClienteBean cliente) {
        try {
            new ClienteControllers().Insert(cliente);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    
    @POST
    @Path("/getCliente")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response getCustomer(ClienteBean queryData) {
        try {
            List<ClienteBean> clientes = new ClienteControllers().DefaultSelect(queryData);
            return Response.status(200).entity(clientes).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    
    @POST
    @Path("/removeCliente")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response removeCustomer(ClienteBean queryData) {
        try {
            new ClienteControllers().Delete(queryData);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    
    @POST
    @Path("/updateCliente")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateCustomer(ClienteBean queryData) {
        try {
            new ClienteControllers().Update(queryData);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
}
