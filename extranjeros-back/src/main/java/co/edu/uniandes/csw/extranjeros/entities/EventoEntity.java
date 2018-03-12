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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne; 
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author la.ruiz967
 */
@Entity
public class EventoEntity extends BaseEntity implements Serializable{
    
    /**
     * nombre del evento
     */
    private String nombreEvento;
    
    /**
     * tipo del evento
     */
    private String tipoEvento;
    
    /**
     * fecha del evento
     */
    private String fechaEvento;
    
    /**
     * distancia de la vivienda al evento
     */
    private Integer distanciaVivienda;
    
    /**
     * ubicación del evento en longitud
     */
    private String ubicacionLon;
    
    /**
     * ubicación del evento en latitud
     */
    private String ubicacionLat;
    
    /**
     * Indica si el evento es privado o no
     */
    private Boolean privado;
    
    /**
     * Capacidad del evento
     */
    private Integer capacidad;
    
    /**
     * Lista de estudiantes invitados
     */
    @PodamExclude
    @ManyToMany
    private List<EstudianteEntity> estudiantesInvitados;
    
    /**
     * Responsable del evento
     */
    @PodamExclude
    @ManyToOne
    private EstudianteEntity responsableEventoP;
    
    /**
     * Lugar de interés relacionado con el evento
     */
    @PodamExclude
    @OneToOne
    private LugaresDeInteresEntity lugarDeInteres;
    
    /**
     * Retorna la lista de estudiantes invitados
     * @return 
     */
    public List<EstudianteEntity> getEstudiantesInvitados() {
        return estudiantesInvitados;
    }

    /**
     * Actualiza la lista de estudiantes invitados
     * @param estudiantesInvitados 
     */
    public void setEstudiantesInvitados(List<EstudianteEntity> estudiantesInvitados) {
        this.estudiantesInvitados = estudiantesInvitados;
    }
   
    /**
     * Retorna el lugar de interés
     * @return 
     */
    public LugaresDeInteresEntity getLugarDeInteres()
    {
        return lugarDeInteres;
    }
    
    /**
     * Cambia el lugar de interés
     * @param lugarDeInteres 
     */
    public void setLugarDeInteres(LugaresDeInteresEntity lugarDeInteres)
    {
        this.lugarDeInteres = lugarDeInteres;
    }

    /**
     * Retorna el responsable del evento
     * @return 
     */
    public EstudianteEntity getResponsableEventoP() {
        return responsableEventoP;
    }

    /**
     * Cambia el responsable del evento
     * @param responsableEventoP 
     */
    public void setResponsableEventoP(EstudianteEntity responsableEventoP) {
        this.responsableEventoP = responsableEventoP;
    }

    
    /**
     * Retorna el nombre del evento
     * @return 
     */
    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * Cambia el nombre del evento
     * @param nombreEvento
     */
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    /**
     * retorna el tipo del evento
     * @return 
     */
    public String getTipoEvento() {
        return tipoEvento;
    }

    /**
     * cambia el tipo del evento
     * @param tipoEvento 
     */
    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    /**
     * retorna la fecha del evento
     * @return 
     */
    public String getFechaEvento() {
        return fechaEvento;
    }

    /**
     * cambia la fecha del evento
     * @param fechaEvento
     */
    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    /**
     * retorna la distancia a la vivienda del evento
     * @return 
     */
    public Integer getDistanciaVivienda() {
        return distanciaVivienda;
    }

    /**
     * cambia la distancia del evento
     * @param distanciaVivienda
     */
    public void setDistanciaVivienda(Integer distanciaVivienda) {
        this.distanciaVivienda = distanciaVivienda;
    }

    /**
     * retorna la ubicación en longitud del evento
     * @return 
     */
    public String getUbicacionLon() {
        return ubicacionLon;
    }

    /**
     * cambia la longitud del evento
     * @param ubicacionLon
     */
    public void setUbicacionLon(String ubicacionLon) {
        this.ubicacionLon = ubicacionLon;
    }

    /**
     * retorna la ubicación en latitud del evento
     * @return 
     */
    public String getUbicacionLat() {
        return ubicacionLat;
    }

    /**
     * cambia la latitud del evento
     * @param ubicacionLat
     */
    public void setUbicacionLat(String ubicacionLat) {
        this.ubicacionLat = ubicacionLat;
    }

    /**
     * retorna la privacidad del evento
     * @return 
     */
    public Boolean isPrivado() {
        return privado;
    }

    /**
     * cambia la privacidad del evento
     * @param privado
     */
    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    /**
     * retorna la capacidad del evento
     * @return 
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * cambia la capacidad del evento
     * @param capacidad
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    
    
}
