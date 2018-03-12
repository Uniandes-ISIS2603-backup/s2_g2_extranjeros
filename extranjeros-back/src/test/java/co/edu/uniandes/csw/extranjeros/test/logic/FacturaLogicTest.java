/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;

import co.edu.uniandes.csw.extranjeros.ejb.FacturaLogic;
import co.edu.uniandes.csw.extranjeros.ejb.ViviendaLogic;
import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.entities.ViviendaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.FacturaPersistence;
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
 * @author s.rodriguezm
 */
@RunWith(Arquillian.class)
public class FacturaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    /**
     * Inyección de la dependencia a la clase FacturaLogic cuyos métodos
     * se van a probar.
     */
    @Inject
    private FacturaLogic facturaLogic;
    /**
     * Inyección de la dependencia a la clase ViviendaLogic para confirmar reglas de negocio.
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

    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.

     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear una Factura.
     */
    @Test
    public void createFacturaTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        ViviendaEntity vivienda=factory.manufacturePojo(ViviendaEntity.class);
        viviendaLogic.createVivienda(vivienda);
        FacturaEntity result = facturaLogic.createFactura(newEntity,vivienda);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCostoFijo(), entity.getCostoFijo());
        Assert.assertEquals(newEntity.getCostosAdicionales(), entity.getCostosAdicionales());
        Assert.assertEquals(newEntity.isDividirCuentaServicios(), entity.isDividirCuentaServicios());
        Assert.assertEquals(newEntity.getFechaEntrada(), entity.getFechaEntrada());
        Assert.assertEquals(newEntity.getFechaSalida(), entity.getFechaSalida());
        Assert.assertEquals(newEntity.getCostosAdicionales(), entity.getCostosAdicionales());
        Assert.assertEquals(newEntity.getIVA(), entity.getIVA());
    }
    /**
     * Prueba para consultar la lista de Facturas.
     */
    @Test
    public void getFacturasTest() 
    {
        List<FacturaEntity> list = facturaLogic.getFacturas();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity entity : list) {
            boolean found = false;
            for (FacturaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para consultar un Factura.
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaEntity resultEntity = facturaLogic.getFactura(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCostoFijo(), resultEntity.getCostoFijo());
        Assert.assertEquals(entity.getCostosAdicionales(), resultEntity.getCostosAdicionales());
        Assert.assertEquals(entity.isDividirCuentaServicios(), resultEntity.isDividirCuentaServicios());
        Assert.assertEquals(entity.getFechaEntrada(), resultEntity.getFechaEntrada());
        Assert.assertEquals(entity.getFechaSalida(), resultEntity.getFechaSalida());
        Assert.assertEquals(entity.getCostosAdicionales(), resultEntity.getCostosAdicionales());
        Assert.assertEquals(entity.getIVA(), resultEntity.getIVA());
    }
    /**
     * Prueba para eliminar un Factura.
     */
    @Test
    public void deleteFacturaTest() {
        FacturaEntity entity = data.get(0);
        facturaLogic.deleteFactura(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    /**
     * Prueba para actualizar un Factura.
     */
    @Test
    public void updateFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);

        pojoEntity.setId(entity.getId());

        facturaLogic.updateFactura(pojoEntity);

        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getCostoFijo(), resp.getCostoFijo());
        Assert.assertEquals(pojoEntity.getCostosAdicionales(), resp.getCostosAdicionales());
        Assert.assertEquals(pojoEntity.isDividirCuentaServicios(), resp.isDividirCuentaServicios());
        Assert.assertEquals(pojoEntity.getFechaEntrada(), resp.getFechaEntrada());
        Assert.assertEquals(pojoEntity.getFechaSalida(), resp.getFechaSalida());
        Assert.assertEquals(pojoEntity.getCostosAdicionales(), resp.getCostosAdicionales());
        Assert.assertEquals(pojoEntity.getIVA(), resp.getIVA());
    }
}
