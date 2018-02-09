/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 * UsuarioDTO es un Objeto de transferencia de datos de Arrendatario. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "usuario": String,
 *      "clave": string,
 *      "correoAsociado": string,
 *      "celular": number
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
 *   }
 *
 * </pre>
 * @author Jose Pacheco
 */
public class UsuarioDTO {
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    
    private String usuario;
    private String clave;
    private String correo;
    private Long celular;

    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------
    
    /**
     * Metodo para modelar el constructor por defecto
     */
    public UsuarioDTO(){
        
    }
    // Entity
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
    public Long getCelular(){
        return celular;
    }
    
    /**
     * Crea o modifica el numero asociado a un usuario.
     * @param newPhone El nuevo correo. 
     */
    public void setCelular(Long newPhone){
        this.celular = newPhone;
    }
    
}