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
public class ProvidenciaDetailDTO extends ProvidenciaDTO {
    
    public ProvidenciaDetailDTO(){
        super();
    }
    public ProvidenciaDetailDTO(ProvidenciaEntity entity)
    {
        super (entity);
    }
        @Override
    public ProvidenciaEntity toEntity()
    {
        return super.toEntity();
    }
    
    
}
