/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author s.rodriguezm
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable
{
    //---------------------------------------------------
    // Atributos 
    //---------------------------------------------------
    private Double costoFijo;
    private Double costosAdicionales;
    private String formaDePago;
    private Integer mesesAPagar;
    private Integer numerodeInquilinos;
    private Boolean dividirCuentaServicios;
    
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    
    private Double IVA;
    
    /**
     * Vivienda que se factura.
     */
    @OneToOne
    private ViviendaEntity vivienda;
    
    /**
     * Lista de servicios que ya estan incluidos en el precio base.
     */
    @PodamExclude
    @OneToMany
    private List<ServicioEntity> serviciosIncluidos;
    
    /**
     * Lista de servicios adicionales.
     */
    @PodamExclude
    @OneToMany
    private List<ServicioEntity> serviciosAdicionales;
     /**
     * @return costo fijo del arriendo.
     */
    public Double getCostoFijo() {
        return costoFijo;
    }
    /**
     * @param costoFijo Nuevo costo fijo para cambiar.
     */
    public void setCostoFijo(Double costoFijo) {
        this.costoFijo = costoFijo;
    }
     /**
     * @return costo adicional del arriendo.
     */
    public Double getCostosAdicionales() {
        return costosAdicionales;
    }
    /**
     * @param costosAdicionales Nuevo costo adicional para cambiar.
     */
    public void setCostosAdicionales(Double costosAdicionales) {
        this.costosAdicionales = costosAdicionales;
    }
     /**
     * @return forma de pago del arriendo.
     */
    public String getFormaDePago() {
        return formaDePago;
    }
    /**
     * @param formaDePago Nueva forma de pago para cambiar.
     */
    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }
     /**
     * @return Número de meses a pagar.
     */
    public Integer getMesesAPagar() {
        return mesesAPagar;
    }
    /**
     * @param mesesAPagar Nuevo número de meses a pagar.
     */
    public void setMesesAPagar(Integer mesesAPagar) {
        this.mesesAPagar = mesesAPagar;
    }
     /**
     * @return Número de nquilinos en la vivienda.
     */
    public Integer getNumerodeInquilinos() {
        return numerodeInquilinos;
    }
    /**
     * @param numerodeInquilinos Nuevo número de inquilinos en la vivienda.
     */
    public void setNumerodeInquilinos(Integer numerodeInquilinos) {
        this.numerodeInquilinos = numerodeInquilinos;
    }
    /**
     * @return Si se va a dividir o no el pago de los servicios.
     */
    public Boolean isDividirCuentaServicios() {
        return dividirCuentaServicios;
    }
    /**
    * @param dividirCuentaServicios Si se va a dividir o no el pago de los servicios.
    */
    public void setDividirCuentaServicios(Boolean dividirCuentaServicios) {
        this.dividirCuentaServicios = dividirCuentaServicios;
    }
    /**
     * @return Fecha de entrada a la vivienda.
     */
    public Date getFechaEntrada() {
        return fechaEntrada;
    }
    /**
    * @param fechaEntrada Nueva fecha de entrada.
    */
    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }
    /**
     * @return Fecha de salida a la vivienda.
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }
    /**
    * @param fechaSalida Nueva fecha de salida.
    */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    /**
     * @return Valor del IVA.
     */
    public Double getIVA() {
        return IVA;
    }
    /**
    * @param IVA Nuevo valor del IVA.
    */
    public void setIVA(Double IVA) {
        this.IVA = IVA;
    }

    public ViviendaEntity getVivienda() {
        return vivienda;
    }

    public void setVivienda(ViviendaEntity vivienda) {
        this.vivienda = vivienda;
    }

    public List<ServicioEntity> getServiciosIncluidos() {
        return serviciosIncluidos;
    }

    public void setServiciosIncluidos(List<ServicioEntity> serviciosIncluidos) {
        this.serviciosIncluidos = serviciosIncluidos;
    }

    public List<ServicioEntity> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioEntity> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }
    
}
