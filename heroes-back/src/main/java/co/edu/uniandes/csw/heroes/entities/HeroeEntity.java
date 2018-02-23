/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.heroes.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Profesor
 */
@Entity
public class HeroeEntity extends BaseEntity {
    
    
    //----------------------
    //  Relacion
    //----------------------
    
    @PodamExclude 
    @OneToMany (cascade = CascadeType.ALL)
    private List <VillanoEntity> villanos;
    
    
    private String name;
    private String habilidad;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public HeroeEntity() {
    }

    //----------------------
    //  Relacion
    //----------------------

    public List<VillanoEntity> getVillanos() {
        return villanos;
    }

    public void setVillanos(List<VillanoEntity> villanos) {
        this.villanos = villanos;
    }
    
}
