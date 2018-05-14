/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.entities.LugaresDeInteresEntity;
import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.ArrendatarioPersistence;
import co.edu.uniandes.csw.extranjeros.persistence.EstudiantePersistence;
import co.edu.uniandes.csw.extranjeros.persistence.FacturaPersistence;
import co.edu.uniandes.csw.extranjeros.persistence.LugaresDeInteresPersistence;
import co.edu.uniandes.csw.extranjeros.persistence.ServicioPersistence;
import co.edu.uniandes.csw.extranjeros.persistence.ViviendaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jd.arango
 */
@Stateless
public class ViviendaLogic {
 @Inject
 ViviendaPersistence persistence;
 
 @Inject
 ArrendatarioPersistence arrendatarioPersistence;
 
 @Inject
 EstudiantePersistence estudiantePersistence;
 
 @Inject
 LugaresDeInteresPersistence lugaresDeInteresPersistence;
 
 @Inject
 FacturaPersistence facturaPersistence;
 
 @Inject
 ServicioPersistence servicioPersistence;
 
 private static final Logger LOGGER = Logger.getLogger(ServicioLogic.class.getName());

   public List<ViviendaEntity> getViviendas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los viviendas");
        return persistence.findAll();
    }
 
 public ViviendaEntity getVivienda(Long id){
     LOGGER.log(Level.INFO, "Inicia proceso de consultar la vivienda con el id dado");
       
     return persistence.find(id);
 }
 
 public ViviendaEntity createVivienda(ViviendaEntity vivienda){
      
     LOGGER.log(Level.INFO, "Inicia proceso de crear la vivienda");
   
     ArrendatarioEntity arrendatariosPropietarios = null;
    if(vivienda.getArrendatariosPropietarios()!=null)
    arrendatariosPropietarios = arrendatarioPersistence.find(vivienda.getArrendatariosPropietarios().getId());
   if(arrendatariosPropietarios!=null) vivienda.setArrendatariosPropietarios(arrendatariosPropietarios);
   
   List <EstudianteEntity> estudiantes = new ArrayList<>();
     for (EstudianteEntity estudiante : vivienda.getEstudiantes()) {
         EstudianteEntity temp = estudiantePersistence.find(estudiante.getId());
         if(temp !=null)
         estudiantes.add(temp);
     }
    vivienda.setEstudiantes(estudiantes);
    
    List <LugaresDeInteresEntity> lugaresDeInteres = new ArrayList<>();
     for (LugaresDeInteresEntity lugares : vivienda.getLugaresDeInteres()) {
         LugaresDeInteresEntity temp = lugaresDeInteresPersistence.find(lugares.getId());
         if(temp !=null)
         lugaresDeInteres.add(temp);
     }
     vivienda.setLugaresDeInteres(lugaresDeInteres);
     
    List<FacturaEntity> facturas = new ArrayList<>();
     for (FacturaEntity factura : vivienda.getFacturas()) {
         FacturaEntity temp = facturaPersistence.find(factura.getId());
         if(temp !=null)
         facturas.add(temp);
     }
    vivienda.setFacturas(facturas);
    
    List<ServicioEntity> serviciosFijos = new ArrayList<>();
     for (ServicioEntity servicioFijo : vivienda.getServiciosFijos()) {
         ServicioEntity temp = servicioPersistence.find(servicioFijo.getId());
         if(temp !=null)
         serviciosFijos.add(temp);
     }
    vivienda.setServiciosFijos(serviciosFijos);
     
    List<ServicioEntity> serviciosAdicionales = new ArrayList<>();
     for (ServicioEntity servicioAdicional : vivienda.getServiciosAdicionales()) {
         ServicioEntity temp = servicioPersistence.find(servicioAdicional.getId());
         if(temp !=null)
         serviciosAdicionales.add(temp);
     }
     vivienda.setServiciosAdicionales(serviciosAdicionales);
     
         return persistence.create(vivienda);
 }
 
  public ViviendaEntity updateVivienda(ViviendaEntity vivienda) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una vivienda");
      ArrendatarioEntity arrendatariosPropietarios = null;
    if(vivienda.getArrendatariosPropietarios()!=null)
    arrendatariosPropietarios = arrendatarioPersistence.find(vivienda.getArrendatariosPropietarios().getId());
   if(arrendatariosPropietarios!=null) vivienda.setArrendatariosPropietarios(arrendatariosPropietarios);
   
   List <EstudianteEntity> estudiantes = new ArrayList<>();
     for (EstudianteEntity estudiante : vivienda.getEstudiantes()) {
         EstudianteEntity temp = estudiantePersistence.find(estudiante.getId());
         if(temp !=null)
         estudiantes.add(temp);
     }
    vivienda.setEstudiantes(estudiantes);
    
    List <LugaresDeInteresEntity> lugaresDeInteres = new ArrayList<>();
     for (LugaresDeInteresEntity lugares : vivienda.getLugaresDeInteres()) {
         LugaresDeInteresEntity temp = lugaresDeInteresPersistence.find(lugares.getId());
         if(temp !=null)
         lugaresDeInteres.add(temp);
     }
     vivienda.setLugaresDeInteres(lugaresDeInteres);
     
    List<FacturaEntity> facturas = new ArrayList<>();
     for (FacturaEntity factura : vivienda.getFacturas()) {
         FacturaEntity temp = facturaPersistence.find(factura.getId());
         if(temp !=null)
         facturas.add(temp);
     }
    vivienda.setFacturas(facturas);
    
    List<ServicioEntity> serviciosFijos = new ArrayList<>();
     for (ServicioEntity servicioFijo : vivienda.getServiciosFijos()) {
         ServicioEntity temp = servicioPersistence.find(servicioFijo.getId());
         if(temp !=null)
         serviciosFijos.add(temp);
     }
    vivienda.setServiciosFijos(serviciosFijos);
     
    List<ServicioEntity> serviciosAdicionales = new ArrayList<>();
     for (ServicioEntity servicioAdicional : vivienda.getServiciosAdicionales()) {
         ServicioEntity temp = servicioPersistence.find(servicioAdicional.getId());
         if(temp !=null)
         serviciosAdicionales.add(temp);
     }
     vivienda.setServiciosAdicionales(serviciosAdicionales);
     
        return persistence.update(vivienda);
    }
 
   public void deleteVivienda(Long id)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de eliminar una vivienda con id={0}",id);
        persistence.delete(id);
    }
  
   
   //-- GET LUGARES DE INTERES ASOCIADOS:
    public List<LugaresDeInteresEntity> getFacturas(Long userID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar las Facturas asociadas al Arrendatario con id = {0}", userID);
        return getVivienda(userID).getLugaresDeInteres();
    }
}
