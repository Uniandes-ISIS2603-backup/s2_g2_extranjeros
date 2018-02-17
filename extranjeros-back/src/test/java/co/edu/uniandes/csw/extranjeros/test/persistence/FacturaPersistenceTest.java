/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.persistence;
import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.persistence.FacturaPersistence;
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
public class FacturaPersistenceTest 
{
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de facturas, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Inyección de la dependencia a la clase ServicioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private FacturaPersistence servicioPersistence;

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
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }

    /**
     * Lista de facturas que representa los datos de la base de datos temporal.
     */
    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            FacturaEntity factura = factory.manufacturePojo(FacturaEntity.class);
            em.persist(factura);
            data.add(factura);
        }
    }
    /**
     * Prueba para crear una factura.
     */
    @Test
    public void createFacturaTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity factura1 = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result = servicioPersistence.create(factura1);
        
        Assert.assertNotNull(result);

        FacturaEntity factura2 = em.find(FacturaEntity.class, result.getId());

        Assert.assertEquals(factura1.getCostoFijo(), factura2.getCostoFijo());
        Assert.assertEquals(factura1.getCostosAdicionales(), factura2.getCostosAdicionales());
        Assert.assertEquals(factura1.getDividirCuentaServicios(), factura2.getDividirCuentaServicios());
        Assert.assertEquals(factura1.getFechaEntrada(), factura2.getFechaEntrada());
        Assert.assertEquals(factura1.getFechaSalida(), factura2.getFechaSalida());
        Assert.assertEquals(factura1.getCostosAdicionales(), factura2.getCostosAdicionales());
        Assert.assertEquals(factura1.getIVA(), factura2.getIVA());
    }
    /**
     * Prueba para consultar una factura.
     */
    @Test
    public void getFacturaTest()
    {
        FacturaEntity factura1=data.get(0);
        FacturaEntity factura2=servicioPersistence.find(factura1.getId());
        Assert.assertNotNull(factura2);
        Assert.assertEquals(factura1.getCostoFijo(), factura2.getCostoFijo());
        Assert.assertEquals(factura1.getCostosAdicionales(), factura2.getCostosAdicionales());
        Assert.assertEquals(factura1.getDividirCuentaServicios(), factura2.getDividirCuentaServicios());
        Assert.assertEquals(factura1.getFechaEntrada(), factura2.getFechaEntrada());
        Assert.assertEquals(factura1.getFechaSalida(), factura2.getFechaSalida());
        Assert.assertEquals(factura1.getCostosAdicionales(), factura2.getCostosAdicionales());
        Assert.assertEquals(factura1.getIVA(), factura2.getIVA());
    }
    /**
     * Prueba para consultar todas las facturas.
     */
    @Test
    public void getFacturasTest()
    {
        List<FacturaEntity> facturasPR=servicioPersistence.findAll();
        Assert.assertEquals(facturasPR.size(), data.size());
        for(FacturaEntity temp : facturasPR)
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
     * Prueba para borrar una factura.
     */
    @Test
    public void deleteFacturaTest()
    {
        Long id=data.get(0).getId();
        servicioPersistence.delete(id);
        FacturaEntity factura1=servicioPersistence.find(id);
        Assert.assertNull(factura1);
    }
    /**
     * Prueba para actualizar una factura.
     */
    @Test
    public void updateFacturaTest()
    {
        FacturaEntity factura1 = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity factura2 = factory.manufacturePojo(FacturaEntity.class);

        factura2.setId(factura1.getId());

        servicioPersistence.update(factura2);

        FacturaEntity resp = em.find(FacturaEntity.class, factura1.getId());

        Assert.assertEquals(factura1.getCostoFijo(), factura2.getCostoFijo());
        Assert.assertEquals(factura1.getCostosAdicionales(), factura2.getCostosAdicionales());
        Assert.assertEquals(factura1.getDividirCuentaServicios(), factura2.getDividirCuentaServicios());
        Assert.assertEquals(factura1.getFechaEntrada(), factura2.getFechaEntrada());
        Assert.assertEquals(factura1.getFechaSalida(), factura2.getFechaSalida());
        Assert.assertEquals(factura1.getCostosAdicionales(), factura2.getCostosAdicionales());
        Assert.assertEquals(factura1.getIVA(), factura2.getIVA());
    }
}
