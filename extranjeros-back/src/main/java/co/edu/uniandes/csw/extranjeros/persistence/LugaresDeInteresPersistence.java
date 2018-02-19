/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;

import co.edu.uniandes.csw.extranjeros.entities.LugaresDeInteresEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Oliver
 */
public class LugaresDeInteresPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(LugaresDeInteresPersistence.class.getName());
    
    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em; 
    /**
     * Busca el lugar de interes en la base de datos con el id enviado en el argumento
     * @param id Id del lugar de interes que se buscará en la base de datos
     * @return devuelve el lugar de interes encontrado en la base de datos.
     */
    public LugaresDeInteresEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando lugar de interes con id={0}", id);
        return em.find(LugaresDeInteresEntity.class, id);
    }
    /**
     * Busca el lugar de interes en la base de datos
     * @return devuelve una lista de los lugares de interes que estan en la base de datos.
     */
    public List<LugaresDeInteresEntity> findAll()
    {
        LOGGER.info("Consultando todas los lugares de interes");
        Query q=em.createQuery("select u from LugaresDeInteresEntity u");
        return q.getResultList();
    }
    /**
     * Crea un lugar de interes en la base de datos
     * @param lugarDeInteres Lugar de Interes que se creará en la base de datos
     * @return devuelve el lugar de interes creado con un id dado por la base de datos.
     */
    public LugaresDeInteresEntity create(LugaresDeInteresEntity lugarDeInteres)
    {
        LOGGER.info("Creando un nuevo lugar de interes");
        em.persist(lugarDeInteres);
        LOGGER.info("Lugar de interes creada");
        return lugarDeInteres;
    }
    /**
     * Actualiza un lugar de interes en la base de datos
     * @param lugarDeInteres Lugar de Interes por el que se remplaza el que esta en la base de datos
     * @return devuelve el lugar de interes actualizado.
     */
    public LugaresDeInteresEntity update(LugaresDeInteresEntity lugarDeInteres)
    {
        LOGGER.log(Level.INFO, "Actualizando lugar de interes con el id={0}", lugarDeInteres.getId());
        return em.merge(lugarDeInteres);
    }
    /**
     * Borra un lugar de interes en la base de datos con el id enviado en el argumento
     * @param id Id del lugar de interes que se borrará de la base de datos
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando lugar de interes con el id={0}",id);
        em.remove(find(id));
    }
    
}
