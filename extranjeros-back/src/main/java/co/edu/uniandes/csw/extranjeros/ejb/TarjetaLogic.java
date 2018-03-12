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
    
    /**
     * Método que verifica que el número de la tarjeta cumpla con la longitud establecida por la franquicia.
     * @param num
     * @return 
     */
    public boolean verificarNumero(Long num)
    {
        boolean rta = false;
        String tar = num+"";
        char[] chars = tar.toCharArray();
        if((chars[0]=='4' || chars[0]=='5') && chars.length == 16)
        {
            rta = true;
        }
        if (chars[0]=='3' && chars.length == 15)
        {
            rta = true;
        }
        return rta;
    }
    
    /**
     * Verifica que la franquicia sea alguna de las 3 seleccionadas.
     * @param pBanco
     * @return 
     */
    public boolean verificarBanco(String pBanco)
    {
        if(pBanco.equals("MasterCard") || pBanco.equals("VISA") || pBanco.equals("American Express"))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Verifica que la fecha de vencimiento de la tarjeta sea como mínimo, 1 mes calendario después de la fecha de registro.
     * @param fecha
     * @return 
     */
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
    
    /**
     * Método que crea una tarjeta verificando las reglas anteriormente descritas.
     * @param tarjeta
     * @return
     * @throws BusinessLogicException 
     */
    public TarjetaEntity create(TarjetaEntity tarjeta) throws BusinessLogicException
    {
        if(verificarBanco(tarjeta.getBanco()))
        {
            if(verificarFecha(tarjeta.getFechaCaducidad()))
            {
                if(verificarNumero(tarjeta.getNumero()))
                {
                    return persistence.create(tarjeta);
                }
                else
                {
                    throw new BusinessLogicException("El nùmero de digitos no concuerda");
                }
                
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
    
    /**
     * Método que actualiza una tarjeta validando las condiciones anteriormente mencionadas.
     * @param tarjeta
     * @return
     * @throws BusinessLogicException 
     */
    public TarjetaEntity update(TarjetaEntity tarjeta) throws BusinessLogicException
    {
        if(verificarBanco(tarjeta.getBanco()))
        {
           if(verificarFecha(tarjeta.getFechaCaducidad()))
            {
                if(verificarNumero(tarjeta.getNumero()))
                {
                    return persistence.update(tarjeta);
                }
                else
                {
                    throw new BusinessLogicException("El nùmero de digitos no concuerda");
                }
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
    
    /**
     * Método que encuentra una tarjeta dado su identificador.
     * @param id
     * @return 
     */
    public TarjetaEntity find(Long id)
    {
        return persistence.find(id);
    }
    
    /**
     * Método que retorna todas las tarjetas. 
     * @return 
     */
    public List<TarjetaEntity> findAll()
    {
        return persistence.findAll();
    }
    
    /**
     * Método que elimina una tarjeta dado su identificador.
     * @param id 
     */
    public void delete(Long id)
    {
        persistence.delete(id);
    }
}
