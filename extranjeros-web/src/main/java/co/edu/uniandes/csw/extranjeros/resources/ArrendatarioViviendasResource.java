/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.dtos.ViviendaDetailDTO;
import co.edu.uniandes.csw.extranjeros.ejb.ArrendatarioLogic;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <pre>Clase que implementa el recurso "arrendatarios/viviendas".
 * URL: /api/arrendatarios/{arrendatarioID}/viviendas
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * que el servicio {@link BookResource} define este servicio de forma relativa 
 * con la ruta "authors" con respecto un libro.</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author jr.pacheco10
 * @version 1.0
 */
@Path("arrendatarios/{arrendatarioID: \\d+}/viviendas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArrendatarioViviendasResource {
    
    //------------------------------------------
    // Inject: Logica - Arrendatario
    //------------------------------------------
    @Inject
    private ArrendatarioLogic logic;
    
    
    //------------------------------------------
    // Conversion tipo de Clases:
    //------------------------------------------
    
    /**
     * Convierte una lista de ViviendaEntity a una lista de ViviendaDetailDTO.
     * @param entityList Lista de ViviendaEntity a convertir.
     * @return Lista de ViviendaDetailDTO convertida.
     */
    private List<ViviendaDetailDTO> viviendasListEntity2DTO(List<ViviendaEntity> entityList) {
        List<ViviendaDetailDTO> list = new ArrayList<>();
        for (ViviendaEntity entity : entityList) {
            list.add(new ViviendaDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de ViviendaDetailDTO a una lista de ViviendaEntity.
     * @param dtos Lista de ViviendaDetailDTO a convertir.
     * @return Lista de ViviendaEntity convertida.
     */
    private List<ViviendaEntity> viviendasListDTO2Entity(List<ViviendaDetailDTO> dtos) {
        List<ViviendaEntity> list = new ArrayList<>();
        for (ViviendaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    //------------------------------------------
    // Servicios de la aplicacion
    //------------------------------------------
    
    /**
     * <h1>GET /api/arrendatarios/{arrendatarioID}/viviendas : Obtener todos las viviendas de un Arrendatario.</h1>
     * <pre> Busca y devuelve todos las viviendas que existen en un Arrendatario.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos las viviendas del arrendatario.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un arrendatario con el id dado.
     * </code>
     * @param arrendID El ID del arrendatario del cual se buscan las viviendas.
     * @return JSONArray {@link ViviendaDetailDTO} - Las facturas encontradas en el arrendatario.
     */
    @GET
    public List<ViviendaDetailDTO> getViviendas(@PathParam("arrendatarioID") Long arrendID) {
        return viviendasListEntity2DTO(logic.getViviendas(arrendID));
    }
    
    /**
     * <h1>GET /api/arrendatarios/{arrendatarioID}/viviendas/{viviendaId}: Obtener una vivienda de un arrendatario.</h1>
     * <pre>Busca y devuelve la vivienda con el ID recibido en la URL, relativo a un arrendatario.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la vivienda del arrendatario.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe el objeto.
     * </code>
     * @param viviendaId El ID de la vivienda que se busca.
     * @param arrendID El ID del arrendatario del cual se busca la factura.
     * @return {@link ViviendaDetailDTO} - La vivienda encontrada en el arrendatario.
     */
    @GET
    @Path("{viviendaId: \\d+}")
    public ViviendaDetailDTO getVivienda(@PathParam("arrendatarioID") Long arrendID, @PathParam("viviendaId") Long viviendaId) throws BusinessLogicException {
        return new ViviendaDetailDTO(logic.getVivienda(arrendID, viviendaId));
    }
    
    /**
     * <h1>POST /api/arrendatarios/{arrendatarioID}/viviendas/{viviendaId}: Asociar una vivienda a un arrendatario.</h1>
     * <pre> Asocia una vivienda existente con un arrendatario existente.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Asoció el objeto.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el recurso.
     * </code>
     * </pre>
     * @param arrendID El ID del arrendatario que se va a asociar
     * @param viviendaId El ID de la vivienda al cual se le va a asociar el arrendatario
     * @return JSON {@link FacturaDetailDTO} - La factura asociado.
     */
    @POST
    @Path("{viviendaId: \\d+}")
    public ViviendaDetailDTO addViviendas(@PathParam("arrendatarioID") Long arrendID, @PathParam("viviendaId") Long viviendaId) throws BusinessLogicException {
        return new ViviendaDetailDTO(logic.createViviendaIn(arrendID, viviendaId));
    }

    /**
     * <h1>PUT /api/arrendatarios/{arrendatarioID}/viviendas/: Actualizar las viviendas de un arrendatario.</h1>
     * <pre>Cuerpo de petición: JSONArray {@link ViviendaDetailDTO}.
     * Actualiza la lista.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la lista.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la lista
     * </code>
     * </pre>
     * @param arrendatarioID El ID del arrendatario al cual se le va a asociar la lista de viviendas.
     * @param viviendas JSONArray {@link ViviendaDetailDTO} - La lista de viviendas que se desea guardar.
     * @return JSONArray {@link ViviendaDetailDTO}  - La lista actualizada.
     */
    @PUT
    public List<ViviendaDetailDTO> replaceVivienda(@PathParam("arrendatarioID") Long arrendatarioID, List<ViviendaDetailDTO> viviendas) {
        return viviendasListEntity2DTO(logic.updateViviendas(arrendatarioID, viviendasListDTO2Entity(viviendas)));
    }
    
    /**
     * <h1>DELETE /api/arrendatarios/{arrendatarioID}/viviendas/{viviendaId}: Desasociar vivienda por id.</h1>
     * <pre>Elimina la conexión entre la vivienda y el arrendatario recibidos en la URL.
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la referencia.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe el recurso.
     * </code>
     * </pre>
     * @param arrendatarioID El ID del arrendatario al cual se le va a desasociar la factura
     * @param viviendaId El ID de la vivienda.
     */
    @DELETE
    @Path("{viviendaId: \\d+}")
    public void removeVivienda(@PathParam("arrendatarioID") Long arrendatarioID, @PathParam("viviendaId") Long viviendaId) {
        logic.removeVivienda(arrendatarioID, viviendaId);
    }
}
