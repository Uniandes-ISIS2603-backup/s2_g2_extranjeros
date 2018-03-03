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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author la.ruiz967
 */
@Entity
public class EventoEntity extends BaseEntity implements Serializable{
    
    private String nombreEvento;
    private String tipoEvento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEvento;
    private Integer distanciaVivienda;
    private String ubicacionLon;
    private String ubicacionLat;
    private Boolean privado;
    private Integer capacidad;
    
    //Faltan asociaciones. Falta la lista de estudiantes.
    @PodamExclude
    @ManyToMany
    private List<EstudianteEntity> estudiantesInvitados;
     
    @PodamExclude
    @ManyToOne
    private EstudianteEntity responsableEventoP;
    
    @PodamExclude
    @OneToOne
    private LugaresDeInteresEntity lugarDeInteres;
    
    public LugaresDeInteresEntity getLugarDeInteres()
    {
        return lugarDeInteres;
    }
    
    public void setLugarDeInteres(LugaresDeInteresEntity lugarDeInteres)
    {
        this.lugarDeInteres = lugarDeInteres;
    }

    public EstudianteEntity getResponsableEventoP() {
        return responsableEventoP;
    }

    public void setResponsableEventoP(EstudianteEntity responsableEventoP) {
        this.responsableEventoP = responsableEventoP;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Integer getDistanciaVivienda() {
        return distanciaVivienda;
    }

    public void setDistanciaVivienda(Integer distanciaVivienda) {
        this.distanciaVivienda = distanciaVivienda;
    }

    public String getUbicacionLon() {
        return ubicacionLon;
    }

    public void setUbicacionLon(String ubicacionLon) {
        this.ubicacionLon = ubicacionLon;
    }

    public String getUbicacionLat() {
        return ubicacionLat;
    }

    public void setUbicacionLat(String ubicacionLat) {
        this.ubicacionLat = ubicacionLat;
    }

    public Boolean isPrivado() {
        return privado;
    }

    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    
    
}
