/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
import co.edu.uniandes.csw.extranjeros.persistence.FacturaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.rodriguezm
 */
@Stateless
public class FacturaLogic {
    private static final Logger LOGGER = Logger.getLogger(ServicioLogic.class.getName());

    @Inject
    private FacturaPersistence persistence;
    
    /**
     * Obtiene la lista de las Facturas.
     *
     * @return Colección de objetos de FacturaEntity.
     */
    public List<FacturaEntity> getFacturas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los servicios");
        return persistence.findAll();
    }
    /**
     * Obtiene los datos de una instancia de Factura a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de FacturaEntity con los datos del Servicio consultado.
     */
    public FacturaEntity getFactura(Long id)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de consultar un servicio con id={0}",id);
        return persistence.find(id);
    }
    /**
     * Se encarga de crear un Factura en la base de datos.
     *
     * @param entity Objeto de FacturaEntity con los datos nuevos
     * @return Objeto de FacturaEntity con los datos nuevos y su ID.
     */
    public FacturaEntity createFactura(FacturaEntity entity)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de crear un servicio");
        return persistence.create(entity);
    }
    /**
     * Actualiza la información de una instancia de Factura.
     *
     * @param entity Instancia de FacturaEntity con los nuevos datos.
     * @return Instancia de FacturaEntity con los datos actualizados.
     */
    public FacturaEntity updateFactura(FacturaEntity entity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un servicio");
        return persistence.update(entity);
    }
    /**
     * Elimina una instancia de Factura de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteFactura(Long id)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de eliminar un servicio con id={0}",id);
        persistence.delete(id);
    }
}
