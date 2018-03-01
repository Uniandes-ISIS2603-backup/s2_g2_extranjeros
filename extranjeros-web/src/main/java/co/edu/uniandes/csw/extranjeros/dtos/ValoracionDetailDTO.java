/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ValoracionEntity;

/**
 * Clase que extiende de {@link ValoracionDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la valoracion vaya a la documentacion de {@link ViviendaDTO}
 * @author jd.arango
 */
public class ValoracionDetailDTO  extends ValoracionDTO {
   
    public ValoracionDetailDTO(){
        super();
    }
    public ValoracionEntity toEntity(){
        return super.toEntity();
    }
}
