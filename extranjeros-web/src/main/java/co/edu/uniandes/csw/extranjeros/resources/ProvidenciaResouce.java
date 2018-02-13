/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;


import co.edu.uniandes.csw.extranjeros.dtos.ProvidenciaDetailDTO;
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
 * @author am.quintero12
 */
@Path("providencia")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProvidenciaResouce {
    
    @POST
     public ProvidenciaDetailDTO createEstudiante(ProvidenciaDetailDTO providencia) {
        return providencia;
    }
    
     @GET
    public List<ProvidenciaDetailDTO> getEstudiantes() {
        return new ArrayList<>();
    }
    
    @GET
    @Path("{id: \\d+}")
    public ProvidenciaDetailDTO getEstudiante(@PathParam("id") Long id) {
        return null;
    }
    
    @PUT
    @Path("{id: \\d+}")
     public ProvidenciaDetailDTO updateEstudiante(@PathParam("id") Long id, ProvidenciaDetailDTO providencia) {
        return providencia;
    }
     
     @DELETE
    @Path("{id: \\d+}")
     public void deleteEstudiante(@PathParam("id") Long id) {
        
    }
    
}
