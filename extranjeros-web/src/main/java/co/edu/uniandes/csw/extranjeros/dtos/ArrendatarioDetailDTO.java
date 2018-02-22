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
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre": String,
 *      "id": number
 *      "usuario": String,
 *      "clave": string,
 *      "correoAsociado": string,
 *      "celular": number,
 *      "viviendas": [  
 *        {
 *              "id": number,
 *              "direccion": string,
 *              "disponible": boolean,
 *              "servicio": Servicio,
 *              "valoracion": Valoracion,
 *              "valoracionPromedio": number,
 *              "latitud": number,
 *              "longitud": number,
 *              "tipoAlojamiento": string,
 *        } 
 *       ],
 *      "facturas": [
 *              "id": number,
 *              "costoFijo": number,
 *              "costosAdicionales": number,
 *              "formaDePago": string,
 *              "mesesAPagar": number,
 *              "numerodeInquilinos": number,
 *              "dividirCuentaServicios": boolean,
 *              "fechaEntrada": string,
 *              "fechaSalida":string,
 *              "IVA": number
 *      ]
 *   }
 *   }
 * </pre>
 * Por ejemplo un Arrendatario se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "nombre": "Carlos Hugo" 
 *      "usuario": "carlManson",
 *      "clave": "holamundo12345",
 *      "correoAsociado": "carlmanson@outlook.com",
 *      "celular": 31132867894
 *      "id": 31231,
 *      "viviendas": [  
 *        {
 *           "id": 3312,
 *           "direccion": "calle 21 1307",
 *           "disponible": true,
 *           "servicio": [{servicio1,servicio2}],
 *           "valoracion": [{valoracion1,valoracion2, valoracion3}],
 *           "valoracionPromedio": 4,
 *           "latitud": 32,
 *           "longitud": 456,
 *           "tipoAlojamiento": "B"
 *        } 
 *       ], 
 *      "facturas": [
 *      {
 *          "id": 9185,
 *          "costoFijo": 120000.0,
 *          "costosAdicionales": 5000.0,
 *          "formaDePago": "efectivo",
 *          "mesesAPagar": 12,
 *          "numerodeInquilinos": 3,
 *          "dividirCuentaServicios": true,
 *          "fechaEntrada": "16 enero",
 *          "fechaSalida":"18 febrero",
 *          "IVA": 0.013
 *      }
 *     ] 
 *   }
 *
 * </pre>
 * @author Jose Pacheco
 */

public class ArrendatarioDetailDTO extends ArrendatarioDTO {
    
    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    
    /**
     * Constructor por defecto de la Clase.
     */
    public ArrendatarioDetailDTO(){
        super();
    }
}
