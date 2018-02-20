/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author am.quintero12
 */

@Entity
public class EstudianteEntity extends BaseEntity implements Serializable{
    
    private String nombre;
    private String estadoArrendamiento;
    private String usuario;
    private String clave;
    private String correo;
    private int celular;

    
    /**
     * Método que retorna el nombre del estudiante
     * @return Nombre del estudiante
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del estudiante 
     * @param nombre del estudiante
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *Método que retorna el estado de arrendaiento del estudiante 
     * @return El estado de arrendamiento del estudiante
     */
    public String getEstadoArrendamiento() {
        return estadoArrendamiento;
    }

    /**
     * Asigna el estado de arrentamiento
     * @param estadoArrendamiento 
     */
    public void setEstadoArrendamiento(String estadoArrendamiento) {
        this.estadoArrendamiento = estadoArrendamiento;
    }

    /**
     * 
     * @return usuario del estudiante
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * 
     * @param usuario del estudiante 
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * 
     * @return clave del estudiante
     */
    public String getClave() {
        return clave;
    }

    /**
     * Asigna la clave del estudiante 
     * @param clave del estudiante 
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * 
     * @return el correo del estudiante 
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Asigna el correo del estudiante
     * @param correo del estudiante
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * 
     * @return celular del estudiante 
     */
    public int getCelular() {
        return celular;
    }

    /**
     *Asigna el celular del estudiante 
     * @param celular del estudiante
     */
    public void setCelular(int celular) {
        this.celular = celular;
    }
    
    
}
