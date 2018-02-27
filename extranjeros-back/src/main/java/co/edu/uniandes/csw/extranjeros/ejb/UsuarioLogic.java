/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.entities.UsuarioEntity;
import co.edu.uniandes.csw.extranjeros.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de Usuario.
 * @author jr.pacheco10
 */
@Stateless
public class UsuarioLogic {
    
    //---------------------------------------------------
    // LOGGER: Syso(...)
    //---------------------------------------------------
     private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());   


    //---------------------------------------------------
    // Inject: Logica asociaciones y Persistence Clase
    //---------------------------------------------------
    
     @Inject
     private UsuarioPersistence usuarioPersistence; 

    //---------------------------------------------------
    // Metodos Usuario(DTO) - Resource: sin relaciones
    //---------------------------------------------------
     
     //-- GET ONE
    /**
     * Obtiene los datos de una instancia de Usuario a partir de su ID (identificador).
     * @param id Identificador de la instancia a consultar.
     * @return Instancia de UsuarioEntity con los datos del Usuario consultado.
     */
     public UsuarioEntity getUsuario(Long id){
          LOGGER.log(Level.INFO, "Inicia proceso de consultar un usuario con id = {0}", id);
          return usuarioPersistence.find(id);
     }
     
     //-- GET ALL
    /**
     * Obtiene la lista de los registros de Usuario.
     * @return Colección de objetos de UsuarioEntity.
     */
     public List <UsuarioEntity> getUsuarios(){
         LOGGER.info("Inicia proceso de consultar todos los usuarios de la plataforma");
         return usuarioPersistence.findAll();
     }
     
     //-- CREATE
    /**
     * Se encarga de crear un Usuario en la base de datos.
     * @param newUser Objeto de UsuarioEntity con los datos nuevos.
     * @return Objeto de UsuarioEntity con los datos nuevos y su ID.
     */
    public UsuarioEntity createUsuario(UsuarioEntity newUser){
        LOGGER.log(Level.INFO, "Inicia el proceso de crear un Usuario en la plataforma");
        return usuarioPersistence.create(newUser);
    }
    
    //-- UPDATE
    /**
     * Actualiza la información de una instancia de Usuario.
     * @param newUser Instancia de UsuarioEntity con los nuevos datos.
     * @return Instancia de UsuarioEntity con los datos actualizados.
     */
    public UsuarioEntity updateUsuario(UsuarioEntity newUser){
        LOGGER.log(Level.INFO, "Inicia el proceso de actualizar un Usuario en la plataforma");
        return usuarioPersistence.update(newUser);
    }
    
    //-- DELETE
    /**
     * Elimina una instancia de Usuario de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteUsuario(Long id){
        LOGGER.log(Level.INFO, "Inicia el proceso de borrado del Usuario con id = {0}", id);
        usuarioPersistence.delete(id);
    }   

    //---------------------------------------------------
    // Metodos UsuarioDetail - Resource: con relaciones
    //---------------------------------------------------
    
    //------------------------
    //  RELACION CON FACTURAS:
    //------------------------
    
    //-- GET FACTURAS ASOCIADAS:
    public List<FacturaEntity> getFacturas(Long userID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar las Facturas asociadas al usuario con id = {0}", userID);
        return getUsuario(userID).getFacturas();
    }
    
    //-- GET FACTURA ESPECIFICA ASOCIADO AL USUARIO
    /**
     * Obtiene una instancia de FacturaEntity asociada a una instancia de Usuario
     * @param userID Identificador de la instancia de Usuario.
     * @param facturaID Identificador de la instancia de Factura.
     * @return La entidadd de Factura correspondiente al autor.
     */
    public FacturaEntity getFactura(Long userID, Long facturaID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar la Factura asociada con id = {0}", facturaID + " " + "del usuario con id = " + userID);
        
        // Lista
        List<FacturaEntity> facturasUsuario = getUsuario(userID).getFacturas();
        
        // Creacion FacturaEntity para comparacion/busqueda
        FacturaEntity facturaBuscada = new FacturaEntity();
        facturaBuscada.setId(facturaID);
        
        // ¿Existe esa entidad con ID dado en la Lista?
        int indiceFacturaBuscada = facturasUsuario.indexOf(facturaBuscada);
        
        // Respuesta en el caso de que exista.
        if (indiceFacturaBuscada >= 0){
            return facturasUsuario.get(indiceFacturaBuscada);
        } 
        
        // No existe
        return null;
    } 
            
    
}
