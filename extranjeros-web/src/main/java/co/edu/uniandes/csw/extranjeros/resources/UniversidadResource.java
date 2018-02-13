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
 *
 * @author o.amaya724
 */

@Path("universidades")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class UniversidadResource {
   @GET
   @Path("{id: \\d+}")
   public UniversidadDetailDTO getUniversidad(@PathParam("id")Long id){
        return null;
    }
   
   @GET
    public List<UniversidadDetailDTO> getUniversidades()
    {
        return new ArrayList<>();
    }
    
    @POST
    public UniversidadDetailDTO createUniversidad(UniversidadDetailDTO universidad)throws BusinessLogicException
    {
        return universidad;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public UniversidadDetailDTO updateUniversidad(@PathParam("id") Long id, UniversidadDetailDTO universidad)
    {
        return null;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUniversidad(@PathParam("id") Long id)
    {
    }
    
}
