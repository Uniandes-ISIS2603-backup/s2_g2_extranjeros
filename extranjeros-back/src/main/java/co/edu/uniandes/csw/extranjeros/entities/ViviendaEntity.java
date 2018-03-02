/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jd.arango
 */
@Entity
public class ViviendaEntity extends BaseEntity implements Serializable{

    //---------------------------------------------------
    // Atributos Relacional
    //---------------------------------------------------
    
    @PodamExclude
    @ManyToMany
    private List <UsuarioEntity> usuarioInquilinos;
    
     //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    private boolean disponible;
    
    private String direccion;
     
    private Integer capacidad;
    
    private String latitud;
    
    private String longitud;

    private String tipoAlojamiento;
    
    @OneToMany
    @PodamExclude
    private List<ServicioEntity> serviciosFijos;
    @OneToMany
    @PodamExclude
    private List<ServicioEntity> serviciosAdicionales;
    @OneToMany
    @PodamExclude
    private List<ValoracionEntity> valoraciones;

    /**
     * @return the disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the capacidad
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the tipoAlojamiento
     */
    public String getTipoAlojamiento() {
        return tipoAlojamiento;
    }

    /**
     * @param tipoAlojamiento the tipoAlojamiento to set
     */
    public void setTipoAlojamiento(String tipoAlojamiento) {
        this.tipoAlojamiento = tipoAlojamiento;
    }

    /**
     * @return the serviciosFijos
     */
    public List<ServicioEntity> getServiciosFijos() {
        return serviciosFijos;
    }

    /**
     * @param serviciosFijos the serviciosFijos to set
     */
    public void setServiciosFijos(List<ServicioEntity> serviciosFijos) {
        this.serviciosFijos = serviciosFijos;
    }

    /**
     * @return the serviciosAdicionales
     */
    public List<ServicioEntity> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    /**
     * @param serviciosAdicionales the serviciosAdicionales to set
     */
    public void setServiciosAdicionales(List<ServicioEntity> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    /**
     * @return the valoraciones
     */
    public List<ValoracionEntity> getValoraciones() {
        return valoraciones;
    }

    /**
     * @param valoraciones the valoraciones to set
     */
    public void setValoraciones(List<ValoracionEntity> valoraciones) {
        this.valoraciones = valoraciones;
    }
    
    
    
}
