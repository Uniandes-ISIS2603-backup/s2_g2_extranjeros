/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 * ArrendatarioDTO Objeto de transferencia de datos de Arrendatario. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre": String,
 *      "vivienda": Vivienda
 *      "id": number
 *   }
 * </pre>
 * Por ejemplo una ciudad se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "nombre": "Carlos Hugo" 
 *      "vivienda": [{Vivienda1, Vivienda2, Vivienda3}]
 *      "id": 4323
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
    private Long id;
  
    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    
    /**
     * Constructor por defecto de la Clase.
     */
    public ArrendatarioDTO(){
    }
    
    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------

    /**
     * @return Obtiene el nombre del arrendatario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Crea el nombre asociado a un usuario de tipo arrendatario
     * @param pNombre Nombre a asociar 
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

     /**
     * @return Retorna el ID de un Usuario. 
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Le asgina un identificador a un Usuario
     * @param id Identificador que se asociara
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    

    
}
