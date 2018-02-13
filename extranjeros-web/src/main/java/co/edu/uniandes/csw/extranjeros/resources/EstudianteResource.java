/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.dtos.CityDetailDTO;
import co.edu.uniandes.csw.extranjeros.dtos.EstudianteDetailDTO;
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
@Path("estudiantes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EstudianteResource {
    
    @POST
    public EstudianteDetailDTO createEstudiante(EstudianteDetailDTO estudiante) {
        return estudiante;
    }
    
    @GET
    public List<EstudianteDetailDTO> getEstudiantes() {
        return new ArrayList<>();
    }
    
    @GET
    @Path("{id: \\d+}")
    public EstudianteDetailDTO getEstudiante(@PathParam("id") Long id) {
        return null;
    }
    
    @PUT
    @Path("{id: \\d+}")
     public EstudianteDetailDTO updateEstudiante(@PathParam("id") Long id, EstudianteDetailDTO estudiante) {
        return estudiante;
    }
     
     @DELETE
    @Path("{id: \\d+}")
     public void deleteEstudiante(@PathParam("id") Long id) {
        
    }
    
}
