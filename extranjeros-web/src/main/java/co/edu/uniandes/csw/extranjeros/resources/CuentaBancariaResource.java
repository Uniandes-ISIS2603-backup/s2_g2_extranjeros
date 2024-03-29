/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.resources;

import co.edu.uniandes.csw.extranjeros.dtos.ArrendatarioDetailDTO;
import co.edu.uniandes.csw.extranjeros.dtos.CuentaBancariaDTO;
import co.edu.uniandes.csw.extranjeros.ejb.CuentaBancariaLogic;
import co.edu.uniandes.csw.extranjeros.entities.CuentaBancariaEntity;
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
 * <pre> Clase que implementa el recurso "cuentasBancarias".
 * URL: /api/cuentasBancarias
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

@Path("arrendatarios/{idArrendatario: \\d+}/cuentasBancarias")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class CuentaBancariaResource {

    //---------------------------------------------------
    // Inject: Logica
    //---------------------------------------------------
    
    @Inject
    CuentaBancariaLogic cuentaLogic;
    
    //---------------------------------------------------
    // Lista de conversion
    //---------------------------------------------------

    private List<CuentaBancariaDTO> listEntity2DTO (List<CuentaBancariaEntity> entityList) {
        List<CuentaBancariaDTO> list = new ArrayList<>();
        for (CuentaBancariaEntity entity : entityList) {
            list.add(new CuentaBancariaDTO(entity));
        }
        return list;
    }
    
    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------
    
    /**
     * <h1> GET /api/cuentasBancarias{id} : Obtener una Cuenta bancaria por ID.</h1>
     * <pre> Busca el arrendatario al cual corresponde la ID ingresada en la URL y lo devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la Cuenta Bancaria correspondiente al ID.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una Cuenta Bancaria con el id dado.
     * </code> 
     * </pre>
     * 
     * @param idArrendatario identificador del arrendatario.
     * @param id Identificador del arrendatario que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link CuentaBancariaDetailDTO} - El arrendatario buscado.
     */
    @GET
    @Path("{id: \\d+}")
    public CuentaBancariaDTO getCuentaBancaria(@PathParam("idArrendatario") Long idArrendatario, @PathParam("id") Long id) throws BusinessLogicException{
        CuentaBancariaEntity entidadCuenta = cuentaLogic.getCuentaBancaria(idArrendatario, id);
        if (entidadCuenta == null){
            throw new WebApplicationException("El recurso /arrendatarios/" + idArrendatario + "/cuentasBancarias/" + id + " no existe.", 404);
        }
        return new CuentaBancariaDTO(entidadCuenta);
    }
    

    /**
     * <h1> GET /api/cuentasBancarias: Obtener todas las Cuentas Bancarias. </h1>
     * <pre> Busca y devuelve todas las cuentas bancarias que existen dentro de la aplicacion. 
     * Codigos de respuesta:
     * 
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las Cuentas Bancarias de la aplicacion. 
     * </code> 
     * </pre>
     * @throws BusinessLogicException excepcion de nulidad.
     * @param idArrendatario Identificador del Arrendatario el cual posee la cuenta de banco
     * @return JSONArray {@link ArrendatarioDetailDTO} - Los arrendatarios que existen en la aplicación. Si no hay ningún arrendatario 
     * (nadie se ha registrado como arrendatario) se retorna una lista vacía.
     */
    @GET
    public List<CuentaBancariaDTO> getCuentasBancarias(@PathParam("idArrendatario") Long idArrendatario) throws BusinessLogicException{
        return listEntity2DTO(cuentaLogic.getCuentasBancarias(idArrendatario)); 
    }
    
    /**
     * <h1> POST /api/cuentasBancarias : Crear una Cuenta Bancaria. </h1>
     * <pre> Cuerpo de petición: JSON {@link CuentaBancariaDetailDTO}.
     * 
     * Crea una nueva Cuenta Bancaria con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó una nueva Cuenta Bancaria.
     * </code>
     * 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la Cuenta Bancaria ingresada.
     * </code>
     * </pre>
     * 
     * @param idArrendatario Arrendatario titular. 
     * @param cBElement {@link CuentaBancariaDetailDTO} - La cuenta de banco que se desea guardar.
     * @return JSON {@link CuentaBancariaDetailDTO}  - La cuenta de banco guardado con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe el arrendatario.
     */
    @POST
    public CuentaBancariaDTO createCuentaBancaria (@PathParam ("idArrendatario") Long idArrendatario, CuentaBancariaDTO cBElement) throws BusinessLogicException{
        return new CuentaBancariaDTO(cuentaLogic.createCuentaBancaria(idArrendatario, cBElement.toEntity()));
    }
    
    /**
     * <h1> PUT /api/cuentasBancarias/{id} : Actualiza una cuenta de banco asociada con el ID dado. </h1>
     * <pre> Cuerpo de petición: JSON {@link CuentaBancariaDetailDTO}.
     * 
     * Actualiza la cuenta de banco con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Se actualizó la cuenta de banco con el ID dado y con la información enviada desde el Body como parámetro. Retorna un objeto identico.</code> 
     * 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una cuenta de banco con el id dado.
     * </code> 
     * </pre>
     * 
     * @param idArrendatario 
     * @param id Identificador de la cuenta de banco que se desea actualizar. Este debe ser una cadena de dígitos.
     * @param cuentaBancaria {@link CuentaBancariaDetailDTO} La cuenta de banco que se desea guardar.
     * @return JSON {@link CuentaBancariaDetailDTO} - La cuenta de banco que se ha guardado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar el Usuario porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public CuentaBancariaDTO updateCuentaBancaria (@PathParam("idArrendatario") Long idArrendatario, @PathParam("id") Long id, CuentaBancariaDTO cuentaBancaria) throws BusinessLogicException {
        
        cuentaBancaria.setId(id);
        CuentaBancariaEntity entidad = cuentaLogic.getCuentaBancaria(idArrendatario, id);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /arrendatarios/" + idArrendatario + "/cuentasBancarias/" + id + " no existe.", 404);
        }
        return new CuentaBancariaDTO(cuentaLogic.updateCuentaDeBanco(idArrendatario, cuentaBancaria.toEntity()));
    }
    
    /**
     * <h1> DELETE /api/cuentasBancarias/{id} : Borrar una cuenta de banco por id.</h1>
     * <pre> Borra la cuenta de banco con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta: <br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la cuenta de banco correspondiente al id dado. </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una cuenta de banco con el ID dado.
     * </code>
     * </pre>
     * @param idArrendatario
     * @param id Identificador de la cuenta de banco que se desea borrar. Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException En caso de que una regla de negocio no se cumpla. 
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCuentaBancaria (@PathParam("idArrendatario") Long idArrendatario, @PathParam ("id") Long id) throws BusinessLogicException{
        CuentaBancariaEntity entidad = cuentaLogic.getCuentaBancaria(idArrendatario, id);
        if(entidad == null){
            throw new WebApplicationException("El recurso /arrendatarios/" + idArrendatario + "/cuentasBancarias/" + id + " no existe.", 404);
        }
        cuentaLogic.deleteReview(idArrendatario, id);
    }
}
