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
 * @author la.ruiz967
 */
@Entity
public class TarjetaEntity extends BaseEntity implements Serializable {
    
    /**
     * Numero de la tarjeta
     */
    private Long numero;
    
    /**
     * Nombre de la franquicia
     */
    private String banco;
    
    /**
     * Fecha de caducidad de la tarjeta
     */
    private String fechaCaducidad;

    /**
     * Retorna el número de la tarjeta
     * @return 
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * Cambia el nùmero de la tarjeta
     * @param numero 
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    /**
     * Retorna la franquicia de la tarjeta
     * @return 
     */
    public String getBanco() {
        return banco;
    }

    /**
     * Cambia la franquicia de la tarjeta
     * @param banco 
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }

    /**
     * Retorna la fecha de caducidad de la tarjeta
     * @return 
     */
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * Cambia la fecha de caducidad de la tarjeta
     * @param fechaCaducidad 
     */
    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    
}
