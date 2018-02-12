/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 *
 * @author am.quintero12
 */
public class ProvidenciaDTO {
    
    private String pais;
    private String region;
    
    public ProvidenciaDTO()
    {
        
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPais() {
        return pais;
    }

    public String getRegion() {
        return region;
    }
    
}
