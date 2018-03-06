/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
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
public class EstudianteLogic  {
    
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
    public void deleteEstudiante(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar estudiante con id={0}", id);
        estudiantePersistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar estudiante con id={0}", id);
    }
    
  
    /**
     * Actualiza la información de un estudiante
     * @param newUser Instancia de EstudianteEntityy con los nuevos datos.
     * @return Instancia de EstudianteEntityy con los datos actualizados.
     */
    public EstudianteEntity updateEstudiante (EstudianteEntity newUser) throws BusinessLogicException{
        
        LOGGER.info("Inicia el proceso de actualizar un estudiante en la plataforma");
        
       
        
        if(newUser.getClave().length() < 8 || newUser.getClave().length() > 12){
            throw new BusinessLogicException("Su contraseña debe tener más de 8 caracteres y menos de 12");
        }
        
         
        boolean encontradoNumero = false;
        char[] caracteres = newUser.getClave().toCharArray();
        for (int i = 0; i < caracteres.length; i++){
            
            String m = String.valueOf(caracteres[i]);
            if( Integer.class.isInstance(Integer.parseInt(m))){
                encontradoNumero = true;
            }
        } 
        
        if (encontradoNumero == false){
            throw new BusinessLogicException("Su clave debe contener al menos un numero");
        }
        
         if (!newUser.getCorreo().contains("@") || !newUser.getCorreo().contains(".com")){
            throw new BusinessLogicException("Su correo no es válido.");
        }
        
        String celular = String.valueOf(newUser.getCelular());
        char numeros[] = celular.toCharArray();
        
        // El numero de acuerdo a la Proveniencia se verificará después del cambio de concepto
        // de Usuario (hacerlo abstracto). Por ahora será de acuerdo a nuestro pais.
        if (numeros.length != 10){
            throw new BusinessLogicException("Ingrese un celular válido para Colombia.");
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
        
        LOGGER.log(Level.INFO, "Inicia el proceso de crear un estudiante en la plataforma");
       
        
        EstudianteEntity buscado = estudiantePersistence.findByUser(newUser.getUsuario());
        if (buscado != null){
            throw new BusinessLogicException("Hay un estudiante con el mismo usuario");
        }
        
        if (newUser.getEdad() < 18){
            throw new BusinessLogicException("El Usuario no puede ser menor de edad");
        }
        
        if(newUser.getClave().length() < 8 || newUser.getClave().length() > 12){
            throw new BusinessLogicException("Su contraseña debe tener más de 8 caracteres y menos de 12");
        }
        
         
        boolean encontradoNumero = false;
        char[] caracteres = newUser.getClave().toCharArray();
        for (int i = 0; i < caracteres.length; i++){
            
            String m = String.valueOf(caracteres[i]);
            if( Integer.class.isInstance(Integer.parseInt(m))){
                encontradoNumero = true;
            }
        } 
        
        if (encontradoNumero == false){
            throw new BusinessLogicException("Su clave debe contener al menos un numero");
        }
        
         if (!newUser.getCorreo().contains("@") || !newUser.getCorreo().contains(".com")){
            throw new BusinessLogicException("Su correo no es válido.");
        }
        
        String celular = String.valueOf(newUser.getCelular());
        char numeros[] = celular.toCharArray();
        
        // El numero de acuerdo a la Proveniencia se verificará después del cambio de concepto
        // de Usuario (hacerlo abstracto). Por ahora será de acuerdo a nuestro pais.
        if (numeros.length != 10){
            throw new BusinessLogicException("Ingrese un celular válido para Colombia.");
        }
        
        
        return estudiantePersistence.create(newUser);
    
   
     }
     
     
    
    
    
    
}
