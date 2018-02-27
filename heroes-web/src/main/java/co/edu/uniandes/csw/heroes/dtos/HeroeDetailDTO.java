/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.heroes.dtos;

import co.edu.uniandes.csw.heroes.entities.HeroeEntity;
import co.edu.uniandes.csw.heroes.entities.VillanoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Profesor
 */
public class HeroeDetailDTO extends HeroeDTO{
    
    private List <VillanoDTO> villanos;
    
    
    public HeroeDetailDTO(){
        
    }
    
    public HeroeDetailDTO(HeroeEntity entity){
        super(entity);
        if (entity != null){
           
            List<VillanoEntity> villanosE = entity.getVillanos();
            for (VillanoEntity vEntity : villanosE){
                villanos.add(new VillanoDTO(vEntity));
            }
        }
    }
    
    @Override
    public HeroeEntity toEntity(){
        HeroeEntity heroe = super.toEntity();
        if (villanos != null){
            
            List<VillanoEntity> entidad = new ArrayList<>();
            
            for (VillanoDTO villano : villanos){
               entidad.add(villano.toEntity());
            }
            heroe.setVillanos(entidad);
        }
        return heroe;
    }
    
    public List<VillanoDTO> getVillanos() {
        return villanos;
    }

    public void setVillanos(List<VillanoDTO> villanos) {
        this.villanos = villanos;
    }

    
    
    
}
