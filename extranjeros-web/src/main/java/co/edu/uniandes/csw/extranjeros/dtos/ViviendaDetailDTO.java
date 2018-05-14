/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.entities.LugaresDeInteresEntity;
import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
import co.edu.uniandes.csw.extranjeros.entities.ValoracionEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.time.Clock;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

/**
 * Clase que extiende de {@link ViviendaDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la vivienda vaya a la documentacion de {@link ViviendaDTO}
 * @author jd.arango
 */
public class ViviendaDetailDTO extends ViviendaDTO {
    
    private List<ServicioDTO> serviciosFijos;
   
    private List<ServicioDTO> serviciosAdicionales;
    
    private List<ValoracionDTO> valoraciones;
    
    private List<FacturaDTO> facturas;
    
    private ArrendatarioDTO arrendatariosPropietarios;
    
    private List <EstudianteDTO> estudiantes;
    
    private List <LugaresDeInteresDTO> lugaresDeInteres;
    
    public ViviendaDetailDTO(){
        super();
    }
    
     public ViviendaDetailDTO(ViviendaEntity entity){
        super(entity);
         serviciosAdicionales = new ArrayList<>();
         serviciosFijos = new ArrayList<>();
         valoraciones = new ArrayList<>();
         estudiantes = new ArrayList();
         facturas = new ArrayList<>();
         arrendatariosPropietarios = new ArrendatarioDTO(entity.getArrendatariosPropietarios());
         lugaresDeInteres = new ArrayList<>();
          if(entity.getFacturas()!=null){
             for (int i = 0; i < entity.getFacturas().size(); i++) {
                 facturas.add(new FacturaDTO(entity.getFacturas().get(i)));
             }
         }
          if(entity.getLugaresDeInteres()!=null){
             for (int i = 0; i < entity.getLugaresDeInteres().size(); i++) {
                 lugaresDeInteres.add(new LugaresDeInteresDTO(entity.getLugaresDeInteres().get(i)));
             }
         }
          if(entity.getEstudiantes()!=null){
             for (int i = 0; i < entity.getEstudiantes().size(); i++) {
                 estudiantes.add(new EstudianteDTO(entity.getEstudiantes().get(i)));
             }
         }
         if(entity.getServiciosAdicionales()!=null){
             for (int i = 0; i < entity.getServiciosAdicionales().size(); i++) {
                 serviciosAdicionales.add(new ServicioDTO(entity.getServiciosAdicionales().get(i)));
             }
         }
         if(entity.getServiciosFijos()!=null){
             for (int i = 0; i < entity.getServiciosFijos().size(); i++) {
                 serviciosFijos.add(new ServicioDTO(entity.getServiciosFijos().get(i)));
             }
         }
          if(entity.getValoraciones()!=null){
             for (int i = 0; i < entity.getValoraciones().size(); i++) {
                 valoraciones.add(new ValoracionDTO(entity.getValoraciones().get(i)));
             }
         }
       //preguntar for de creadores de dtos asociados
    }
    
    public ViviendaEntity toEntity()
    {
        
        ViviendaEntity e = super.toEntity();
        
        List<ServicioEntity> PserviciosFijos = new ArrayList<>();
   
        List<ServicioEntity> PserviciosAdicionales = new ArrayList<>();
    
        List<ValoracionEntity> Pvaloraciones = new ArrayList<>();
        
        List<FacturaEntity> PFacturas = new ArrayList<>();
        
        List<EstudianteEntity> PEstudiantes = new ArrayList<>();
        
        List<LugaresDeInteresEntity> PLugares = new ArrayList<>();
        
        if(arrendatariosPropietarios!=null){
        e.setArrendatariosPropietarios(arrendatariosPropietarios.toEntity());
        }
        if(getValoraciones()!=null){
        for (int i = 0; i < getValoraciones().size(); i++) {
            ValoracionEntity get = getValoraciones().get(i).toEntity();
            Pvaloraciones.add(get);
        }
        }
         if(getEstudiantes()!=null){
        for (int i = 0; i < getEstudiantes().size(); i++) {
            EstudianteEntity get = getEstudiantes().get(i).toEntity();
            PEstudiantes.add(get);
        }
        }
         if(getFacturas()!=null){
        for (int i = 0; i < getFacturas().size(); i++) {
            FacturaEntity get = getFacturas().get(i).toEntity();
            PFacturas.add(get);
        }
        }
        
        if(getServiciosAdicionales()!=null){
          for (int i = 0; i < serviciosAdicionales.size(); i++) {
              System.out.println("PUTA"+serviciosAdicionales.get(i)  ); 
            ServicioEntity get = serviciosAdicionales.get(i).toEntity();
            PserviciosAdicionales.add(get);
        }
        }
        if(getServiciosFijos()!=null){
           for (int i = 0; i < serviciosFijos.size(); i++) {
            ServicioEntity get = serviciosFijos.get(i).toEntity();
            PserviciosFijos.add(get);
        }
        }
        if(getLugaresDeInteres()!=null){
           for (int i = 0; i < lugaresDeInteres.size(); i++) {
            LugaresDeInteresEntity get = lugaresDeInteres.get(i).toEntity();
            PLugares.add(get);
        }
        }
           e.setLugaresDeInteres(PLugares);
           e.setFacturas(PFacturas);
           e.setValoraciones(Pvaloraciones);
           e.setServiciosAdicionales(PserviciosAdicionales);
           e.setServiciosFijos(PserviciosFijos);
           e.setEstudiantes(PEstudiantes);
      return e;
    }
    
    /**
     * @return the serviciosFijos
     */
    public List<ServicioDTO> getServiciosFijos() {
        return serviciosFijos;
    }

    /**
     * @param serviciosFijos the serviciosFijos to set
     */
    public void setServiciosFijos(List<ServicioDTO> serviciosFijos) {
        this.serviciosFijos = serviciosFijos;
    }

    /**
     * @return the serviciosAdicionales
     */
    public List<ServicioDTO> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    /**
     * @param serviciosAdicionales the serviciosAdicionales to set
     */
    public void setServiciosAdicionales(List<ServicioDTO> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    /**
     * @return the valoraciones
     */
    public List<ValoracionDTO> getValoraciones() {
        return valoraciones;
    }

    /**
     * @param valoraciones the valoraciones to set
     */
    public void setValoraciones(List<ValoracionDTO> valoraciones) {
        this.valoraciones = valoraciones;
    }

    /**
     * @return the facturas
     */
    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<FacturaDTO> facturas) {
        this.facturas = facturas;
    }

    /**
     * @return the arrendatariosPropietarios
     */
    public ArrendatarioDTO getArrendatariosPropietarios() {
        return arrendatariosPropietarios;
    }

    /**
     * @param arrendatariosPropietarios the arrendatariosPropietarios to set
     */
    public void setArrendatariosPropietarios(ArrendatarioDTO arrendatariosPropietarios) {
        this.arrendatariosPropietarios = arrendatariosPropietarios;
    }

    /**
     * @return the estudiantes
     */
    public List <EstudianteDTO> getEstudiantes() {
        return estudiantes;
    }

    /**
     * @param estudiantes the estudiantes to set
     */
    public void setEstudiantes(List <EstudianteDTO> estudiantes) {
        this.estudiantes = estudiantes;
    }

    /**
     * @return the lugaresDeInteres
     */
    public List <LugaresDeInteresDTO> getLugaresDeInteres() {
        return lugaresDeInteres;
    }

    /**
     * @param lugaresDeInteres the lugaresDeInteres to set
     */
    public void setLugaresDeInteres(List <LugaresDeInteresDTO> lugaresDeInteres) {
        this.lugaresDeInteres = lugaresDeInteres;
    }
}

