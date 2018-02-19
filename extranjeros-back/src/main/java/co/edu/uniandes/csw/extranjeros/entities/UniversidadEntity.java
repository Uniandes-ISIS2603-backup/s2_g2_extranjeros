/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Oliver
 */

@Entity
public class UniversidadEntity extends BaseEntity implements Serializable {
    private String nombre;
    private String direccion;
    private String ubicacionLat;
    private String ubicacionLon;
    private Long id;

    //------------------
    //Metodos
    //------------------
    
    
    /**
     * @return El nombre de la Universidad
     */
    
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Asigna el nombre de la universidad
     * @param nombre El nuevo nombre
     */
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * @return La direccion de la universidad
     */
    
    public String getDireccion(){
        return direccion;
    }
    
    /**
     * Asigna una direccion a la universidad
     * @param direccion La direccion
     */
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    /**
     * @return La latitud de la universidad
     */
    
    public String getUbicacionLat(){
        return ubicacionLat;
    }
    
    /**
     * Asigna latitud de la ubicacion de la universidad.
     * @param latitud La latitud
     */
    
    public void setUbicacionLat(String ubicacionLat){
        this.ubicacionLat = ubicacionLat;
    }
    
     /**
     * @return La longitud de la universidad
     */
    
    public String getUbicacionLon(){
        return ubicacionLon;
    }
    
    /**
     * Asigna longitud de la ubicacion de la universidad.
     * @param longitud La longitud
     */
    
    public void setUbicacionLon(String ubicacionLon){
        this.ubicacionLon = ubicacionLon;
    }
    
    /**
     * @return El ID de la universidad
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id El nuevo ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    
}
