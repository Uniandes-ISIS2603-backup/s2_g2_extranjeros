/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;


import co.edu.uniandes.csw.extranjeros.ejb.ValoracionLogic;
import co.edu.uniandes.csw.extranjeros.ejb.ViviendaLogic;
import co.edu.uniandes.csw.extranjeros.entities.ValoracionEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.persistence.ValoracionPersistence;
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
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author jd.arango
 */
@RunWith(Arquillian.class)
public class ValoracionLogicTest {
     
   private PodamFactory factory = new PodamFactoryImpl();
    /**
     * Inyección de la dependencia a la clase viviendaLogic cuyos métodos
     * se van a probar.
     */
    @Inject
    private ValoracionLogic valoracionLogic;
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

    private List<ValoracionEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ValoracionEntity.class.getPackage())
                .addPackage(ValoracionLogic.class.getPackage())
                .addPackage(ValoracionPersistence.class.getPackage())
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from ValoracionEntity").executeUpdate();
    }
    
    
     
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 20; i++) {
            ValoracionEntity entity = factory.manufacturePojo(ValoracionEntity.class);
           em.persist(entity);
            data.add(entity);
        }
    }
    
      /**
     * Prueba para crear una Valoracion.
     *
     *
     */
    @Test
    public void createValoracionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ValoracionEntity newEntity = factory.manufacturePojo(ValoracionEntity.class);
        ValoracionEntity result = valoracionLogic.createValoracion(newEntity);

        Assert.assertNotNull(result);

        ValoracionEntity entity = em.find(ValoracionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getValoracion(), entity.getValoracion());
       }
    

   /**
     * Prueba para consultar la lista de Employees.
     *
     *
     */
    @Test
    public void getValoracionsTest() {
        List<ValoracionEntity> list = valoracionLogic.getValoraciones();
        Assert.assertEquals(data.size(), list.size());
        for (ValoracionEntity ent : list) {
            boolean found = false;
            for (ValoracionEntity entity : data) {
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
    public void getValoracionTest() {
        ValoracionEntity entity = data.get(0);
        ValoracionEntity newEntity = valoracionLogic.getValoracion(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
     Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getValoracion(), entity.getValoracion());
    
    }

    /**
     * Prueba para eliminar un Employee.
     *
     *
     */
    @Test
    public void deleteValoracionTest() {
        ValoracionEntity entity = data.get(0);
        valoracionLogic.deleteValoracion(entity.getId());
        ValoracionEntity deleted = em.find(ValoracionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Employee.
     *
     *
     */
    @Test
    public void updateValoracionTest() {
        ValoracionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ValoracionEntity newEntity = factory.manufacturePojo(ValoracionEntity.class);

        newEntity.setId(entity.getId());

        valoracionLogic.updateValoracion(newEntity);

        ValoracionEntity resp = em.find(ValoracionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getComentario(), resp.getComentario());
        Assert.assertEquals(newEntity.getValoracion(), resp.getValoracion());
      
    }

}
