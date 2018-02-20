/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.persistence;


import co.edu.uniandes.csw.extranjeros.entities.LugaresDeInteresEntity;
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
 * @author Oliver
 */
@RunWith(Arquillian.class)
public class LugaresDeInteresPersistenceTest {
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de facturas, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugaresDeInteresEntity.class.getPackage())
                .addPackage(LugaresDeInteresPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Inyección de la dependencia a la clase lugaresDeInteresPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private LugaresDeInteresPersistence lugaresDeInteresPersistence;

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

    /**
     * Configuración inicial de la prueba.
     */
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
     */
    private void clearData() {
        em.createQuery("delete from LugaresDeInteresEntity").executeUpdate();
    }

    /**
     * Lista de lugares de interes que representa los datos de la base de datos temporal.
     */
    private List<LugaresDeInteresEntity> data = new ArrayList<LugaresDeInteresEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            LugaresDeInteresEntity lugarDeInteres = factory.manufacturePojo(LugaresDeInteresEntity.class);
            em.persist(lugarDeInteres);
            data.add(lugarDeInteres);
        }
    }

    /**
     * Prueba para crear un lugar de Interes.
     */
    @Test
    public void createLugarDeInteresTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        LugaresDeInteresEntity lugarDeInteres1 = factory.manufacturePojo(LugaresDeInteresEntity.class);
        LugaresDeInteresEntity result = lugaresDeInteresPersistence.create(lugarDeInteres1);
        
        Assert.assertNotNull(result);

        LugaresDeInteresEntity lugarDeInteres2 = em.find(LugaresDeInteresEntity.class, result.getId());
        
        Assert.assertEquals(lugarDeInteres1.getNombre(), lugarDeInteres2.getNombre());
        Assert.assertEquals(lugarDeInteres1.getDireccion(), lugarDeInteres2.getDireccion());
        Assert.assertEquals(lugarDeInteres1.getTipo(), lugarDeInteres2.getTipo());
        Assert.assertEquals(lugarDeInteres1.getUbicacionLon(), lugarDeInteres2.getUbicacionLon());
        Assert.assertEquals(lugarDeInteres1.getUbicacionLat(), lugarDeInteres2.getUbicacionLat());
        Assert.assertEquals(lugarDeInteres1.getTelefono(), lugarDeInteres2.getTelefono());
        Assert.assertEquals(lugarDeInteres1.getId(), lugarDeInteres2.getId());

    }
    
    /**
     * Prueba para consultar todos los lugares de interes.
     */
    @Test
    public void getLugaresDeInteresTest()
    {
        List<LugaresDeInteresEntity> lugaresDeInteresPR=lugaresDeInteresPersistence.findAll();
        Assert.assertEquals(lugaresDeInteresPR.size(), data.size());
        for(LugaresDeInteresEntity temp : lugaresDeInteresPR)
        {
            boolean encontrado=false;
            for (int i = 0; i < data.size()&&!encontrado; i++) 
            {
                if(temp.getId().equals(data.get(i).getId()))
                    encontrado=true;
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    /**
     * Prueba para consultar un lugar de interes.
     */
    @Test
    public void getLugarDeInteresTest()
    {
        LugaresDeInteresEntity lugarDeInteres1=data.get(0);
        LugaresDeInteresEntity lugarDeInteres2=lugaresDeInteresPersistence.find(lugarDeInteres1.getId());
        Assert.assertNotNull(lugarDeInteres2);
        Assert.assertEquals(lugarDeInteres1.getNombre(), lugarDeInteres2.getNombre());
        Assert.assertEquals(lugarDeInteres1.getDireccion(), lugarDeInteres2.getDireccion());
        Assert.assertEquals(lugarDeInteres1.getTipo(), lugarDeInteres2.getTipo());
        Assert.assertEquals(lugarDeInteres1.getUbicacionLon(), lugarDeInteres2.getUbicacionLon());
        Assert.assertEquals(lugarDeInteres1.getUbicacionLat(), lugarDeInteres2.getUbicacionLat());
        Assert.assertEquals(lugarDeInteres1.getTelefono(), lugarDeInteres2.getTelefono());
        Assert.assertEquals(lugarDeInteres1.getId(), lugarDeInteres2.getId());
    }
    
    /**
     * Prueba para eliminar un lugar de interes.
     *
     *
     */
    @Test
    public void deleteLugarDeInteresTest() {
        LugaresDeInteresEntity lugarDeInteres = data.get(0);
        lugaresDeInteresPersistence.delete(lugarDeInteres.getId());
        LugaresDeInteresEntity deleted = em.find(LugaresDeInteresEntity.class, lugarDeInteres.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un lugar de interes.
     *
     *
     */
    @Test
    public void updateUniversidadTest() {
        LugaresDeInteresEntity lugarDeInteres = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LugaresDeInteresEntity newLugarDeInteres = factory.manufacturePojo(LugaresDeInteresEntity.class);

        newLugarDeInteres.setId(lugarDeInteres.getId());

        lugaresDeInteresPersistence.update(newLugarDeInteres);

        LugaresDeInteresEntity resp = em.find(LugaresDeInteresEntity.class, lugarDeInteres.getId());

        Assert.assertEquals(newLugarDeInteres.getNombre(), resp.getNombre());
        Assert.assertEquals(newLugarDeInteres.getDireccion(), resp.getDireccion());
        Assert.assertEquals(newLugarDeInteres.getTipo(), resp.getTipo());
        Assert.assertEquals(newLugarDeInteres.getUbicacionLon(), resp.getUbicacionLon());
        Assert.assertEquals(newLugarDeInteres.getUbicacionLat(), resp.getUbicacionLat());
        Assert.assertEquals(newLugarDeInteres.getTelefono(), resp.getTelefono());
        Assert.assertEquals(newLugarDeInteres.getId(), resp.getId());
    }
}
