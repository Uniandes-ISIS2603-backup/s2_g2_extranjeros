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
public class ArrendatarioEntity extends UsuarioEntity implements Serializable {
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    private String nombre;
    
    
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
    
}
