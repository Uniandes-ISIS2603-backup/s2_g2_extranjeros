/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.dtos.ValoracionDetailDTO;
import co.edu.uniandes.csw.extranjeros.ejb.ValoracionLogic;
import co.edu.uniandes.csw.extranjeros.ejb.ViviendaLogic;
import co.edu.uniandes.csw.extranjeros.entities.ValoracionEntity;
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
 *
 * 
 * @author jd.arango
 */
@Path("viviendas/{viviendaId: \\d+}/valoraciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ValoracionResource {
    
    @Inject
   private ValoracionLogic logic; 
    
    @Inject 
   private ViviendaLogic viviendaLogic; 
    
   /**
     * Convierte una lista de ValoracionEntity a una lista de ValoracionDetailDTO.
     *
     * @param entityList Lista de AuthorEntity a convertir.
     * @return Lista de AuthorDetailDTO convertida.
     * 
     */
    private List<ValoracionDetailDTO> listEntity2DTO(List<ValoracionEntity> entityList) {
        List<ValoracionDetailDTO> list = new ArrayList<>();
        for (ValoracionEntity entity : entityList) {
            list.add(new ValoracionDetailDTO(entity));
        }
        return list;
    }
    
   
    /**
     * <h1>POST /api/valoraciones : Crear una valoracion.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link ValoracionDetailDTO}.
     * 
     * Crea una nueva valoracion con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creo la nueva valoracion .
     * </code>
     * </pre>
     * @param vivi {@link ValoracionDetailDTO} - La valoracion que se desea guardar.
     * @return JSON {@link ValoracionDetailDTO}  - La valoracion guardada con el atributo id autogenerado.
     */
    @POST
    public ValoracionDetailDTO createValoracion(ValoracionDetailDTO vivi,@PathParam("viviendaId") Long id) throws BusinessLogicException {
       if(viviendaLogic.getVivienda(id)==null){
          throw new BusinessLogicException("La vivienda asociada no existe");
       }
       if(vivi.getValoracion()>5 || vivi.getValoracion()<0){
          throw new BusinessLogicException("el rating es menor a 0 o mayor a 5 y no es valido");  
       }
       viviendaLogic.getVivienda(id).getValoraciones().add((logic.createValoracion(vivi.toEntity())));
       ValoracionDetailDTO val = new ValoracionDetailDTO(logic.createValoracion(vivi.toEntity()));
       
       return val;
    }
      /**
     * <h1>GET /api/valoraciones : Obtener todas las valoraciones.</h1>
     *
     * <pre>Busca y devuelve todas las valoraciones que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las valoracioness de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link AuthorDetailDTO} -las valoraciones encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ValoracionDetailDTO> getValoraciones(){
        return listEntity2DTO(logic.getValoraciones());
    }
    
    /**
     * <h1>GET /api/valoraciones/{id} : Obtener valoracion por id.</h1>
     *
     * <pre>Busca la valoracion con el id asociado recibido en la URL y la devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la valoracion correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una valoracion con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la valoracion que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ValoracionDetailDTO} - La valoracion buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{id: \\d+}")
    public ValoracionDetailDTO getValoracion(@PathParam("id") Long id) {
        ValoracionEntity entity = logic.getValoracion(id);
        if (entity == null) {
            throw new WebApplicationException("La valoracion no existe", 404);
        }
        return new ValoracionDetailDTO(entity);
    }
    
   /**
     * <h1>PUT /api/valoraciones/{id} : Actualizar valoracion con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ValoracionDetailDTO}.
     *
     * Actualiza la valoracion con el id recibido en la URL con la información que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la valoracion con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una valoracion con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del autor que se desea actualizar. Este debe ser una cadena de dígitos.
     * @param author {@link ValoracionDetailDTO} La valoracion que se desea guardar.
     * @return JSON {@link ValoracionDetailDTO} - La valoracion guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra la valoracion a actualizar.
     */
    @PUT
    @Path("{id: \\d+}")
    public ValoracionDetailDTO updateValoracion(@PathParam("id") Long id, ValoracionDetailDTO valoracion) {
        ValoracionEntity entity = valoracion.toEntity();
        entity.setId(id);
        ValoracionEntity oldEntity = logic.getValoracion(id);
        if (oldEntity == null) {
            throw new WebApplicationException("La valoracion no existe", 404);
        }
        return new ValoracionDetailDTO(logic.updateValoracion(entity));
    }

    
    
   /**
     * <h1>DELETE /api/valoraciones/{id} : Borrar valoracion por id.</h1>
     *
     * <pre>Borra la valoracion con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la valoracion correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una valoracion con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la valoracion que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteValoracion(@PathParam("id") Long id) {
        ValoracionEntity entity = logic.getValoracion(id);
        if (entity == null) {
            throw new WebApplicationException("La valoracion no existe", 404);
        }
        logic.deleteValoracion(id);
    }
    
    
    

 
    
            
}
