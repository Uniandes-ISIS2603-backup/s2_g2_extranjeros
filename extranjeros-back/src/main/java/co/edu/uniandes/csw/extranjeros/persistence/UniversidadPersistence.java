/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;


import co.edu.uniandes.csw.extranjeros.entities.UniversidadEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Oliver
 */

@Stateless
public class UniversidadPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(UniversidadPersistence.class.getName());
    
    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em; 
    /**
     * Busca la universidad en la base de datos con el id enviado en el argumento
     * @param id Id de la universidad que se buscará en la base de datos
     * @return devuelve la universidad encontrada en la base de datos.
     */
    public UniversidadEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando universidad con id={0}", id);
        return em.find(UniversidadEntity.class, id);
    }
    /**
     * Busca las universidades en la base de datos
     * @return devuelve una lista de las universidades que estan en la base de datos.
     */
    public List<UniversidadEntity> findAll()
    {
        LOGGER.info("Consultando todas las universidades");
        Query q=em.createQuery("select u from UniversidadEntity u");
        return q.getResultList();
    }
    /**
     * Crea una universidad en la base de datos
     * @param universidad Universidad que se creará en la base de datos
     * @return devuelve la universidad creada con un id dado por la base de datos.
     */
    public UniversidadEntity create(UniversidadEntity universidad)
    {
        LOGGER.info("Creando una nueva universidad");
        em.persist(universidad);
        LOGGER.info("Universidad creada");
        return universidad;
    }
    /**
     * Actualiza una universidad en la base de datos
     * @param universidad Universidad por la que se remplaza la que esta en la base de datos
     * @return devuelve la universidad actualizada.
     */
    public UniversidadEntity update(UniversidadEntity universidad)
    {
        LOGGER.log(Level.INFO, "Actualizando universidad con el id={0}", universidad.getId());
        return em.merge(universidad);
    }
    /**
     * Borra una universidad en la base de datos con el id enviado en el argumento
     * @param id Id de la universidad que se borrará de la base de datos
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando universidad con el id={0}",id);
        em.remove(find(id));
    }
    
}