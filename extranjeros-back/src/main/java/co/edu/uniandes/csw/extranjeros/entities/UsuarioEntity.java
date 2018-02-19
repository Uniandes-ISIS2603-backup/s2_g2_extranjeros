/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 * @author jr.pacheco10
 */

@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    
    private String usuario;
    private String clave;
    private String correo;
    private Integer celular;    

    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------
    
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
    public Integer getCelular(){
        return celular;
    }
    
    /**
     * Crea o modifica el numero asociado a un usuario.
     * @param newPhone El nuevo correo. 
     */
    public void setCelular(Integer newPhone){
        this.celular = newPhone;
    }
}
