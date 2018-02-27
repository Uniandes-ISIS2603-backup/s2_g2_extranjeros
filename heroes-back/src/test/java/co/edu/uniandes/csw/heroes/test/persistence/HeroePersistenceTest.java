package co.edu.uniandes.csw.heroes.test.persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.edu.uniandes.csw.heroes.entities.HeroeEntity;
import co.edu.uniandes.csw.heroes.entities.VillanoEntity;
import co.edu.uniandes.csw.heroes.persistence.HeroePersistence;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Ruby C
 */
@RunWith(Arquillian.class)
public class HeroePersistenceTest {

    @Inject
    HeroePersistence heroePersistence;

    @PersistenceContext
    EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HeroeEntity.class.getPackage())
                .addPackage(HeroePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createHeroeTest() {
        PodamFactory factory = new PodamFactoryImpl();
        HeroeEntity newEntity = factory.manufacturePojo(HeroeEntity.class);
        HeroeEntity result = heroePersistence.create(newEntity);

        Assert.assertNotNull(result);

        HeroeEntity entity = em.find(HeroeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());     
        Assert.assertEquals(newEntity.getHabilidad(), entity.getHabilidad());  
        
        // Tamanio
        Assert.assertEquals(newEntity.getVillanos().size(), entity.getVillanos().size());
        
        // Objetos
        List <VillanoEntity> villanosNewEntity = newEntity.getVillanos();
        List <VillanoEntity> villanosEntity = entity.getVillanos();
        
        for (VillanoEntity villanonew : villanosNewEntity){
            boolean encontrado = false;
            
            for (VillanoEntity villanoEnt : villanosEntity){
                
                if(villanonew.getId().equals(villanoEnt.getId())){
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    
    @Test
    public void deleteHeroeTest(){
        
        // Dado que no Hay data, genero un Heroe para despúes borrarlo
//        PodamFactory factory = new PodamFactoryImpl();
//        HeroeEntity newEntity = factory.manufacturePojo(HeroeEntity.class);
//        HeroeEntity result = heroePersistence.create(newEntity);
//
//        Assert.assertNotNull(result);
//        em.remove(result);
//        
//        Assert.assertNull(result);
    }
}
