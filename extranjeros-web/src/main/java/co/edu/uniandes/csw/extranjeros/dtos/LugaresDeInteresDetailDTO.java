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
 * 
 * * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "tipo": string,
 *      "nombre": string,
 *      "direccion": string,
 *      "telefono": number,
 *      "eventos": EventoDTO,
 *      "ubicacionLon": string,
 *      "ubicacionLat": string
 *      
 *   }
 * </pre>
 * Por ejemplo un Lugar de Interes se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "id": 12311,
 *      "tipo": "Bar",
 *      "nombre": "La Pola",
 *      "direccion": "Calle 22 # 1 - 15",
 *      "telefono": 3224593,
 *      "eventos": EventoDTO,
 *      "ubicacionLon": "1.343344",
 *      "ubicacionLat": "-44.23233"       
 *   }
 *
 * 
 * @author Oliver Amaya
 */
public class LugaresDeInteresDetailDTO extends LugaresDeInteresDTO{
    
    /**
     * Constructor por defecto
     */
    public LugaresDeInteresDetailDTO(){
        super();
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
