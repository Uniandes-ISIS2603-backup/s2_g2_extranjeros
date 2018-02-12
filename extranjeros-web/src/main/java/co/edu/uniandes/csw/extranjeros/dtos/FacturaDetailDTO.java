
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;
import java.util.*;
/**
 *
 * @author s.rodriguezm
 */
public class FacturaDetailDTO extends FacturaDTO {
    /**
     * Servicios incluidos por la vivienda.
     */
    private List<ServicioDTO> serviciosIncluidos;
    /**
     * Servicios opcionales ofrecidos por la vivienda.
     */
    private List<ServicioDTO> serviciosExtra;
    /**
     * vivienda de la factura.
     */
    private ViviendaDTO vivienda;
    /**
     * Constructor por defecto
     */
    public FacturaDetailDTO(){}
    
   
    
}
