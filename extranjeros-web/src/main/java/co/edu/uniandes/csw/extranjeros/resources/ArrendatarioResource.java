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
 * <pre> Clase que implementa el recurso "arrendatarios".
 * URL: /api/arrendatarios
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
@Path("arrendatarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ArrendatarioResource {
    
    
    /**
     * <h1> GET /api/arrendatarios/{id} : Obtener arrendatario por ID.</h1>
     * <pre> Busca el arrendatario al cual corresponde la ID ingresada en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el arrendatario correspondiente al ID.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un arrendatario con el id dado.
     * </code> 
     * </pre>
     * 
     * @param id Identificador del arrendatario que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ArrendatarioDetailDTO} - El arrendatario buscado.
     */
    @GET
    @Path("{id: \\d+}")
    public ArrendatarioDetailDTO getArrendatario(@PathParam("id")Long id){
        return null;
    }
    
    
    /**
     * <h1> GET /api/arrendatarios : Obtener todas los arrendatarios. </h1>
     * <pre> Busca y devuelve todas los arrendatarios que existen dentro de la aplicacion. 
     * Codigos de respuesta:
     * 
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los arrendatarios de la aplicacion. 
     * </code> 
     * </pre>
     * 
     * @return JSONArray {@link ArrendatarioDetailDTO} - Los arrendatarios que existen en la aplicación. Si no hay ningún arrendatario 
     * (nadie se ha registrado como arrendatario) se retorna una lista vacía.
     */
    @GET
    public List<ArrendatarioDetailDTO> getArrendatarios(){
        List<ArrendatarioDetailDTO> retorno = new ArrayList<>();
        return retorno;
    }
    
    
    /**
     * <h1> POST /api/arrendatarios : Crear un Arrendatario. </h1>
     * <pre> Cuerpo de petición: JSON {@link ArrendatarioDetailDTO}.
     * 
     * Crea un nuevo arrendatario con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó un nuevo arrendatario.
     * </code>
     * 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el arrendatario ingresado.
     * </code>
     * </pre>
     * 
     * @param arrendatarioElement {@link ArrendatarioDetailDTO} - El arrendatario que se desea guardar.
     * @return JSON {@link ArrendatarioDetailDTO}  - El arrendatario guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el arrendatario.
     */
    @POST
    public ArrendatarioDetailDTO createArrendatario(ArrendatarioDetailDTO arrendatarioElement) throws BusinessLogicException{
        return arrendatarioElement;
    }
    
    
    /**
     * <h1> PUT /api/arrendatarios/{id} : Actualiza un arrendatario asociado con el ID dado. </h1>
     * <pre> Cuerpo de petición: JSON {@link ArrendatarioDetailDTO}.
     * 
     * Actualiza el arrendatario con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó el arrendatario con el ID dado y con la información enviada desde el Body como parámetro. Retorna un objeto identico.</code> 
     * 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un arrendatario con el id dado.
     * </code> 
     * </pre>
     * 
     * @param id Identificador del arrendatario que se desea actualizar. Este debe ser una cadena de dígitos.
     * @param pArrendatario {@link ArrendatarioDetailDTO} El Usuario que se desea guardar.
     * @return JSON {@link ArrendatarioDetailDTO} - El Usuario guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el Usuario porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public ArrendatarioDetailDTO updateArrendatario (@PathParam ("id") Long id, ArrendatarioDetailDTO pArrendatario) throws BusinessLogicException {
        return pArrendatario;
    }
    
    
    /**
     * <h1> DELETE /api/arrendatarios/{id} : Borrar arrendatario por id.</h1>
     * <pre> Borra el arrendatario con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta: <br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el arrendatario correspondiente al id dado. </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un arrendatario con el ID dado.
     * </code>
     * </pre>
     * @param id Identificador del arrendatario que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUser(@PathParam ("id") Long id){
    }
}
