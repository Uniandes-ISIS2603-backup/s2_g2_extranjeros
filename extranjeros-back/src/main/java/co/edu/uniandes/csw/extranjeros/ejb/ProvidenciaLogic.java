/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.ProvidenciaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.ProvidenciaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author am.quintero12
 */
@Stateless
public class ProvidenciaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ProvidenciaLogic.class.getName());

    @Inject
    private ProvidenciaPersistence persistence;
    
    /**
     * Devuelve todas las providencias que hay en la base de datos.
     * @return Lista de entidades de tipo libro.
     */
    public List<ProvidenciaEntity> getProvidencias() {
        LOGGER.info("Inicia proceso de consultar todas las providencias");
        List<ProvidenciaEntity> providencias = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las providencias");
        return providencias;
    }
    
    /**
     * Busca una providencia por ID
     * @param id El id de la providencia a buscar
     * @return La providencia encontrado, null si no lo encuentra.
     */
    public ProvidenciaEntity getProvidencia(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar providencia con id={0}", id);
        ProvidenciaEntity providencia = persistence.find(id);
        if (providencia == null) {
            LOGGER.log(Level.SEVERE, "La providencia con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar providencia con id={0}", id);
        return providencia;
    }
    
    /**
     * Eliminar una providencia por ID
     * @param id El ID de la providencia a eliminar
     */
    public void deleteProvidencia(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar providencia con id={0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar providencia con id={0}", id);
    }
    
    /**
     * Guardar una nueva providencia
     * @param entity La entidad de tipo providencia  a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si no se tiene región o pais .
     */
    public ProvidenciaEntity createProvidencia(ProvidenciaEntity entity) throws BusinessLogicException  
    {
         LOGGER.info("Inicia proceso de creación de providencia");
        // Verifica la regla de negocio que dice que no puede haber dos cityes con el mismo nombre
        if (!((entity.getPais()!=null)&& (entity.getRegion()!=null))) {
            throw new BusinessLogicException("No se cumplen con los datos requeridos para crear una providencia");
        }
        // Invoca la persistencia para crear la city
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de providencia");
        return entity;
        
    }
    
    
    /**
     * Actualizar un libro por ID
     * @param entity La entidad de la providencia con los cambios deseados
     * @return La entidad de la providencia luego de actualizarla
     * @throws BusinessLogicException Si no existe la providencia ingresada
     */
     public ProvidenciaEntity updatProvidencia(ProvidenciaEntity entity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id={0}", entity.getId());
        if (!(persistence.find(entity.getId())!=null)) {
            throw new BusinessLogicException("No existe la providencia ingresada");
        }
        ProvidenciaEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar providencia con id={0}", entity.getId());
        return newEntity;
    }
    
    
}
