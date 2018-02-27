/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.LugaresDeInteresEntity;

/**
 * LugaresDeInteresDTO Objeto de transferencia de datos de Extranjeros. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "tipo": string,
 *      "nombre": string,
 *      "direccion": string,
 *      "telefono": number,
 *      "eventos": EventoDTO,
 *      "ubicacionLon": string,
 *      "ubicacionLat": string
 *      
 *   }
 * </pre>
 * Por ejemplo un Lugar de Interes se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 12311,
 *      "tipo": "Bar",
 *      "nombre": "La Pola",
 *      "direccion": "Calle 22 # 1 - 15",
 *      "telefono": 3224593,
 *      "eventos": EventoDTO,
 *      "ubicacionLon": "1.343344",
 *      "ubicacionLat": "-44.23233"       
 *   }
 *
 * </pre>
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
     **/
    
    public LugaresDeInteresDTO (){
        
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param city: Es la entidad que se va a convertir a DTO
     */
    public LugaresDeInteresDTO(LugaresDeInteresEntity lugarDeInteres) {
        if(lugarDeInteres != null){
        this.nombre = lugarDeInteres.getNombre();
        this.direccion = lugarDeInteres.getDireccion();
        this.telefono = lugarDeInteres.getTelefono();
        this.tipo = lugarDeInteres.getTipo();
        this.ubicacionLat = lugarDeInteres.getUbicacionLat();
        this.ubicacionLon = lugarDeInteres.getUbicacionLon();
        }
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
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public LugaresDeInteresEntity toEntity() {
        LugaresDeInteresEntity entity = new LugaresDeInteresEntity();
        entity.setNombre(this.nombre);
        entity.setDireccion(this.direccion);
        entity.setTelefono(this.telefono);
        entity.setTipo(this.tipo);
        entity.setUbicacionLat(this.ubicacionLat);
        entity.setUbicacionLon(this.ubicacionLon);
        return entity;
    }
    
    
    
}
