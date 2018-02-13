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
import javax.ws.rs.PathParam;


/**
 *
 * @author am.quintero12
 */
public class EstudianteResource {
    
    public EstudianteDetailDTO createEstudiante(EstudianteDetailDTO estudiante) {
        return estudiante;
    }
    
    public List<EstudianteDetailDTO> getEstudiantes() {
        return new ArrayList<>();
    }
    
    public EstudianteDetailDTO getEstudiante(@PathParam("id") Long id) {
        return null;
    }
    
     public EstudianteDetailDTO updateEstudiante(@PathParam("id") Long id, EstudianteDetailDTO estudiante) {
        return estudiante;
    }
     
     public void deleteEstudiante(@PathParam("id") Long id) {
        
    }
    
}
