/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author jr.pacheco10
 */

@MappedSuperclass
public abstract class UsuarioEntity implements Serializable {

    //----------------------------------------------------------
    // ID: Usuario pasa a ser Padre de Arrendatario y Estudiante
    //----------------------------------------------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    
    private String usuario;
    private String clave;
    private String correo;
    private Integer celular;
    private Integer cedula;
    private Integer edad;

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
    
    /**
     * @return El numero de cedula de un usuario.
     */
    public Integer getCedula() {
        return cedula;
    }
    
    /**
     * Crea o modifica el numero de cedula asociado a un usuario.
     * @param cedula La nueva cedula. 
     */
    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    /**
     * @return La edad de un Usuario.
     */
    public Integer getEdad() {
        return edad;
    }
    
    /**
     * Crea o modifica la edad asociado a un usuario.
     * @param edad La nueva edad. 
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    //----------------------------------------------------------
    // Métodos para emular una MappedSuperClass
    //----------------------------------------------------------
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this.getId() != null && ((BaseEntity) obj).getId() != null) {
            return this.getId().equals(((BaseEntity) obj).getId());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }
}
