/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;
import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
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
public class ServicioPersistence 
{
    private static final Logger LOGGER= Logger.getLogger(ServicioPersistence.class.getName());
    
    @PersistenceContext(unitName="ExtranjerosPU")
    protected EntityManager em;
    /**
     * Busca el servicio en la base de datos con el id enviado en el argumento
     * @param id Id del servicio que se buscará en la base de datos
     * @return devuelve el servicio encontrado en la base de datos.
     */
    public ServicioEntity find(Long id)
    {
        LOGGER.log(Level.INFO,"Consultando servicio con el id={0}",id);
        return em.find(ServicioEntity.class, id);
    }
    /**
     * Busca los servicios en la base de datos
     * @return devuelve una lista de los servicios que estan en la base de datos.
     */
    public List<ServicioEntity> findAll()
    {
        LOGGER.info("Consultando todos los servicios");
        Query q=em.createQuery("select u from ServicioEntity u");
        return q.getResultList();
    }
    /**
     * Crea un servicio en la base de datos
     * @param servicio Servicio que se creará en la base de datos
     * @return devuelve el servicio creada con un id dado por la base de datos.
     */
    public ServicioEntity create(ServicioEntity servicio)
    {
        LOGGER.info("Creando un nuevo servicio");
        em.persist(servicio);
        LOGGER.info("Servicio creado");
        return servicio;
    }
    /**
     * Actualiza un servicio en la base de datos
     * @param servicio Servicio por el que se remplaza el que esta en la base de datos
     * @return devuelve la factura actualizada.
     */
    public ServicioEntity update(ServicioEntity servicio)
    {
        LOGGER.log(Level.INFO,"Actualizando el servicio con id={0}",servicio.getId());
        return em.merge(servicio);
    }
    /**
     * Borra un servicio en la base de datos con el id enviado en el argumento
     * @param id Id del servicio que se borrará de la base de datos
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando servicio con id={0}", id);
        em.remove(find(id));
    }
}
