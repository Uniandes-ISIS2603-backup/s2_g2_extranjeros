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
 * @author jd.arango
 */
@Entity
public class ValoracionEntity extends BaseEntity implements Serializable {
     private double valoracion;
    
    private String comentario;

    /**
     * @return the valoracion
     */
    public double getValoracion() {
        return valoracion;
    }

    /**
     * @param valoracion the valoracion to set
     */
    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
