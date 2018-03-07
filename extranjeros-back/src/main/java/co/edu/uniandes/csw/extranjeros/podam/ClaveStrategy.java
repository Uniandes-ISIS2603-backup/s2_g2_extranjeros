/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.podam;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import uk.co.jemos.podam.common.AttributeStrategy;

/**
 * @author jr.pacheco10
 */

public class ClaveStrategy implements AttributeStrategy <String> {
    
    @Override
    public String getValue() {
        String randomST = createRandomCode(15, "abcdefghijklmnopqrstuvxyz123456789890@. ");
        return randomST;
    }
    
    public static String createRandomCode(int codeLength, String id) {
    return new SecureRandom()
            .ints(codeLength, 0, id.length())
            .mapToObj(id::charAt)
            .map(Object::toString)
            .collect(Collectors.joining());
    }
}
