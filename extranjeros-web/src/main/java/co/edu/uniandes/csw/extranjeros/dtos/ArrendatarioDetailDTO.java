/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import java.util.ArrayList;
import java.util.List;

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
    // Atributos relacionales
    //---------------------------------------------------
    
    private CuentaBancariaDTO cuentaBancaria;
    private List <FacturaDTO> facturas;
    private List <ViviendaDTO> viviendas;
    
    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    
    /**
     * Constructor por defecto de la Clase.
     */
    public ArrendatarioDetailDTO(){
        super();
    }
    
    /**
     * Crea un objeto ArrendatarioDetailDTO a partir de un objeto ArrendatarioEntity.
     * @param entity Entidad ArrendatarioEntity desde la cual se va a crear el nuevo
     * objeto.
     */
     public ArrendatarioDetailDTO(ArrendatarioEntity entity) {
          super(entity);
          if (entity != null) {
            
            CuentaBancariaDTO buscadaFromEnt = new CuentaBancariaDTO(entity.getCuentaBancaria());
            this.cuentaBancaria = buscadaFromEnt;
            facturas = new ArrayList<>();
            viviendas = new ArrayList<>();
            
            for (FacturaEntity facturasEntity : entity.getFacturas()) {
                facturas.add(new FacturaDTO(facturasEntity));
            }
            
            for (ViviendaEntity viviendasEntity : entity.getViviendas()){
                viviendas.add(new ViviendaDTO(viviendasEntity));
            }
            
        }
    }

    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------
        
    /**
     * Convierte un objeto ArrendatarioDetailDTO a ArrendatarioEntity incluyendo los
     * atributos de ArrendatarioDTO (relacionales).
     * @return Nueva objeto AuthorEntity.
     */
    @Override    
    public ArrendatarioEntity toEntity(){
         
        // Entity
        ArrendatarioEntity entity = super.toEntity();
         
         // Verificacion relaciones
         if (cuentaBancaria != null){
             entity.setCuentaBancaria(cuentaBancaria.toEntity());
         }
         
         if (facturas != null) {
            List<FacturaEntity> facturaEntity = new ArrayList<>();
            for (FacturaDTO DTOFactura : facturas) {
                facturaEntity.add(DTOFactura.toEntity());
            }
            entity.setFacturas(facturaEntity);
        }
         
         if (viviendas != null) {
            List<ViviendaEntity> viviendaEntity = new ArrayList<>();
            for (ViviendaDTO DTOFactura : viviendas) {
                viviendaEntity.add(DTOFactura.toEntity());
            }
            entity.setViviendas(viviendaEntity);
        }
         
        // Retorno
        return entity;         
    }
        
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

    /**
     * @return La cuenta bancaria asociadas a un arrendatario.
     */
    public CuentaBancariaDTO getCuentaBancaria() {
        return cuentaBancaria;
    }

    /**
     * Crea o modifica la cuenta de banco asociada a un Arrendatario.
     * @param cuentaBancaria Cuenta de Banco. 
     */
    public void setCuentaBancaria(CuentaBancariaDTO cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
}
