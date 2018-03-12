/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;
import co.edu.uniandes.csw.extranjeros.podam.InDateStrategy;
import co.edu.uniandes.csw.extranjeros.podam.OutDateStrategy;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;
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
    private Boolean dividirCuentaServicios;
    
    @Temporal(TemporalType.TIMESTAMP)
    @PodamStrategyValue(InDateStrategy.class)
    private Date fechaEntrada;
    
    @Temporal(TemporalType.TIMESTAMP)
    @PodamStrategyValue(OutDateStrategy.class)
    private Date fechaSalida;
    
    private Double iva;
    
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
     * Lista de estudiantes asociados.
     */
    @PodamExclude
    @OneToOne
    private EstudianteEntity estudianteAsociado;
    /**
     * Lista de arrendatarios asociados.
     */
    @PodamExclude
    @ManyToOne
    private ArrendatarioEntity arrendatariosAsociados;
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
    * @return  El estudiante asociado a la factura.
    */
    public EstudianteEntity getEstudianteAsociados() {
        return estudianteAsociado;
    }
   /**
    * @param estudianteAsociados El nuevo estudiante asociado a la factura.
    */
    public void setEstudianteAsociados(EstudianteEntity estudianteAsociados) {
        this.estudianteAsociado = estudianteAsociados;
    }
   /**
    * @return  El arrendatario asociado a la factura.
    */
    public ArrendatarioEntity getArrendatarioAsociados() {
        return arrendatariosAsociados;
    }
   /**
    * @param arrendatarioAsociados El nuevo arrendatario asociado a la factura.
    */
    public void setArrendatarioAsociados(ArrendatarioEntity arrendatarioAsociados) {
        this.arrendatariosAsociados= arrendatarioAsociados;
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
        return iva;
    }
    /**
    * @param IVA Nuevo valor del IVA.
    */
    public void setIVA(Double IVA) {
        this.iva = IVA;
    }
    /**
     * @return servicios inculidos por la vivienda.
     */
    public List<ServicioEntity> getServiciosIncluidos() {
        return serviciosIncluidos;
    }
    /**
     * @param serviciosIncluidos nuevos servicios inculidos por la vivienda.
     */
    public void setServiciosIncluidos(List<ServicioEntity> serviciosIncluidos) {
        this.serviciosIncluidos = serviciosIncluidos;
    }
    /**
     * @return servicios adicionales por la vivienda.
     */
    public List<ServicioEntity> getServiciosAdicionales() {
        return serviciosAdicionales;
    }
    /**
     * @param serviciosAdicionales nuevos servicios adicionales por la vivienda.
     */
    public void setServiciosAdicionales(List<ServicioEntity> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }
    
}
