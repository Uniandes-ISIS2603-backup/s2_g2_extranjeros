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
 * <pre> Clase que implementa el recurso "usuarios".
 * URL: /api/usuarios
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
 * @author jr.pacheco10  
 */

@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class UsuarioResource {
    
    /**
     * <h1> GET /api/usuarios/{id} : Obtener usuario por id.</h1>
     * <pre> Busca el Usuario al cual corresponde la ID ingresada en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el Usuario correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un Usuario con el id dado.
     * </code> 
     * </pre>
     * 
     * @param id Identificador del Usuario que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link UsuarioDetailDTO} - El usuario buscado.
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUser(@PathParam("id")Long id){
        return null;
    }

    
    /**
     * <h1> GET /api/usuarios : Obtener todas las ciudades. </h1>
     * <pre> Busca y devuelve todas los usuarios que existen dentro de la aplicacion. 
     * Codigos de respuesta:
     * 
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los usuarios de la aplicacion. 
     * </code> 
     * </pre>
     * 
     * @return JSONArray {@link UsuarioDetailDTO} - Los usuarios que existen en la aplicación. Si no hay ningún usuario 
     * (nadie se ha registrado) se retorna una lista vacía.
     */
    @GET
    public List<UsuarioDetailDTO> getUsers(){
        List<UsuarioDetailDTO> retorno = new ArrayList<>();
        return retorno;
    }
    
    
    /**
     * <h1> POST /api/usuarios : Crear un Usuario. </h1>
     * <pre> Cuerpo de petición: JSON {@link UsuarioDetailDTO}.
     * 
     * Crea un nuevo Usuario con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó un nuevo Usuario.
     * </code>
     * 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el Usuario ingresado.
     * </code>
     * </pre>
     * 
     * @param userElement {@link UsuarioDetailDTO} - El Usuario que se desea guardar.
     * @return JSON {@link UsuarioDetailDTO}  - El Usuario guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el Usuario.
     */
    @POST
    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO userElement) throws BusinessLogicException{
        return userElement;
    }

    /**
     * <h1> PUT /api/usuarios/{id} : Actualiza un Usuario asociado con el ID asociado/dado. </h1>
     * <pre> Cuerpo de petición: JSON {@link UsuarioDetailDTO}.
     * 
     * Actualiza el usuario con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó el Usuario con el ID dado y con la información enviada desde el Body como parámetro. Retorna un objeto identico.</code> 
     * 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Usuario con el id dado.
     * </code> 
     * </pre>
     * 
     * @param id Identificador del Usuario que se desea actualizar. Este debe ser una cadena de dígitos.
     * @param userUp {@link UsuarioDetailDTO} El Usuario que se desea guardar.
     * @return JSON {@link UsuarioDetailDTO} - El Usuario guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el Usuario porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailDTO updateUser(@PathParam ("id") Long id, UsuarioDetailDTO userUp) throws BusinessLogicException {
        return userUp;
    }
    
    
    /**
     * <h1> DELETE /api/usuarios/{id} : Borrar Usuario por id.</h1>
     * <pre> Borra el Usuario con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta: <br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el Usuario correspondiente al id dado. </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Usuario con el ID dado.
     * </code>
     * </pre>
     * @param id Identificador del Usuario que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUser(@PathParam ("id") Long id){
    }
}
