/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.persistence;

import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.entities.CuentaBancariaEntity;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author jr.pacheco10
 */

@RunWith(Arquillian.class)
public class CuentaBancariaPersistenceTest {
    
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
                .addPackage(CuentaBancariaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase CuentaBancariaPersistence cuyos métodos se van a probar.
     */
    @Inject
    private CuentaBancariaPersistence cBancariaPersistence;
    
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
        em.createQuery("delete from ArrendatarioEntity").executeUpdate();
        em.createQuery("delete from CuentaBancariaEntity").executeUpdate();
    }
    
    /**
     * Lista de las Cuentas Bancarias y de los Arrendatarios
     * asociados que representan los datos de la Base de Datos temporal.
     */
    private List<CuentaBancariaEntity> data = new ArrayList<>();
    private List<ArrendatarioEntity> dataArrendatario = new ArrayList<>();
    

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        
        // Creacion arrendatarios
        for (int i = 0; i < 3; i++) {
            ArrendatarioEntity arrendatario = factory.manufacturePojo(ArrendatarioEntity.class);
            em.persist(arrendatario);
            dataArrendatario.add(arrendatario);
        }
        
        // Creacion cuentas bancarias
        for (int i = 0; i < 3; i++) {
            CuentaBancariaEntity cuentaB = factory.manufacturePojo(CuentaBancariaEntity.class);
            if(i==0) {cuentaB.setArrendatarioTitular(dataArrendatario.get(0));}
            em.persist(cuentaB);
            data.add(cuentaB);
        }
    }
    
     /**
     * Prueba para crear una cuenta de banco.
     */
    @Test
    public void createCuentaBancariaTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        CuentaBancariaEntity newEntity = factory.manufacturePojo(CuentaBancariaEntity.class);
        CuentaBancariaEntity result = cBancariaPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        CuentaBancariaEntity entity = em.find(CuentaBancariaEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getBancoAsociado(), entity.getBancoAsociado());
        Assert.assertEquals(newEntity.getNumeroCuenta(), entity.getNumeroCuenta());
        Assert.assertEquals(newEntity.getSaldoCuenta(), entity.getSaldoCuenta());
        Assert.assertEquals(newEntity.getTipoCuenta(), entity.getTipoCuenta());
        Assert.assertEquals(newEntity.getArrendatarioTitular(), entity.getArrendatarioTitular());
    }
    
    /**
     * Prueba para consultar una cuenta de banco. 
     */
    @Test
    public void getCuentaBancaria(){
        CuentaBancariaEntity cuentaSingle = data.get(0);
        CuentaBancariaEntity newEntity = cBancariaPersistence.find(cuentaSingle.getId(), dataArrendatario.get(0).getId());
       
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(cuentaSingle.getBancoAsociado(), newEntity.getBancoAsociado());
        Assert.assertEquals(cuentaSingle.getNumeroCuenta(), newEntity.getNumeroCuenta());
        Assert.assertEquals(cuentaSingle.getSaldoCuenta(), newEntity.getSaldoCuenta());
        Assert.assertEquals(cuentaSingle.getTipoCuenta(), newEntity.getTipoCuenta());
    }
    
    /**
     * Prueba para actualizar una Cuenta Bancaria.
     */
//    @Test
//    public void updateCuentaBancaria(){
//        
//        PodamFactory factory = new PodamFactoryImpl();      
//        CuentaBancariaEntity entity = data.get(0);
//        CuentaBancariaEntity newEntity = factory.manufacturePojo(CuentaBancariaEntity.class);
//
//        newEntity.setId(entity.getId());
//        cBancariaPersistence.update(newEntity);
//        
//        CuentaBancariaEntity resp = em.find(CuentaBancariaEntity.class, entity.getId());
//        
//        Assert.assertEquals(newEntity.getBancoAsociado(), resp.getBancoAsociado());
//        Assert.assertEquals(newEntity.getNumeroCuenta(), resp.getNumeroCuenta());
//        Assert.assertEquals(newEntity.getSaldoCuenta(), resp.getSaldoCuenta());
//        Assert.assertEquals(newEntity.getTipoCuenta(), resp.getTipoCuenta());        
//    }
    
    /**
     * Prueba para eliminar una Cuenta Bancaria.
     */
    @Test
    public void deleteCuentaBancaria(){
        CuentaBancariaEntity cuentaEliminar = data.get(0);
        cBancariaPersistence.delete(cuentaEliminar.getId());
        CuentaBancariaEntity eliminada = em.find(CuentaBancariaEntity.class, cuentaEliminar.getId());
        Assert.assertNull(eliminada);
    }
}
