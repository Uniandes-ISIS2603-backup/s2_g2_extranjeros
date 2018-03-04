/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.ejb;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.ArrendatarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jr.pacheco10
 */

@Stateless
public class ArrendatarioLogic {
    
    //---------------------------------------------------
    // LOGGER: Syso(...)
    //---------------------------------------------------
     private static final Logger LOGGER = Logger.getLogger(ArrendatarioLogic.class.getName());

    //---------------------------------------------------
    // Inject: Logica asociaciones y Persistence Clase
    //---------------------------------------------------
    @Inject  
    private ArrendatarioPersistence persistence;

    //---------------------------------------------------
    // Metodos Usuario(DTO) - Resource: sin relaciones
    //---------------------------------------------------
     
     //-- GET ONE
    /**
     * Obtiene los datos de una instancia de Arrendatario a partir de su ID (identificador).
     * @param id Identificador de la instancia a consultar.
     * @return Instancia de ArrendatarioEntity con los datos del Usuario consultado.
     */
    public ArrendatarioEntity getArrendatario(Long id){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un usuario con id = {0}", id);
        return persistence.find(id);
    }
    
    //-- GET ALL
    /**
     * Obtiene la lista de los registros de Arrendatario.
     * @return Colección de objetos de ArrendatarioEntity.
     */
     public List <ArrendatarioEntity> getArrendatarios(){
         LOGGER.info("Inicia proceso de consultar todos los arrendatarios de la plataforma");
         return persistence.findAll();
     }
     
     //-- CREATE
    /**
     * Se encarga de crear un Arrendatario en la base de datos.
     * @param newUser Objeto de ArrendatarioEntity con los datos nuevos.
     * @return Objeto de ArrendatarioEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException
     */
    public ArrendatarioEntity createArrendatario (ArrendatarioEntity newUser) throws BusinessLogicException{
        
        LOGGER.log(Level.INFO, "Inicia el proceso de crear un arrendatario en la plataforma");
        
        ArrendatarioEntity buscado = persistence.findByName(newUser.getNombre());
        if (buscado != null){
            throw new BusinessLogicException("Hay un arrendatario con el mismo nombre");
        }
        
        ArrendatarioEntity buscadoPorLogin = persistence.findByLogin(newUser.getUsuario());
        if (buscadoPorLogin != null){
            throw new BusinessLogicException("Existe un Usuario con el mismo login");
        }
        
        
        if (newUser.getEdad() < 18){
            throw new BusinessLogicException("El Usuario no puede ser menor de edad");
        }
        
        if(newUser.getClave().length() < 8 || newUser.getClave().length() > 12){
            throw new BusinessLogicException("Su contraseña debe tener más de 8 caracteres y menos de 12");
        }
        
        boolean encontradoNumero = false;
        char[] caracteres = newUser.getClave().toCharArray();
        for (int i = 0; i < caracteres.length; i++){
            
            String m = String.valueOf(caracteres[i]);
            if( Integer.class.isInstance(Integer.parseInt(m))){
                encontradoNumero = true;
            }
        } 
        
        if (encontradoNumero == false){
            throw new BusinessLogicException("Su clave debe contener al menos un numero");
        }
        
        if (!newUser.getCorreo().contains("@") || !newUser.getCorreo().contains(".com")){
            throw new BusinessLogicException("Su correo no es válido.");
        }
        
        String celular = String.valueOf(newUser.getCelular());
        char numeros[] = celular.toCharArray();
        
        // El numero de acuerdo a la Proveniencia se verificará después del cambio de concepto
        // de Usuario (hacerlo abstracto). Por ahora será de acuerdo a nuestro pais.
        if (numeros.length != 10){
            throw new BusinessLogicException("Ingrese un celular válido para Colombia.");
        }
        
        
        return persistence.create(newUser);
    }
    
    //-- UPDATE
    /**
     * Actualiza la información de una instancia de Arrendatario.
     * @param newUser Instancia de ArrendatarioEntity con los nuevos datos.
     * @return Instancia de ArrendatarioEntity con los datos actualizados.
     */
    public ArrendatarioEntity updateArrendatario (ArrendatarioEntity newUser){
        LOGGER.info("Inicia el proceso de actualizar un arrendatario en la plataforma");
        return persistence.update(newUser);
    }
    
    //-- DELETE
    /**
     * Elimina una instancia de Arrendatario de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     */
    public void deleteArrendatario (Long id){
        LOGGER.log(Level.INFO, "Inicia el proceso de borrado del arrendatario con id = {0}", id);
        persistence.delete(id);
    }
    
    //---------------------------------------------------
    // Metodos UsuarioDetail - Resource: con relaciones
    //---------------------------------------------------
    
    //------------------------
    //  RELACION CON FACTURAS:
    //------------------------
    
    //-- GET FACTURAS ASOCIADAS:
    public List<FacturaEntity> getFacturas(Long userID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar las Facturas asociadas al Arrendatario con id = {0}", userID);
        return getArrendatario(userID).getFacturas();
    }
    
    //-- GET FACTURA ESPECIFICA ASOCIADO AL USUARIO
    /**
     * Obtiene una instancia de FacturaEntity asociada a una instancia de Arrendatario
     * @param userID Identificador de la instancia de Arrendatario.
     * @param facturaID Identificador de la instancia de Factura.
     * @return La entidadd de Factura correspondiente al autor.
     */
    public FacturaEntity getFactura(Long userID, Long facturaID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar la Factura asociada con id = {0}", facturaID + " " + "del Arrendatario con id = " + userID);
        
        // Lista
        List<FacturaEntity> facturasUsuario = getArrendatario(userID).getFacturas();
        
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
    
    
    //------------------------
    //  RELACION CON VIVIENDAS:
    //------------------------
         
    //-- GET VIVIENDAS ASOCIADAS:
    public List<ViviendaEntity> getViviendas(Long userID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar las Viviendas asociadas al Arrendatario con id = {0}", userID);
        return getArrendatario(userID).getViviendas();
    }
    
    //-- GET FACTURA ESPECIFICA ASOCIADO AL USUARIO
    /**
     * Obtiene una instancia de FacturaEntity asociada a una instancia de Usuario
     * @param userID Identificador de la instancia de Usuario.
     * @param viviendaID Identificador de la instancia de Vivienda.
     * @return La entidadd de Factura correspondiente al autor.
     */
    public ViviendaEntity getVivienda(Long userID, Long viviendaID){
        LOGGER.log(Level.INFO, "Inicia el proceso para consultar la Vivienda asociada con id = {0}", viviendaID + " " + "del usuario con id = " + userID);
        
        // Lista
        List<ViviendaEntity> viviendasUsuario = getArrendatario(userID).getViviendas();
        
        // Creacion FacturaEntity para comparacion/busqueda
        ViviendaEntity viviendaBuscada = new ViviendaEntity();
        viviendaBuscada.setId(viviendaID);
        
        // ¿Existe esa entidad con ID dado en la Lista?
        int indiceFacturaBuscada = viviendasUsuario.indexOf(viviendaBuscada);
        
        // Respuesta en el caso de que exista.
        if (indiceFacturaBuscada >= 0){
            return viviendasUsuario.get(indiceFacturaBuscada);
        } 
        
        // No existe
        return null;
    }
}
