/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.dtos;
import co.edu.uniandes.csw.extranjeros.entities.CuentaBancariaEntity;

/**
 * Clase que extiende de {@link CuentaBancariaDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido de la ciudad vaya a la documentacion de {@link CuentaBancariaDTO}
 * @author jr.pacheco10
 */
public class CuentaBancariaDetailDTO extends CuentaBancariaDTO {

    //---------------------------------------------------
    // Atributos relacionales
    //---------------------------------------------------
    
    private ArrendatarioDTO arrendatarioTitular;
    
    //---------------------------------------------------
    // Constructor
    //---------------------------------------------------
    
    /**
     * Constructor por defecto de la Clase.
     */
    public CuentaBancariaDetailDTO(){
        super();
    }
    
      /**
     * Crea un objeto ArrendatarioDetailDTO a partir de un objeto ArrendatarioEntity.
     * @param entity Entidad ArrendatarioEntity desde la cual se va a crear el nuevo
     * objeto.
     */
     public CuentaBancariaDetailDTO(CuentaBancariaEntity entity) {
          super(entity);
          if (entity != null) {
              this.arrendatarioTitular = (new ArrendatarioDTO(entity.getArrendatarioTitular()));
        }
    }
     
    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------

      /**
     * Convierte un objeto CuentaBancariaDetailDTO a CuentaBancariaEntity incluyendo los
     * atributos de CuentaBancariaDTO (relacionales).
     * @return Nueva objeto CuentaBancariaEntity.
     */
    @Override    
    public CuentaBancariaEntity toEntity(){
         
        // Entity
        CuentaBancariaEntity entity = super.toEntity();
         
        // Verificacion relaciones
        if (arrendatarioTitular != null){
             entity.setArrendatarioTitular(arrendatarioTitular.toEntity());
         }
         
        // Retorno
        return entity;         
    }
    
    public ArrendatarioDTO getArrendatarioTitular() {
        return arrendatarioTitular;
    }

    public void setArrendatarioTitular(ArrendatarioDTO arrendatarioTitular) {
        this.arrendatarioTitular = arrendatarioTitular;
    }
}
