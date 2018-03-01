/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.TarjetaEntity;
import co.edu.uniandes.csw.extranjeros.persistence.TarjetaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author la.ruiz967
 */
@Stateless
public class TarjetaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(TarjetaLogic.class.getName());
    
    @Inject
    private TarjetaPersistence persistence;
    
    
    public TarjetaEntity create(TarjetaEntity tarjeta)
    {
        return persistence.create(tarjeta);
    }
    
    public TarjetaEntity update(TarjetaEntity tarjeta)
    {
        return persistence.update(tarjeta);
    }
    
    public TarjetaEntity findByNumber(Long numero)
    {
        return persistence.findByNumber(numero);
    }
    
    public TarjetaEntity find(Long id)
    {
        return persistence.find(id);
    }
    
    public List<TarjetaEntity> findAll()
    {
        return persistence.findAll();
    }
    
    public void delete(Long id)
    {
        persistence.delete(id);
    }
}
