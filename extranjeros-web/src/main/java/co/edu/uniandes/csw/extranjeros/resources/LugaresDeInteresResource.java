/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.dtos.*;
import co.edu.uniandes.csw.extranjeros.ejb.LugaresDeInteresLogic;
import co.edu.uniandes.csw.extranjeros.entities.LugaresDeInteresEntity;
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
 * <pre> Clase que implementa el recurso "lugares de interes".
 * URL: /api/lugaresdeinteres
 * </pre>
 * 
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "lugaresdeinteres".</i>
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

@Path("lugares")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LugaresDeInteresResource {
    
    //---------------------------------------------------
    // Inject: Logica
    //---------------------------------------------------
    
    @Inject 
    private LugaresDeInteresLogic logica;
    
    //---------------------------------------------------
    // Lista de conversion
    //---------------------------------------------------
    
    
    private List<LugaresDeInteresDetailDTO> listEntity2DTO (List<LugaresDeInteresEntity> entityList) {
        List<LugaresDeInteresDetailDTO> list = new ArrayList<>();
        for (LugaresDeInteresEntity entity : entityList) {
            list.add(new LugaresDeInteresDetailDTO(entity));
        }
        return list;
    }
    
     /**
     * <h1> GET /api/lugaresdeinteres/{id} : Obtener lugar de interes por id.</h1>
     * <pre> Busca el Lugar de Interes al cual corresponde la ID ingresada en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el Lugar de Interes correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un lugar de interes con el id dado.
     * </code> 
     * </pre>
     * 
     * @param id Identificador del Lugar de Interes que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link LugaresDeInteresDetailDTO} - el Lugar de Interes buscado.
     */
    @GET
   @Path("{id: \\d+}")
   public LugaresDeInteresDetailDTO getLugar(@PathParam("id")Long id){
        LugaresDeInteresEntity entidadBuscada = logica.getLugarDeInteres(id);
        if(entidadBuscada == null){
            throw new WebApplicationException("El recurso /lugaresdeinteres/" + id + " no existe.", 404);
        }
        return new LugaresDeInteresDetailDTO(entidadBuscada);
    }
   /**
     * <h1> GET /api/lugaresdeinteres : Obtener todos los lugares de interes. </h1>
     * <pre> Busca y devuelve todos los lugares de interes que existen dentro de la aplicacion. 
     * Codigos de respuesta:
     * 
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los lugares de interes de la aplicacion. 
     * </code> 
     * </pre>
     * 
     * @return JSONArray {@link LugaresDeInteresDetailDTO} - Los usuarios que existen en la aplicación. Si no hay ningun lugar de interes 
     * (ninguno se ha registrado) se retorna una lista vacía.
     */
   @GET
    public List<LugaresDeInteresDetailDTO> getLugares()
    {
        return listEntity2DTO(logica.getLugaresDeInteres());
    }
    /**
     * <h1> POST /api/lugaresdeinteres : Crear un lugar de interes. </h1>
     * <pre> Cuerpo de petición: JSON {@link LugaresDeInteresDetailDTO}.
     * 
     * Crea un nuevo lugar de interes con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó un nuevo lugar de interes.
     * </code>
     * 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el Lugar de Interes ingresado.
     * </code>
     * </pre>
     * 
     * @param lugar {@link LugaresDeInteresDetailDTO} - el Lugar de Interes que se desea guardar.
     * @return JSON {@link LugaresDeInteresDetailDTO}  - el Lugar de Interes guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el Lugar de Interes.
     */
    @POST
    public LugaresDeInteresDetailDTO createLugar(LugaresDeInteresDetailDTO lugar)throws BusinessLogicException
    {
        return new LugaresDeInteresDetailDTO(logica.createLugarDeInteres(lugar.toEntity()));
    }
    /**
     * <h1> PUT /api/lugaresdeinteres/{id} : Actualiza un lugar de interes asociado con el ID asociado/dado. </h1>
     * <pre> Cuerpo de petición: JSON {@link LugaresDeInteresDetailDTO}.
     * 
     * Actualiza el Lugar de Interes con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó el Lugar de Interes con el ID dado y con la información enviada desde el Body como parámetro. Retorna un objeto identico.</code> 
     * 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un lugar de interes con el id dado.
     * </code> 
     * </pre>
     * 
     * @param id Identificador del Lugar de Interes que se desea actualizar. Este debe ser una cadena de dígitos.
     * @param lugar {@link LugaresDeInteresDetailDTO} el Lugar de Interes que se desea guardar.
     * @return JSON {@link LugaresDeInteresDetailDTO} - el Lugar de Interes guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el Lugar de Interes porque ya existe uno con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public LugaresDeInteresDetailDTO updateLugar(@PathParam("id") Long id, LugaresDeInteresDetailDTO lugar) throws BusinessLogicException
    {
        lugar.setId(id);
        LugaresDeInteresEntity entidad = logica.getLugarDeInteres(id);
        if(entidad == null){
            throw new WebApplicationException("El recurso /lugaresdeinteres/" + id + " no existe.", 404);
        }
        return new LugaresDeInteresDetailDTO(logica.updateLugarDeInteres(lugar.toEntity()));
    }
    /**
     * <h1> DELETE /api/lugaresdeinteres/{id} : Borrar lugar de interes por id.</h1>
     * <pre> Borra el Lugar de Interes con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta: <br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el Lugar de Interes correspondiente al id dado. </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un lugar de interes con el ID dado.
     * </code>
     * </pre>
     * @param id Identificador del Lugar de Interes que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLugar(@PathParam("id") Long id)
    {
        LugaresDeInteresEntity entidadAEliminar = logica.getLugarDeInteres(id);
        if(entidadAEliminar == null){
            throw new WebApplicationException("El recurso /lugaresdeinteres/" + id + " no existe.", 404);
        }
        logica.deleteLugarDeInteres(id);
    }
    
}
