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
public class CorreoStrategy implements AttributeStrategy<String>{
    
    @Override
    public String getValue() {
        
        String random = getSaltString();
        
        if(! (random.contains("@") && random.contains(".com"))){
            random = (random.contains("@") && !random.contains(".com"))? random + ".com": deleteString(random, "@" + getSaltStringAux() + ".com", ".com");
        }
        return random;
    }
    
    public String deleteString(String actual,String inducir, String eliminar){
        actual.replace(eliminar, "");
        actual += inducir;
        return actual;
    }
    
    
    protected String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890@.";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    
        protected String getSaltStringAux() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    
}
