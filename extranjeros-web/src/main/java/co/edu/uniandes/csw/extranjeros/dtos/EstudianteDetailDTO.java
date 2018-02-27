/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import java.util.List;

/**
 *
 * @author am.quintero12
 */
public class EstudianteDetailDTO extends EstudianteDTO {
    
    private ProvidenciaDTO providencia;
    private List<EventoDTO> eventosInvitado;
    private List<EventoDTO> eventosCreados;
    private List<TarjetaDTO>  tarjetas;
    private UniversidadDTO universidad;
    
    
    public EstudianteDetailDTO()
    {
        
    }
    
}
