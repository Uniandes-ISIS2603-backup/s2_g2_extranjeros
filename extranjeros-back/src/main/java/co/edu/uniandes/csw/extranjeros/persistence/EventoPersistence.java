/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;

import co.edu.uniandes.csw.extranjeros.entities.EventoEntity;
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
public class EventoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(EventoPersistence.class.getName());
    
    @PersistenceContext(unitName="ExtranjerosPU")
    protected EntityManager em;
    
    /**
     *
     * @param entity objeto tarjeta que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EventoEntity create(EventoEntity entity) {
        LOGGER.info("Creando un evento nuevo");
        em.persist(entity);
        LOGGER.info("Evento creada");
        return entity;
    }
    
    /**
     * Encuentra elemento con el id dado por parámetro
     * @param id
     * @return 
     */
    public EventoEntity find(Long id) {
        return em.find(EventoEntity.class, id);
    }

    /**
     * Actualiza el elemento de la base de datos por el que llega por parámetro
     * @param entity
     * @return 
     */
    public EventoEntity update(EventoEntity entity) {
         return em.merge(entity);
    }
    
    /**
     * Borra de la base de datos el elemento con el id dado. 
     * @param id 
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando Evento con el id={0}",id);
        em.remove(find(id));
    }
    
    /**
     * Retorna la lista de elementos en la base de datos.
     * @return 
     */
    public List<EventoEntity> findAll() {
        LOGGER.info("Consultando todos los eventos");
        TypedQuery query = em.createQuery("select u from EventoEntity u", EventoEntity.class);
        return query.getResultList();
    }
}
