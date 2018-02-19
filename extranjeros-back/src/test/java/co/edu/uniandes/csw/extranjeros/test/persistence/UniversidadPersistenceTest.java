/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.persistence;


import co.edu.uniandes.csw.extranjeros.entities.UniversidadEntity;
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
 * @author Oliver
 */

@RunWith(Arquillian.class)
public class UniversidadPersistenceTest {
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de facturas, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UniversidadEntity.class.getPackage())
                .addPackage(UniversidadPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Inyección de la dependencia a la clase universidadPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private UniversidadPersistence universidadPersistence;

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
        em.createQuery("delete from UniversidadEntity").executeUpdate();
    }

    /**
     * Lista de universidades que representa los datos de la base de datos temporal.
     */
    private List<UniversidadEntity> data = new ArrayList<UniversidadEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            UniversidadEntity universidad = factory.manufacturePojo(UniversidadEntity.class);
            em.persist(universidad);
            data.add(universidad);
        }
    }

    /**
     * Prueba para crear una universidad.
     */
    @Test
    public void createUniversidadTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        UniversidadEntity universidad1 = factory.manufacturePojo(UniversidadEntity.class);
        UniversidadEntity result = universidadPersistence.create(universidad1);
        
        Assert.assertNotNull(result);

        UniversidadEntity universidad2 = em.find(UniversidadEntity.class, result.getId());
        
        Assert.assertEquals(universidad1.getNombre(), universidad2.getNombre());
        Assert.assertEquals(universidad1.getDireccion(), universidad2.getDireccion());
        Assert.assertEquals(universidad1.getUbicacionLat(), universidad2.getUbicacionLat());
        Assert.assertEquals(universidad1.getUbicacionLon(), universidad2.getUbicacionLon());
        Assert.assertEquals(universidad1.getId(), universidad2.getId());

    }
    
    /**
     * Prueba para consultar todas las universidades.
     */
    @Test
    public void getUniversidadesTest()
    {
        List<UniversidadEntity> universidadesPR=universidadPersistence.findAll();
        Assert.assertEquals(universidadesPR.size(), data.size());
        for(UniversidadEntity temp : universidadesPR)
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
     * Prueba para consultar una universidad.
     */
    @Test
    public void getUniversidadTest()
    {
        UniversidadEntity universidad1=data.get(0);
        UniversidadEntity universidad2=universidadPersistence.find(universidad1.getId());
        Assert.assertNotNull(universidad2);
        Assert.assertEquals(universidad1.getNombre(), universidad2.getNombre());
        Assert.assertEquals(universidad1.getDireccion(), universidad2.getDireccion());
        Assert.assertEquals(universidad1.getUbicacionLat(), universidad2.getUbicacionLat());
        Assert.assertEquals(universidad1.getUbicacionLon(), universidad2.getUbicacionLon());
        Assert.assertEquals(universidad1.getId(), universidad2.getId());
    }
    
    /**
     * Prueba para eliminar una universidad.
     *
     *
     */
    @Test
    public void deleteUniversidadTest() {
        UniversidadEntity universidad = data.get(0);
        universidadPersistence.delete(universidad.getId());
        UniversidadEntity deleted = em.find(UniversidadEntity.class, universidad.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar una universidad.
     *
     *
     */
    @Test
    public void updateUniversidadTest() {
        UniversidadEntity universidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UniversidadEntity newUniversidad = factory.manufacturePojo(UniversidadEntity.class);

        newUniversidad.setId(universidad.getId());

        universidadPersistence.update(newUniversidad);

        UniversidadEntity resp = em.find(UniversidadEntity.class, universidad.getId());

        Assert.assertEquals(newUniversidad.getNombre(), resp.getNombre());
        Assert.assertEquals(newUniversidad.getDireccion(), resp.getDireccion());
        Assert.assertEquals(newUniversidad.getUbicacionLat(), resp.getUbicacionLat());
        Assert.assertEquals(newUniversidad.getUbicacionLon(), resp.getUbicacionLon());
        Assert.assertEquals(newUniversidad.getId(), resp.getId());;
    }
    
    
}
