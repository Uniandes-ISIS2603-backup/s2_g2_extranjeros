/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import java.util.List;

/**
 *
 * @author am.quintero12
 */
public class EstudianteDTO extends UsuarioDTO {
    
    // ATRIBUTOS DE LA CLASE 
    /**
     * Nombre del estudiante 
     */
    private String nombre;
    
    /**
     * 
     */
    private Boolean estadoArrendamiento;
    
    /**
     * Modela la providencia del estudiante
     */
    private ProvidenciaDTO providencia;
    
    /*
    * Modela la universidad del estudiante
    */
    private UniversidadDTO universidad;
    
    /**
     * Tarjeta de crédito del estudiante 
     */
    private TarjetaDTO tarjeta;
    
    //_______________________________________________
    //METODOS 
    //_______________________________________________
    
    //Constructor
    /**
     * Constructor
     */
    public EstudianteDTO(){
        
    }
    
 

    //GETTERS
    public String getNombre() {
        return nombre;
    }

    public Boolean getEstadoArrendamiento() {
        return estadoArrendamiento;
    }

    public ProvidenciaDTO getProvidencia() {
        return providencia;
    }

    public UniversidadDTO getUniversidad() {
        return universidad;
    }

    public TarjetaDTO getTarjeta() {
        return tarjeta;
    }

    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstadoArrendamiento(Boolean estadoArrendamiento) {
        this.estadoArrendamiento = estadoArrendamiento;
    }

    public void setProvidencia(ProvidenciaDTO providencia) {
        this.providencia = providencia;
    }

    public void setUniversidad(UniversidadDTO universidad) {
        this.universidad = universidad;
    }

    public void setTarjeta(TarjetaDTO tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    
    
            
         
}
