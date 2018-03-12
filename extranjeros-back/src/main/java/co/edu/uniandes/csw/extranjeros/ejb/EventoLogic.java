/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.EventoEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.EventoPersistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author la.ruiz967
 */
@Stateless
public class EventoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(EventoLogic.class.getName());
    
    @Inject
    private EventoPersistence persistence;
    
    /**
     * Mètodo que valida que el nombre del evento no contenga palabras restringidas.
     * @param nombreP
     * @return 
     */
    public boolean validarNombreEvento(String nombreP)
    {
        boolean rta = true;
        if(nombreP.contains(nombreP))
        {
            
        }
        return rta;
    }
    
    /**
     * Método que valida que el tipo del evento haga parte de algunos preestablecidos.
     * @param tipoEvento
     * @return 
     */
    public boolean validarTipoEvento(String tipoEvento)
    {
        boolean rta = false;
        if(tipoEvento.equals("Fiesta")||tipoEvento.equals("Integración")||tipoEvento.equals("Concierto") || tipoEvento.equals("Festival"))
        {
            rta = true;
        }
        return rta;
    }
    
    /**
     * Mètodo que valida que la capacidad del evento no sea exedida por el número de asistentes.
     * @param lista
     * @param capacidad
     * @return 
     */
    public boolean validarCapacidad(List lista, int capacidad)
    {
        boolean rta = true;
        if(lista.size()<capacidad)
        {
            rta = false;
        }
        return rta;
    }
    
    /**
     * Método que valida que la fecha del evento sea por lo menos una hora después de la actual.
     * @param fechaP
     * @return 
     */
    public boolean validarFecha(String fechaP)
    {
        boolean rta = false;
        Date now = new Date();
        DateFormat form = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try
        {
            Date fechaE = form.parse(fechaP);
            Calendar myCal = Calendar.getInstance();
            myCal.setTime(fechaE);
            myCal.add(Calendar.HOUR, -1);
            fechaE= myCal.getTime();
            
            if(!now.after(fechaE))
            {
                rta=true;
            }
        }
        catch(Exception ex)
        {
            
        }
        return rta;
    }
    
    /**
     * Método que se conecta con la persistencia para crear el evento.
     * Revisa que se cumplan todas las condiciones. Que el nombre no incluya palabras ofensivas,
     * que el tipo esté entre los tipos ofrecidos, y que la fecha del evento sea mínimo una hora después de 
     * la actual.
     * @param entity
     * @return
     * @throws BusinessLogicException 
     */
    public EventoEntity create(EventoEntity entity) throws BusinessLogicException
    {
        if(validarNombreEvento(entity.getNombreEvento()))
        {
            if(validarTipoEvento(entity.getTipoEvento()))
            {
                if(validarFecha(entity.getFechaEvento()))
                {
                   return persistence.create(entity); 
                }
                throw new BusinessLogicException("La fecha no cumple el requisito de ser mínimo una hora más tarde que la actual.");
            }
            throw new BusinessLogicException("El tipo de evento no hace parte de la lista de eventos posibles.");
        }
        throw new BusinessLogicException("El nombre del evento contiene palabras que no son adecuadas.");
    }
    
    /**
     * Método que encuentra un evento con el id dado.
     * @param id
     * @return 
     */
    public EventoEntity find(Long id)
    {
        return persistence.find(id);
    }
    
    /**
     * Método que encuentra todos los eventos
     * @return 
     */
    public List<EventoEntity> findAll()
    {
        return persistence.findAll();
    }
    
    /**
     * Método que actualiza un evento.
     * Revisa que se cumplan todas las condiciones. Que el nombre no incluya palabras ofensivas,
     * que el tipo esté entre los tipos ofrecidos, y que la fecha del evento sea mínimo una hora después de 
     * la actual.
     * @param entity
     * @return
     * @throws BusinessLogicException 
     */
    public EventoEntity update(EventoEntity entity) throws BusinessLogicException
    {
        if(validarNombreEvento(entity.getNombreEvento()))
        {
            if(validarTipoEvento(entity.getTipoEvento()))
            {
                if(validarFecha(entity.getFechaEvento()))
                {
                   return persistence.update(entity); 
                }
                throw new BusinessLogicException("La fecha no cumple el requisito de ser mínimo una hora más tarde que la actual.");
            }
            throw new BusinessLogicException("El tipo de evento no hace parte de la lista de eventos posibles.");
        }
        throw new BusinessLogicException("El nombre del evento contiene palabras que no son adecuadas.");
    }
    
    /**
     * Método que elimina el evento con el id dado.
     * @param id 
     */
    public void delete(Long id)
    {
        persistence.delete(id);
    }
}
