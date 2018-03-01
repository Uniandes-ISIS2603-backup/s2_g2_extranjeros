/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
import co.edu.uniandes.csw.extranjeros.entities.ValoracionEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link ViviendaDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la vivienda vaya a la documentacion de {@link ViviendaDTO}
 * @author jd.arango
 */
public class ViviendaDetailDTO extends ViviendaDTO {
    
    private List<ServicioDTO> serviciosFijos;
   
    private List<ServicioDTO> serviciosAdicionales;
    
    private List<ValoracionDTO> valoraciones;
 
    public ViviendaDetailDTO(){
        super();
    }
    
     public ViviendaDetailDTO(ViviendaEntity entity){
        super(entity);
    }
    
    public ViviendaEntity toEntity()
    {
        
        ViviendaEntity e = super.toEntity();
        
        List<ServicioEntity> PserviciosFijos = new ArrayList<>();
   
        List<ServicioEntity> PserviciosAdicionales = new ArrayList<>();
    
        List<ValoracionEntity> Pvaloraciones = new ArrayList<>();
        
        for (int i = 0; i < valoraciones.size(); i++) {
            ValoracionEntity get = valoraciones.get(i).toEntity();
            Pvaloraciones.add(get);
        }
          for (int i = 0; i < serviciosAdicionales.size(); i++) {
            ServicioEntity get = serviciosAdicionales.get(i).toEntity();
            PserviciosAdicionales.add(get);
        }
           for (int i = 0; i < serviciosFijos.size(); i++) {
            ServicioEntity get = serviciosFijos.get(i).toEntity();
            PserviciosFijos.add(get);
        }
           e.setValoraciones(Pvaloraciones);
           e.setServiciosAdicionales(PserviciosAdicionales);
           e.setServiciosFijos(PserviciosFijos);
        
      return e;
    }
    
    /**
     * @return the serviciosFijos
     */
    public List<ServicioDTO> getServiciosFijos() {
        return serviciosFijos;
    }

    /**
     * @param serviciosFijos the serviciosFijos to set
     */
    public void setServiciosFijos(List<ServicioDTO> serviciosFijos) {
        this.serviciosFijos = serviciosFijos;
    }

    /**
     * @return the serviciosAdicionales
     */
    public List<ServicioDTO> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    /**
     * @param serviciosAdicionales the serviciosAdicionales to set
     */
    public void setServiciosAdicionales(List<ServicioDTO> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }
}

