/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;


import co.edu.uniandes.csw.extranjeros.entities.LugaresDeInteresEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.LugaresDeInteresPersistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author o.amaya724
 */
public class LugaresDeInteresLogic {
    
    private static final Logger LOGGER = Logger.getLogger(UniversidadLogic.class.getName());

    @Inject
    private LugaresDeInteresPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public LugaresDeInteresEntity createlugarDeInteres(LugaresDeInteresEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de lugar de interes");
        // Verifica la regla de negocio que dice que no puede haber dos lugares de interes con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe un Lugar de Interes con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear el lugar de interes
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación del lugar de interes");
        return entity;
    }

    public List<LugaresDeInteresEntity> getLugaresDeInteres() {
        LOGGER.info("Inicia proceso de consultar todos los lugares de interes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<LugaresDeInteresEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los lugares de interes");
        return editorials;
    }

    public LugaresDeInteresEntity getLugarDeInteres(Long id) {
        return persistence.find(id);
    }

    public LugaresDeInteresEntity updateLugarDeInteres(LugaresDeInteresEntity entity) throws BusinessLogicException  {
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe un lugar de interes con el nombre \"" + entity.getName() + "\"");
        }
        return persistence.update(entity);
    }
    
    public void deleteLugarDeInteres(LugaresDeInteresEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar lugar de interes con id={0}", entity.getId());    
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar lugar de interes con id={0}", entity.getId());
    }
    
}
