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
     */
    public UsuarioDetailDTO(){
        super();
    }

    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------
    
    /**
     * Obtiene las facturas asociadas al Usuario
     * @return List. Lista con las facturas.
     */
    public List<FacturaDTO> getFacturas() {
        return facturas;
    }
    
     /**
     * Genera una lista de las Facturas a asociar con un usuario. 
     * @param pFacturas La nueva lista de Facturas. 
     */
    public void setFacturas(List<FacturaDTO> pFacturas) {
        this.facturas = pFacturas;
    }
    
     /**
     * Obtiene las Viviendas asociadas al Usuario
     * @return List. Lista con las facturas.
     */
    public List<ViviendaDTO> getViviendas(){
        return viviendas;
    }
    
    /**
     * Genera una lista de las Viviendas a asociar con un usuario. 
     * @param pViviendas La nueva lista de Viviendas. 
     */
    public void setViviendas (List<ViviendaDTO> pViviendas) {
        this.viviendas = pViviendas;
    }
}
