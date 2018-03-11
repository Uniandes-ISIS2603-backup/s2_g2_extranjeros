/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
CITYS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.extranjeros.resources;


import co.edu.uniandes.csw.extranjeros.dtos.EventoDetailDTO;
import co.edu.uniandes.csw.extranjeros.ejb.EventoLogic;
import co.edu.uniandes.csw.extranjeros.entities.EventoEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.mappers.BusinessLogicExceptionMapper;
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
 * <pre>Clase que implementa el recurso "Evento".
 * URL: /api/evento
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "Evento".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author ISIS2603  
 * @version 1.0
 */
@Path("eventos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EventoResource {

    @Inject
    EventoLogic eventoLogic;
    /**
     * <h1>POST /api/eventos : Crear un evento.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link EventoDTO}.
     * 
     * Crea un nuevo evento con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo evento .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el evento.
     * </code>
     * </pre>
     * @param evento {@link EventoDetailDTO} - El evento que se desea guardar.
     * @return JSON {@link EventoDetailDTO}  - El evento guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la tarjeta.
     */
    @POST
    public EventoDetailDTO createEvento(EventoDetailDTO evento) throws BusinessLogicException {
        EventoEntity eventoE = eventoLogic.create(evento.toEntity());
        return new EventoDetailDTO(eventoE);
    }

    /**
     * <h1>GET /api/eventos : Obtener todos los eventos.</h1>
     * 
     * <pre>Busca y devuelve todos los eventos que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todos los eventos de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link EventoDetailDTO} - Los Eventos encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EventoDetailDTO> getEventos() {
        List<EventoEntity> evens = eventoLogic.findAll();
        List<EventoDetailDTO> res = new ArrayList<>();
        for(EventoEntity eve:evens)
        {
            res.add(new EventoDetailDTO(eve));
        }
        return res;
    }

    /**
     * <h1>GET /api/eventos/{id} : Obtener eventos por id.</h1>
     * 
     * <pre>Busca el evento con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el evento correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un evento con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del evento que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link EventoDetailDTO} - El evento buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra el evento.
     */
    @GET
    @Path("{id: \\d+}")
    public EventoDetailDTO getEvento(@PathParam("id") Long id) throws WebApplicationException{
        EventoEntity ev = eventoLogic.find(id);
        if(ev == null)
        {
            throw new WebApplicationException("El evento no existe", 404);
        }
        return new EventoDetailDTO(ev);
    }
    
    /**
     * <h1>PUT /api/eventos/{id} : Actualizar eventos con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link EventoDetailDTO}.
     * 
     * Actualiza el evento con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el evento con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un evento con el id dado.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precondition Failed. No se puede actualizar el evento con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador del evento que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param evento {@link EventoDetailDTO} El evento que se desea guardar.
     * @return JSON {@link EventoDetailDTO} - El Evento guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el evento.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra el evento a actualizar.
     */
    @PUT
    @Path("{id: \\d+}")
    public EventoDetailDTO updateEvento(@PathParam("id") Long id, EventoDetailDTO evento) throws BusinessLogicException, WebApplicationException  {
        EventoEntity eve = evento.toEntity();
        eve.setId(id);
        if(eventoLogic.find(id) == null)
        {
            throw new WebApplicationException("El evento no existe", 404);
        }
        EventoEntity res = eventoLogic.update(eve);
        return new EventoDetailDTO(res);
    }
    
    /**
     * <h1>DELETE /api/eventos/{id} : Borrar ciudad por id.</h1>
     * 
     * <pre>Borra el evento con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el evento correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un evento con el id dado.
     * </code>
     * </pre>
     * @param id Identificador del evento que se desea borrar. Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra el evento a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteEvento(@PathParam("id") Long id) throws WebApplicationException {
        if(eventoLogic.find(id)== null)
        {
            throw new WebApplicationException("El evento no existe", 404);
        }
        eventoLogic.delete(id);
    }
}
