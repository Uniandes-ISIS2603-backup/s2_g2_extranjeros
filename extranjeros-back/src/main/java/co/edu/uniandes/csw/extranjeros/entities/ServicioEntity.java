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
 * @author s.rodriguezm
 */
@Entity
public class ServicioEntity extends BaseEntity implements Serializable 
{
    private String tipo;
    private Boolean adicional;
    private Double precioMensual;
    
    /**
     * @return el tipo de servicio.
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * @param tipo el nuevo tipo del servicio.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * @return si es adicional o no.
     */
    public Boolean getAdicional() {
        return adicional;
    }
    /**
     * @param adicional si el servicio es adicional o no.
     */
    public void setAdicional(Boolean adicional) {
        this.adicional = adicional;
    }
    /**
     * @return precio mensual del servicio.
     */
    public Double getPrecioMensual() {
        return precioMensual;
    }
    /**
     * @param precioMensual nuevo precio mensual del servicio.
     */
    public void setPrecioMensual(Double precioMensual) {
        this.precioMensual = precioMensual;
    }
    
}
