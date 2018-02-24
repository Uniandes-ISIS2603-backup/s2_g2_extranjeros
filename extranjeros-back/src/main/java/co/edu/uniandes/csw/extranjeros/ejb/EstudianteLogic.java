/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import co.edu.uniandes.csw.extranjeros.persistence.EstudiantePersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author am.quintero12
 */

@Stateless
public class EstudianteLogic {
    
    private static final Logger LOGGER = Logger.getLogger(EstudianteLogic.class.getName());
    
    @Inject
    private EstudiantePersistence estudiantePersistence;
    
    /**
     * Devuelve todos los estudiantes que hay en la base de datos.
     * @return Lista de entidades de tipo libro.
     */
    public List<EstudianteEntity> getBooks() {
        LOGGER.info("Inicia proceso de consultar todos los estudiantes");
        List<EstudianteEntity> estudiantes = estudiantePersistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los estudiantes");
        return estudiantes;
    }
    
    
    
}
