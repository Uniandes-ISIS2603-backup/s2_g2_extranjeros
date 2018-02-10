/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

/**
 *
 * @author s.rodriguezm
 */
public class FacturaDTO {
    //Atributos
    private Long id;
    private Double costoFijo;
    private Double costosAdicionales;
    private String formaDePago;
    private Integer mesesAPagar;
    private Integer numerodeInquilinos;
    private Boolean dividirCuentaServicios;
    private String fechaEntrada;
    private String fechaSalida;
    private Double IVA;
    
    
    /**
     * Constructor por defecto
     */
    public FacturaDTO(){}
    
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
     * @return los meses a pagar del arriendo.
     */
    public Integer getMesesAPagar() {
        return mesesAPagar;
    }
    /**
     * @param mesesAPagar los nuevos meses a pagar del arriendo.
     */
    public void setMesesAPagar(Integer mesesAPagar) {
        this.mesesAPagar = mesesAPagar;
    }
    /**
     * @return numero inquilinos.
     */
    public Integer getNumerodeInquilinos() {
        return numerodeInquilinos;
    }
    /**
     * @param numerodeInquilinos nuevo numero inquilinos.
     */
    public void setNumerodeInquilinos(Integer numerodeInquilinos) {
        this.numerodeInquilinos = numerodeInquilinos;
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
    public String getFechaEntrada() {
        return fechaEntrada;
    }
    /**
     * @param fechaEntrada nueva fecha de entrada.
     */
    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    /**
     * @return fecha de salida.
     */
    public String getFechaSalida() {
        return fechaSalida;
    }
    /**
     * @param fechaSalida nueva fecha de salida.
     */
    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
     /**
     * @return IVA.
     */
    public Double getIVA() {
        return IVA;
    }
     /**
     * @param IVA nuevo IVA.
     */
    public void setIVA(Double IVA) {
        this.IVA = IVA;
    }
    
    
}
