/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.dtos.*;
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
 * <pre> Clase que implementa el recurso "universidades".
 * URL: /api/universidades
 * </pre>
 * 
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "cities".</i>
 *
 * <h2> Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * 
 * @author Oliver Amaya  
 */

@Path("universidades")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class UniversidadResource {
    /**
     * <h1> GET /api/universidades/{id} : Obtener universidad por id.</h1>
     * <pre> Busca la Universidad al cual corresponde la ID ingresada en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la Universidad correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una Universidad con el id dado.
     * </code> 
     * </pre>
     * 
     * @param id Identificador de la Universidad que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link UniversidadDetailDTO} - La universidad buscada.
     */
   @GET
   @Path("{id: \\d+}")
   public UniversidadDetailDTO getUniversidad(@PathParam("id")Long id){
        return null;
    }
   /**
     * <h1> GET /api/universidades : Obtener todas las universidades. </h1>
     * <pre> Busca y devuelve todas las universidades que existen dentro de la aplicacion. 
     * Codigos de respuesta:
     * 
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las universidades de la aplicacion. 
     * </code> 
     * </pre>
     * 
     * @return JSONArray {@link UniversidadDetailDTO} - Los usuarios que existen en la aplicación. Si no hay ninguna universidad 
     * (ninguna se ha registrado) se retorna una lista vacía.
     */
   @GET
    public List<UniversidadDetailDTO> getUniversidades()
    {
        return new ArrayList<>();
    }
    
    /**
     * <h1> POST /api/universidades : Crear una universidad. </h1>
     * <pre> Cuerpo de petición: JSON {@link UniversidadDetailDTO}.
     * 
     * Crea una nueva universidad con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó una nueva universidad.
     * </code>
     * 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la Universidad ingresada.
     * </code>
     * </pre>
     * 
     * @param userElement {@link UniversidadDetailDTO} - La Universidad que se desea guardar.
     * @return JSON {@link UniversidadDetailDTO}  - La Universidad guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la universidad.
     */
    
    @POST
    public UniversidadDetailDTO createUniversidad(UniversidadDetailDTO universidad)throws BusinessLogicException
    {
        return universidad;
    }
    
    /**
     * <h1> PUT /api/universidades/{id} : Actualiza una Universidad asociada con el ID asociado/dado. </h1>
     * <pre> Cuerpo de petición: JSON {@link UniversidadDetailDTO}.
     * 
     * Actualiza la universidad con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la Universidad con el ID dado y con la información enviada desde el Body como parámetro. Retorna un objeto identico.</code> 
     * 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una Universidada con el id dado.
     * </code> 
     * </pre>
     * 
     * @param id Identificador de la Universidad que se desea actualizar. Este debe ser una cadena de dígitos.
     * @param userUp {@link UniversidadDetailDTO} La Universidad que se desea guardar.
     * @return JSON {@link UniversidadDetailDTO} - La Universidad guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la Universidad porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public UniversidadDetailDTO updateUniversidad(@PathParam("id") Long id, UniversidadDetailDTO universidad)
    {
        return null;
    }
    /**
     * <h1> DELETE /api/universidades/{id} : Borrar Universidad por id.</h1>
     * <pre> Borra la Universidad con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta: <br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la Universidad correspondiente al id dado. </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una Universidad con el ID dado.
     * </code>
     * </pre>
     * @param id Identificador de la Universidad que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUniversidad(@PathParam("id") Long id)
    {
    }
    
}
