/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import beans.LoginBean;
import controllers.LoginController;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Douglas
 */

@Path("/")
public class LoginApi {
    @POST
    @Path("/addLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertCustomer(LoginBean login) {
        try {
            new LoginController().Insert(login);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    
    @POST
    @Path("/getLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCustomer(LoginBean queryData) {
        try {
            List<LoginBean> logins = new LoginController().DefaultSelect(queryData);
            return Response.status(200).entity(logins).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    
    @POST
    @Path("/removeLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeCustomer(LoginBean queryData) {
        try {
            new LoginController().Delete(queryData);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    
    @POST
    @Path("/updateLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(LoginBean queryData) {
        try {
            new LoginController().Update(queryData);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    
    /*
    @POST
    @Path("/nextLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nextCustomer(LoginBean queryData) {
        try {
            new LoginController().NextValue(queryData);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e).build();
        }
    }
    */
}
