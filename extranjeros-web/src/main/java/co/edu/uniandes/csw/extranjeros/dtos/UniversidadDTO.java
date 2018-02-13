/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

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
 *      "zipcode": string
 *   }
 * </pre>
 * Por ejemplo una ciudad se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 91852,
 *      "name: "Bogota, DC",
 *      "zipcode": "121110"
 *   }
 *
 * </pre>
 *
 *
 * @author Oliver Amaya
 */
public class UniversidadDTO {
    
    private String nombre;
    private String direccion;
    private String ubicacionLat;
    private String ubicacionLon;
    
    
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
    
    public UniversidadDTO(String pNombre, String pDireccion, String pUbicacionLat, String pUbicacionLon)
    {
        nombre = pNombre;
        direccion = pDireccion;
        ubicacionLat = pUbicacionLat;
        ubicacionLon = pUbicacionLon;
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
    
}
