
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;
import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
import java.util.*;
/**
 * Clase que extiende de {@link FacturaDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la factura vaya a la documentacion de {@link FacturaDTO}
 * @author s.rodriguezm
 */
public class FacturaDetailDTO extends FacturaDTO {
    /**
     * Servicios incluidos por la vivienda.
     */
    private List<ServicioDTO> serviciosIncluidos;
    /**
     * Servicios opcionales ofrecidos por la vivienda.
     */
    private List<ServicioDTO> serviciosExtra;
    /**
     * Estudiante asociado a la vivienda.
     */
    private EstudianteDTO estudianteAsociado;
    /**
     * Arrendatario asociado a la vivienda.
     */
    private ArrendatarioDTO arrendatarioAsociado;

    /**
     * Constructor por defecto.
     */
    public FacturaDetailDTO(){}
    /**
     * Constructor que recibe un entity.
     * @param entity entidad
     */
    public FacturaDetailDTO(FacturaEntity entity)
    {
        super(entity);
        if(entity.getServiciosAdicionales()!=null)
            serviciosExtra=servicioEntityAServicioDTO(entity.getServiciosAdicionales());
        if(entity.getServiciosIncluidos()!=null)
            serviciosIncluidos=servicioEntityAServicioDTO(entity.getServiciosIncluidos());
        if(entity.getArrendatarioAsociados()!=null)
            arrendatarioAsociado=new ArrendatarioDTO(entity.getArrendatarioAsociados());
        if(entity.getEstudianteAsociados()!=null)
            estudianteAsociado=new EstudianteDTO(entity.getEstudianteAsociados());
    }
    /**
     * @return la lista de servicios incluidos.
     */
    public List<ServicioDTO> getServiciosIncluidos() {
        return serviciosIncluidos;
    }
    /**
     * @param serviciosIncluidos la nueva lista de servicios incluidos.
     */
    public void setServiciosIncluidos(List<ServicioDTO> serviciosIncluidos) {
        this.serviciosIncluidos = serviciosIncluidos;
    }
    /**
     * @return la lista de servicios extra.
     */
    public List<ServicioDTO> getServiciosExtra() {
        return serviciosExtra;
    }
    /**
     * @param serviciosExtra la nueva lista de servicios extra.
     */
    public void setServiciosExtra(List<ServicioDTO> serviciosExtra) {
        this.serviciosExtra = serviciosExtra;
    }
    /**
     * @return la lista de servicios Entity.
     */
    
    public List<ServicioEntity> servicioDTOAServicioEntity(List<ServicioDTO> dto)
    {
       List<ServicioEntity> res=new ArrayList<>();
       for(ServicioDTO x: dto)
       {
           res.add(x.toEntity());
       }
       return res;
    }
    /**
     * @return la lista de servicios DTO.
     */
    
    public List<ServicioDTO> servicioEntityAServicioDTO(List<ServicioEntity> entity)
    {
       List<ServicioDTO> res=new ArrayList<>();
       for(ServicioEntity x: entity)
       {
           res.add(new ServicioDTO(x));
       }
       return res;
    }
    
    @Override
    public FacturaEntity toEntity()
    {
        FacturaEntity e=super.toEntity();
        if(serviciosExtra!=null)
            e.setServiciosAdicionales(servicioDTOAServicioEntity(serviciosExtra));
        if(serviciosIncluidos!=null)
            e.setServiciosIncluidos(servicioDTOAServicioEntity(serviciosIncluidos));
        if(estudianteAsociado!=null)
            e.setEstudianteAsociados(estudianteAsociado.toEntity());
        if(arrendatarioAsociado!=null)
            e.setArrendatarioAsociados(arrendatarioAsociado.toEntity());
        return e;
    }
    
    
    
}
