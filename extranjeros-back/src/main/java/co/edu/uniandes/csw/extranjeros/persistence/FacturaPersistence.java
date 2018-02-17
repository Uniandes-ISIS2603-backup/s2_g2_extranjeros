/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;

import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
/**
 *
 * @author s.rodriguezm
 */
@Stateless
public class FacturaPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());
    
    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em; 
    /**
     * Busca la factura en la base de datos con el id enviado en el argumento
     * @param id Id de la factura que se buscará en la base de datos
     * @return devuelve la factura encontrada en la base de datos.
     */
    public FacturaEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando factura con id={0}", id);
        return em.find(FacturaEntity.class, id);
    }
    /**
     * Busca las facturas en la base de datos
     * @return devuelve una lista de las facturas que estan en la base de datos.
     */
    public List<FacturaEntity> findAll()
    {
        LOGGER.info("Consultando todas las facturas");
        Query q=em.createQuery("select u from FacturaEntity u");
        return q.getResultList();
    }
    /**
     * Crea una factura en la base de datos
     * @param factura Factura que se creará en la base de datos
     * @return devuelve la factura creada con un id dado por la base de datos.
     */
    public FacturaEntity create(FacturaEntity factura)
    {
        LOGGER.info("Creando una nueva factura");
        em.persist(factura);
        LOGGER.info("Factura creada");
        return factura;
    }
    /**
     * Actualiza una factura en la base de datos
     * @param factura Factura por la que se remplaza la que esta en la base de datos
     * @return devuelve la factura actualizada.
     */
    public FacturaEntity update(FacturaEntity factura)
    {
        LOGGER.log(Level.INFO, "Actualizando factura con el id={0}", factura.getId());
        return em.merge(factura);
    }
    /**
     * Borra una factura en la base de datos con el id enviado en el argumento
     * @param id Id de la factura que se borrará de la base de datos
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando factura con el id={0}",id);
        em.remove(find(id));
    }
}
