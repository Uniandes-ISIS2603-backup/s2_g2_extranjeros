/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;


import co.edu.uniandes.csw.extranjeros.dtos.ProvidenciaDetailDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PathParam;

/**
 *
 * @author am.quintero12
 */
public class ProvidenciaResouce {
    
     public ProvidenciaDetailDTO createEstudiante(ProvidenciaDetailDTO providencia) {
        return providencia;
    }
    
    public List<ProvidenciaDetailDTO> getEstudiantes() {
        return new ArrayList<>();
    }
    
    public ProvidenciaDetailDTO getEstudiante(@PathParam("id") Long id) {
        return null;
    }
    
     public ProvidenciaDetailDTO updateEstudiante(@PathParam("id") Long id, ProvidenciaDetailDTO providencia) {
        return providencia;
    }
     
     public void deleteEstudiante(@PathParam("id") Long id) {
        
    }
    
}
