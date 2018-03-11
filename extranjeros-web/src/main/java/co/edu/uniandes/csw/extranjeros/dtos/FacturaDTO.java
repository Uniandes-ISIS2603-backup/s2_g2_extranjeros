/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import java.util.Date;

/**
 * FacturaDTO Objeto de transferencia de datos de Facturas. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *    "id": number,
 *    "costoFijo": number,
 *    "costosAdicionales": number,
 *    "formaDePago": string,
 *    "numerodeInquilinos": number,
 *    "dividirCuentaServicios": boolean,
 *    "fechaEntrada": string,
 *    "fechaSalida":string,
 *    "iva": number
 *  }
 * </pre>
 * Por ejemplo una factura se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *    "id": 9185,
 *    "costoFijo": 120000.0,
 *    "costosAdicionales": 5000.0,
 *    "formaDePago": "efectivo",
 *    "numerodeInquilinos": 3,
 *    "dividirCuentaServicios": true,
 *    "fechaEntrada": "16/01/2018",
 *    "fechaSalida":"18/02/2018",
 *    "iva": 0.013
 *  }
 *
 * </pre>
 * @author s.rodriguezm
 */
public class FacturaDTO {
    //Atributos
    private Long id;
    private Double costoFijo;
    private Double costosAdicionales;
    private String formaDePago;
    private Boolean dividirCuentaServicios;
    private Date fechaEntrada;
    private Date fechaSalida;
    private Double iva;
    
    
    /**
     * Constructor por defecto.
     */
    public FacturaDTO(){}
    /**
     * Constructor que recibe un entity.
     * @param entity entidad
     */
    public FacturaDTO(FacturaEntity entity)
    {
        id=entity.getId();
        costoFijo=entity.getCostoFijo();
        costosAdicionales=entity.getCostosAdicionales();
        formaDePago=entity.getFormaDePago();
        dividirCuentaServicios=entity.isDividirCuentaServicios();
        fechaEntrada=entity.getFechaEntrada();
        fechaSalida=entity.getFechaSalida();
        iva=entity.getIVA();
    }
    //Métodos
    /**
     * @return el ID del servicio.
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id el nuevo ID del Servicio.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return el costo fijo del arriendo.
     */
    public Double getCostoFijo() {
        return costoFijo;
    }
    /**
     * @param costoFijo el nuevo costo fijo del arriendo.
     */
    public void setCostoFijo(Double costoFijo) {
        this.costoFijo = costoFijo;
    }
    /**
     * @return los costos adicionales del arriendo.
     */
    public Double getCostosAdicionales() {
        return costosAdicionales;
    }
    /**
     * @param costosAdicionales los nuevos costos adicionales del arriendo.
     */
    public void setCostosAdicionales(Double costosAdicionales) {
        this.costosAdicionales = costosAdicionales;
    }
    /**
     * @return la forma de pago del arriendo.
     */
    public String getFormaDePago() {
        return formaDePago;
    }
    /**
     * @param formaDePago la neuva forma de pago del arriendo.
     */
    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }
    /**
     * @return si se divide la cuenta o no.
     */
    public boolean isDividirCuentaServicios() {
        return dividirCuentaServicios;
    }
    /**
     * @param dividirCuentaServicios numero inquilinos.
     */
    public void setDividirCuentaServicios(boolean dividirCuentaServicios) {
        this.dividirCuentaServicios = dividirCuentaServicios;
    }
    /**
     * @return fecha de entrada.
     */
    public Date getFechaEntrada() {
        return fechaEntrada;
    }
    /**
     * @param fechaEntrada nueva fecha de entrada.
     */
    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    /**
     * @return fecha de salida.
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }
    /**
     * @param fechaSalida nueva fecha de salida.
     */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
     /**
     * @return IVA.
     */
    public Double getIVA() {
        return iva;
    }
     /**
     * @param IVA nuevo IVA.
     */
    public void setIVA(Double IVA) {
        this.iva = IVA;
    }
    /**
     * Convierte un objeto FacturaDTO a FacturaEntity incluyendo los
     * atributos de FacturaDTO.
     *
     * @return Nueva objeto FacturaEntity.
     *
     */
    public FacturaEntity toEntity()
    {
        FacturaEntity e=new FacturaEntity();
        e.setId(id);
        e.setCostoFijo(costoFijo);
        e.setCostosAdicionales(costosAdicionales);
        e.setDividirCuentaServicios(dividirCuentaServicios);
        e.setFechaEntrada(fechaEntrada);
        e.setFechaSalida(fechaSalida);
        e.setFormaDePago(formaDePago);
        e.setIVA(iva);
        return e;
    }
    
}
