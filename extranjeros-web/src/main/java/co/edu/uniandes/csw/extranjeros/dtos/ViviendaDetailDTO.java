/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
import co.edu.uniandes.csw.extranjeros.entities.ValoracionEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.AbstractList;
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
    
    private List<FacturaDTO> facturas;
 
    public ViviendaDetailDTO(){
        super();
    }
    
     public ViviendaDetailDTO(ViviendaEntity entity){
        super(entity);
         serviciosAdicionales = new ArrayList<>();
         serviciosFijos = new ArrayList<>();
         valoraciones = new ArrayList<>();
         facturas = new ArrayList<>();
          if(entity.getFacturas()!=null){
             for (int i = 0; i < entity.getServiciosAdicionales().size(); i++) {
                 facturas.add(new FacturaDTO(entity.getFacturas().get(i)));
             }
         }
         if(entity.getServiciosAdicionales()!=null){
             for (int i = 0; i < entity.getServiciosAdicionales().size(); i++) {
                 serviciosAdicionales.add(new ServicioDTO(entity.getServiciosAdicionales().get(i)));
             }
         }
         if(entity.getServiciosFijos()!=null){
             for (int i = 0; i < entity.getServiciosFijos().size(); i++) {
                 serviciosFijos.add(new ServicioDTO(entity.getServiciosFijos().get(i)));
             }
         }
          if(entity.getValoraciones()!=null){
             for (int i = 0; i < entity.getValoraciones().size(); i++) {
                 valoraciones.add(new ValoracionDTO(entity.getValoraciones().get(i)));
             }
         }
       //preguntar for de creadores de dtos asociados
    }
    
    public ViviendaEntity toEntity()
    {
        
        ViviendaEntity e = super.toEntity();
        
        List<ServicioEntity> PserviciosFijos = new ArrayList<>();
   
        List<ServicioEntity> PserviciosAdicionales = new ArrayList<>();
    
        List<ValoracionEntity> Pvaloraciones = new ArrayList<>();
        
        List<FacturaEntity> PFacturas = new ArrayList<>();
        
        if(getValoraciones()!=null){
        for (int i = 0; i < getValoraciones().size(); i++) {
            ValoracionEntity get = getValoraciones().get(i).toEntity();
            Pvaloraciones.add(get);
        }
        }
         if(getFacturas()!=null){
        for (int i = 0; i < getFacturas().size(); i++) {
            FacturaEntity get = getFacturas().get(i).toEntity();
            PFacturas.add(get);
        }
        }
        
        if(getServiciosAdicionales()!=null){
          for (int i = 0; i < serviciosAdicionales.size(); i++) {
            ServicioEntity get = serviciosAdicionales.get(i).toEntity();
            PserviciosAdicionales.add(get);
        }
        }
        if(getServiciosFijos()!=null){
           for (int i = 0; i < serviciosFijos.size(); i++) {
            ServicioEntity get = serviciosFijos.get(i).toEntity();
            PserviciosFijos.add(get);
        }
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

    /**
     * @return the valoraciones
     */
    public List<ValoracionDTO> getValoraciones() {
        return valoraciones;
    }

    /**
     * @param valoraciones the valoraciones to set
     */
    public void setValoraciones(List<ValoracionDTO> valoraciones) {
        this.valoraciones = valoraciones;
    }

    /**
     * @return the facturas
     */
    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<FacturaDTO> facturas) {
        this.facturas = facturas;
    }
}

