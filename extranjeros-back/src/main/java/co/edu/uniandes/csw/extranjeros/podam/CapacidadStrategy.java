/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.podam;

import uk.co.jemos.podam.common.AttributeStrategy;

/**
 *
 * @author s.rodriguezm
 */
public class CapacidadStrategy implements AttributeStrategy<Integer>{
    
    @Override
    public Integer getValue() {
        return 3;
    }
}
