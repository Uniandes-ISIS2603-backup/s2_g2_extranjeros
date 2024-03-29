/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Oliver
 */

@Entity
public class LugaresDeInteresEntity extends BaseEntity implements Serializable {
    
    //Atributos
    private String tipo;
    private String nombre;
    private String direccion;
    private Integer telefono;
    private String ubicacionLat;
    private String ubicacionLon;
    private String imagen;
    
    //Atributos relacionales
    @PodamExclude
    @ManyToOne
    private ViviendaEntity viviendaConectada;
    
    
    
    //------------------
    //Metodos
    //------------------

    
    
    /**
     * @return El tipo de sitio
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Asigna un tipo al sitio de interes
     * @param tipo 
     */

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return El nombre del sitio
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Asigna un nombre al sitio de interes
     * @param nombre 
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return La imagen del sitio
     */
    public String getImagen() {
        return imagen;
    }
    
    /**
     * Asigna una imagen al sitio de interes
     * @param imagen 
     */

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return La direccion del sitio de interes
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Asigna una direccion al sitio de interes
     * @param direccion 
     */

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return El numero de la universidad
     */
    public Integer getTelefono() {
        return telefono;
    }
    
    /**
     * Asigna un telefono al sitio de interes
     * @param telefono 
     */

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    /**
     * @return La latitud del sitio de interes
     */
    public String getUbicacionLat() {
        return ubicacionLat;
    }
    
    /**
     * Asigna latitud de la ubicacion del sitio de interes.
     * @param ubicacionLat La latitud
     */

    public void setUbicacionLat(String ubicacionLat) {
        this.ubicacionLat = ubicacionLat;
    }
    
    /**
     * @return La longitud del sitio de interes
     */

    public String getUbicacionLon() {
        return ubicacionLon;
    }
    
    /**
     * Asigna longitud de la ubicacion del sitio de interes.
     * @param ubicacionLon La longitud
     */
    

    public void setUbicacionLon(String ubicacionLon) {
        this.ubicacionLon = ubicacionLon;
    }
    
    /**
     * @return the viviendaConectada
     */
    public ViviendaEntity getViviendaConectada() {
        return viviendaConectada;
    }

    /**
     * @param arrendatariosPropietarios the viviendaConectada to set
     */
    public void setArrendatariosPropietarios(ViviendaEntity viviendaConectada) {
        this.viviendaConectada = viviendaConectada;
    }
    
}
