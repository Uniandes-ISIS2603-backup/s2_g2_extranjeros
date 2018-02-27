/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.persistence.ArrendatarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jr.pacheco10
 */

@Stateless
public class ArrendatarioLogic {
    
    //---------------------------------------------------
    // LOGGER: Syso(...)
    //---------------------------------------------------
     private static final Logger LOGGER = Logger.getLogger(ArrendatarioLogic.class.getName());

    //---------------------------------------------------
    // Inject: Logica asociaciones y Persistence Clase
    //---------------------------------------------------
    @Inject  
    private ArrendatarioPersistence persistence;

    //---------------------------------------------------
    // Metodos Usuario(DTO) - Resource: sin relaciones
    //---------------------------------------------------
     
     //-- GET ONE
    /**
     * Obtiene los datos de una instancia de Arrendatario a partir de su ID (identificador).
     * @param id Identificador de la instancia a consultar.
     * @return Instancia de ArrendatarioEntity con los datos del Usuario consultado.
     */
    public ArrendatarioEntity getArrendatario(Long id){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un usuario con id = {0}", id);
        return persistence.find(id);
    }
    
    //-- GET ALL
    /**
     * Obtiene la lista de los registros de Arrendatario.
     * @return Colección de objetos de ArrendatarioEntity.
     */
     public List <ArrendatarioEntity> getArrendatarios(){
         LOGGER.info("Inicia proceso de consultar todos los arrendatarios de la plataforma");
         return persistence.findAll();
     }
     
     //-- CREATE
    /**
     * Se encarga de crear un Arrendatario en la base de datos.
     * @param newUser Objeto de ArrendatarioEntity con los datos nuevos.
     * @return Objeto de ArrendatarioEntity con los datos nuevos y su ID.
     */
    public ArrendatarioEntity createArrendatario (ArrendatarioEntity newUser){
        LOGGER.log(Level.INFO, "Inicia el proceso de crear un arrendatario en la plataforma");
        return persistence.create(newUser);
    }
    
    //-- UPDATE
    /**
     * Actualiza la información de una instancia de Arrendatario.
     * @param newUser Instancia de ArrendatarioEntity con los nuevos datos.
     * @return Instancia de ArrendatarioEntity con los datos actualizados.
     */
    public ArrendatarioEntity updateArrendatario (ArrendatarioEntity newUser){
        LOGGER.info("Inicia el proceso de actualizar un arrendatario en la plataforma");
        return persistence.update(newUser);
    }
    
    //-- DELETE
    /**
     * Elimina una instancia de Arrendatario de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteArrendatario (Long id){
        LOGGER.log(Level.INFO, "Inicia el proceso de borrado del arrendatario con id = {0}", id);
        persistence.delete(id);
    }
}
