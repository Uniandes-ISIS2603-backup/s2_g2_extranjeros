/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

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
    private Integer cedula;
    private Integer edad;

    //---------------------------------------------------
    // Atributos Relacionales
    //---------------------------------------------------
    
    @PodamExclude
    @ManyToMany(mappedBy = "usuariosAsociados")
    private List <FacturaEntity> facturas;
    
    @PodamExclude
    @ManyToMany(mappedBy = "usuarioInquilinos", cascade = CascadeType.ALL)
    private List <ViviendaEntity> viviendas;
    
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
    
    //---------------------------------------------------
    // Metodos atributos Relacionales
    //--------------------------------------------------- 
    
    /**
     * @return La lista de facturas asociadas a un usuario.
     */
    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    /**
     * Crea o modifica la lista de Facturas asociadas a un Usuario.
     * @param facturas Lista de Facturas.
     */
    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    /**
     * @return La lista de viviendas asociadas a un usuario.
     */
    public List<ViviendaEntity> getViviendas() {
        return viviendas;
    }

    /**
     * Crea o modifica la lista de Viviendas asociadas a un Usuario.
     * @param viviendas Lista de Facturas. 
     */
    public void setViviendas(List<ViviendaEntity> viviendas) {
        this.viviendas = viviendas;
    }
}
