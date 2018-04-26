/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import java.util.List;
/**
 * ViviendaDTO: Objeto que representa las viviendas fisicas del mundo, como una casa o un apartamento.
 * 
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *    "id": number,
 *    "direccion": string,
 *    "disponible": boolean,
 *    "servicio": Servicio,
 *    "valoracion": Valoracion,
 *    "valoracionPromedio": number,
 *    "latitud": number,
 *    "longitud": number,
 *    "tipoAlojamiento": string,
 *  
 *  }
 * </pre>
 * Por ejemplo una vivienda se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *    "id": 3312,
 *    "direccion": "calle 21 1307",
 *    "disponible": true,
 *    "servicio": [{servicio1,servicio2}],
 *    "valoracion": [{valoracion1,valoracion2, valoracion3},
 *    "valoracionPromedio": 4,
 *    "latitud": 32,
 *    "longitud": 456,
 *    "tipoAlojamiento": "B",
 *      
 *  }
 *
 * </pre>
 * @author jd.arango
 */
public class ViviendaDTO {
    private Long id;
    
    private boolean disponible;
    
    private String direccion;
      
    private double valoracionPromedio;
    
    private Integer capacidad;
    
    private String latitud;
    
    private String longitud;

    private String tipoAlojamiento;
    
    private Integer inquilinos;
    
    private Double precioMensual; 

    public ViviendaDTO(){
        
    }
    
    public ViviendaDTO(ViviendaEntity entity){
      this.id =entity.getId();
    
      this.disponible =entity.isDisponible();
    
      this.direccion = entity.getDireccion();
      
     this. valoracionPromedio = 0;
    
     this. capacidad =entity.getCapacidad();
    
      this.latitud = entity.getLatitud();
    
      this.longitud =entity.getLongitud();
      
      this.tipoAlojamiento = entity.getTipoAlojamiento(); 
   
      this.inquilinos = entity.getInquilinos();
  
      this.precioMensual = entity.getPrecioMensual();
    }
    /**
     * @return the disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    

    /**
     * @return the capacidad
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the tipoAlojamiento
     */
    public String getTipoAlojamiento() {
        return tipoAlojamiento;
    }

    /**
     * @param tipoAlojamiento the tipoAlojamiento to set
     */
    public void setTipoAlojamiento(String tipoAlojamiento) {
        this.tipoAlojamiento = tipoAlojamiento;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    public ViviendaEntity toEntity(){
        ViviendaEntity en = new ViviendaEntity();
        
        en.setCapacidad(capacidad);
        en.setDireccion(direccion);
        en.setDisponible(disponible);
        en.setId(id);
        en.setLatitud(latitud);
        en.setLongitud(longitud);
        en.setTipoAlojamiento(tipoAlojamiento);
        en.setInquilinos(inquilinos);
        en.setPrecioMensual(precioMensual);
        return en;
        
    }

    /**
     * @return the inquilinos
     */
    public int getInquilinos() {
        return inquilinos;
    }

    /**
     * @param inquilinos the inquilinos to set
     */
    public void setInquilinos(int inquilinos) {
        this.inquilinos = inquilinos;
    }

    /**
     * @return the precioMensual
     */
    public Double getPrecioMensual() {
        return precioMensual;
    }

    /**
     * @param precioMensual the precioMensual to set
     */
    public void setPrecioMensual(Double precioMensual) {
        this.precioMensual = precioMensual;
    }
    
    
   
    
}
