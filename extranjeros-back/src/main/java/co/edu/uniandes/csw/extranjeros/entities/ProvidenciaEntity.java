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
public class ProvidenciaEntity extends BaseEntity implements Serializable {
    
   private String pais;
   private String region;

   
   /**
    * 
    * @return el pais del estudiante
    */
    public String getPais() {
        return pais;
    }

    /**
     * Asigna el pais del estudiante
     * @param pais del estudiante
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * 
     * @return la región del estudiante
     */
    public String getRegion() {
        return region;
    }

    /**
     * Asigna la región del estudiante
     * @param region del estudiante
     */
    public void setRegion(String region) {
        this.region = region;
    }
   
    
    
}
