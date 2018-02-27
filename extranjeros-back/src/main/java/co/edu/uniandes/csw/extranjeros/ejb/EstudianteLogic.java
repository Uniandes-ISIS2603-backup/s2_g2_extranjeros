/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import co.edu.uniandes.csw.extranjeros.persistence.EstudiantePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author am.quintero12
 */

@Stateless
public class EstudianteLogic extends UsuarioLogic {
    
    private static final Logger LOGGER = Logger.getLogger(EstudianteLogic.class.getName());
    
    @Inject
    private EstudiantePersistence estudiantePersistence;
    
    /**
     * Devuelve todos los estudiantes que hay en la base de datos.
     * @return Lista de entidades de tipo libro.
     */
    public List<EstudianteEntity> getEstudiantes() {
        LOGGER.info("Inicia proceso de consultar todos los estudiantes");
        List<EstudianteEntity> estudiantes = estudiantePersistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los estudiantes");
        return estudiantes;
    }
    
     /**
     * Busca un libro por ID
     * @param id El id del estudiante a buscar
     * @return El estudiante encontrado, null si no lo encuentra.
     */
    public EstudianteEntity getEstudiante(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar estudiante con id={0}", id);
        EstudianteEntity estudiante = estudiantePersistence.find(id);
        if (estudiante == null) {
            LOGGER.log(Level.SEVERE, "El estudiante con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar estudiante con id={0}", id);
        return estudiante;
    }
    
     /**
     * Eliminar un estudiante por ID
     * @param id El ID del estudiante a eliminar
     */
    public void deleteBook(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar estudiante con id={0}", id);
        estudiantePersistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar estudiante con id={0}", id);
    }

    
    
    
    
}
