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
import co.edu.uniandes.csw.extranjeros.entities.UniversidadEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.ArrendatarioPersistence;
import co.edu.uniandes.csw.extranjeros.persistence.EstudiantePersistence;
import co.edu.uniandes.csw.extranjeros.persistence.FacturaPersistence;
import co.edu.uniandes.csw.extranjeros.persistence.LugaresDeInteresPersistence;
import co.edu.uniandes.csw.extranjeros.persistence.ServicioPersistence;
import co.edu.uniandes.csw.extranjeros.persistence.UniversidadPersistence;
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
 
 @Inject
 UniversidadPersistence universidadPersistence;
 
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
       List <UniversidadEntity> universidades = new ArrayList<>();
   if(vivienda.getUniversidades()!=null){
       for (UniversidadEntity universidad : vivienda.getUniversidades()) {
         UniversidadEntity temp = universidadPersistence.find(universidad.getId());
         if(temp !=null)
         universidades.add(temp);
     }
   }
    vivienda.setUniversidades(universidades);
    
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
    List <LugaresDeInteresEntity> lugaresBaseDatos = lugaresDeInteresPersistence.findAll();
    for (LugaresDeInteresEntity lugar : lugaresBaseDatos) {
         if(getDistance(Double.parseDouble(vivienda.getLatitud()), Double.parseDouble(vivienda.getLongitud()),
                 Double.parseDouble(lugar.getUbicacionLat()) , Double.parseDouble(lugar.getUbicacionLon()))<=1000){
              lugaresDeInteres.add(lugar);
              
         }
        
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
    
  /*  List <LugaresDeInteresEntity> lugaresDeInteres = new ArrayList<>();
     for (LugaresDeInteresEntity lugares : vivienda.getLugaresDeInteres()) {
         LugaresDeInteresEntity temp = lugaresDeInteresPersistence.find(lugares.getId());
         if(temp !=null)
         lugaresDeInteres.add(temp);
     }
     vivienda.setLugaresDeInteres(lugaresDeInteres);
     */
     List <LugaresDeInteresEntity> lugaresDeInteres = lugaresDeInteresPersistence.findAll();
    
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
   /*
   *´retorna la lista de viviendas asociadas a una universidad
   */
   public List<ViviendaEntity> viviendaPorUniversidad(Long idUniversidad){
       ArrayList<ViviendaEntity> retornar = new ArrayList<>();
       UniversidadEntity universidad = universidadPersistence.find(idUniversidad);
       List<ViviendaEntity> iterar = getViviendas();
       for (int i = 0; i < iterar.size(); i++) {
           if(iterar.get(i).getUniversidades().contains(universidad)){
               retornar.add(iterar.get(i));
           }
       }
       return retornar;
   }
   
   /*
   *Retorna una lsta de viviendas debajo de un precio dado
   */
     public List<ViviendaEntity> viviendaPorPrecio(Double precio){
       ArrayList<ViviendaEntity> retornar = new ArrayList<>();
       List<ViviendaEntity> iterar = getViviendas();
       for (int i = 0; i < iterar.size(); i++) {
           if(iterar.get(i).getPrecioMensual()<=precio){
               retornar.add(iterar.get(i));
           }
       }
       return retornar;
   }
     
   /*
   *Retorna una lsta de viviendas que tengas una lista de servicios 
   */
     public List<ViviendaEntity> viviendaPorServicios(ArrayList<ServicioEntity> servicios){
       ArrayList<ViviendaEntity> retornar = new ArrayList<>();
       List<ViviendaEntity> iterar = getViviendas();
       for (int i = 0; i < iterar.size(); i++) {
           if(iterar.get(i).getServiciosFijos().containsAll(servicios)){
               retornar.add(iterar.get(i));
           }
       }
       return retornar;
   }
   /*
     *Metodo que calcula la distancia en metros entre dos puntos
     */
     public double getDistance(double lat1, double lon1, double lat2, double lon2) 
	{
		int R = 6371*1000; 
		double latDistance = toRad(lat2-lat1);
		double lonDistance = toRad(lon2-lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = R * c;

		return distance;

	}

	private double toRad(double value) {
		return value * Math.PI / 180;
	}
   
   
   //-- GET LUGARES DE INTERES ASOCIADOS:
    public List<LugaresDeInteresEntity> getFacturas(Long userID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar las Facturas asociadas al Arrendatario con id = {0}", userID);
        return getVivienda(userID).getLugaresDeInteres();
    }
}
