/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.ValoracionEntity;
import co.edu.uniandes.csw.extranjeros.persistence.ValoracionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.arango
 */
@Stateless
public class ValoracionLogic {
    @Inject
    ValoracionPersistence persistence;
    
    private static final Logger LOGGER = Logger.getLogger(ServicioLogic.class.getName());

   public List<ValoracionEntity> getValoraciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las valoraciones");
        return persistence.findAll();
    }
 
 public ValoracionEntity getValoracion(Long id){
     LOGGER.log(Level.INFO, "Inicia proceso de consultar la valoracion con el id dado");
       
     return persistence.find(id);
 }
 
 public ValoracionEntity createValoracion(ValoracionEntity val){
      
     LOGGER.log(Level.INFO, "Inicia proceso de crear la valoracion");
     
     return persistence.create(val);
 }
 
  public ValoracionEntity updateValoracion(ValoracionEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una valoracion");
        return persistence.update(entity);
    }
 
   public void deleteValoracion(Long id)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de eliminar una valoracion con id={0}",id);
        persistence.delete(id);
    }
    
}
