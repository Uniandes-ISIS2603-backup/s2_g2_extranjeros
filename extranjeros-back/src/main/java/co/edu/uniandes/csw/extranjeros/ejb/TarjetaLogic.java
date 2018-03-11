/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.TarjetaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.TarjetaPersistence;
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
public class TarjetaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(TarjetaLogic.class.getName());
    
    @Inject
    private TarjetaPersistence persistence;
    
    
    public boolean verificarBanco(String pBanco)
    {
        if(pBanco.equals("MasterCard") || pBanco.equals("VISA") || pBanco.equals("American Express"))
        {
            return true;
        }
        return false;
    }
    
    public boolean verificarFecha(String fecha)
    {
        boolean rta = false;
        DateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        String fechaN = "31/" + fecha;
        Date hoy = new Date();
        try
        {
           Date fechaVen = form.parse(fechaN);
           Calendar myCal = Calendar.getInstance();
           myCal.setTime(fechaVen);
           myCal.add(Calendar.MONTH, -1);
           fechaVen= myCal.getTime();
            
           if(!hoy.after(fechaVen))
           {
               rta= true;
           }
        }
        catch (Exception ex)
        {
            
        }
        return rta;
        
        
    }
    
    public TarjetaEntity create(TarjetaEntity tarjeta) throws BusinessLogicException
    {
        if(verificarBanco(tarjeta.getBanco()))
        {
            if(verificarFecha(tarjeta.getFechaCaducidad()))
            {
                return persistence.create(tarjeta);
            }
            else
            {
                throw new BusinessLogicException("La fecha de caducidad no cumple los requisitos");
            }
            
        }
        else
        {
            throw new BusinessLogicException("El Banco no tiene uno de los valores aceptados");
        }
    }
    
    public TarjetaEntity update(TarjetaEntity tarjeta) throws BusinessLogicException
    {
        if(verificarBanco(tarjeta.getBanco()))
        {
           if(verificarFecha(tarjeta.getFechaCaducidad()))
            {
                return persistence.update(tarjeta);
            }
            else
            {
                throw new BusinessLogicException("La fecha de caducidad no cumple los requisitos");
            }
        }
        else
        {
            throw new BusinessLogicException("El Banco no tiene uno de los valores aceptados");
        }
    }
    
    public TarjetaEntity find(Long id)
    {
        return persistence.find(id);
    }
    
    public List<TarjetaEntity> findAll()
    {
        return persistence.findAll();
    }
    
    public void delete(Long id)
    {
        persistence.delete(id);
    }
}
