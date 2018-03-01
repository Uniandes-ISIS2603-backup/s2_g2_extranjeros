/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
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
   
    
    //_______________________________________________
    //METODOS 
    //_______________________________________________
    
    //Constructor
    /**
     * Constructor
     */
    public EstudianteDTO(){
        
    }
    
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param city: Es la entidad que se va a convertir a DTO
     */
    public EstudianteDTO(EstudianteEntity estudiante) {
        //super(estudiante);
        this.nombre = estudiante.getNombre();
       this.usuario = estudiante.getUsuario();
       this.clave = estudiante.getClave();
       this.cedula = estudiante.getCedula();
       this.celular = estudiante.getCelular();
       this.estadoArrendamiento = estudiante.isEstadoArrendamiento();
       this.edad = this.getEdad();
       this.correo = estudiante.getCorreo();

    }
    
    public EstudianteEntity toEntity(){
       EstudianteEntity entity = new EstudianteEntity();
       
       
       
       return entity;
    }
            
 

 
    public String getNombre() {
        return nombre;
    }

    public Boolean getEstadoArrendamiento() {
        return estadoArrendamiento;
    }

 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstadoArrendamiento(Boolean estadoArrendamiento) {
        this.estadoArrendamiento = estadoArrendamiento;
    }
         
         
}
