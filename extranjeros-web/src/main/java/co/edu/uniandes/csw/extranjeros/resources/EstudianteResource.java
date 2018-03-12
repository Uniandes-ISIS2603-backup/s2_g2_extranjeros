/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.dtos.EstudianteDetailDTO;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 *
 * @author am.quintero12
 */
/**
 * <pre>Clase que implementa el recurso "Estudiante".
 * URL: /api/estudiante
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "Evento".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author ISIS2603  
 * @version 1.0
 */
@Path("estudiante")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EstudianteResource {
    
    /**
     * <h1>POST /api/estudiante : Crear un estudiante.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link EventoDTO}.
     * 
     * Crea un nuevo estudiante con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo evento .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el estudiante.
     * </code>
     * </pre>
     * @param estudiante {@link EstudianteDetailDTO} - El evento que se desea guardar.
     * @return JSON {@link EstudianteDetailDTO}  - El estudiante guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la tarjeta.
     */
    @POST
    public EstudianteDetailDTO createEstudiante(EstudianteDetailDTO estudiante) throws BusinessLogicException{
        return estudiante;
    }
    
     /**
     * <h1>GET /api/estudiante : Obtener todas los estudiantes.</h1>
     * 
     * <pre>Busca y devuelve todos los estudiantes que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los estudiantes de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link EstudianteDetailDTO} - Los Estudiantes encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EstudianteDetailDTO> getEstudiantes() {
        return new ArrayList<>();
    }
    
     /**
     * <h1>GET /api/estudiante/{id} : Obtener estudiantes por id.</h1>
     * 
     * <pre>Busca el estudiante con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el estudiante correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un estudiante con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del estudiante que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link EstudianteDetailDTO} - El estudiante buscado
     */
    @GET
    @Path("{id: \\d+}")
    public EstudianteDetailDTO getEstudiante(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/estudiante/{id} : Actualizar estudiante con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link EstudianteDetailDTO}.
     * 
     * Actualiza el estudiante con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el estudiante con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un estudiante con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del estudiante que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param estudiante {@link EstudianteDetailDTO} El evento que se desea guardar.
     * @return JSON {@link EstudianteDetailDTO} - El Evento guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el estudiante porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
     public EstudianteDetailDTO updateEstudiante(@PathParam("id") Long id, EstudianteDetailDTO estudiante) throws BusinessLogicException{
        return estudiante;
    }
     
      /**
     * <h1>DELETE /api/estudiante/{id} : Borrar estudiante por id.</h1>
     * 
     * <pre>Borra el estudiante con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el estudiante correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un estudiante con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del estudiante que se desea borrar. Este debe ser una cadena de dígitos.
     */
     @DELETE
    @Path("{id: \\d+}")
     public void deleteEstudiante(@PathParam("id") Long id) {
        
    }
    
}
