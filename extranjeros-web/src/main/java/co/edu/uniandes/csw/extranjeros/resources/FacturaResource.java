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
 *
 * @author s.rodriguezm
 */
@Path("facturas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FacturaResource 
{
    @POST
    public FacturaDetailDTO createFactura(FacturaDetailDTO fac)throws BusinessLogicException
    {
        return fac;
    }
    
    @GET
    public List<FacturaDetailDTO> getFacturas()
    {
        return new ArrayList<>();
    }
    
     @GET
     @Path("{id: \\d+}")
    public FacturaDetailDTO getFactura(@PathParam("id") Long id)
    {
        return null;
    }
    
    @PUT
    @Path("{id: \\d+}")
    public FacturaDetailDTO updateFactura(@PathParam("id") Long id, FacturaDetailDTO fac)
    {
        return null;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFactura(@PathParam("id") Long id)
    {
    }
    
}
