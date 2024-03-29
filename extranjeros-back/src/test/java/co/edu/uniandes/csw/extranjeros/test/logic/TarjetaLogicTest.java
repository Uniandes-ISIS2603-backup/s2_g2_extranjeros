/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;

import co.edu.uniandes.csw.extranjeros.ejb.TarjetaLogic;
import co.edu.uniandes.csw.extranjeros.entities.TarjetaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.TarjetaPersistence;
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
 * @author la.ruiz967
 */
@RunWith(Arquillian.class)
public class TarjetaLogicTest {
    
    
    /**
     * Inyección de la dependencia a la clase ServicioLogic cuyos métodos
     * se van a probar.
     */
    @Inject
    private TarjetaLogic tarjetaLogic;
    
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
    
    private List<TarjetaEntity> data = new ArrayList<TarjetaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaEntity.class.getPackage())
                .addPackage(TarjetaLogic.class.getPackage())
                .addPackage(TarjetaPersistence.class.getPackage())
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
        em.createQuery("delete from TarjetaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.

     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TarjetaEntity entity = factory.manufacturePojo(TarjetaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una Tarjeta.
     *
     *
     */
    @Test
    public void createTarjetaTest() {
        try
        {
            PodamFactory factory = new PodamFactoryImpl();
            TarjetaEntity newEntity = factory.manufacturePojo(TarjetaEntity.class);
            TarjetaEntity result = tarjetaLogic.create(newEntity);

            Assert.assertNotNull(result);

            TarjetaEntity entity = em.find(TarjetaEntity.class, result.getId());

            Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
            Assert.assertEquals(newEntity.getBanco(), entity.getBanco());
            Assert.assertEquals(newEntity.getFechaCaducidad(), entity.getFechaCaducidad());  
        }
        catch(BusinessLogicException ex)
        {
            
        }
 
    }
    
    /**
     * Prueba para consultar una Tarjeta.
     *
     *
     */
    @Test
    public void getTarjetaTest() {
        TarjetaEntity entity = data.get(0);
        TarjetaEntity newEntity = tarjetaLogic.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNumero(), newEntity.getNumero());
        Assert.assertEquals(entity.getBanco(), newEntity.getBanco());
        Assert.assertEquals(entity.getFechaCaducidad(), newEntity.getFechaCaducidad());
    }
    
    /**
     * Prueba para consultar la lista de Tarjetas.
     *
     *
     */
    @Test
    public void getTarjetasTest() {
        List<TarjetaEntity> list = tarjetaLogic.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaEntity ent : list) {
            boolean found = false;
            for (TarjetaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para eliminar una Tarjeta.
     *
     *
     */
    @Test
    public void deleteTarjetaTest() {
        TarjetaEntity entity = data.get(0);
        tarjetaLogic.delete(entity.getId());
        TarjetaEntity deleted = em.find(TarjetaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una Tarjeta.
     *
     *
     */
    @Test
    public void updateTarjetaTest() {
        try
        {
            TarjetaEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            TarjetaEntity newEntity = factory.manufacturePojo(TarjetaEntity.class);

            newEntity.setId(entity.getId());

            tarjetaLogic.update(newEntity);

            TarjetaEntity resp = em.find(TarjetaEntity.class, entity.getId());

            Assert.assertEquals(newEntity.getNumero(), resp.getNumero());
            Assert.assertEquals(newEntity.getBanco(), resp.getBanco());
            Assert.assertEquals(newEntity.getFechaCaducidad(), resp.getFechaCaducidad());
        }
        catch (BusinessLogicException ex)
        {
            
        }
        
    }
    
    /**
     * Prueba de verificar que la franquicia se encuentra entre las soportadas.
     */
    @Test
    public void verificarBancoTest()
    {
        //Caso donde la franquicia es correcta
        boolean rta = tarjetaLogic.verificarBanco("VISA");
        Assert.assertEquals(true, rta);
        
        //Caso donde la franquicia es incorrecta
        rta = tarjetaLogic.verificarBanco("Prueba");
        Assert.assertEquals(false, rta);
    }
    
    /**
     * Prueba de verificar que el numero de la cédula cumpla con el tamaño
     */
    @Test
    public void verificarNumeroTest()
    {
        //Caso donde la franquicia es correcta
        //boolean rta = tarjetaLogic.verificarNumero(4111111111111111);
        //Assert.assertEquals(true, rta);
        
        //Caso donde la franquicia es incorrecta
        Long num = new Long(123456);
        boolean rta = tarjetaLogic.verificarNumero(num);
        Assert.assertEquals(false, rta);
    }
    
    /**
     * Prueba que verifica que la fecha cumpla con las reglas de negocio.
     */
    @Test
    public void verificarFechaTest()
    {
        //Fecha que cumple 
        boolean rta = tarjetaLogic.verificarFecha("08/2019");
        Assert.assertEquals(true, rta);
        
        //Prueba que falla
        rta = tarjetaLogic.verificarFecha("03/2018");
        Assert.assertEquals(false, rta);
    }
    
    
}
