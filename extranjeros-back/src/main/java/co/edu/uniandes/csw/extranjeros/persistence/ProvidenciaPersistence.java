/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;

import co.edu.uniandes.csw.extranjeros.entities.ProvidenciaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author am.quintero12
 */
@Stateless
public class ProvidenciaPersistence {
     private static final Logger LOGGER = Logger.getLogger(ProvidenciaPersistence.class.getName());
    
    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em; 
    
    /**
     * Busca la providencia en la base de datos con el id enviado en el argumento
     * @param id Id de la providencia que se buscará en la base de datos
     * @return devuelve la providencia encontrado en la base de datos.
     */
    public ProvidenciaEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando la providencia con id={0}", id);
        return em.find(ProvidenciaEntity.class, id);
    }
    /**
     * Busca la providencia en la base de datos
     * @return devuelve una lista de las providencias que estan en la base de datos.
     */
    public List<ProvidenciaEntity> findAll()
    {
        LOGGER.info("Consultando todas las providencias");
        Query q=em.createQuery("select u from providencia u");
        return q.getResultList();
    }
    /**
     * Crea una providencia en la base de datos
     * @param providencia Providencia que se creará en la base de datos
     * @return devuelve la providencia creada con un id dado por la base de datos.
     */
    public ProvidenciaEntity create(ProvidenciaEntity providencia)
    {
        LOGGER.info("Creando una nueva providencia");
        em.persist(providencia);
        LOGGER.info("Providencia creada");
        return providencia;
    }
    
    /**
     * Actualiza una providencia en la base de datos
     * @param providencia Providencia por la que se remplaza la que esta en la base de datos
     * @return devuelve la providencia actualizado.
     */
    public ProvidenciaEntity update(ProvidenciaEntity providencia)
    {
        LOGGER.log(Level.INFO, "Actualizando providencia con el id={0}", providencia.getId());
        return em.merge(providencia);
    }
    
    /**
     * Borra una providencia en la base de datos con el id enviado en el argumento
     * @param id Id de la providencia que se borrará de la base de datos
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando la providencia con el id={0}",id);
        em.remove(find(id));
    }
    
}
