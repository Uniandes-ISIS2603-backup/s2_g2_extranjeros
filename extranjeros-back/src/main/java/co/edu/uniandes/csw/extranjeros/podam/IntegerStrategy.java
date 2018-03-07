/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.podam;

import java.util.Random;
import uk.co.jemos.podam.common.AttributeStrategy;

/**
 * @author jr.pacheco10
 */
public class IntegerStrategy implements AttributeStrategy<Integer>{
    
    @Override
    public Integer getValue() {
        
        Random r = new Random();
        int nextInt = r.nextInt();
        
        if(nextInt > 18){
           Integer c = 0;
           c = nextInt;
           return c;
        }
        
        else {
            Integer c = 20;
            return c;
        }
    }
} 
