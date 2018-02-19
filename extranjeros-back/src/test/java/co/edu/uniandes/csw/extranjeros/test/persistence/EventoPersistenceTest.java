/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.persistence;

import co.edu.uniandes.csw.extranjeros.entities.EventoEntity;
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
public class EventoPersistenceTest {
    
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
                .addPackage(EventoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase EventoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private EventoPersistence eventoPersistence;
    
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
     *
     */
    private List<EventoEntity> data = new ArrayList<EventoEntity>();

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
        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);
        EventoEntity result = eventoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EventoEntity entity = em.find(EventoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getResponsableEventoP(), entity.getResponsableEventoP());
        Assert.assertEquals(newEntity.getNombreEvento(), entity.getNombreEvento());
        Assert.assertEquals(newEntity.getTipoEvento(), entity.getTipoEvento());
        Assert.assertEquals(newEntity.getFechaEvento(), entity.getFechaEvento());
        Assert.assertEquals(newEntity.getDistanciaVivienda(), entity.getDistanciaVivienda());
        Assert.assertEquals(newEntity.getUbicacionLon(), entity.getUbicacionLon());
        Assert.assertEquals(newEntity.getUbicacionLat(), entity.getUbicacionLat());
        Assert.assertEquals(newEntity.getPrivado(), entity.getPrivado());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
    }
    
    /**
     * Prueba para consultar una Evento.
     *
     *
     */
    @Test
    public void getEventoTest() {
        EventoEntity entity = data.get(0);
        EventoEntity newEntity = eventoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getResponsableEventoP(), entity.getResponsableEventoP());
        Assert.assertEquals(newEntity.getNombreEvento(), entity.getNombreEvento());
        Assert.assertEquals(newEntity.getTipoEvento(), entity.getTipoEvento());
        Assert.assertEquals(newEntity.getFechaEvento(), entity.getFechaEvento());
        Assert.assertEquals(newEntity.getDistanciaVivienda(), entity.getDistanciaVivienda());
        Assert.assertEquals(newEntity.getUbicacionLon(), entity.getUbicacionLon());
        Assert.assertEquals(newEntity.getUbicacionLat(), entity.getUbicacionLat());
        Assert.assertEquals(newEntity.getPrivado(), entity.getPrivado());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
    }
    
    /**
     * Prueba para consultar la lista de Eventos.
     *
     *
     */
    @Test
    public void getEventosTest() {
        List<EventoEntity> list = eventoPersistence.findAll();
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
        eventoPersistence.delete(entity);
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
        EventoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EventoEntity newEntity = factory.manufacturePojo(EventoEntity.class);

        newEntity.setId(entity.getId());

        eventoPersistence.update(newEntity);

        EventoEntity resp = em.find(EventoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getResponsableEventoP(), entity.getResponsableEventoP());
        Assert.assertEquals(newEntity.getNombreEvento(), entity.getNombreEvento());
        Assert.assertEquals(newEntity.getTipoEvento(), entity.getTipoEvento());
        Assert.assertEquals(newEntity.getFechaEvento(), entity.getFechaEvento());
        Assert.assertEquals(newEntity.getDistanciaVivienda(), entity.getDistanciaVivienda());
        Assert.assertEquals(newEntity.getUbicacionLon(), entity.getUbicacionLon());
        Assert.assertEquals(newEntity.getUbicacionLat(), entity.getUbicacionLat());
        Assert.assertEquals(newEntity.getPrivado(), entity.getPrivado());
        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
    }
    
}
