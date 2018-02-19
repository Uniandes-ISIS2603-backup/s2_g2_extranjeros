/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;

import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
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
public class ViviendaPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(ViviendaPersistence.class.getName());

    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em;

    public ViviendaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando vivienda con id={0}", id);
        return em.find(ViviendaEntity.class, id);
    }
    
     public List<ViviendaEntity> findAll() {
        LOGGER.info("Consultando todas las viviendas");
        Query q = em.createQuery("select u from ViviendaEntity u");
        return q.getResultList();
    }

    public ViviendaEntity create(ViviendaEntity entity) {
        LOGGER.info("Creando una vivienda nueva");
        em.persist(entity);
        LOGGER.info("Vivienda creada");
        return entity;
    }

    public ViviendaEntity update(ViviendaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando vivienda con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando vivienda con id={0}", id);
        ViviendaEntity entity = em.find(ViviendaEntity.class, id);
        em.remove(entity);
    }
    
}
