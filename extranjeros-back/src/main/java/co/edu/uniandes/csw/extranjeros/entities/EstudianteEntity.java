/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author am.quintero12
 */

@Entity
public class EstudianteEntity extends UsuarioEntity {
    
    private String nombre;
    private boolean estadoArrendamiento;
    private int edad;
    
    //RELACIONES
   @PodamExclude
   @OneToOne
    private ProvidenciaEntity providencia;
   
   @PodamExclude
   @OneToOne (cascade = CascadeType.ALL)
   private TarjetaEntity tarjeta;
   
   @PodamExclude
   @OneToOne 
   private UniversidadEntity universidad;
   
   @PodamExclude
   @OneToMany(mappedBy = "responsableEventoP",cascade = CascadeType.ALL)
   private List<EventoEntity> eventosCreados;
   
   @PodamExclude
   @ManyToMany
   private List<EventoEntity> eventosInvitado;
   
   @PodamExclude
   @ManyToMany
    private List <FacturaEntity> facturas;
    
    @PodamExclude
    @ManyToOne
    private ViviendaEntity vivienda;

    
    
   
    
    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    public ViviendaEntity getViviendas() {
        return vivienda;
    }

    public void setViviendas(ViviendaEntity viviendas) {
        this.vivienda = viviendas;
    }
    
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
    public boolean isEstadoArrendamiento() {
        return estadoArrendamiento;
    }

    /**
     * Asigna el estado de arrentamiento
     * @param estadoArrendamiento 
     */
    public void setEstadoArrendamiento(Boolean estadoArrendamiento) {
        this.estadoArrendamiento = estadoArrendamiento;
    }
    
    public void agregarEventoCreado(EventoEntity e)
    {
        this.eventosCreados.add(e);
    }
    public void agregarEventoInvitado(EventoEntity e)
    {
        this.eventosInvitado.add(e);
    }

    public Integer  getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ViviendaEntity getVivienda() {
        return vivienda;
    }

    public void setVivienda(ViviendaEntity vivienda) {
        this.vivienda = vivienda;
    }
    
    
    
}
