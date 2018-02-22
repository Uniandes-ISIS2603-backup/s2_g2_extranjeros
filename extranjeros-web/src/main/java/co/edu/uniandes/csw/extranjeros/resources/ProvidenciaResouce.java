/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;


import co.edu.uniandes.csw.extranjeros.dtos.ProvidenciaDetailDTO;
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
 /**
 * <pre>Clase que implementa el recurso "Providencia".
 * URL: /api/evento
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
@Path("providencia")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProvidenciaResouce {
    
    
    /**
     * <h1>POST /api/providencia : Crear una providencia .</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link ProvidenciaDTO}.
     * 
     * Crea una nueva providencia con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva providencia .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la providencia.
     * </code>
     * </pre>
     * @param providencia  {@link ProvidenciaDetailDTO} - La providencia que se desea guardar.
     * @return JSON {@link ProvidenciaDetailDTO}  - La providencia guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la providencia.
     */
    @POST
     public ProvidenciaDetailDTO createEstudiante(ProvidenciaDetailDTO providencia) throws BusinessLogicException{
        return providencia;
    }
    
     /**
     * <h1>GET /api/providencia : Obtener todas las providencias.</h1>
     * 
     * <pre>Busca y devuelve todos las providencias que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las providencias de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link ProvidenciaDetailDTO} - Las providencias encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
     @GET
    public List<ProvidenciaDetailDTO> getEstudiantes() {
        return new ArrayList<>();
    }
    
     /**
     * <h1>GET /api/providencia/{id} : Obtener providencia por id.</h1>
     * 
     * <pre>Busca la providencia con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la providencia correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una providencia con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la providencia que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ProvidenciaDetailDTO} - El evento buscada
     */
    @GET
    @Path("{id: \\d+}")
    public ProvidenciaDetailDTO getEstudiante(@PathParam("id") Long id) {
        return null;
    }
    
    /**
     * <h1>PUT /api/providencia/{id} : Actualizar providencia con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ProvidenciaDetailDTO}.
     * 
     * Actualiza la providencia con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la providencia con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe providencia con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador la providencia que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param providencia {@link ProvidenciaDetailDTO} El evento que se desea guardar.
     * @return JSON {@link ProvidenciaDetailDTO} - La providencia guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la providencia porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
     public ProvidenciaDetailDTO updateEstudiante(@PathParam("id") Long id, ProvidenciaDetailDTO providencia) throws BusinessLogicException {
        return providencia;
    }
     
      /**
     * <h1>DELETE /api/providencia/{id} : Borrar providencia por id.</h1>
     * 
     * <pre>Borra la providencia con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la providencia correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una providencia con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la providencia que se desea borrar. Este debe ser una cadena de dígitos.
     */
     @DELETE
    @Path("{id: \\d+}")
     public void deleteEstudiante(@PathParam("id") Long id) {
        
    }
    
}
