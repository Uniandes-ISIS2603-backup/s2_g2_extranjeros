/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ProvidenciaEntity;

/**
 *
 * @author am.quintero12
 */
public class ProvidenciaDTO {
    
    private String pais;
    private String region;
    private Long id;
    
    public ProvidenciaDTO()
    {
        
    }
    
    public ProvidenciaDTO(ProvidenciaEntity providencia)
     {
        this.pais = providencia.getPais();
        this.region = providencia.getRegion();
        this.id = providencia.getId();
    }       

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public ProvidenciaEntity toEntity(){
        ProvidenciaEntity entity= new ProvidenciaEntity();
        entity.setId(id);
        entity.setRegion(region);
        entity.setPais(pais);
        return entity;
    }
            
}
