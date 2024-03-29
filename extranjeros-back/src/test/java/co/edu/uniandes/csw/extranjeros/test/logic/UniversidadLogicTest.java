/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;

import co.edu.uniandes.csw.extranjeros.ejb.UniversidadLogic;
import co.edu.uniandes.csw.extranjeros.entities.UniversidadEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.UniversidadPersistence;
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
public class UniversidadLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UniversidadLogic universidadLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<UniversidadEntity> data = new ArrayList<UniversidadEntity>();
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de servicio, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UniversidadEntity.class.getPackage())
                .addPackage(UniversidadLogic.class.getPackage())
                .addPackage(UniversidadPersistence.class.getPackage())
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
        em.createQuery("delete from UniversidadEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            UniversidadEntity entity = factory.manufacturePojo(UniversidadEntity.class);
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
    public void createUniversidadTest() throws BusinessLogicException {
        UniversidadEntity newEntity = factory.manufacturePojo(UniversidadEntity.class);
        UniversidadEntity result = universidadLogic.createUniversidad(newEntity);
        Assert.assertNotNull(result);
        UniversidadEntity entity = em.find(UniversidadEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getUbicacionLat(), entity.getUbicacionLat());
        Assert.assertEquals(newEntity.getUbicacionLon(), entity.getUbicacionLon());
        
    }

    /**
     * Prueba para consultar la lista de Universidades
     *
     *
     */
    @Test
    public void getUniversidadesTest() {
        List<UniversidadEntity> list = universidadLogic.getUniversidades();
        Assert.assertEquals(data.size(), list.size());
        for (UniversidadEntity entity : list) {
            boolean found = false;
            for (UniversidadEntity storedEntity : data) {
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
    public void getUniversidadTest() {
        UniversidadEntity entity = data.get(0);
        UniversidadEntity resultEntity = universidadLogic.getUniversidad(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getUbicacionLat(), resultEntity.getUbicacionLat());
        Assert.assertEquals(entity.getUbicacionLon(), resultEntity.getUbicacionLon());
    }

    /**
     * Prueba para eliminar una universidad
     *
     *
     */
    
    
    @Test
    public void deleteUniversidadTest() {
        UniversidadEntity entity = data.get(0);
        universidadLogic.deleteUniversidad(entity.getId());
        UniversidadEntity deleted = em.find(UniversidadEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    

    /**
     * Prueba para actualizar una Universidad
     *
     *
     */
    
    
    @Test
    public void updateAuthorTest() throws BusinessLogicException {
        UniversidadEntity entity = data.get(0);
        UniversidadEntity pojoEntity = factory.manufacturePojo(UniversidadEntity.class);

        pojoEntity.setId(entity.getId());

        universidadLogic.updateUniversidad(pojoEntity) ;

        UniversidadEntity resp = em.find(UniversidadEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getUbicacionLat(), resp.getUbicacionLat());
        Assert.assertEquals(pojoEntity.getUbicacionLon(), resp.getUbicacionLon());
    }
   
}

