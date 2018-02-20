/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;

import co.edu.uniandes.csw.extranjeros.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author jr.pacheco10
 */

@Stateless
public class UsuarioPersistence {
    
    //---------------------------------------------------
    // LOGGER: Syso(...)
    //---------------------------------------------------
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
    
    //---------------------------------------------------
    // Contexto de Persistencia y Controlador Entity
    //---------------------------------------------------
    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em; 
    
    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------

    /**
     * Busca en la Base de Datos si existe un Usuario asociado al ID dado por parametro.
     * @param id Id del Usuario que se consulta en las relaciones de la BD. 
     * @return Retorna el Usuario al que corresponde la ID dada.
     */
    public UsuarioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando el Usuario con id={0}", id);
        return em.find(UsuarioEntity.class, id);
    }
    
    /**
     * Busca todos los Usuarios existentes en la base de datos
     * @return Retrona una lista de los usuarios que estan en la base de datos.
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.info("Consultando los usuarios del Sistema");
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }
    /**
     * Crea un Usuario dentro de su relacion en la Base de Datos
     * @param pUsuario Usuario que se desea ingresar a la BD.
     * @return Retorna el Usuario creado y su ID asociado en la BD.
     */
    public UsuarioEntity create(UsuarioEntity pUsuario) {
        LOGGER.info("Generando un nuevo Usuario");
        em.persist(pUsuario);
        LOGGER.info("El Usuario ha sido creado");
        return pUsuario;
    }
    /**
     * Actualiza un Usuario dentro de la Base de Datos.
     * @param pUsuario Nuevo Usuario que sera remplazado por el viejo en la BD.
     * @return Retorna el Usuario que ha sido actualizado y esta ahora en la BD.
     */
    public UsuarioEntity update(UsuarioEntity pUsuario) {
        LOGGER.log(Level.INFO, "Actualizando el Usuario con el id={0}", pUsuario.getId());
        return em.merge(pUsuario);
    }
    /**
     * Borra el Usuario asociado con el ID dado de la Base de Datos.
     * @param id Identificador del Usuario a borrar en la BD.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Eliminando el Usuario con el id={0}",id);
        em.remove(find(id));
    }
  
}
