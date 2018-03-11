/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;

import co.edu.uniandes.csw.extranjeros.ejb.ViviendaLogic;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.arango
 */
@RunWith(Arquillian.class)
public class ViviendaLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();
    /**
     * Inyección de la dependencia a la clase viviendaLogic cuyos métodos
     * se van a probar.
     */
    @Inject
    private ViviendaLogic viviendaLogic;
    /**
     * Contexto de persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    /**
     * Inyección de variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas, permite la transaccionalidad.
     */
    @Inject
    private UserTransaction utx;

    private List<ViviendaEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViviendaEntity.class.getPackage())
                 .addPackage(ViviendaLogic.class.getPackage())
                .addPackage(ViviendaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Configuración inicial de la prueba.
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
     */
    private void clearData() {
        em.createQuery("delete from ViviendaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.

     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ViviendaEntity entity = factory.manufacturePojo(ViviendaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear una vivienda.
     */
    @Test
    public void createViviendaTest() throws BusinessLogicException {
           PodamFactory factory = new PodamFactoryImpl();
        ViviendaEntity newEntity = factory.manufacturePojo(ViviendaEntity.class);
        ViviendaEntity result = viviendaLogic.createVivienda(newEntity);

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
    /**
     * Prueba para consultar la lista de viviendas.
     */
    @Test
    public void getViviendasTest() 
    {
          List<ViviendaEntity> list = viviendaLogic.getViviendas();
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
     * Prueba para consultar una vivienda.
     *
     */
    @Test
    public void getViviendaTest() {
        ViviendaEntity entity = data.get(0);
        ViviendaEntity newEntity = viviendaLogic.getVivienda(entity.getId());
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
     * Prueba para eliminar un vivienda.
     */
    @Test
    public void deleteViviendaTest() {
          ViviendaEntity entity = data.get(0);
        viviendaLogic.deleteVivienda(entity.getId());
        ViviendaEntity deleted = em.find(ViviendaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una vivienda.
     *
     *
     */
    @Test
    public void updateViviendaTest() throws BusinessLogicException {
        ViviendaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ViviendaEntity newEntity = factory.manufacturePojo(ViviendaEntity.class);

        newEntity.setId(entity.getId());

        viviendaLogic.updateVivienda(newEntity);

        ViviendaEntity resp = em.find(ViviendaEntity.class, entity.getId());

         Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getCapacidad(), resp.getCapacidad());
        Assert.assertEquals(newEntity.getDireccion(), resp.getDireccion());
        Assert.assertEquals(newEntity.getLatitud(), resp.getLatitud());
        Assert.assertEquals(newEntity.getLongitud(), resp.getLongitud());
        Assert.assertEquals(newEntity.getTipoAlojamiento(), resp.getTipoAlojamiento());
        Assert.assertEquals(newEntity.getInquilinos(), resp.getInquilinos());
   
          }
   
}
