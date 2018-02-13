/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 *
 * @author Oliver Amaya
 */
public class LugaresDeInteresDTO {
    
    private String tipo;
    private String nombre;
    private String direccion;
    private Integer telefono;
    private EventoDTO eventos;
    private String ubicacionLat;
    private String ubicacionLon;
    
    
    //------------------
    //Constructor
    //------------------
    /**
     * Metodo para modelar el constructor por defecto
     * @param pTipo
     * @param pNombre
     * @param pDireccion
     * @param pTelefono
     * @param pEventos
     * @param pUbicacionLat
     * @param pUbicacionLon 
     */
    
    public LugaresDeInteresDTO (){
        
        
    }
    
    
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
     * @return El evento del sitio de interes
     */
    public EventoDTO getEventos() {
        return eventos;
    }
    
    /**
     * Asigna los eventos que posee el sitio de interes
     * @param eventos 
     */

    public void setEventos(EventoDTO eventos) {
        this.eventos = eventos;
    }

    /**
     * @return La latitud del sitio de interes
     */
    public String getUbicacionLat() {
        return ubicacionLat;
    }
    
    /**
     * Asigna latitud de la ubicacion del sitio de interes.
     * @param latitud La latitud
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
     * @param longitud La longitud
     */
    

    public void setUbicacionLon(String ubicacionLon) {
        this.ubicacionLon = ubicacionLon;
    }
    
    
    
}
