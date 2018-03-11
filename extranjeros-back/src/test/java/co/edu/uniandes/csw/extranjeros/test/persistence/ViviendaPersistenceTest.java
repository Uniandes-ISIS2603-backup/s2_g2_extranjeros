/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.persistence;

import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.persistence.ViviendaPersistence;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.arango
 */
@RunWith(Arquillian.class)
public class ViviendaPersistenceTest {
    
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViviendaEntity.class.getPackage())
                .addPackage(ViviendaPersistence.class.getPackage())
               .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
   
    @Inject
    private  ViviendaPersistence viviendaPersistence;

    /**
     * Contexto de persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas, permite la transaccionalidad.
     */
    @Inject
    UserTransaction utx;
    
     @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
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
        em.createQuery("delete from ViviendaEntity").executeUpdate();
    }
    
    
     private List<ViviendaEntity> data = new ArrayList<ViviendaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 12; i++) {
            ViviendaEntity entity = factory.manufacturePojo(ViviendaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
      /**
     * Prueba para crear una vivienda.
     *
     *
     */
    @Test
    public void createViviendaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ViviendaEntity newEntity = factory.manufacturePojo(ViviendaEntity.class);
        ViviendaEntity result = viviendaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ViviendaEntity entity = em.find(ViviendaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
        Assert.assertEquals(newEntity.getTipoAlojamiento(), entity.getTipoAlojamiento());
        Assert.assertEquals(newEntity.getInquilinos(), entity.getInquilinos());
  
    }
    
    
    public ViviendaPersistenceTest() {
    }
    
   /**
     * Prueba para consultar la lista de Employees.
     *
     *
     */
    @Test
    public void getViviendasTest() {
        List<ViviendaEntity> list = viviendaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ViviendaEntity ent : list) {
            boolean found = false;
            for (ViviendaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Employee.
     *
     *
     */
    @Test
    public void getViviendaTest() {
        ViviendaEntity entity = data.get(0);
        ViviendaEntity newEntity = viviendaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
        Assert.assertEquals(newEntity.getLatitud(), entity.getLatitud());
        Assert.assertEquals(newEntity.getLongitud(), entity.getLongitud());
        Assert.assertEquals(newEntity.getTipoAlojamiento(), entity.getTipoAlojamiento());
        Assert.assertEquals(newEntity.getInquilinos(), entity.getInquilinos());
  
    }

    /**
     * Prueba para eliminar un Employee.
     *
     *
     */
    @Test
    public void deleteViviendaTest() {
            ViviendaEntity entity = data.get(0);
            viviendaPersistence.delete(entity.getId());
            ViviendaEntity deleted = em.find(ViviendaEntity.class, entity.getId());
            Assert.assertNull(deleted);
        }

        /**
         * Prueba para actualizar un Employee.
         *
         *
         */
        @Test
        public void updateViviendaTest() {
            ViviendaEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            ViviendaEntity newEntity = factory.manufacturePojo(ViviendaEntity.class);

            newEntity.setId(entity.getId());

            viviendaPersistence.update(newEntity);

            ViviendaEntity resp = em.find(ViviendaEntity.class, entity.getId());

             Assert.assertNotNull(newEntity);
            Assert.assertEquals(newEntity.getName(), resp.getName());
            Assert.assertEquals(newEntity.getCapacidad(), resp.getCapacidad());
            Assert.assertEquals(newEntity.getDireccion(), resp.getDireccion());
            Assert.assertEquals(newEntity.getLatitud(), resp.getLatitud());
            Assert.assertEquals(newEntity.getLongitud(), resp.getLongitud());
            Assert.assertEquals(newEntity.getTipoAlojamiento(), resp.getTipoAlojamiento());
            Assert.assertEquals(newEntity.getInquilinos(), entity.getInquilinos());
  
    }

 
}
