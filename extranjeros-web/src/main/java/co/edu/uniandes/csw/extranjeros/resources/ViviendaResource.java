/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.dtos.ViviendaDTO;
import co.edu.uniandes.csw.extranjeros.dtos.ViviendaDetailDTO;
import java.util.ArrayList;
import java.util.LinkedList;
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
 * @author jd.arango
 */
@Path("viviendas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ViviendaResource {
    
     @POST
    public ViviendaDTO createVivienda(ViviendaDTO vivienda)  {
        return vivienda;
    }
    
    @GET
    public List<ViviendaDetailDTO> getViviendas(){
        return new ArrayList<ViviendaDetailDTO>();
    }
    
    @GET
    @Path("{id: \\d+}")
    public ViviendaDetailDTO getVivienda(@PathParam("id") Long id) {
        return null;
    }
    
     @PUT
    @Path("{id: \\d+}")
    public ViviendaDetailDTO updateVivienda(@PathParam("id") Long id, ViviendaDetailDTO vivienda) {
        return vivienda;
    }
    
    
     @DELETE
    @Path("{id: \\d+}")
     public void deleteVivienda(@PathParam("id") Long id) {
        
    }
    
    
    

 
}
