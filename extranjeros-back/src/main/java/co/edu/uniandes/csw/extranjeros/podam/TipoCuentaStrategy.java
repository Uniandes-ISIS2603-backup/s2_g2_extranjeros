/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.podam;

import uk.co.jemos.podam.common.AttributeStrategy;
/**
 * @author jr.pacheco10
 */
public class TipoCuentaStrategy implements AttributeStrategy <String>{
    
    @Override
    public String getValue() {
        String randomST = createRandomCode(10);
        return randomST;
    }
    
    public String createRandomCode(int pNumero){
        String retorno = "A";
        double compar = pNumero*Math.random();
        if(compar < 5){retorno = "Ahorros";}
        else{retorno = "Corriente";}
        return retorno;
    }
}
