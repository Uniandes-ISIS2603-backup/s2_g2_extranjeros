/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;

/**
 * Clase que extiende de {@link ServicioDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del servicio vaya a la documentacion de {@link ServicioDTO}
 * @author s.rodriguezm
 */
public class ServicioDetailDTO extends ServicioDTO{
    /**
     * Constructor por defecto
     */
    public ServicioDetailDTO(){}
    /**
     * Constructor que recibe un entity.
     * @param entity entidad
     */
    public ServicioDetailDTO(ServicioEntity entity)
    {
        super(entity);
    }
    @Override
    public ServicioEntity toEntity()
    {
        return super.toEntity();
    }
}
