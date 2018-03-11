/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.extranjeros.resources;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.extranjeros.dtos.*;
import co.edu.uniandes.csw.extranjeros.ejb.FacturaLogic;
import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.ejb.ViviendaLogic;
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
 * <pre>Clase que implementa el recurso "facturas".
 * URL: /api/facturas
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "facturas".</i>
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
@Path("viviendas/{viviendaId: \\d+}/facturas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FacturaResource 
{
    @Inject
    FacturaLogic facturaLogic;
    
    @Inject
    ViviendaLogic viviendaLogic;

    /**
     * Convierte una lista de FacturaEntity a una lista de FacturaDetailDTO.
     *
     * @param entityList Lista de FacturaEntity a convertir.
     * @return Lista de FacturaDetailDTO convertida.
     * 
     */
    private List<FacturaDetailDTO> listEntity2DTO(List<FacturaEntity> entityList) {
        List<FacturaDetailDTO> list = new ArrayList<>();
        for (FacturaEntity entity : entityList) {
            list.add(new FacturaDetailDTO(entity));
        }
        return list;
    }
    /**
     * <h1>POST /api/facturas : Crear una factura.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link FacturaDetailDTO}.
     * 
     * Crea una nuevo factura con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva factura .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la factura.
     * </code>
     * </pre>
     * @param fac{@link FacturaDetailDTO} - la factura que se desea guardar.
     * @param id Identificador de la vivienda sobre al que se va a hacer la factura. Este debe ser una cadena de dígitos.
     * @return JSON {@link FacturaDetailDTO}  - la factura guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la factura.
     */
    @POST
    public FacturaDetailDTO createFactura(FacturaDetailDTO fac,@PathParam("viviendaId") Long id)throws BusinessLogicException
    {
        if(viviendaLogic.getVivienda(id)==null)
            throw new BusinessLogicException("La factura debe ser sobre una vivienda pre-existente.");
        return new FacturaDetailDTO(facturaLogic.createFactura(fac.toEntity(),viviendaLogic.getVivienda(id)));
    }
    /**
     * <h1>GET /api/facturas : Obtener todos las facturas.</h1>
     *
     * <pre>Busca y devuelve todos las facturas que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos las facturas de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link FacturaDetailDTO} - las facturas encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<FacturaDetailDTO> getFacturas()
    {
        return listEntity2DTO(facturaLogic.getFacturas());
    }
    /**
     * <h1>GET /api/facturas/{id} : Obtener la factura por id.</h1>
     *
     * <pre>Busca la factura con el id asociado recibido en la URL y lo devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la factura correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un servicio con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la factura que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link FacturaDetailDTO} - la factura buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra la factura.
     */
    @GET
    @Path("{id: \\d+}")
    public FacturaDetailDTO getFactura(@PathParam("id") Long id)throws WebApplicationException
    {
        FacturaEntity entity=facturaLogic.getFactura(id);
        if(entity==null)
            throw new WebApplicationException("La factura no existe", 404);
        return new FacturaDetailDTO(entity);
    }
    /**
     * <h1>PUT /api/facturas/{id} : Actualizar factura con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link FacturaDetailDTO}.
     *
     * Actualiza la factura con el id recibido en la URL con la información que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la factura con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una factura con el id dado.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precondition Failes. No se puede actualizar la factura con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la factura que se desea actualizar. Este debe ser una cadena de dígitos.
     * @param fac {@link FacturaDetailDTO} la factura que se desea guardar.
     * @return JSON {@link FacturaDetailDTO} - la factura guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra la factura a actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se puede actualizar la factura.
     */
    @PUT
    @Path("{id: \\d+}")
    public FacturaDetailDTO updateFactura(@PathParam("id") Long id, FacturaDetailDTO fac)throws BusinessLogicException, WebApplicationException
    {
        FacturaEntity entity=fac.toEntity();
        entity.setId(id);
        if(facturaLogic.getFactura(id)==null)
            throw new WebApplicationException("La factura no existe", 404);
        return new FacturaDetailDTO(facturaLogic.updateFactura(entity));
    }
    /**
     * <h1>DELETE /api/facturas/{id} : Borrar factura por id.</h1>
     *
     * <pre>Borra el factura con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la factura correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una factura con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la factura que se desea borrar. Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando no se puede eliminar la factura.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFactura(@PathParam("id") Long id)
    {
        if(facturaLogic.getFactura(id)==null)
            throw new WebApplicationException("La factura no existe", 404);
        facturaLogic.deleteFactura(id);
    }
    
}
