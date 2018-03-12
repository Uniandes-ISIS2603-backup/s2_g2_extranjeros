/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.dtos.ViviendaDTO;
import co.edu.uniandes.csw.extranjeros.dtos.ViviendaDetailDTO;
import co.edu.uniandes.csw.extranjeros.ejb.ArrendatarioLogic;
import co.edu.uniandes.csw.extranjeros.ejb.ServicioLogic;
import co.edu.uniandes.csw.extranjeros.ejb.ValoracionLogic;
import co.edu.uniandes.csw.extranjeros.ejb.ViviendaLogic;
import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
import co.edu.uniandes.csw.extranjeros.entities.ValoracionEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
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
 *"arrendatarios/{arrendatarioId: \\\\d+}/viviendas"
 * @author jd.arango
 */
@Path("viviendas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ViviendaResource {
   @Inject
   private ViviendaLogic logic; 
    
   @Inject
   private ArrendatarioLogic arrendatarioLO;
  
   @Inject
   private ServicioLogic serviLogic;
   
   @Inject
   private ValoracionLogic valLogic;
   /**
     * Convierte una lista de ViviendaEntity a una lista de ViviendaDetailDTO.
     *
     * @param entityList Lista de AuthorEntity a convertir.
     * @return Lista de AuthorDetailDTO convertida.
     * 
     */
    private List<ViviendaDetailDTO> listEntity2DTO(List<ViviendaEntity> entityList) {
        List<ViviendaDetailDTO> list = new ArrayList<>();
        for (ViviendaEntity entity : entityList) {
            list.add(new ViviendaDetailDTO(entity));
        }
        return list;
    }
    
   
    /**
     * <h1>POST /api/viviendas : Crear una vivienda.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link ViviendaDetailDTO}.
     * 
     * Crea una nueva vivienda con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo la nueva vivienda .
     * </code>
     * </pre>
     * @param vivi {@link ViviendaDetailDTO} - La vivienda que se desea guardar.
     * @return JSON {@link ViviendaDetailDTO}  - La vivienda guardada con el atributo id autogenerado.
     */
    @POST
    public ViviendaDetailDTO createVivienda(ViviendaDetailDTO vivi) {
        return new ViviendaDetailDTO(logic.createVivienda(vivi.toEntity()));
    }
      /**
     * <h1>GET /api/viviendas : Obtener todas las viviendas.</h1>
     *
     * <pre>Busca y devuelve todas las viviendas que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las viviendass de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link AuthorDetailDTO} -las viviendas encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ViviendaDetailDTO> getViviendas(){
        return listEntity2DTO(logic.getViviendas());
    }
    
    /**
     * <h1>GET /api/viviendas/{id} : Obtener vivienda por id.</h1>
     *
     * <pre>Busca la vivienda con el id asociado recibido en la URL y la devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la vivienda correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una vivienda con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la vivienda que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ViviendaDetailDTO} - La vivienda buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{id: \\d+}")
    public ViviendaDetailDTO getVivienda(@PathParam("id") Long id) {
        ViviendaEntity entity = logic.getVivienda(id);
        if (entity == null) {
            throw new WebApplicationException("La vivienda no existe", 404);
        }
        return new ViviendaDetailDTO(entity);
    }
    
   /**
     * <h1>PUT /api/viviendas/{id} : Actualizar vivienda con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ViviendaDetailDTO}.
     *
     * Actualiza la vivienda con el id recibido en la URL con la información que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la vivienda con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una vivienda con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del autor que se desea actualizar. Este debe ser una cadena de dígitos.
     * @param author {@link ViviendaDetailDTO} La vivienda que se desea guardar.
     * @return JSON {@link ViviendaDetailDTO} - La vivienda guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra la vivienda a actualizar.
     */
    @PUT
    @Path("{id: \\d+}")
    public ViviendaDetailDTO updateVivienda(@PathParam("id") Long id, ViviendaDetailDTO vivienda) throws BusinessLogicException {
        ViviendaEntity entity = vivienda.toEntity();
        entity.setId(id);
        ViviendaEntity oldEntity = logic.getVivienda(id);
        if (oldEntity == null) {
            throw new WebApplicationException("La vivienda no existe", 404);
        }
        List<ServicioEntity> adicional = new ArrayList<>();
        for (int i = 0; i < vivienda.getServiciosAdicionales().size(); i++) {
           ServicioEntity ser = (serviLogic.getServicio(vivienda.getServiciosAdicionales().get(i).getId()));
           if(ser == null){
               throw new BusinessLogicException("El servicio no existe");
           }
           adicional.add(ser);
        }
        List<ServicioEntity> fijos = new ArrayList<>();
        for (int i = 0; i < vivienda.getServiciosFijos().size(); i++) {
           ServicioEntity ser = (serviLogic.getServicio(vivienda.getServiciosFijos().get(i).getId()));
           if(ser == null){
               throw new BusinessLogicException("El servicio no existe");
           }
           fijos.add(ser);
        }
        List<ValoracionEntity> valo = new ArrayList<>();
        if(vivienda.getValoraciones()!=null){
            for (int i = 0; i < vivienda.getValoraciones().size(); i++) {
                valo.add(vivienda.getValoraciones().get(i).toEntity());
            }
        }
        
        entity.setServiciosAdicionales(adicional);
        entity.setServiciosFijos(fijos);
        entity.setValoraciones(valo);
        return new ViviendaDetailDTO(logic.updateVivienda(entity));
    }

    
    
   /**
     * <h1>DELETE /api/viviendas/{id} : Borrar vivienda por id.</h1>
     *
     * <pre>Borra la vivienda con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la vivienda correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una vivienda con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la vivienda que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteVivienda(@PathParam("id") Long id) {
        ViviendaEntity entity = logic.getVivienda(id);
        if (entity == null) {
            throw new WebApplicationException("La vivienda no existe", 404);
        }
        logic.deleteVivienda(id);
    }
    
    
    

 
}
