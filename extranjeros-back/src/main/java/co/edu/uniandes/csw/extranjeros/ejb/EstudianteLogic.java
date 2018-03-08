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
            throw new BusinessLogicException("Su contraseña debe tener más de 8 caracteres y menos de 20");
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
        
        if(newUser.getClave().length() < 8 || newUser.getClave().length() >17){
            throw new BusinessLogicException("Su contraseña debe tener más de 8 caracteres y menos de 17");
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
     
    public ProvidenciaEntity getProvidencia(Long userID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar la providencia asociada del estudiante con id = {0}");
        return getEstudiante(userID).getProvidencia();
    }
     
   public  ProvidenciaEntity updateProvidencia(Long userID,ProvidenciaEntity nuevaProvidencia )throws BusinessLogicException
   {
       LOGGER.log(Level.INFO, "Inicia el proceso para actualizar la providencia asociada del estudiante con id = {0}");
       if(Objects.equals(getEstudiante(userID).getProvidencia().getId(), nuevaProvidencia.getId()))
       {
           throw new BusinessLogicException("Se esta asignando la misma providencia que tenía el estudiante ");
       }
       
       getEstudiante(userID).setProvidencia(nuevaProvidencia);
       return  getEstudiante(userID).getProvidencia();
   }
    
  public UniversidadEntity getUniversidad(Long userID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar la universidad asociada del estudiante con id = {0}");
        return getEstudiante(userID).getUniversidad();
    }
     
   public  UniversidadEntity updateUniversidad(Long userID,UniversidadEntity universidad)throws BusinessLogicException
   {
       LOGGER.log(Level.INFO, "Inicia el proceso para actualizar la universidad asociada del estudiante con id = {0}");
       if(Objects.equals(getEstudiante(userID).getUniversidad().getId(), universidad.getId()))
       {
           throw new BusinessLogicException("Se esta asignando la misma universidad que tenía el estudiante ");
       }
       getEstudiante(userID).setUniversidad(universidad);
       return  getEstudiante(userID).getUniversidad();
   }
    
    public TarjetaEntity getTarjeta(Long userID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar la tarjeta asociada del estudiante con id = {0}");
        return getEstudiante(userID).getTarjeta();
    }
     
   public  TarjetaEntity updateTarjeta(Long userID,TarjetaEntity tarjeta)throws BusinessLogicException
   {
       LOGGER.log(Level.INFO, "Inicia el proceso para actualizar la providencia asociada del estudiante con id = {0}");
       if(Objects.equals(getEstudiante(userID).getTarjeta().getId(), tarjeta.getId()))
       {
           throw new BusinessLogicException("Se esta asignando la misma vivienda que tenía el estudiante ");
       }
       getEstudiante(userID).setTarjeta(tarjeta);
       return  getEstudiante(userID).getTarjeta();
   }
   
   
    public ViviendaEntity getVivienda(Long userID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar la vivienda asociada del estudiante con id = {0}");
        return getEstudiante(userID).getVivienda();
    }
     
   public  ViviendaEntity updateVivienda(Long userID,ViviendaEntity vivienda)throws BusinessLogicException
   {
       LOGGER.log(Level.INFO, "Inicia el proceso para actualizar la providencia asociada del estudiante con id = {0}");
       if(Objects.equals(getEstudiante(userID).getVivienda().getId(), vivienda.getId()))
       {
           throw new BusinessLogicException("Se esta asignando la misma vivienda que tenía el estudiante ");
       }
       getEstudiante(userID).setVivienda(vivienda);
       return  getEstudiante(userID).getVivienda();
   }
  
   public List<EventoEntity> getEventosCreados(Long userID)
   {
       LOGGER.log(Level.INFO, "Inicia el proceso para consultar los eventos creados del estudiante con id = {0}");
       return getEstudiante(userID).getEventosCreados();
   }
   
   public EventoEntity addEventoCreado(Long userID, EventoEntity evento) throws BusinessLogicException
   {
        LOGGER.log(Level.INFO, "Inicia el proceso para agregar un evento a creados del estudiante con id = {0}");
        if(!getEstudiante(userID).isEstadoArrendamiento())
        {
            throw new BusinessLogicException("El estudiante no puede crear un evento si no se encuentra activo ");
        }
        getEstudiante(userID).addEventosCreados(evento);
        return getEstudiante(userID).getEventosCreados().get(getEstudiante(userID).getEventosCreados().size() -1);
    }
    
   public List<EventoEntity> getEventosInvitado(Long userID)
   {
       LOGGER.log(Level.INFO, "Inicia el proceso para consultar los eventos a los que asistirá del estudiante con id = {0}");
       return getEstudiante(userID).getEventosInvitado();
   }
   
   public EventoEntity addEventoInvitado(Long userID, EventoEntity evento) throws BusinessLogicException
   {
        LOGGER.log(Level.INFO, "Inicia el proceso para agregar un evento a los que asistirá del estudiante con id = {0}");
        if(evento.isPrivado())
        {
            if (getEstudiante(userID).getProvidencia() == evento.getResponsableEventoP().getProvidencia())
            {
            throw new BusinessLogicException("El evento es privado y el estudiante no puede asistir");
            }
        }
        getEstudiante(userID).addEventosInvitado(evento);
        return getEstudiante(userID).getEventosInvitado().get(getEstudiante(userID).getEventosInvitado().size() -1);
    }
   
}
