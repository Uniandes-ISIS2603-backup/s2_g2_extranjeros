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
         
        if (arrendEntity.getCuentasBancarias()== null) {
            throw new BusinessLogicException("El arrendatario que se esta consultando aún no tiene una cuenta bancaria."); 
        }
        
        if (arrendEntity.getCuentasBancarias().isEmpty()) {
            throw new BusinessLogicException("El arrendatario que se esta consultando aún no tiene una cuenta bancaria."); 
        }
        
        return arrendEntity.getCuentasBancarias();
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
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException
     */
    public CuentaBancariaEntity getCuentaBancaria(Long titularID, Long cuentaID ) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la cuenta de Banco con id = {0}", cuentaID);
        if(persistence.find(cuentaID, titularID) == null){
            throw new BusinessLogicException("El arrendatario que se esta consultando aún no tiene una cuenta bancaria o esta no existe."); 
        }
        return persistence.find(cuentaID, titularID);
    }
    
    /**
     * Se encarga de crear una Cuenta Bancaria en la base de datos.
     * @param entity Objeto de CuentaBancariaEntity con los datos nuevos
     * @param titularID id del arrendatario el cual sera padre de la nueva cuenta.
     * @return Objeto de CuentaBancariaEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException 
     * 
     */
    public CuentaBancariaEntity createCuentaBancaria(Long titularID, CuentaBancariaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de crear una Cuenta Bancaria.");
        ArrendatarioEntity arrendatario = arrendatarioLogic.getArrendatario(titularID);
        entity.setArrendatarioTitular(arrendatario);
        CuentaBancariaEntity existe = persistence.findByNumeroCuenta(entity.getNumeroCuenta());
        if (existe != null){
            throw new BusinessLogicException("Existe una cuenta de banco con el mismo Numero de Cuenta."); 
        }
        
        if(!entity.getTipoCuenta().equals("Ahorros") && !entity.getTipoCuenta().equals("Corriente")){
            throw new BusinessLogicException("Su cuenta debe ser de Ahorros o Corriente."); 
        }
        return persistence.create(entity);
    }

    //-- UPDATE
    /**
     * Actualiza la información de una instancia de cuenta Bancaria.
     * @param entity Instancia de CuentaBancariaEntity con los nuevos datos.
     * @param arrendID id del Arrendatario el cual sera padre del Cuenta Bancaria actualizado.
     * @return Instancia de CuentaBancariaEntity con los datos actualizados.
     * @throws BusinessLogicException En caso de que el arrendatario no exista. 
     */
    public CuentaBancariaEntity updateCuentaDeBanco(Long arrendID, CuentaBancariaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de actualizar una cuenta de banco.");
        ArrendatarioEntity arrendatario = arrendatarioLogic.getArrendatario(arrendID);
        if(arrendatario == null){
           throw new BusinessLogicException("El arrendatario que se esta consultando no existe.");  
        }
        entity.setArrendatarioTitular(arrendatario);
        return persistence.update(entity);
    }

    //-- DELETE
    /**
     * Elimina una instancia de Cuenta Bancaria de la base de datos.
     * @param cuentaID ID de la cuenta bancaria que se consulta en las relaciones de la BD.
     * @param titularID Id del Arrendatario que se consulta en las relaciones de la BD. 
     * @throws BusinessLogicException 
     */
    public void deleteReview(Long titularID, Long cuentaID) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de borrar una cuenta de banco.");
        CuentaBancariaEntity old = getCuentaBancaria(titularID, cuentaID);
        if(old == null){
          throw new BusinessLogicException("El arrendatario que se esta consultando no existe o aún no tiene una cuenta de banco.");   
        }
        persistence.delete(old.getId());
    }
}
