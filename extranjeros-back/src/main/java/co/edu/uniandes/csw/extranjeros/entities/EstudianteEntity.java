/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author am.quintero12
 */

@Entity
public class EstudianteEntity extends UsuarioEntity implements Serializable{
    
    private String nombre;
    private String estadoArrendamiento;
    
    //RELACIONES
   @PodamExclude
   @OneToOne
    private ProvidenciaEntity providencia;
   
   @OneToMany
   private TarjetaEntity tarjeta;
   
   @OneToOne 
   private UniversidadEntity universidad;
   
   @PodamExclude
   @OneToMany
   private List<EventoEntity> eventosCreados;
   
   @PodamExclude
   @ManyToMany
   private List<EventoEntity> eventosInvitado;
   
   

    public TarjetaEntity getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaEntity tarjeta) {
        this.tarjeta = tarjeta;
    }

    public UniversidadEntity getUniversidad() {
        return universidad;
    }

    public void setUniversidad(UniversidadEntity universidad) {
        this.universidad = universidad;
    }

    public List<EventoEntity> getEventosCreados() {
        return eventosCreados;
    }

    public void setEventosCreados(List<EventoEntity> eventosCreados) {
        this.eventosCreados = eventosCreados;
    }

    public List <EventoEntity> getEventosInvitado() {
        return eventosInvitado;
    }

    public void setEventosInvitado(List<EventoEntity> eventosInvitado) {
        this.eventosInvitado = eventosInvitado;
    }
   
   

    public ProvidenciaEntity getProvidencia() {
        return providencia;
    }

    public void setProvidencia(ProvidenciaEntity providencia) {
        this.providencia = providencia;
    }
    
    
    
    /**
     * Método que retorna el nombre del estudiante
     * @return Nombre del estudiante
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del estudiante 
     * @param nombre del estudiante
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *Método que retorna el estado de arrendaiento del estudiante 
     * @return El estado de arrendamiento del estudiante
     */
    public String getEstadoArrendamiento() {
        return estadoArrendamiento;
    }

    /**
     * Asigna el estado de arrentamiento
     * @param estadoArrendamiento 
     */
    public void setEstadoArrendamiento(String estadoArrendamiento) {
        this.estadoArrendamiento = estadoArrendamiento;
    }
    
}
