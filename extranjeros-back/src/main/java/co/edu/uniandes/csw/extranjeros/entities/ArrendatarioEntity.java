/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.entities;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @author jr.pacheco10
 */

@Entity
public class ArrendatarioEntity extends UsuarioEntity implements Serializable {
    
    //---------------------------------------------------
    // Atributos Relacionales
    //---------------------------------------------------
    
    @PodamExclude
    @ManyToMany(mappedBy = "arrendatariosAsociados")
    private List <FacturaEntity> facturas;
    
    @PodamExclude
    @OneToMany(mappedBy = "arrendatariosPropietarios", cascade = CascadeType.ALL)
    private List <ViviendaEntity> viviendas;
    
    
    //---------------------------------------------------
    // Atributos
    //---------------------------------------------------
    private String nombre;
    
    
    //---------------------------------------------------
    // Metodos
    //---------------------------------------------------
    
    /**
     * @return Obtiene el nombre del arrendatario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Crea el nombre asociado a un usuario de tipo arrendatario
     * @param pNombre Nombre a asociar 
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }
    
        
    //---------------------------------------------------
    // Metodos atributos Relacionales
    //--------------------------------------------------- 
    
    /**
     * @return La lista de facturas asociadas a un usuario.
     */
    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    /**
     * Crea o modifica la lista de Facturas asociadas a un Usuario.
     * @param facturas Lista de Facturas.
     */
    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    /**
     * @return La lista de viviendas asociadas a un usuario.
     */
    public List<ViviendaEntity> getViviendas() {
        return viviendas;
    }

    /**
     * Crea o modifica la lista de Viviendas asociadas a un Usuario.
     * @param viviendas Lista de Facturas. 
     */
    public void setViviendas(List<ViviendaEntity> viviendas) {
        this.viviendas = viviendas;
    }
    
}
