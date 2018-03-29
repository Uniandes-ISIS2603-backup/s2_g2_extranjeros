/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.dtos.FacturaDetailDTO;
import co.edu.uniandes.csw.extranjeros.ejb.ArrendatarioLogic;
import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
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
 * <pre>Clase que implementa el recurso "arrendatarios/facturas".
 * URL: /api/arrendatarios/{arrendatarioID}/facturas
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
@Path("arrendatarios/{arrendatarioID: \\d+}/facturas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArrendatarioFacturasResource {
    
    //------------------------------------------
    // Inject: Logica - Arrendatario
    //------------------------------------------
    @Inject
    private ArrendatarioLogic logic;
    
    
    //------------------------------------------
    // Conversion tipo de Clases:
    //------------------------------------------
    
    /**
     * Convierte una lista de FacturaEntity a una lista de FacturaDetailDTO.
     * @param entityList Lista de FacturaEntity a convertir.
     * @return Lista de FacturaDetailDTO convertida.
     */
    private List<FacturaDetailDTO> facturasListEntity2DTO(List<FacturaEntity> entityList) {
        List<FacturaDetailDTO> list = new ArrayList<>();
        for (FacturaEntity entity : entityList) {
            list.add(new FacturaDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de FacturaDetailDTO a una lista de FacturaEntity.
     * @param dtos Lista de FacturaDetailDTO a convertir.
     * @return Lista de FacturaEntity convertida.
     */
    private List<FacturaEntity> facturasListDTO2Entity(List<FacturaDetailDTO> dtos) {
        List<FacturaEntity> list = new ArrayList<>();
        for (FacturaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    //------------------------------------------
    // Servicios de la aplicacion
    //------------------------------------------

    /**
     * <h1>GET /api/arrendatarios/{arrendatarioID}/facturas : Obtener todos las facturas de un Arrendatario.</h1>
     * <pre> Busca y devuelve todos las facturas que existen en un Arrendatario.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos las facturas del arrendatario.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un arrendatario con el id dado.
     * </code>
     * @param arrendID El ID del arrendatario del cual se buscan las facturas.
     * @return JSONArray {@link FacturaDetailDTO} - Las facturas encontradas en el arrendatario.
     */
    @GET
    public List<FacturaDetailDTO> getFacturas(@PathParam("arrendatarioID") Long arrendID) {
        return facturasListEntity2DTO(logic.getFacturas(arrendID));
    }
    
    /**
     * <h1>GET /api/arrendatarios/{arrendatarioID}/facturas/{facturaId}: Obtener una factura de un arrendatario.</h1>
     * <pre>Busca y devuelve la factura con el ID recibido en la URL, relativo a un arrendatario.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la factura del arrendatario.</code> 
     * </pre>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe el objeto.
     * </code>
     * @param facturaId El ID de la factura que se busca.
     * @param arrendID El ID del arrendatario del cual se busca la factura.
     * @return {@link FacturaDetailDTO} - La factura encontrada en el arrendatario.
     */
    @GET
    @Path("{facturaId: \\d+}")
    public FacturaDetailDTO getFactura(@PathParam("arrendatarioID") Long arrendID, @PathParam("facturaId") Long facturaId) {
        return new FacturaDetailDTO(logic.getFactura(arrendID, facturaId));
    }

    /**
     * <h1>POST /api/arrendatarios/{arrendatarioID}/facturas/{facturaId} : Asociar una factura a un arrendatario.</h1>
     * <pre> Asocia una factura existente con un arrendatario existente
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Asoció el objeto.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found: No existe el recurso.
     * </code>
     * </pre>
     * @param arrendID El ID del arrendatario que se va a asociar
     * @param facturaID El ID de la factura al cual se le va a asociar el arrendatario
     * @return JSON {@link FacturaDetailDTO} - La factura asociado.
     */
    @POST
    @Path("{facturaId: \\d+}")
    public FacturaDetailDTO createFactura(@PathParam("arrendatarioID") Long arrendID, @PathParam("facturaId") Long facturaID) {
        return new FacturaDetailDTO(logic.createFacturaIn(arrendID, facturaID));
    }

    /**
     * <h1>PUT /api/arrendatarios/{arrendatarioID}/facturas/ : Actualizar las facturas de un arrendatario.</h1>
     * <pre>Cuerpo de petición: JSONArray {@link FacturaDetailDTO}.
     * Actualiza la lista.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la lista.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: No se pudo actualizar la lista
     * </code>
     * </pre>
     * @param arrendatarioID El ID del arrendatario al cual se le va a asociar la lista de facturas.
     * @param facturas JSONArray {@link FacturaDetailDTO} - La lista de facturas que se desea guardar.
     * @return JSONArray {@link FacturaDetailDTO}  - La lista actualizada.
     */
    @PUT
    public List<FacturaDetailDTO> replaceFactura(@PathParam("arrendatarioID") Long arrendatarioID, List<FacturaDetailDTO> facturas) {
        return facturasListEntity2DTO(logic.updateFacturas(arrendatarioID, facturasListDTO2Entity(facturas)));
    }

    /**
     * <h1>DELETE /api/arrendatarios/{arrendatarioID}/facturas/{facturaId}: Desasociar factura por id.</h1>
     * <pre>Elimina la conexión entre la factura y el arrendatario recibidos en la URL.
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la referencia.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe el recurso.
     * </code>
     * </pre>
     * @param arrendatarioID El ID del arrendatario al cual se le va a desasociar la factura
     * @param facturaId El ID de la factura.
     */
    @DELETE
    @Path("{facturaId: \\d+}")
    public void removeFacturas(@PathParam("arrendatarioID") Long arrendatarioID, @PathParam("facturaId") Long facturaId) {
        logic.removeFactura(arrendatarioID, facturaId);
    }
}