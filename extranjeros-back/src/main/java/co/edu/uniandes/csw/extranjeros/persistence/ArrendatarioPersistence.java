/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author jr.pacheco10
 */

@Stateless
public class ArrendatarioPersistence {
    
    //---------------------------------------------------
    // LOGGER: Syso(...)
    //---------------------------------------------------
    private static final Logger LOGGER = Logger.getLogger(ArrendatarioPersistence.class.getName());
    
    //---------------------------------------------------
    // Contexto de Persistencia y Controlador Entity
    //---------------------------------------------------
    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em; 
    
    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------
   
    /**
     * Busca si hay algun arrendatario con la cedula que se envia de argumento.
     * @param pCedula: Cedula del arrendatario que se esta buscando.
     * @return null si no existe ningun arrendatario con la cedula del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ArrendatarioEntity findByCedula (String pCedula){
        
        LOGGER.log(Level.INFO, "Consultando el Arrendatario por cedula", pCedula);

        TypedQuery query = em.createQuery("Select e From ArrendatarioEntity e where e.cedula = :cedula", ArrendatarioEntity.class);
        query = query.setParameter("cedula", pCedula);
        List<ArrendatarioEntity> sameName = query.getResultList();
        
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
    /**
     * Busca si hay algun arrendatario con el login que se envia de argumento.
     * @param pLogin: Login (nombre de Usuario) del usuario que se esta buscando.
     * @return null si no existe ningun Usuario con el login del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ArrendatarioEntity findByLogin (String pLogin){
        
        LOGGER.log(Level.INFO, "Consultando el Arrendatario por login ", pLogin);

        TypedQuery query = em.createQuery("Select e From ArrendatarioEntity e where e.usuario = :usuario", ArrendatarioEntity.class);
        query = query.setParameter("usuario", pLogin);
        List<ArrendatarioEntity> sameName = query.getResultList();
        
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }

    /**
     * Busca si hay algun Usuario con el nombre que se envia de argumento.
     * @param pLogin: Nombre del arrendatario que se esta buscando.
     * @return null si no existe ningun arrendatario con el login del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ArrendatarioEntity findByName (String pLogin){
        
        LOGGER.log(Level.INFO, "Consultando el Usuario por login ", pLogin);

        TypedQuery query = em.createQuery("Select e From ArrendatarioEntity e where e.nombre = :nombre", ArrendatarioEntity.class);
        query = query.setParameter("nombre", pLogin);
        List<ArrendatarioEntity> sameName = query.getResultList();
        
        if (sameName.isEmpty()) {
            return null;
        } else {
            return sameName.get(0);
        }
    }
    
    
    /**
     * Busca en la Base de Datos si existe un Arrendatario asociado al ID dado por parametro.
     * @param id Id del Arrendatario que se consulta en las relaciones de la BD. 
     * @return Retorna el Arrendatario al que corresponde la ID dada.
     */
    public ArrendatarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando el Arrendatario con id={0}", id);
        return em.find(ArrendatarioEntity.class, id);
    }
    
    /**
     * Busca todos los Arrendatarios existentes en la base de datos
     * @return Retrona una lista de los arrendatarios que estan en la base de datos.
     */
    public List<ArrendatarioEntity> findAll() {
        LOGGER.info("Consultando los arrendatarios en el Sistema");
        Query q = em.createQuery("select u from ArrendatarioEntity u");
        return q.getResultList();
    }
    /**
     * Crea un Arrendatario dentro de su relacion en la Base de Datos
     * @param pArrendatario Arrendatario que se desea ingresar a la BD.
     * @return Retorna el Arrendatario creado y su ID asociado en la BD.
     */
    public ArrendatarioEntity create(ArrendatarioEntity pArrendatario) {
        LOGGER.info("Generando un nuevo Arrendatario");
        em.persist(pArrendatario);
        LOGGER.info("El Arrendatario ha sido creado");
        return pArrendatario;
    }
    /**
     * Actualiza un Arrendatario dentro de la Base de Datos.
     * @param pArrendatario Nuevo Arrendatario que sera remplazado por el viejo en la BD.
     * @return Retorna el Arrendatario que ha sido actualizado y esta ahora en la BD.
     */
    public ArrendatarioEntity update(ArrendatarioEntity pArrendatario) {
        LOGGER.log(Level.INFO, "Actualizando el Arrendatario con el id={0}", pArrendatario.getId());
        return em.merge(pArrendatario);
    }
    /**
     * Borra el Arrendatario asociado con el ID dado de la Base de Datos.
     * @param id Identificador del Arrendatario a borrar en la BD.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Eliminando el Arrendatario con el id={0}",id);
        em.remove(find(id));
    }
}
