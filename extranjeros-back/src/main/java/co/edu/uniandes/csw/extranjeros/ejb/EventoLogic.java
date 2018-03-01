/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.EventoEntity;
import co.edu.uniandes.csw.extranjeros.persistence.EventoPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author la.ruiz967
 */
@Stateless
public class EventoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(EventoLogic.class.getName());
    
    @Inject
    private EventoPersistence persistence;
    
    public EventoEntity create(EventoEntity entity)
    {
        return persistence.create(entity);
    }
    
    public EventoEntity findByName(String name)
    {
        return persistence.findByName(name);
    }
    
    public EventoEntity find(Long id)
    {
        return persistence.find(id);
    }
    
    public List<EventoEntity> findAll()
    {
        return persistence.findAll();
    }
    
    public EventoEntity update(EventoEntity entity)
    {
        return persistence.update(entity);
    }
    
    public void delete(Long id)
    {
        persistence.delete(id);
    }
}
