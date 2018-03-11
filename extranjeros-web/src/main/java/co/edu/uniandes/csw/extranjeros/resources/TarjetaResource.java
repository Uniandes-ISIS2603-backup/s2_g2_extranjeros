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


import co.edu.uniandes.csw.extranjeros.dtos.TarjetaDetailDTO;
import co.edu.uniandes.csw.extranjeros.ejb.TarjetaLogic;
import co.edu.uniandes.csw.extranjeros.entities.TarjetaEntity;
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
 * <pre>Clase que implementa el recurso "Tarjeta".
 * URL: /api/tarjeta
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "Tarjeta".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * 
 * @author la.ruiz967
 */
@Path("tarjeta")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaResource {

    @Inject
    TarjetaLogic tarjetaLogic;
    
    /**
     * <h1>POST /api/tarjeta : Crear una tarjeta.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link TarjetaDetailDTO}.
     * 
     * Crea una nueva tarjetas con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva ciudad .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la tarjeta.
     * </code>
     * </pre>
     * @param tarjeta {@link TarjetaDetailDTO} - La tarjeta que se desea guardar.
     * @return JSON {@link TarjetaDetailDTO}  - La tarjeta guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la tarjeta.
     */
    @POST
    public TarjetaDetailDTO createTarjeta(TarjetaDetailDTO tarjeta) throws BusinessLogicException {
        TarjetaEntity rta = tarjetaLogic.create(tarjeta.toEntity());
        TarjetaDetailDTO res = new TarjetaDetailDTO(rta);
        return res;
    }

    /**
     * <h1>GET /api/tarjeta : Obtener todas las tarjetas.</h1>
     * 
     * <pre>Busca y devuelve todas las tarjetas que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las tarjetas de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link TarjetaDetailDTO} - Las tarjetas encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<TarjetaDetailDTO> getTarjetas() {
        List<TarjetaEntity> tars = tarjetaLogic.findAll();
        List<TarjetaDetailDTO> res = new ArrayList<>();
        for(TarjetaEntity en: tars)
        {
            res.add(new TarjetaDetailDTO(en));
        }
        return res;
    }

    /**
     * <h1>GET /api/tarjeta/{id} : Obtener tarjeta por id.</h1>
     * 
     * <pre>Busca la tarjeta con el id asociado recibido en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la tarjeta correspondiente al id.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una tarjeta con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la tarjeta que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link TarjetaDetailDTO} - La tarjeta buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra la tarjeta.
     */
    @GET
    @Path("{id: \\d+}")
    public TarjetaDetailDTO getTarjeta(@PathParam("id") Long id) throws WebApplicationException
    {
        TarjetaEntity tar = tarjetaLogic.find(id);
        if(tar == null)
        {
            throw new WebApplicationException("La tarjeta no existe", 404);
        }
        return new TarjetaDetailDTO(tar);
    }
    
    /**
     * <h1>PUT /api/tarjeta/{id} : Actualizar tarjeta con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link TarjetaDetailDTO}.
     * 
     * Actualiza la tarjeta con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la tarjeta con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una tarjeta con el id dado.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precondition Failed. No se puede actualizar la tarjeta con el id dado.
     * </code> 
     * </pre>
     * @param id Identificador de la tarjeta que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param tarjeta {@link TarjetaDetailDTO} La tarjeta que se desea guardar.
     * @return JSON {@link TarjetaDetailDTO} - La tarjeta guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra la tarjeta a actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la tarjeta.
     */
    @PUT
    @Path("{id: \\d+}")
    public TarjetaDetailDTO updateTarjeta(@PathParam("id") Long id, TarjetaDetailDTO tarjeta) throws BusinessLogicException, WebApplicationException {
        TarjetaEntity tar = tarjeta.toEntity();
        tar.setId(id);
        if(tarjetaLogic.find(id) == null)
        {
            throw new WebApplicationException("La factura no existe", 404);
        }
        TarjetaEntity res = tarjetaLogic.update(tar);
        return new TarjetaDetailDTO(res);
    }
    
    /**
     * <h1>DELETE /api/tarjeta/{id} : Borrar ciudad por id.</h1>
     * 
     * <pre>Borra la tarjeta con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la tarjeta correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una tarjeta con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la tarjeta que se desea borrar. Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - Error de lógica que se genera cuando no se encuentra la tarjeta a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
     public void deleteTarjeta(@PathParam("id") Long id) throws WebApplicationException {
        if(tarjetaLogic.find(id)== null)
        {
            throw new WebApplicationException("La tarjeta no existe", 404);
        }
        tarjetaLogic.delete(id);
    }
}
