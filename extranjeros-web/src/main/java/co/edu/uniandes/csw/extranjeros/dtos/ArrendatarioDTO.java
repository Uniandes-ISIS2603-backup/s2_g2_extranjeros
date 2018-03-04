/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.entities.UsuarioEntity;

/**
 * ArrendatarioDTO Objeto de transferencia de datos de Arrendatario. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre": String,
 *      "id": number
 *   }
 * </pre>
 * Por ejemplo una Arrendatario se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "nombre": "Carlos Hugo" 
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
    
    /**
     * Crea un objeto UsuarioTO a partir de un objeto UsuarioEntity.
     * @param entity Entidad UsuarioEntity desde la cual se va a crear el nuevo
     * objeto.
     */
        public ArrendatarioDTO (ArrendatarioEntity entity) {
        if (entity != null) {
            
            // De Arrendatario
            this.nombre = entity.getNombre();
            
            // De Usuario
            this.id = entity.getId();
            this.usuario = entity.getUsuario();
            this.clave = entity.getClave();
            this.correo = entity.getCorreo();
            this.celular = entity.getCelular();
            this.cedula = entity.getCedula();
            this.edad = entity.getEdad();
            
        }
    }
    
    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------

     /**
     * Convierte un objeto UsuarioDTO a UsuarioEntity.
     * @return Nueva objeto UsuarioEntity.
     */
    @Override
    public ArrendatarioEntity toEntity() {
        
        // UsuarioEntity
        UsuarioEntity userEntity = super.toEntity();
        
        // Genera
        ArrendatarioEntity entity = new ArrendatarioEntity();
        
        // Asocia atributos
        entity.setId(this.getId());
        entity.setNombre(this.nombre);
        
        entity.setUsuario(userEntity.getUsuario());
        entity.setClave(userEntity.getClave());
        entity.setCorreo(userEntity.getCorreo());
        entity.setCelular(userEntity.getCelular());
        entity.setEdad(this.edad);
        entity.setCedula(this.cedula);
        
        // Return
        return entity;
    }
    
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
    public Long getId() {
        return id;
    }

    /**
     * Le asgina un identificador a un Usuario
     * @param id Identificador que se asociara
     */
    public void setId(Long id) {
        this.id = id;
    }
}
