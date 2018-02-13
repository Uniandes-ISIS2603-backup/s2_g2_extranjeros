/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 * ArrendatarioDTO Objeto de transferencia de datos de Arrendatario. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre": String,
 *      "vivienda": Vivienda
 *   }
 * </pre>
 * Por ejemplo una ciudad se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "nombre": "Carlos Hugo" 
 *      "vivienda": [ {Vivienda1, Vivienda2,Vivienda3}]  ,
 *   }
 *
 * </pre>
 * @author Jose Pacheco
 */
public class ArrendatarioDTO extends UsuarioDTO {
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    
    private String nombre;
  
    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    
    public ArrendatarioDTO(String pUsuario, String pClave, String pCorreo, int pCelular, String pNombre){
        super(pUsuario, pClave, pCorreo, pCelular);
        nombre = pNombre;
    }
    
    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    
    /**
     * @return Obtiene el nombre del arrendatario
     */
    public String getName(){
        return nombre;
    }
    
    /**
     * Crea el nombre asociado a un usuario de tipo arrendatario
     * @param newName 
     */
    public void setName(String newName){
        this.nombre = newName;
    }
    
}
