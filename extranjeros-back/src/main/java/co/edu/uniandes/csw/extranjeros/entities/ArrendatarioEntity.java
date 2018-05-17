/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;

import co.edu.uniandes.csw.extranjeros.podam.CelularStrategy;
import co.edu.uniandes.csw.extranjeros.podam.ClaveStrategy;
import co.edu.uniandes.csw.extranjeros.podam.CorreoStrategy;
import co.edu.uniandes.csw.extranjeros.podam.IntegerStrategy;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 * @author jr.pacheco10
 */

@Entity
public class ArrendatarioEntity extends BaseEntity implements Serializable {
       
    //---------------------------------------------------
    // Atributos Relacionales
    //---------------------------------------------------
    
    @PodamExclude
    @OneToMany(mappedBy = "arrendatariosAsociados")
    private List <FacturaEntity> facturas;
    
    @PodamExclude
    @OneToMany(mappedBy = "arrendatariosPropietarios")
    private List <ViviendaEntity> viviendas;
    
    @PodamExclude
    @OneToMany(mappedBy = "arrendatarioTitular", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CuentaBancariaEntity> cuentasBancarias;
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    private String nombre;
    
    private String usuario;
    
    @PodamStrategyValue(CorreoStrategy.class)
    private String correo;
    
    @PodamStrategyValue(ClaveStrategy.class)
    private String clave;
    
    @PodamStrategyValue(CelularStrategy.class)
    private String celular;
    
    private String cedula;
    
    @PodamStrategyValue(IntegerStrategy.class)
    private Integer edad;
    
    private String imagen;
    
    private String rol;
    
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
     * @param cedula La nueva cedula. 
     */
    public void setCedula(String cedula) {
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
    
    /**
     * @return La cuenta bancaria asociadas a un arrendatario.
     */
    public List<CuentaBancariaEntity> getCuentasBancarias() {
        return cuentasBancarias;
    }

    /**
     * Crea o modifica la cuenta de banco asociada a un Arrendatario.
     * @param cuentaBancaria Cuenta de Banco. 
     */
    public void setCuentasBancarias(List<CuentaBancariaEntity> cuentaBancaria) {
        this.cuentasBancarias = cuentaBancaria;
    } 

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
} 
