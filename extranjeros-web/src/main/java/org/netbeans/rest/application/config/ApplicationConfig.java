/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author jr.pacheco10
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.uniandes.csw.extranjeros.mappers.BusinessLogicExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.ArrendatarioFacturasResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.ArrendatarioResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.ArrendatarioViviendasResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.CuentaBancariaResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.EstudianteResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.EventoResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.FacturaResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.LugaresDeInteresResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.ProvidenciaResouce.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.ServicioResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.TarjetaResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.UniversidadResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.ValoracionResource.class);
        resources.add(co.edu.uniandes.csw.extranjeros.resources.ViviendaResource.class);
    }
    
}
