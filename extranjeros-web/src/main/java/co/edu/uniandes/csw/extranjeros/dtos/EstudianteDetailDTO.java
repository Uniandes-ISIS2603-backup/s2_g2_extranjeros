/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import java.util.List;

/**
 *
 * @author am.quintero12
 */
public class EstudianteDetailDTO extends EstudianteDTO {
    
    private ProvidenciaDTO providencia;
    private List<EventoDTO> eventosInvitado;
    private List<EventoDTO> eventosCreados;
    private TarjetaDTO  tarjeta;
    private UniversidadDTO universidad;
    
    
    public EstudianteDetailDTO()
    {
        
    }
    
    public EstudianteDetailDTO(EstudianteEntity entity)
    {
          super(entity);
          providencia = new ProvidenciaDTO (entity.getProvidencia());
          tarjeta = new TarjetaDTO(entity.getTarjeta());
          universidad = new UniversidadDTO(entity.getUniversidad());
          for (int i=0; i< entity.getEventosCreados().size(); i++)
          {
              eventosCreados.add(new EventoDTO(entity.getEventosCreados().get(i)));
          }
          for (int i=0; i< entity.getEventosInvitado().size(); i++)
          {
              eventosInvitado.add(new EventoDTO(entity.getEventosInvitado().get(i)));
          }
         
    }

    public ProvidenciaDTO getProvidencia() {
        return providencia;
    }

    public void setProvidencia(ProvidenciaDTO providencia) {
        this.providencia = providencia;
    }

    public List<EventoDTO> getEventosInvitado() {
        return eventosInvitado;
    }

    public void setEventosInvitado(List<EventoDTO> eventosInvitado) {
        this.eventosInvitado = eventosInvitado;
    }

    public List<EventoDTO> getEventosCreados() {
        return eventosCreados;
    }

    public void setEventosCreados(List<EventoDTO> eventosCreados) {
        this.eventosCreados = eventosCreados;
    }

    public TarjetaDTO getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaDTO tarjetas) {
        this.tarjeta = tarjetas;
    }

    public UniversidadDTO getUniversidad() {
        return universidad;
    }

    public void setUniversidad(UniversidadDTO universidad) {
        this.universidad = universidad;
    }
    
    public EstudianteEntity toEntity(){
        EstudianteEntity entity = super.toEntity();
        entity.setUniversidad(universidad.toEntity());
        entity.setTarjeta(tarjeta.toEntity());
        entity.setProvidencia(providencia.toEntity());
        for (int i=0; i<getEventosCreados().size(); i++)
        {
            entity.agregarEventoCreado(eventosCreados.get(i).toEntity());
        }
        for (int i=0; i< getEventosInvitado().size(); i++)
        {
            entity.agregarEventoInvitado(eventosInvitado.get(i).toEntity());
        }
        return entity ;
    }
            
           
    
}
