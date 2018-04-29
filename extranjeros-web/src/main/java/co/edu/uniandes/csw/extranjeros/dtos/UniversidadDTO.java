/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.UniversidadEntity;

/**
 * UniversidadDTO Objeto de transferencia de datos. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name: string,
 *      "direccion": string,
 *      "ubicacionLat": string,
 *      "ubicacionLon": string
 *   }
 * </pre>
 * Por ejemplo una ciudad se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 121,
 *      "name: "Universidad de los Andes",
 *      "direccion": "Carrera 1 # 19 - 33",
 *      "ubicacionLon": "1.343344",
 *      "ubicacionLat": "-44.23233" 
 *   }
 *
 * </pre>
 *
 *
 * @author Oliver Amaya
 */
public class UniversidadDTO {
    //Atributos
    
    private String nombre;
    private String direccion;
    private String ubicacionLat;

    
    private String ubicacionLon;
    private String imagen;
    private Long id;
    
    
    //------------------
    //Constructor
    //------------------
    /**
     * Metodo para modelar el constructor por defecto
     * @param pNombre
     * @param pDireccion
     * @param pUbicacionLat
     * @param pUbicacionLon 
     */
    
    public UniversidadDTO()
    {
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param city: Es la entidad que se va a convertir a DTO
     */
    public UniversidadDTO(UniversidadEntity universidad) {
   
        if(universidad != null){
        this.nombre = universidad.getNombre();
        this.direccion = universidad.getDireccion();
        this.ubicacionLat =universidad.getUbicacionLat();
        this.ubicacionLon = universidad.getUbicacionLon();
        this.imagen = universidad.getImagen();
        this.id = universidad.getId();
        }
    }
    
      /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public UniversidadEntity toEntity() {
        UniversidadEntity entity = new UniversidadEntity();
        entity.setId(this.getId());
        entity.setNombre(this.nombre);
        entity.setDireccion(this.direccion);
        entity.setUbicacionLat(this.ubicacionLat);
        entity.setUbicacionLon(this.ubicacionLon);
        entity.setImagen(this.imagen);
        return entity;
    }
    
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
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
      
    
    
}
