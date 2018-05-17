/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import co.edu.uniandes.csw.extranjeros.entities.EventoEntity;
import co.edu.uniandes.csw.extranjeros.entities.ProvidenciaEntity;
import co.edu.uniandes.csw.extranjeros.entities.TarjetaEntity;
import co.edu.uniandes.csw.extranjeros.entities.UniversidadEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.EstudiantePersistence;
import co.edu.uniandes.csw.extranjeros.persistence.ProvidenciaPersistence;
import co.edu.uniandes.csw.extranjeros.persistence.UniversidadPersistence;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author am.quintero12
 */

@Stateless
public class EstudianteLogic  {
    
    private static final Logger LOGGER = Logger.getLogger(EstudianteLogic.class.getName());
    
    @Inject
    private EstudiantePersistence estudiantePersistence;
    
    @Inject
    private ProvidenciaPersistence providenciaPersistence;
    
    @Inject
    private UniversidadPersistence universidadPersistence;
    
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
    public void deleteEstudiante(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar estudiante con id={0}", id);
        estudiantePersistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar estudiante con id={0}", id);
    }
    
  
    /**
     * Actualiza la información de un estudiante
     * @param newUser Instancia de EstudianteEntityy con los nuevos datos.
     * @return Instancia de EstudianteEntityy con los datos actualizados.
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException
     */
    public EstudianteEntity updateEstudiante (EstudianteEntity newUser) throws BusinessLogicException{
        
        LOGGER.info("Inicia el proceso de actualizar un estudiante en la plataforma");
        
         if (universidadPersistence.find(newUser.getUniversidad().getId())!=null){
            Long id = newUser.getUniversidad().getId();
            newUser.setUniversidad(universidadPersistence.find(id));
        }
        else{
            throw new BusinessLogicException("No existe la universidad seleccionada");
        }
        
        if(newUser.getClave().length() < 8 && newUser.getClave().length() > 15){
            throw new BusinessLogicException("Su contraseña debe tener más de 8 caracteres y menos de 15");
        }
        
         if (!newUser.getCorreo().contains("@") || !newUser.getCorreo().contains(".com")){
            throw new BusinessLogicException("Su correo no es válido.");
        }
        
        String celular = String.valueOf(newUser.getCelular());
        char numeros[] = celular.toCharArray();
        
        if (numeros.length != 10){
            throw new BusinessLogicException("Ingrese un celular válido para Colombia.");
        }
        
        if(!(newUser.getVivienda()!=null))
        {
            newUser.setEstadoArrendamiento(false);
        }
        
        
        return estudiantePersistence.update(newUser);
    }
    
      //-- CREATE
    /**
     * Se encarga de crear un Estudiante en la base de datos.
     * @param newUser Objeto de EstudianteEntity con los datos nuevos.
     * @return Objeto de EstudianteEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException
     */
    public EstudianteEntity createEstudiante (EstudianteEntity newUser) throws BusinessLogicException{
        
        LOGGER.log(Level.INFO, "Inicia el proceso de crear un estudiante en la plataforma.");
        
  
        if (providenciaPersistence.find(newUser.getProvidencia().getId())!=null){
            Long id = newUser.getProvidencia().getId();
            newUser.setProvidencia(providenciaPersistence.find(id));
        }
        else{
            throw new BusinessLogicException("No existe la providencia seleccionada");
        }
        
        if (universidadPersistence.find(newUser.getUniversidad().getId())!=null){
            Long id = newUser.getUniversidad().getId();
            newUser.setUniversidad(universidadPersistence.find(id));
        }
        else{
            throw new BusinessLogicException("No existe la universidad seleccionada");
        }
        
        if (estudiantePersistence.findByUsuario(newUser.getUsuario()) != null){
            throw new BusinessLogicException("Existe un estudiante con el mismo usuario.");
        }
        
        // Verificacion: no existe un arrendatario con la misma cedula.
        if (estudiantePersistence.findByCedula(newUser.getCedula()) != null){
            throw new BusinessLogicException("Existe un estudiante con la misma cedula.");
        }
        
        if (!newUser.getCorreo().contains("@") || !newUser.getCorreo().contains(".com")){
            throw new BusinessLogicException("Su correo no es válido.");
        }
        
        if (newUser.getEdad() < 18){
            throw new BusinessLogicException("El estudiante no puede ser menor de edad");
        }

        
        if(newUser.getClave().length() < 8 && newUser.getClave().length() > 15){
            throw new BusinessLogicException("Su contraseña debe tener más de 8 caracteres y menos de 15.");
        }
      
        String celular = String.valueOf(newUser.getCelular());
        char[] numeros = celular.toCharArray();
        
        if (numeros.length != 10){
            throw new BusinessLogicException("Ingrese un celular válido para Colombia.");
        }
        
        if(!(newUser.getVivienda()!=null))
        {
            newUser.setEstadoArrendamiento(false);
        }
        return estudiantePersistence.create(newUser);
    }
     
}
