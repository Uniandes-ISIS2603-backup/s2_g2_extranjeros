/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.persistence;

import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import co.edu.uniandes.csw.extranjeros.entities.ProvidenciaEntity;
import co.edu.uniandes.csw.extranjeros.persistence.ProvidenciaPersistence;
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
 * @author am.quintero12
 */

@RunWith (Arquillian.class)
public class ProvidenciaPersistenceTest {
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de servicio, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProvidenciaEntity.class.getPackage())
                .addPackage(ProvidenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");              
        
    }
    
     /**
     * Inyección de la dependencia a la clase ServicioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ProvidenciaPersistence providenciaPersistence;
    
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
        em.createQuery("delete from ProvidenciaEntity").executeUpdate();
    }
    
    /**
     * Lista de universidades que representa los datos de la base de datos temporal.
     */
    private List<ProvidenciaEntity> data = new ArrayList<ProvidenciaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            ProvidenciaEntity providencia = factory.manufacturePojo(ProvidenciaEntity.class);
            em.persist(providencia);
            data.add(providencia);
        }
    }
    
    
     /**
     * Prueba para crear una Providencia
     */
    @Test
    public void createProvidenciaTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ProvidenciaEntity providencia1 = factory.manufacturePojo(ProvidenciaEntity.class);
        ProvidenciaEntity result = providenciaPersistence.create(providencia1);
        
        Assert.assertNotNull(result);

        ProvidenciaEntity providencia2 = em.find(ProvidenciaEntity.class, result.getId());
        
        Assert.assertEquals(providencia1.getPais(), providencia2.getPais());
        Assert.assertEquals(providencia1.getRegion(), providencia2.getRegion());
        Assert.assertEquals(providencia1.getId(), providencia2.getId());

    }
    
    /**
     * Prueba para consultar todas las providencias
     * */
    
    @Test
    public void getProvidenciasTest()
    {
        List<ProvidenciaEntity> providenciaPR= providenciaPersistence.findAll();
        Assert.assertEquals(providenciaPR.size(), data.size());
        for(ProvidenciaEntity temp : providenciaPR)
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
     * Prueba para consultar una providencia.
     */
    @Test
    public void getProvidenciaTest()
    {
        ProvidenciaEntity providencia1=data.get(0);
        ProvidenciaEntity providencia2 = providenciaPersistence.find(providencia1.getId());
        Assert.assertNotNull(providencia2);
        
        Assert.assertEquals(providencia1.getPais(), providencia2.getPais());
        Assert.assertEquals(providencia1.getRegion(), providencia2.getRegion());
        Assert.assertEquals(providencia1.getId(), providencia2.getId());
           
    }
    
    /**
     * Prueba para eliminar una providencia.
     */
    @Test
    public void deleteUniversidadTest() {
        ProvidenciaEntity providencia = data.get(0);
        providenciaPersistence.delete(providencia.getId());
        ProvidenciaEntity deleted = em.find(ProvidenciaEntity.class, providencia.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una providencia
     */
    @Test
    public void updateProvidenciaTest() {
        ProvidenciaEntity providencia1 = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ProvidenciaEntity nuevaProvidencia = factory.manufacturePojo(ProvidenciaEntity.class);

        nuevaProvidencia.setId(providencia1.getId());

        providenciaPersistence.update(nuevaProvidencia);

        ProvidenciaEntity providencia2 = em.find(ProvidenciaEntity.class, providencia1.getId());

        Assert.assertEquals(providencia1.getPais(), providencia2.getPais());
        Assert.assertEquals(providencia1.getRegion(), providencia2.getRegion());
        Assert.assertEquals(providencia1.getId(), providencia2.getId());
    }
    
    
}
