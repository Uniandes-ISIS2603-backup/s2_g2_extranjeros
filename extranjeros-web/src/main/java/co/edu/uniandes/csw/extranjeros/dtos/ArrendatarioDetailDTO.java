/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 * Clase que extiende de {@link ArrendatarioDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link ArrendatarioDTO}
 * @author jr.pacheco10
 */

public class ArrendatarioDetailDTO extends ArrendatarioDTO {
    
    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    
    public ArrendatarioDetailDTO(String pUsuario, String pClave, String pCorreo, int pCelular, String pNombre){
     super(pUsuario, pClave, pCorreo, pCelular, pNombre);
    }
}
