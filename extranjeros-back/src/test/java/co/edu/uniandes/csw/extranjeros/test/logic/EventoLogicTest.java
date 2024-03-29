/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;

import co.edu.uniandes.csw.extranjeros.ejb.EventoLogic;
import co.edu.uniandes.csw.extranjeros.entities.EventoEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.EventoPersistence;
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
public class EventoLogicTest {
    
    /**
     * Inyección de la dependencia a la clase EventoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private EventoLogic eventoLogic;
    
    /**
     * Contexto de Persostencia que se va autilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    /**
     *
     */
    private List<EventoEntity> data = new ArrayList<EventoEntity>();

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Eventos, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EventoEntity.class.getPackage())
                .addPackage(EventoLogic.class.getPackage())
                .addPackage(EventoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    /**
     * Configuración inicial de la prueba.
     *
     *
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
        em.createQuery("delete from EventoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EventoEntity entity = factory.manufacturePojo(EventoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una Evento.
     *
     *
     */
    @Test
    public void createEventoTest() {
        try
        {
            PodamFactory factory = new PodamFactoryImpl();
            EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
            EventoEntity result = eventoLogic.create(newEntity);

            Assert.assertNotNull(result);

            EventoEntity entity = em.find(EventoEntity.class, result.getId());

            Assert.assertEquals(newEntity.getNombreEvento(), entity.getNombreEvento());
            Assert.assertEquals(newEntity.getTipoEvento(), entity.getTipoEvento());
            Assert.assertEquals(newEntity.getFechaEvento(), entity.getFechaEvento());
            Assert.assertEquals(newEntity.getDistanciaVivienda(), entity.getDistanciaVivienda());
            Assert.assertEquals(newEntity.getUbicacionLon(), entity.getUbicacionLon());
            Assert.assertEquals(newEntity.getUbicacionLat(), entity.getUbicacionLat());
            Assert.assertEquals(newEntity.isPrivado(), entity.isPrivado());
            Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        }
        catch(BusinessLogicException ex)
        {
            
        }
        
    }
    
    /**
     * Prueba para consultar una Evento.
     *
     *
     */
    @Test
    public void getEventoTest() {
        EventoEntity entity = data.get(0);
        EventoEntity newEntity = eventoLogic.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getNombreEvento(), entity.getNombreEvento());
        Assert.assertEquals(newEntity.getTipoEvento(), entity.getTipoEvento());
        Assert.assertEquals(newEntity.getFechaEvento(), entity.getFechaEvento());
        Assert.assertEquals(newEntity.getDistanciaVivienda(), entity.getDistanciaVivienda());
        Assert.assertEquals(newEntity.getUbicacionLon(), entity.getUbicacionLon());
        Assert.assertEquals(newEntity.getUbicacionLat(), entity.getUbicacionLat());
        Assert.assertEquals(newEntity.isPrivado(), entity.isPrivado());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
    }
    
    /**
     * Prueba para consultar la lista de Eventos.
     *
     *
     */
    @Test
    public void getEventosTest() {
        List<EventoEntity> list = eventoLogic.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (EventoEntity ent : list) {
            boolean found = false;
            for (EventoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para eliminar una Evento.
     *
     *
     */
    @Test
    public void deleteEventoTest() {
        EventoEntity entity = data.get(0);
        eventoLogic.delete(entity.getId());
        EventoEntity deleted = em.find(EventoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una Evento.
     *
     *
     */
    @Test
    public void updateEventoTest() {
        try
        {
            EventoEntity entity = data.get(0);
            PodamFactory factory = new PodamFactoryImpl();
            EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);

            newEntity.setId(entity.getId());

            eventoLogic.update(newEntity);

            EventoEntity resp = em.find(EventoEntity.class, entity.getId());

            Assert.assertEquals(newEntity.getNombreEvento(), resp.getNombreEvento());
            Assert.assertEquals(newEntity.getTipoEvento(), resp.getTipoEvento());
            Assert.assertEquals(newEntity.getFechaEvento(), resp.getFechaEvento());
            Assert.assertEquals(newEntity.getDistanciaVivienda(), resp.getDistanciaVivienda());
            Assert.assertEquals(newEntity.getUbicacionLon(), resp.getUbicacionLon());
            Assert.assertEquals(newEntity.getUbicacionLat(), resp.getUbicacionLat());
            Assert.assertEquals(newEntity.isPrivado(), resp.isPrivado());
            Assert.assertEquals(newEntity.getCapacidad(), resp.getCapacidad());
        }
        catch(BusinessLogicException ex)
        {
            
        }
    }
    
    /**
     * Prueba que verifica que la fecha cumpla con la regla
     */
    @Test
    public void verificarFechaTest()
    {
        //Fecha que cumple 
        boolean rta = eventoLogic.validarFecha("10/08/2019 15:00");
        Assert.assertEquals(true, rta);
        
        //Prueba que falla
        rta = eventoLogic.validarFecha("01/03/2018 15:00");
        Assert.assertEquals(false, rta);
    }
    
    /**
     * Prueba que verifica que se cumpla con la regla de la capacidad
     */
    @Test
    public void verificarCapacidadTest()
    {
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i<20; i++)
        {
            Integer inte = new Integer(10);
            lista.add(inte);
        }
        
        //Prueba exitosa
        boolean rta = eventoLogic.validarCapacidad(lista, 25);
        Assert.assertEquals(true, rta);
        
        //Prueba que falla
        rta = eventoLogic.validarCapacidad(lista, 19);
        Assert.assertEquals(false, rta);
    }
    
    /**
     * Prueba que verifica que el tipo de evento sea alguno de los establecidos
     */
    @Test
    public void verificarTipoTest()
    {
        //Prueba Exitosa
        boolean rta = eventoLogic.validarTipoEvento("Fiesta");
        Assert.assertEquals(true, rta);
        
        //Prueba que falla
        rta = eventoLogic.validarTipoEvento("Full Farra mariquis");
        Assert.assertEquals(false, rta);
        
    }
    
    /**
     * Prueba que verifica que el nombre de evento no contiene palabras ofensivas
     */
    @Test
    public void verificarNombreTest()
    {
        //Prueba Exitosa
        boolean rta = eventoLogic.validarNombreEvento("Cumple");
        Assert.assertEquals(true, rta);
        
        //Prueba que falla
        rta = eventoLogic.validarNombreEvento("afucka");
        Assert.assertEquals(false, rta);
        
    }
    
}
