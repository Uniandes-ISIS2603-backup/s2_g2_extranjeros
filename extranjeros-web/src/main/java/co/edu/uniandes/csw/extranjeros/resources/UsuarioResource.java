/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.dtos.*;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author jr.pacheco10
 */

@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class UsuarioResource {
    
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUser(@PathParam("id")Long id){
        return null;
    }
    
    @GET
    public List<UsuarioDetailDTO> getUsers(){
        List<UsuarioDetailDTO> retorno = new ArrayList<>();
        return retorno;
    }
    
    @POST
    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO userElement) throws BusinessLogicException{
        return userElement;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailDTO updateUser(@PathParam ("id") Long id) throws BusinessLogicException {
        return null;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUser(@PathParam ("id") Long id){
        
    }
 
}
