/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.FacturaPersistence;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.rodriguezm
 */
@Stateless
public class FacturaLogic {
    private static final Logger LOGGER = Logger.getLogger(ServicioLogic.class.getName());

    @Inject
    private FacturaPersistence persistence;
    
    /**
     * Obtiene la lista de las Facturas.
     *
     * @return Colección de objetos de FacturaEntity.
     */
    public List<FacturaEntity> getFacturas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los servicios");
         List<FacturaEntity> facs= persistence.findAll();
         LOGGER.info("Termina proceso de consultar todos los libros");
         return facs;
    }
    /**
     * Obtiene los datos de una instancia de Factura a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de FacturaEntity con los datos del Servicio consultado.
     */
    public FacturaEntity getFactura(Long id)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de consultar un servicio con id={0}",id);
        return persistence.find(id);
    }
    /**
     * Se encarga de crear un Factura en la base de datos.
     *
     * @param entity Objeto de FacturaEntity con los datos nuevos
     * @param vivienda Objeto de ViviendaEntity relacionada con la factura.
     * @return Objeto de FacturaEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException si alguna regla de negocio es incumplida.
     */
    public FacturaEntity createFactura(FacturaEntity entity, ViviendaEntity vivienda)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO,"Inicia proceso de crear un servicio");
        if(isFechaMenor(new Date(),entity.getFechaEntrada())||isFechaMenor(new Date(),entity.getFechaSalida()))
            throw new BusinessLogicException("La fecha de entrada o de salida no pueden ser anteriores a la actual.");
        if(isFechaMenor(entity.getFechaEntrada(), entity.getFechaSalida()))
            throw new BusinessLogicException("La fecha de salida no puede ser menor a la de entrada.");
        if(!fechaSalidaAlMenosUnMes(entity))
            throw new BusinessLogicException("La fecha de salida no es de al menos un m despues de la de entrada.");
        return persistence.create(entity);
    }
    /**
     * Actualiza la información de una instancia de Factura.
     *
     * @param entity Instancia de FacturaEntity con los nuevos datos.
     * @return Instancia de FacturaEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException si alguna regla de negocio es incumplida actualizando.
     */
    public FacturaEntity updateFactura(FacturaEntity entity)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un servicio");
        if(isFechaMenor(new Date(),entity.getFechaEntrada())||isFechaMenor(new Date(),entity.getFechaSalida()))
            throw new BusinessLogicException("La fecha de entrada o la fecha de salida no pueden ser anteriores a la actual.");
        if(isFechaMenor(entity.getFechaEntrada(), entity.getFechaSalida()))
            throw new BusinessLogicException("La fecha de salida no puede ser menor a la de entrada.");
        if(!fechaSalidaAlMenosUnMes(entity))
            throw new BusinessLogicException("La fecha de salida no es de al menos un m despues de la de entrada.");
        return persistence.update(entity);
    }
    /**
     * Elimina una instancia de Factura de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteFactura(Long id)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de eliminar un servicio con id={0}",id);
        persistence.delete(id);
    }
    /**
     * Determina si la fecha2 pasada por parametro es anterior a la fecha1.
     * @param fecha1 fecha a la que se debe ser mayor o igual.
     * @param fecha2 fecha que se va a comparar.
     * 
     */
    private boolean isFechaMenor(Date fecha1, Date fecha2) 
    {
        boolean resp=false;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(fecha1);
        int day = cal1.get(Calendar.DAY_OF_MONTH);
        int month = cal1.get(Calendar.MONTH);
        int year = cal1.get(Calendar.YEAR);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(fecha2);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int year2 = cal2.get(Calendar.YEAR);
        if(year>year2)
            resp=true;
        else if(year==year2&&month>month2)
            resp=true;
        else if(year==year2&&month==month2&&day>day2)
            resp=true;
        return resp;
    }
    /**
     * Determina si la fecha de salida esta a al menos un mes de la fecha de entrada.
     * @param entity FacturaEntity a revisar.
     */
    private boolean fechaSalidaAlMenosUnMes(FacturaEntity entity) {
    Date in = entity.getFechaEntrada();
    Date out= entity.getFechaSalida();
    LocalDate lin = in.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate lout = out.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return ChronoUnit.MONTHS.between(lin, lout)>=1;
    }
    /**
     * Determina si la factura se puede hacer segun los cupos disponibles de la vivienda.
     * @param entity ViviendaEntity a revisar.
     */
    private boolean hayEspacio(ViviendaEntity entity)
    {
        return true;
    }
    
}
