/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.UniversidadEntity;

/**
 * Clase que extiende de {@link UnivversidadDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link UniversidadDTO}
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre": String,
 *      "direccion": String,
 *      "ubicacionLat": String,
 *      "ubicacionLon": string
 *      
 *   }
 *   }
 * </pre>
 * Por ejemplo un Arrendatario se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "nombre": "Universidad de los Andes" 
 *      "direccion": "Carrera 1 # 19 - 30",
 *      "ubicacionLat": "65.4545986",
 *      "ubicacionLon": "-74.6989647",
 *      
 *   }
 *
 * </pre>
 * @author Oliver Amaya
 */
public class UniversidadDetailDTO extends UniversidadDTO {
    
    /**
     * Constructor por defecto
     */
    public UniversidadDetailDTO(){
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de ciudad a partir de la cual se construye el objeto
     */
    public UniversidadDetailDTO(UniversidadEntity entity) {
        super(entity);
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return  La entidad construida a partir del DTO.
     */
    @Override
    public UniversidadEntity toEntity() {
        UniversidadEntity universidadE = super.toEntity();
        return universidadE;
    }
    
}
