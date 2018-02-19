/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;
;
import co.edu.uniandes.csw.extranjeros.entities.ValoracionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jd.arango
 */
 @Stateless
public class ValoracionPersistence {
   

    
     private static final Logger LOGGER = Logger.getLogger(ViviendaPersistence.class.getName());

    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em;

    public ValoracionEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando valoracion con id={0}", id);
        return em.find(ValoracionEntity.class, id);
    }
    
     public List<ValoracionEntity> findAll() {
        LOGGER.info("Consultando todas las valoraciones");
        Query q = em.createQuery("select u from ValoracionEntity u");
        return q.getResultList();
    }

    public ValoracionEntity create(ValoracionEntity entity) {
        LOGGER.info("Creando una valoracion nueva");
        em.persist(entity);
        LOGGER.info("valoracion creada");
        return entity;
    }

    public ValoracionEntity update(ValoracionEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando valoracion con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando valoracion con id={0}", id);
        ValoracionEntity entity = em.find(ValoracionEntity.class, id);
        em.remove(entity);
    }
}
