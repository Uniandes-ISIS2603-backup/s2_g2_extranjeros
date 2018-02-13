/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 *
 * @author o.amaya724
 */
public class LugaresDeInteresDetailDTO extends LugaresDeInteresDTO{
    
    public LugaresDeInteresDetailDTO(String pTipo, String pNombre, String pDireccion, Integer pTelefono, EventoDTO pEventos, String pUbicacionLat, String pUbicacionLon){
        super(pTipo, pNombre, pDireccion, pTelefono, pEventos, pUbicacionLat, pUbicacionLon);
    }
    
}
