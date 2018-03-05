/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;

import co.edu.uniandes.csw.extranjeros.ejb.ProvidenciaLogic;
import co.edu.uniandes.csw.extranjeros.entities.ProvidenciaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.ProvidenciaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author am.quintero12
 */
@RunWith(Arquillian.class)
public class ProvidenciaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ProvidenciaLogic providenciaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ProvidenciaEntity> data = new ArrayList<ProvidenciaEntity>();
    
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de servicio, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProvidenciaEntity.class.getPackage())
                .addPackage(ProvidenciaLogic.class.getPackage())
                .addPackage(ProvidenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from ProvidenciaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            ProvidenciaEntity entity = factory.manufacturePojo(ProvidenciaEntity.class);
            em.persist(entity);
            data.add(entity);
         
        }

    }
    
     /**
     * Prueba para crear una Universidad
     *
     *
     */
    @Test
    public void createProvidenciaTest() throws BusinessLogicException {
        ProvidenciaEntity newEntity = factory.manufacturePojo(ProvidenciaEntity.class);
        ProvidenciaEntity result = providenciaLogic.createProvidencia(newEntity);
        Assert.assertNotNull(result);
        ProvidenciaEntity entity = em.find(ProvidenciaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getPais(), entity.getPais());
        Assert.assertEquals(newEntity.getRegion(), entity.getRegion());
    }

    /**
     * Prueba para consultar la lista de Universidades
     *
     *
     */
    @Test
    public void getProvidenciasTest() {
        List<ProvidenciaEntity> list = providenciaLogic.getProvidencias();
        Assert.assertEquals(data.size(), list.size());
        for (ProvidenciaEntity entity : list) {
            boolean found = false;
            for (ProvidenciaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Universidad
     *
     *
     */
    @Test
    public void getProvidenciaTest() {
        ProvidenciaEntity entity = data.get(0);
        ProvidenciaEntity resultEntity = providenciaLogic.getProvidencia(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getPais(), resultEntity.getPais());
        Assert.assertEquals(entity.getRegion(), resultEntity.getRegion());
    }

    /**
     * Prueba para eliminar una universidad
     *
     *
     */
    
    
    @Test
    public void deleteProvidenciaTest() {
        ProvidenciaEntity entity = data.get(0);
        providenciaLogic.deleteProvidencia(entity.getId());
        ProvidenciaEntity deleted = em.find(ProvidenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    

    /**
     * Prueba para actualizar una Universidad
     *
     *
     */
      
    @Test
    public void updateProvidenciaTest() {
        ProvidenciaEntity entity = data.get(0);
        ProvidenciaEntity pojoEntity = factory.manufacturePojo(ProvidenciaEntity.class);

        pojoEntity.setId(entity.getId());

        try {
            providenciaLogic.updatProvidencia(pojoEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ProvidenciaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        ProvidenciaEntity resp = em.find(ProvidenciaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getPais(), resp.getPais());
        Assert.assertEquals(pojoEntity.getRegion(), resp.getRegion());
    }
   
    
}
