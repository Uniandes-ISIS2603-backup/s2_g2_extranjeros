/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.mappers.BusinessLogicExceptionMapper;
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
 * <pre>Clase que implementa el recurso "servicios".
 * URL: /api/servicios
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "servicios".</i>
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
@Path("servicios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ServicioResource {
    
@POST
public ServicioDetailDTO createServicio(ServicioDetailDTO s)
{
    return s;
}

@GET
public List<ServicioDetailDTO> getServicios()
{
    return new ArrayList<ServicioDetailDTO>();
}

@GET
@Path("{id: \\d+}")
public ServicioDetailDTO getServicio(@PathParam("id") Long id)
{
    return null;
}

@PUT
@Path("{id: \\d+}")
public ServicioDetailDTO updateServicio(@PathParam ("id") Long id)
{
    return null;
}

@DELETE
@Path("{id: \\d+}")
public void deleteServicio(@PathParam ("id") Long id)
{
}
}
