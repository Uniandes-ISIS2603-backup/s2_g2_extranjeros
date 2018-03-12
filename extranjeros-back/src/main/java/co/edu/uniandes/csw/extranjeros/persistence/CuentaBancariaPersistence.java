/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.persistence;

import co.edu.uniandes.csw.extranjeros.entities.CuentaBancariaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author jr.pacheco10
 */

@Stateless
public class CuentaBancariaPersistence {

    //---------------------------------------------------
    // LOGGER: Syso(...)
    //---------------------------------------------------
    private static final Logger LOGGER = Logger.getLogger(CuentaBancariaPersistence.class.getName());
    
    //---------------------------------------------------
    // Contexto de Persistencia y Controlador Entity
    //---------------------------------------------------
    @PersistenceContext(unitName = "ExtranjerosPU")
    protected EntityManager em;

    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------
    
    /**
     * Busca en la Base de Datos si existe una cuenta de banco asociada al ID dado por parametro.
     * @param cuentaId ID de la cuenta bancaria que se consulta en las relaciones de la BD.
     * @param arrendatarioID Id del Arrendatario que se consulta en las relaciones de la BD. 
     * @return Retorna la cuenta de banco a la que corresponde la ID dada.
     */
    public CuentaBancariaEntity find(Long cuentaId, Long arrendatarioID) {
        LOGGER.log(Level.INFO, "Consultando la cuenta de banco con id = {0}", cuentaId);
        
        TypedQuery<CuentaBancariaEntity> query = em.createQuery("select p from CuentaBancariaEntity p where (p.arrendatarioTitular.id = :arrendatarioID) and (p.id = :cuentaId)", CuentaBancariaEntity.class);
        query.setParameter("arrendatarioID", arrendatarioID);
        query.setParameter("cuentaId", cuentaId);
        
        
        List <CuentaBancariaEntity> resultado = query.getResultList();
        CuentaBancariaEntity retorno = null;
        
        if(resultado == null){
            return retorno;
        }
        
        else if (resultado.isEmpty()){
            return retorno;
        }
        else if (resultado.size() >= 1){ 
            retorno = resultado.get(0);
        }
        return retorno;
    }
    
    /**
     * Crea una cuenta de banco dentro de su relacion en la Base de Datos
     * @param pCuBan Cuenta bancaria que se desea ingresar a la BD.
     * @return Retorna la cuenta bancaria creada y su ID asociado en la BD.
     */
    public CuentaBancariaEntity create(CuentaBancariaEntity pCuBan) {
        LOGGER.info("Generando una nueva cuenta de banco");
        em.persist(pCuBan);
        LOGGER.info("La cuenta de banco ha sido exitósamente creada");
        return pCuBan;
    }
    /**
     * Actualiza una cuenta bancaria dentro de la Base de Datos.
     * @param pCuBan Nueva cuenta de banco que sera remplazado por la vieja en la BD.
     * @return Retorna la cuenta de banco que ha sido actualizado y esta ahora en la BD.
     */
    public CuentaBancariaEntity update(CuentaBancariaEntity pCuBan) {
        LOGGER.log(Level.INFO, "Actualizando la cuenta de banco con el id={0}", pCuBan.getId());
        return em.merge(pCuBan);
    }
    /**
     * Borra la cuenta bancaria asociado con el ID dado de la Base de Datos.
     * @param id Identificador de la cuenta de banco a borrar en la BD.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Eliminando la Cuenta Bancaria con el id={0}",id);
        CuentaBancariaEntity cuentaEliminar = em.find(CuentaBancariaEntity.class, id);
        em.remove(cuentaEliminar);
    }
}
