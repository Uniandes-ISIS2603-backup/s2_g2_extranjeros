/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.extranjeros.dtos.*;
import co.edu.uniandes.csw.extranjeros.ejb.ServicioLogic;
import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * <pre>Clase que implementa el recurso "servicios".
 * URL: /api/servicios
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "servicios".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author s.rodriguezm  
 * @version 1.0
 */
@Path("servicios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ServicioResource {

    @Inject
    ServicioLogic servicioLogic;
    
    /**
     * Convierte una lista de ServicioEntity a una lista de ServicioDetailDTO.
     *
     * @param entityList Lista de ServicioEntity a convertir.
     * @return Lista de ServicioDetailDTO convertida.
     * 
     */
    private List<ServicioDetailDTO> listEntity2DTO(List<ServicioEntity> entityList) {
        List<ServicioDetailDTO> list = new ArrayList<>();
        for (ServicioEntity entity : entityList) {
            list.add(new ServicioDetailDTO(entity));
        }
        return list;
    }
    /**
     * <h1>POST /api/servicios : Crear un servicio.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link ServicioDetailDTO}.
     * 
     * Crea un nuevo servicio con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo servicio .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el servicio.
     * </code>
     * </pre>
     * @param s {@link ServicioDetailDTO} - EL servicio que se desea guardar.
     * @return JSON {@link ServicioDetailDTO}  - El servicio guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el servicio.
     */
    @POST
    public ServicioDetailDTO createServicio(ServicioDetailDTO s) throws BusinessLogicException
    {
        return new ServicioDetailDTO(servicioLogic.createServicio(s.toEntity()));
    }
    /**
     * <h1>GET /api/servicios : Obtener todos los servicios.</h1>
     *
     * <pre>Busca y devuelve todos los servicios que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los servicios de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link ServicioDetailDTO} - Los servicios encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ServicioDetailDTO> getServicios()
    {
        return listEntity2DTO(servicioLogic.getServicios());
    }
    /**
     * <h1>GET /api/servicios/{id} : Obtener servicio por id.</h1>
     *
     * <pre>Busca el servicio con el id asociado recibido en la URL y lo devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el servicio correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un servicio con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del servicio que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ServicioDetailDTO} - El servicio buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra el servicio.
     */
    @GET
    @Path("{id: \\d+}")
    public ServicioDetailDTO getServicio(@PathParam("id") Long id) throws WebApplicationException
    {
        return new ServicioDetailDTO(servicioLogic.getServicio(id));
    }
    /**
     * <h1>PUT /api/servicios/{id} : Actualizar servicio con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ServicioDetailDTO}.
     *
     * Actualiza el servicio con el id recibido en la URL con la información que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el servicio con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un servicio con el id dado.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precondition Failes. No se puede actualizar el servicio con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del servicio que se desea actualizar. Este debe ser una cadena de dígitos.
     * @param s {@link ServicioDetailDTO} El servicio que se desea guardar.
     * @return JSON {@link ServicioDetailDTO} - El servicio guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra el libro a actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se puede actualizar el libro.
     */
    @PUT
    @Path("{id: \\d+}")
    public ServicioDetailDTO updateServicio(@PathParam ("id") Long id,ServicioDetailDTO s)throws WebApplicationException,BusinessLogicException
    {
        ServicioEntity entity=s.toEntity();
        entity.setId(id);
        if(servicioLogic.getServicio(id)==null)
            throw new WebApplicationException("El servicio no existe", 404);
       return new ServicioDetailDTO(servicioLogic.updateServicio(entity));
    }
    /**
     * <h1>DELETE /api/servicios/{id} : Borrar servicio por id.</h1>
     *
     * <pre>Borra el servicio con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el servicio correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un servicio con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del servicio que se desea borrar. Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se puede eliminar el servicio.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteServicio(@PathParam ("id") Long id)throws BusinessLogicException
    {
        if(servicioLogic.getServicio(id)==null)
            throw new WebApplicationException("El servicio no existe", 404);
        servicioLogic.deleteServicio(id);
    }
}
