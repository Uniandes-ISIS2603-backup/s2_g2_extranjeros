/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 * Clase que extiende de {@link UsuarioDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link UsuarioDTO}
 * @author jr.pacheco10
 */

import java.util.List;

public class UsuarioDetailDTO extends UsuarioDTO {
        
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    
    private List <FacturaDTO> facturas;
    private List <ViviendaDTO> viviendas;

    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    
    /**
     * Constructor para generar un objeto de tipo Usuario, aniadiendo 
     * sus relaciones.
     * @param pUsuario Nombre de usuario (carlManson0506, por ejemplo)
     * @param pClave Clave asociada a la cuenta del usuario
     * @param pCorreo Correo asociado a la cuenta del usuario
     * @param pCelular Celular asociado al usuario
     * @param pFactura Factura asociada a un Usuario. 
     */
    public UsuarioDetailDTO(String pUsuario, String pClave, String pCorreo, int pCelular, List<FacturaDTO> pFactura, 
                            List<ViviendaDTO> pVivienda){
        super(pUsuario, pClave, pCorreo, pCelular);
        facturas = pFactura;
    }

    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------
    
    /**
     * Obtiene las facturas asociadas al Usuario
     * @return List. Lista con las facturas.
     */
    public List<FacturaDTO> getFactura(){
        return facturas;
    }
    
     /**
     * Obtiene las facturas asociadas al Usuario
     * @return List. Lista con las facturas.
     */
    public List<ViviendaDTO> getViviendas(){
        return viviendas;
    }

}
