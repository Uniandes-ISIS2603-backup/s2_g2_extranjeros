/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.podam;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import uk.co.jemos.podam.common.AttributeStrategy;

/**
 *
 * @author s.rodriguezm
 */
public class InDateStrategy implements AttributeStrategy <Date> {

    @Override
    public Date getValue() {
        return new Date();
    }
    
}
