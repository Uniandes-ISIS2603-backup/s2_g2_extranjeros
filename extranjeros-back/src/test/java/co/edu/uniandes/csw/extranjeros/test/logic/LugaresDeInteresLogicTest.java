/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;


import co.edu.uniandes.csw.extranjeros.ejb.LugaresDeInteresLogic;
import co.edu.uniandes.csw.extranjeros.entities.LugaresDeInteresEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.LugaresDeInteresPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author o.amaya724
 */
@RunWith(Arquillian.class)
public class LugaresDeInteresLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private LugaresDeInteresLogic lugaresDeInteresLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<LugaresDeInteresEntity> data = new ArrayList<LugaresDeInteresEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugaresDeInteresEntity.class.getPackage())
                .addPackage(LugaresDeInteresLogic.class.getPackage())
                .addPackage(LugaresDeInteresPersistence.class.getPackage())
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
        em.createQuery("delete from LugaresDeInteresEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            LugaresDeInteresEntity entity = factory.manufacturePojo(LugaresDeInteresEntity.class);
            em.persist(entity);
            data.add(entity);
         
        }

    }

    /**
     * Prueba para crear un lugar de interes
     *
     *
     */
    @Test
    public void createLugarDeInteresTest() throws BusinessLogicException {
        LugaresDeInteresEntity newEntity = factory.manufacturePojo(LugaresDeInteresEntity.class);
        LugaresDeInteresEntity result = lugaresDeInteresLogic.createLugarDeInteres(newEntity);
        Assert.assertNotNull(result);
        LugaresDeInteresEntity entity = em.find(LugaresDeInteresEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getUbicacionLat(), entity.getUbicacionLat());
        Assert.assertEquals(newEntity.getUbicacionLon(), entity.getUbicacionLon());
        
    }
    
   

    /**
     * Prueba para consultar la lista de lugares de interes
     *
     *
     */
    @Test
    public void getLugaresDeInteresTest() {
        List<LugaresDeInteresEntity> list = lugaresDeInteresLogic.getLugaresDeInteres();
        Assert.assertEquals(data.size(), list.size());
        for (LugaresDeInteresEntity entity : list) {
            boolean found = false;
            for (LugaresDeInteresEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un lugar de interes
     *
     *
     */
    @Test
    public void getLugarDeInteresTest() {
        LugaresDeInteresEntity entity = data.get(0);
        LugaresDeInteresEntity resultEntity = lugaresDeInteresLogic.getLugarDeInteres(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getUbicacionLat(), resultEntity.getUbicacionLat());
        Assert.assertEquals(entity.getUbicacionLon(), resultEntity.getUbicacionLon());
        Assert.assertEquals(entity.getTipo(), resultEntity.getTipo());
        Assert.assertEquals(entity.getTelefono(), resultEntity.getTelefono());
    }

    /**
     * Prueba para eliminar un lugar de interes
     *
     *
     */
    
    
    @Test
    public void deleteLugarDeInteresTest() {
        LugaresDeInteresEntity entity = data.get(0);
        lugaresDeInteresLogic.deleteLugarDeInteres(entity.getId());
        LugaresDeInteresEntity deleted = em.find(LugaresDeInteresEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    

    /**
     * Prueba para actualizar una Universidad
     *
     *
     */
    
    
    @Test
    public void updateLugarDeInteresTest() throws BusinessLogicException {
        LugaresDeInteresEntity entity = data.get(0);
        LugaresDeInteresEntity pojoEntity = factory.manufacturePojo(LugaresDeInteresEntity.class);

        pojoEntity.setId(entity.getId());

        lugaresDeInteresLogic.updateLugarDeInteres(pojoEntity);

        LugaresDeInteresEntity resp = em.find(LugaresDeInteresEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getUbicacionLat(), resp.getUbicacionLat());
        Assert.assertEquals(pojoEntity.getUbicacionLon(), resp.getUbicacionLon());
        Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo());
        Assert.assertEquals(pojoEntity.getTelefono(), resp.getTelefono());
    }
   
}
