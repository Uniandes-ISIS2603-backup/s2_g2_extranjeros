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
     * Busca si hay algun evento con el nombre que se envía de argumento
     *
     * @param nombre: Nombre del evento que se está buscando
     * @return null si no existe ningun evento con el nombre del argumento. Si
     * existe alguno devuelve el primera.
     */
    public EventoEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando Evento por nombre ", nombre);

        // Se crea un query para buscar eventos con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From EventoEntity e where e.nombreEvento = :nombre", EventoEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<EventoEntity> sameName = query.getResultList();
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
    public EventoEntity find(Long id) {
        return em.find(EventoEntity.class, id);
    }

    public EventoEntity update(EventoEntity entity) {
         return em.merge(entity);
    }
    
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando Evento con el id={0}",id);
        em.remove(find(id));
    }
    
    public List<EventoEntity> findAll() {
        LOGGER.info("Consultando todos los eventos");
        TypedQuery query = em.createQuery("select u from EventoEntity u", EventoEntity.class);
        return query.getResultList();
    }
    
    
    
    
}
