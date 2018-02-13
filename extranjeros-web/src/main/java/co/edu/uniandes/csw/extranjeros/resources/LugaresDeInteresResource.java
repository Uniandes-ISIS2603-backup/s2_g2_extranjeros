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

@Path("lugares")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LugaresDeInteresResource {
    
    @GET
   @Path("{id: \\d+}")
   public LugaresDeInteresDetailDTO getLugar(@PathParam("id")Long id){
        return null;
    }
   
   @GET
    public List<LugaresDeInteresDetailDTO> getLugares()
    {
        return new ArrayList<>();
    }
    
    @POST
    public LugaresDeInteresDetailDTO createLugar(LugaresDeInteresDetailDTO lugar)throws BusinessLogicException
    {
        return lugar;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public LugaresDeInteresDetailDTO updateLugar(@PathParam("id") Long id, LugaresDeInteresDetailDTO lugar)
    {
        return null;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLugar(@PathParam("id") Long id)
    {
    }
    
}
