/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;
import co.edu.uniandes.csw.extranjeros.entities.LugaresDeInteresEntity;

/**
 * Clase que extiende de {@link LugaresDeInteresDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la evento vaya a la documentacion de {@link LugaresDeInteresDTO}
 * @author Oliver Amaya
 */
public class LugaresDeInteresDetailDTO extends LugaresDeInteresDTO{
    
    /**
     * Constructor por defecto
     */
    public LugaresDeInteresDetailDTO(){
        
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de ciudad a partir de la cual se construye el objeto
     */
    public LugaresDeInteresDetailDTO(LugaresDeInteresEntity entity) {
        super(entity);
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    @Override
    public LugaresDeInteresEntity toEntity() {
        LugaresDeInteresEntity lugarDeInteresE = super.toEntity();
        return lugarDeInteresE;
    }
    
}
