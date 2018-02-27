/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.heroes.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author jr.pacheco10
 */
@Entity
public class VillanoEntity extends BaseEntity{
    
    
    private String name;
    private Boolean preso;
    
    @Temporal(TemporalType.DATE) 
    private Date fechaArresto;

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public Boolean isPreso() {
        return preso;
    }

    public void setPreso(Boolean preso) {
        this.preso = preso;
    }

    public Date getFechaArresto() {
        return fechaArresto;
    }

    public void setFechaArresto(Date fechaArresto) {
        this.fechaArresto = fechaArresto;
    }
    
    
    
}
