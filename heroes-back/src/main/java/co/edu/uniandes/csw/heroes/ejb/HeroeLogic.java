/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.heroes.ejb;

import co.edu.uniandes.csw.heroes.entities.HeroeEntity;
import co.edu.uniandes.csw.heroes.entities.VillanoEntity;
import co.edu.uniandes.csw.heroes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.heroes.persistence.HeroePersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class HeroeLogic {
 
    @Inject
    private HeroePersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
   
    public HeroeEntity getHeroe(Long id) {
        return persistence.find(id);
    }
    
    //TODO modificar el método createHeroe
  
    public HeroeEntity createHeroe(HeroeEntity entity) throws BusinessLogicException{
        
        String nombreIngresar = entity.getName();
        HeroeEntity busqueda = persistence.findByName(nombreIngresar);
        
        if (busqueda != null){
            throw new BusinessLogicException("Existe un Heroe con el mismo nombre en la Plataforma");
        }
        
        List <VillanoEntity> villanosDeHeroe = entity.getVillanos();
        boolean presos = true;
        boolean esMenor = true;
        
        Date fechaHoy = new Date();
        
        for(VillanoEntity villano : villanosDeHeroe){

            if (villano.isPreso() == false){
                presos = false;
                break;
            }

            else if (villano.getFechaArresto().before(fechaHoy)){
                esMenor = false;
                break;
            }
        }
        
        if (presos == false){
            throw new BusinessLogicException("El Heroe que desea crear, no ha capturado a todos sus Villanos");
        }
        
        if (esMenor == false){
            throw new BusinessLogicException("La fecha de captura de los Villanos no es menor a la actual.");
        }
        
        return persistence.create(entity);
    }
    
    
    public void deleteHeroe(Long id) throws BusinessLogicException{
        
        HeroeEntity heroeEncontrar = getHeroe(id);
        if (heroeEncontrar.getVillanos().isEmpty()){
         throw new BusinessLogicException("El heroe no tiene Villanos asociados.");   
        }
        
        persistence.delete(id);
    }
}
