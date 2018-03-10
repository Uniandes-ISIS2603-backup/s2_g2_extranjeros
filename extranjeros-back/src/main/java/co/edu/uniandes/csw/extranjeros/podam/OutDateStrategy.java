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
public class OutDateStrategy implements AttributeStrategy <Date> {

    @Override
    public Date getValue() {
        Calendar c = Calendar.getInstance();
        Date now=new Date();
        int max_year = 2025;
        int nextInt = (int)Math.random()*100+3;
        c.setTime(now);
        c.add(Calendar.MONTH,nextInt);
        return c.getTime();
    }
    
}