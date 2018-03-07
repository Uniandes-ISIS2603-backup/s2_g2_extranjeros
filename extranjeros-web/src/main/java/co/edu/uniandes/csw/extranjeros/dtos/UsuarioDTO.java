/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.UsuarioEntity;

/**
 * UsuarioDTO es un Objeto de transferencia de datos de Usuario. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "usuario": String,
 *      "clave": string,
 *      "correoAsociado": string,
 *      "celular": number,
 *      "id": number,
 *      "cedula": number
 *   }
 * </pre>
 * Por ejemplo un usuario se podria representar asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "usuario": "carlManson",
 *      "clave": "holamundo12345",
 *      "correoAsociado": "carlmanson@outlook.com",
 *      "celular": 31132867894
 *      "id": 31231,
 *      "cedula": number
 *   }
 *
 * </pre>
 * @author Jose Pacheco
 */

public class UsuarioDTO {
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    
    protected String usuario;
    protected String clave;
    protected String correo;
    protected String celular;
    protected String cedula;
    protected int edad;
    
    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    /**
     * Metodo para modelar el constructor por defecto.
     */
    public UsuarioDTO() {
        /* Constructor vacío por defecto*/
    }
    
    /**
     * Crea un objeto UsuarioTO a partir de un objeto UsuarioEntity.
     * @param entity Entidad UsuarioEntity desde la cual se va a crear el nuevo
     * objeto.
     */
        public UsuarioDTO(UsuarioEntity entity) {
        if (entity != null) {
            this.usuario = entity.getUsuario();
            this.clave = entity.getClave();
            this.correo = entity.getCorreo();
            this.celular = entity.getCelular();
        }
    }
    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------

    /**
     * Convierte un objeto UsuarioDTO a UsuarioEntity.
     * @return Nueva objeto UsuarioEntity.
     */
    public UsuarioEntity toEntity() {
        
        // Genera
        UsuarioEntity entity = new UsuarioEntity() {};
        
        // Asocia atributos
        entity.setUsuario(this.getUsuario());
        entity.setClave(this.getClave());
        entity.setCorreo(this.getCorreo());
        entity.setCelular(this.getCelular()); 
        entity.setEdad(this.edad);
        entity.setCedula(this.cedula);
        
        // Return
        return entity;
    }
    
    /**
     * @return El nombre (nickname en la plataforma) del usuario. 
     */
    public String getUsuario(){
        return usuario;
    }
    
    /**
     * Crea un nombre de usuario. 
     * @param newUser El nuevo ID
     */
    public void setUsuario(String newUser){
        this.usuario = newUser;
    }
    
    /**
     * @return La clave del usuario
     */
    public String getClave(){
        return clave;
    }
    
    /**
     * Crea o cambia una contrasenia. 
     * @param newPassword La nueva contrasenia.
     */
    public void setClave(String newPassword){
        this.clave = newPassword;
    }
    
    /**
     * @return El correo de un usuario.
     */
    public String getCorreo(){
        return correo;
    }
    
    /**
     * Crea o modifica el correo asociado a un usuario.
     * @param newEmail El nuevo correo. 
     */
    public void setCorreo(String newEmail){
        this.correo = newEmail;
    }
    
    /**
     * @return El numero de un usuario.
     */
    public String getCelular(){
        return celular;
    }
    
    /**
     * Crea o modifica el numero asociado a un usuario.
     * @param newPhone El nuevo correo. 
     */
    public void setCelular(String newPhone){
        this.celular = newPhone;
    }
    
    /**
     * @return El numero de cedula de un usuario.
     */   
    public String getCedula() {
        return cedula;
    }

    /**
     * Crea o modifica el numero de cedula asociado a un usuario.
     * @param cedula El nuevo correo. 
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }   
    
    /**
     * @return La edad de un Usuario.
     */
    public int getEdad() {
        return edad;
    }
    
    /**
     * Crea o modifica la edad asociado a un usuario.
     * @param edad La nueva edad. 
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
}