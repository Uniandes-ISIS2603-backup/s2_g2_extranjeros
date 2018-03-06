/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.entities.CuentaBancariaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.CuentaBancariaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jr.pacheco10
 */

@Stateless
public class CuentaBancariaLogic {
    
    //---------------------------------------------------
    // LOGGER: Syso(...)
    //---------------------------------------------------
     private static final Logger LOGGER = Logger.getLogger(CuentaBancariaLogic.class.getName());

    //---------------------------------------------------
    // Inject: Logica asociaciones y Persistence Clase
    //---------------------------------------------------
    @Inject  
    private CuentaBancariaPersistence persistence;
    
    @Inject
    private ArrendatarioLogic arrendatarioLogic;

    //---------------------------------------------------
    // Metodos necesario - Estructura
    //---------------------------------------------------    
    
    public List <CuentaBancariaEntity> getCuentasBancarias(Long arrendatarioID) throws BusinessLogicException{
         LOGGER.info("Inicia proceso de consultar las cuentas bancarias del Usuario");
         ArrendatarioEntity arrendEntity = arrendatarioLogic.getArrendatario(arrendatarioID);
         
        if (arrendEntity.getCuentaBancaria()== null) {
            throw new BusinessLogicException("El libro que consulta aún no tiene reviews"); 
        }
        
        List <CuentaBancariaEntity> retorno = new ArrayList<>();
        retorno.add(arrendEntity.getCuentaBancaria());
        return retorno;
    }
    
    //---------------------------------------------------
    // Metodos Usuario(DTO) - Resource: sin relaciones
    //---------------------------------------------------
    
    //-- GET ONE
    /**
     * Obtiene los datos de una instancia de Cuenta Bancaria a partir del  ID de su titular el el suyo propio (identificador).
     * @param cuentaID ID de la cuenta bancaria que se consulta en las relaciones de la BD.
     * @param titularID Id del Arrendatario que se consulta en las relaciones de la BD. 
     * @return Retorna la cuenta bancaria al que corresponde la ID dada.
     */
    public CuentaBancariaEntity getCuentaBancaria(Long titularID, Long cuentaID ){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la cuenta de Banco con id = {0}", cuentaID);
        return persistence.find(cuentaID, titularID);
    }
    
    /**
     * Se encarga de crear una Cuenta Bancaria en la base de datos.
     * @param entity Objeto de CuentaBancariaEntity con los datos nuevos
     * @param titularID id del arrendatario el cual sera padre de la nueva cuenta.
     * @return Objeto de CuentaBancariaEntity con los datos nuevos y su ID.
     * 
     */
    public CuentaBancariaEntity createReview(Long titularID, CuentaBancariaEntity entity) {
        LOGGER.info("Inicia proceso de crear una Cuenta Bancaria.");
        ArrendatarioEntity arrendatario = arrendatarioLogic.getArrendatario(titularID);
        entity.setArrendatarioTitular(arrendatario);
        return persistence.create(entity);
    }

    //-- UPDATE
    /**
     * Actualiza la información de una instancia de cuenta Bancaria.
     * @param entity Instancia de CuentaBancariaEntity con los nuevos datos.
     * @param arrendID id del Arrendatario el cual sera padre del Cuenta Bancaria actualizado.
     * @return Instancia de CuentaBancariaEntity con los datos actualizados.
     */
    public CuentaBancariaEntity updateCuentaDeBanco(Long arrendID, CuentaBancariaEntity entity) {
        LOGGER.info("Inicia proceso de actualizar una cuenta de banco.");
        ArrendatarioEntity arrendatario = arrendatarioLogic.getArrendatario(arrendID);
        if(arrendatario!= null){entity.setArrendatarioTitular(arrendatario);}
        return persistence.update(entity);
    }

    //-- DELETE
    /**
     * Elimina una instancia de Cuenta Bancaria de la base de datos.
     * @param cuentaID ID de la cuenta bancaria que se consulta en las relaciones de la BD.
     * @param titularID Id del Arrendatario que se consulta en las relaciones de la BD. 
     */
    public void deleteReview(Long titularID, Long cuentaID) {
        LOGGER.info("Inicia proceso de borrar una cuenta de banco.");
        CuentaBancariaEntity old = getCuentaBancaria(titularID, cuentaID);
        persistence.delete(old.getId());
    }
}
