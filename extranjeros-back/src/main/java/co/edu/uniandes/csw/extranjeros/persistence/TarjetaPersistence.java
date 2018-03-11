/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;

import co.edu.uniandes.csw.extranjeros.entities.TarjetaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author la.ruiz967
 */
@Stateless
public class TarjetaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(TarjetaPersistence.class.getName());
    
    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto tarjeta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TarjetaEntity create(TarjetaEntity entity) {
        LOGGER.info("Creando una tarjeta nueva");
        em.persist(entity);
        LOGGER.info("Tarjeta creada");
        return entity;
    }
    
    public TarjetaEntity find(Long id) {
        return em.find(TarjetaEntity.class, id);
    }

    public TarjetaEntity update(TarjetaEntity entity) {
         return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando Tarjeta con el id={0}",id);
        em.remove(find(id));
    }
    
    public List<TarjetaEntity> findAll() {
        LOGGER.info("Consultando todas las tarjetas");
        TypedQuery query = em.createQuery("select u from TarjetaEntity u", TarjetaEntity.class);
        return query.getResultList();
    }
    
    
    
    
}
