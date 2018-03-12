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
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author o.amaya724
 */
@Stateless
public class UniversidadLogic {
    
    private static final Logger LOGGER = Logger.getLogger(UniversidadLogic.class.getName());

    @Inject
    private UniversidadPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    //CREATE
    /**
     * Se encarga de crear una universidad en la base de datos
     * @param entity
     * @return Objeto de UniversidadEntity con su ID y sus datos.
     * @throws BusinessLogicException 
     */
    public UniversidadEntity createUniversidad(UniversidadEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de universidad");
        // Verifica la regla de negocio que dice que no puede haber dos universidades con el mismo nombre
        if (persistence.findByName(entity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Universidad con el nombre \"" + entity.getName() + "\"");
        }
        // Invoca la persistencia para crear la universidad
        return persistence.create(entity);
        
    }

    //-- GET ALL
    /**
     * Obtiene la lista de los registros de Universidad.
     * @return Colección de objetos de UniversidadEntity.
     */
    public List<UniversidadEntity> getUniversidades() {
        LOGGER.info("Inicia proceso de consultar todas las universidades");
        return persistence.findAll();
        
    }

    //-- GET ONE
    /**
     * Obtiene los datos de una instancia de Universidad a partir de su ID (identificador).
     * @param id Identificador de la instancia a consultar.
     * @return Instancia de UniversidadEntity con los datos del Usuario consultado.
     */
    public UniversidadEntity getUniversidad(Long id) {
        return persistence.find(id);
    }
    
    
    //-- UPDATE
    /**
     * Actualiza la información de una instancia de Universidad.
     * @param entity Instancia de UniversidadEntity con los nuevos datos.
     * @return Instancia de UniversidadEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException
     */
    public UniversidadEntity updateUniversidad(UniversidadEntity entity) throws BusinessLogicException  {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una universidad");
        if(persistence.findByName(entity.getNombre()) != null){
            throw new BusinessLogicException("Existe una universidad con el mismo nombre."); 
        }
        return persistence.update(entity);
    }
    
    //-- DELETE
    /**
     * Elimina una instancia de Universidad de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteUniversidad(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar universidad con id={0}", id);
        
        persistence.delete(id);
       
    }
    
}
