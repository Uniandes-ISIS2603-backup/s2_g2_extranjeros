/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.persistence;
import co.edu.uniandes.csw.extranjeros.entities.ServicioEntity;
import co.edu.uniandes.csw.extranjeros.persistence.ServicioPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author s.rodriguezm
 */
@RunWith(Arquillian.class)
public class ServicioPersistenceTest 
{
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
                .addPackage(ServicioEntity.class.getPackage())
                .addPackage(ServicioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");              
        
    }
    /**
     * Inyección de la dependencia a la clase ServicioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private ServicioPersistence servicioPersistence;

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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from ServicioEntity").executeUpdate();
    }

    /**
     * Lista de servicios que representa los datos de la base de datos temporal.
     */
    private List<ServicioEntity> data = new ArrayList<ServicioEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            ServicioEntity servicio = factory.manufacturePojo(ServicioEntity.class);
            em.persist(servicio);
            data.add(servicio);
        }
    }
    /**
     * Prueba para crear un servicio.
     */
    @Test
    public void createServicioTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ServicioEntity servicio1 = factory.manufacturePojo(ServicioEntity.class);
        ServicioEntity result = servicioPersistence.create(servicio1);
        
        Assert.assertNotNull(result);

        ServicioEntity servicio2 = em.find(ServicioEntity.class, result.getId());

        Assert.assertEquals(servicio1.getTipo(), servicio2.getTipo());
        Assert.assertEquals(servicio1.getAdicional(), servicio2.getAdicional());
    }
    /**
     * Prueba para consultar un servicio.
     */
    @Test
    public void getServicioTest()
    {
        ServicioEntity servicio1=data.get(0);
        ServicioEntity servicio2=servicioPersistence.find(servicio1.getId());
        Assert.assertNotNull(servicio2);
        Assert.assertEquals(servicio1.getTipo(), servicio2.getTipo());
        Assert.assertEquals(servicio1.getAdicional(),servicio2.getAdicional());
    }
    /**
     * Prueba para consultar todos los servicios.
     */
    @Test
    public void getServiciosTest()
    {
        List<ServicioEntity> serviciosPR=servicioPersistence.findAll();
        Assert.assertEquals(serviciosPR.size(), data.size());
        for(ServicioEntity temp : serviciosPR)
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
     * Prueba para borrar un servicio.
     */
    @Test
    public void deleteServicioTest()
    {
        Long id=data.get(0).getId();
        servicioPersistence.delete(id);
        ServicioEntity servicio=servicioPersistence.find(id);
        Assert.assertNull(servicio);
    }
    /**
     * Prueba para actualizar un servicio.
     */
    @Test
    public void updateServicioTest()
    {
        ServicioEntity servicio1 = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ServicioEntity servicio2 = factory.manufacturePojo(ServicioEntity.class);

        servicio2.setId(servicio1.getId());

        servicioPersistence.update(servicio2);

        ServicioEntity resp = em.find(ServicioEntity.class, servicio1.getId());

        Assert.assertEquals(resp.getTipo(), servicio2.getTipo());
        Assert.assertEquals(resp.getAdicional(),servicio2.getAdicional());
    }
}
