/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.dtos.*;
import co.edu.uniandes.csw.extranjeros.ejb.ArrendatarioLogic;
import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
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
    
    
    //---------------------------------------------------
    // Inject: Logica
    //---------------------------------------------------
    
    @Inject 
    private ArrendatarioLogic logica;
    
    //---------------------------------------------------
    // Lista de conversion
    //---------------------------------------------------
    
    
    private List<ArrendatarioDetailDTO> listEntity2DTO (List<ArrendatarioEntity> entityList) {
        List<ArrendatarioDetailDTO> list = new ArrayList<>();
        for (ArrendatarioEntity entity : entityList) {
            list.add(new ArrendatarioDetailDTO(entity));
        }
        return list;
    }

    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------

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
        ArrendatarioEntity entidadBuscada = logica.getArrendatario(id);
        if(entidadBuscada == null){
            throw new WebApplicationException("El recurso /arrendatarios/" + id + " no existe.", 404);
        }
        return new ArrendatarioDetailDTO(entidadBuscada);
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
        return listEntity2DTO(logica.getArrendatarios());
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
        return new ArrendatarioDetailDTO(logica.createArrendatario(arrendatarioElement.toEntity()));
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
    @Path("{idArr: \\d+}")
    public ArrendatarioDetailDTO updateArrendatario (@PathParam ("idArr") Long id, ArrendatarioDetailDTO pArrendatario) throws BusinessLogicException {
        
        pArrendatario.setId(id);
        ArrendatarioEntity entidadActu = logica.getArrendatario(id);
        if(entidadActu == null){
            throw new WebApplicationException("El recurso /arrendatarios/" + id + " no existe.", 404);
        }
        return new ArrendatarioDetailDTO(logica.updateArrendatario(pArrendatario.toEntity()));
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
        ArrendatarioEntity entidadEliminar = logica.getArrendatario(id);
        if(entidadEliminar == null){
            throw new WebApplicationException("El recurso /arrendatarios/" + id + " no existe.", 404);
        }
        logica.deleteArrendatario(id);
    }
    
    /**
     * Conexión con el servicio de cuentas bancarias asociadas a un arrendatario. {@link CuentaBancariaResource}
     * Este método conecta la ruta de /arrendatarios con las rutas de /cuentasBancarias que dependen
     * del arrendatario, es una redirección al servicio que maneja el segmento de la 
     * URL que se encarga de las cuentas bancarias.
     * @param arrendatarioId El ID del arrendatario con respecto al cual se accede al servicio.
     * @return El servicio de cuentas de banco para ese arrendatario en paricular.
     */
    @Path("{idArrendatario: \\d+}/cuentasBancarias")
    public Class<CuentaBancariaResource> getCuentaBancariaResource(@PathParam("idArrendatario") Long arrendatarioId) {
        ArrendatarioEntity entity = logica.getArrendatario(arrendatarioId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /arrendatarios/" + arrendatarioId + "/cuentasBancarias no existe.", 404);
        }
        return CuentaBancariaResource.class;
    }   
    
    /**
     * Conexión con el servicio de facturas para un arrendatario. {@link ArrendatarioFacturasResource}
     * Este método conecta la ruta de /arrendatarios con las rutas de /facturas que dependen
     * del arrendatario, es una redirección al servicio que maneja el segmento de la 
     * URL que se encarga de las facturas.
     * @param arrendatarioID El ID del arrendatario con respecto al cual se accede al servicio.
     * @return El servicio de Facturas para ese arrendatario en paricular.
     */
    @Path("{arrendatarioID: \\d+}/facturas")
    public Class<ArrendatarioFacturasResource> getArrendatarioFacturasResource(@PathParam("arrendatarioID") Long arrendatarioID) {
        ArrendatarioEntity entity = logica.getArrendatario(arrendatarioID);
        if (entity == null) {
            throw new WebApplicationException("El recurso /arrendatarios/" + arrendatarioID + "/facturas no existe.", 404);
        }
        return ArrendatarioFacturasResource.class;
    }
    
    /**
     * Conexión con el servicio de viviendas para un arrendatario. {@link BookAuthorsResource}
     * Este método conecta la ruta de /arrendatarios con las rutas de /viviendas que dependen
     * del arrendatario, es una redirección al servicio que maneja el segmento de la 
     * URL que se encarga de las viviendas.
     * @param arrendatarioID El ID del arrendatario con respecto al cual se accede al servicio.
     * @return El servicio de Viviendas para ese arrendatario en paricular.
     */
    @Path("{arrendatarioID: \\d+}/viviendas")
    public Class<ArrendatarioViviendasResource> getArrendatarioViviendasResource(@PathParam("arrendatarioID") Long arrendatarioID) {
        ArrendatarioEntity entity = logica.getArrendatario(arrendatarioID);
        if (entity == null) {
            throw new WebApplicationException("El recurso /arrendatarios/" + arrendatarioID + "/viviendas no existe.", 404);
        }
        return ArrendatarioViviendasResource.class;
    } 
}
