/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.heroes.dtos;

import co.edu.uniandes.csw.heroes.entities.VillanoEntity;
import java.util.Date;

/**
 * @author jr.pacheco10
 */
public class VillanoDTO {
 
    private Long id;
    private String name;
    private boolean preso;
    private Date fechaArresto;
    
    public VillanoDTO(){
        
    }
    
    public VillanoDTO(VillanoEntity villanoEn){
        
        if (villanoEn != null){
        this.id = villanoEn.getId();
        this.name = villanoEn.getNombre();
        this.preso = villanoEn.isPreso();
        this.fechaArresto = villanoEn.getFechaArresto();   
        }	
    }
    
    public VillanoEntity toEntity(){
        VillanoEntity nuevoEntity = new VillanoEntity();
        
        nuevoEntity.setId(this.id);
        nuevoEntity.setNombre(this.name);
        nuevoEntity.setPreso(this.preso);
        nuevoEntity.setFechaArresto(this.fechaArresto);
        
        return nuevoEntity;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public boolean isPreso() {
        return preso;
    }

    public void setPreso(boolean preso) {
        this.preso = preso;
    }

    public Date getFechaArresto() {
        return fechaArresto;
    }

    public void setFechaArresto(Date fechaArresto) {
        this.fechaArresto = fechaArresto;
    }
    
    
    
    
    
    
}
