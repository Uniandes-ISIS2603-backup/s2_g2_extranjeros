/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.ViviendaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jd.arango
 */
@Stateless
public class ViviendaLogic {
 @Inject
 ViviendaPersistence persistence;
 
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
    
     
         return persistence.create(vivienda);
 }
 
  public ViviendaEntity updateVivienda(ViviendaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una vivienda");
        return persistence.update(entity);
    }
 
   public void deleteVivienda(Long id)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de eliminar una vivienda con id={0}",id);
        persistence.delete(id);
    }
  
}
