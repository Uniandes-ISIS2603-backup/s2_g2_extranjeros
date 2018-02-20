/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;

import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author am.quintero12
 */
public class EstudiantePersistence {
    private static final Logger LOGGER = Logger.getLogger(EstudiantePersistence.class.getName());
    
    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em; 
    /**
     * Busca el estudiante en la base de datos con el id enviado en el argumento
     * @param id Id del estudiante que se buscará en la base de datos
     * @return devuelve el estudiante encontrado en la base de datos.
     */
    public EstudianteEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando el estudiante con id={0}", id);
        return em.find(EstudianteEntity.class, id);
    }
    /**
     * Busca los estudiantes en la base de datos
     * @return devuelve una lista de los estudiantes que estan en la base de datos.
     */
    public List<EstudianteEntity> findAll()
    {
        LOGGER.info("Consultando todos los estudiantes");
        Query q=em.createQuery("select u from estudiantes u");
        return q.getResultList();
    }
    /**
     * Crea un estudiante en la base de datos
     * @param estudiante Estudiante que se creará en la base de datos
     * @return devuelve el estudiante creado con un id dado por la base de datos.
     */
    public EstudianteEntity create(EstudianteEntity estudiante)
    {
        LOGGER.info("Creando una nueva universidad");
        em.persist(estudiante);
        LOGGER.info("Universidad creada");
        return estudiante;
    }
    /**
     * Actualiza un estudiante en la base de datos
     * @param estudiante Estudiante por la que se remplaza la que esta en la base de datos
     * @return devuelve el estudiante actualizado.
     */
    public EstudianteEntity update(EstudianteEntity estudiante)
    {
        LOGGER.log(Level.INFO, "Actualizando universidad con el id={0}", estudiante.getId());
        return em.merge(estudiante);
    }
    /**
     * Borra un estudiante en la base de datos con el id enviado en el argumento
     * @param id Id del estudiante que se borrará de la base de datos
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando el estudiante con el id={0}",id);
        em.remove(find(id));
    }
    
}
