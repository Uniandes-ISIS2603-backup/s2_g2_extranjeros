/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;


import co.edu.uniandes.csw.extranjeros.entities.UniversidadEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.UniversidadPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author o.amaya724
 */
public class UniversidadLogic {
    
    private static final Logger LOGGER = Logger.getLogger(UniversidadLogic.class.getName());

    @Inject
    private UniversidadPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public UniversidadEntity createUniversidad(UniversidadEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de universidad");
        // Verifica la regla de negocio que dice que no puede haber dos universidades con el mismo nombre
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una Universidad con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear la universidad
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de universidad");
        return entity;
    }

    public List<UniversidadEntity> getUniversidades() {
        LOGGER.info("Inicia proceso de consultar todas las universidades");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<UniversidadEntity> editorials = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las universidades");
        return editorials;
    }

    public UniversidadEntity getUniversidad(Long id) {
        return persistence.find(id);
    }

    public UniversidadEntity updateUniversidad(UniversidadEntity entity) throws BusinessLogicException  {
        if (persistence.findByName(entity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una Universidad con el nombre \"" + entity.getName() + "\"");
        }
        return persistence.update(entity);
    }
    
    public void deleteUniversidad(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar universidad con id={0}", id);    
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar universidad con id={0}", id);
    }
    
}
