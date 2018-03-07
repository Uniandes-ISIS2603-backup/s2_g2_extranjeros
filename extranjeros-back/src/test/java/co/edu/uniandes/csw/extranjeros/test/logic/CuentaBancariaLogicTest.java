/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;

import co.edu.uniandes.csw.extranjeros.ejb.CuentaBancariaLogic;
import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.entities.CuentaBancariaEntity;
import co.edu.uniandes.csw.extranjeros.entities.FacturaEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.CuentaBancariaPersistence;
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
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author jr.pacheco10
 */

@RunWith(Arquillian.class)
public class CuentaBancariaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de facturas, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CuentaBancariaEntity.class.getPackage())
                .addPackage(CuentaBancariaLogic.class.getPackage())
                .addPackage(CuentaBancariaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase CuentaBancariaLogic cuyos métodos se van a probar.
     */
    @Inject
    private CuentaBancariaLogic logic;
    
    /**
     * Contexto de persistencia que se va a utilizar para acceder a la Base de datos por fuera 
     * de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del EntityManager anterior cuando se crean/borran 
     * datos para las pruebas, permite la transaccionalidad.
     */
    @Inject
    UserTransaction utx;
    
    
    //---------------------------------------------------
    // TEST
    //---------------------------------------------------
    
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
        em.createQuery("delete from CuentaBancariaEntity").executeUpdate();
        em.createQuery("delete from ArrendatarioEntity").executeUpdate();
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }
    
    //---------------------------------------------------
    // LISTAS:
    //---------------------------------------------------
    private List<CuentaBancariaEntity> data = new ArrayList<>();
    private List<ArrendatarioEntity> dataArrendatario = new ArrayList<>();
    private List<FacturaEntity> dataFactura = new ArrayList<>();
  
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            FacturaEntity facturas = factory.manufacturePojo(FacturaEntity.class);
            em.persist(facturas);
            dataFactura.add(facturas);
        }
        
        for (int i = 0; i < 3; i++) {
            ArrendatarioEntity entity = factory.manufacturePojo(ArrendatarioEntity.class);
            entity.setFacturas(dataFactura);
            em.persist(entity);
            dataArrendatario.add(entity);
        }
        
        for (int i = 0; i < 3; i++) {
            CuentaBancariaEntity entity = factory.manufacturePojo(CuentaBancariaEntity.class);
            entity.setArrendatarioTitular(dataArrendatario.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una Cuenta Bancaria.
     */
    @Test
    public void createCuentaBancaria() {
        CuentaBancariaEntity newEntity = factory.manufacturePojo(CuentaBancariaEntity.class);
        CuentaBancariaEntity result = logic.createReview(data.get(0).getArrendatarioTitular().getId(), newEntity);
        Assert.assertNotNull(result);
        
        CuentaBancariaEntity entity = em.find(CuentaBancariaEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNumeroCuenta(), entity.getNumeroCuenta());
        Assert.assertEquals(newEntity.getTipoCuenta(), entity.getTipoCuenta());
        Assert.assertEquals(newEntity.getSaldoCuenta(), entity.getSaldoCuenta());
    }
    
    /**
     * Prueba para consultar una Cuenta de Banco.
     */
    @Test
    public void getCuentaBancaria() {
        CuentaBancariaEntity entity = data.get(0);
        CuentaBancariaEntity resultEntity = logic.getCuentaBancaria(dataArrendatario.get(1).getId(), entity.getId());
        
        Assert.assertNotNull(resultEntity);
        
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNumeroCuenta(), resultEntity.getNumeroCuenta());
        Assert.assertEquals(entity.getTipoCuenta(), resultEntity.getTipoCuenta());
        Assert.assertEquals(entity.getSaldoCuenta(), resultEntity.getSaldoCuenta());
    }
    
    /**
     * Prueba para consultar una o mas cuentas de banco.
     */
    @Test
    public void getReviewsTest() throws BusinessLogicException {
        List<CuentaBancariaEntity> list = logic.getCuentasBancarias(dataArrendatario.get(1).getId());        
        
        // Como la relacion es OneToOne, list debe tener un tamanio de 1.
        Assert.assertEquals(1, list.size());
        
        for (CuentaBancariaEntity entity : list) {
            boolean encontrado = false;
            for (CuentaBancariaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }

    /**
     * Prueba para eliminar una Cuenta bancaria.
     */
    @Test
    public void deleteCuentaBancaria() {
        CuentaBancariaEntity entity = data.get(0);
        logic.deleteReview(dataArrendatario.get(1).getId(), entity.getId());
        
        CuentaBancariaEntity deleted = em.find(CuentaBancariaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Review
     */
    @Test
    public void updateReviewTest() {
        
        CuentaBancariaEntity entity = data.get(0);
        CuentaBancariaEntity pojoEntity = factory.manufacturePojo(CuentaBancariaEntity.class);

        pojoEntity.setId(entity.getId());
        logic.updateCuentaDeBanco(dataArrendatario.get(1).getId(), pojoEntity);
        CuentaBancariaEntity resp = em.find(CuentaBancariaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNumeroCuenta(), resp.getNumeroCuenta());
        Assert.assertEquals(pojoEntity.getTipoCuenta(), resp.getTipoCuenta());
        Assert.assertEquals(pojoEntity.getSaldoCuenta(), resp.getSaldoCuenta());
    }
}
