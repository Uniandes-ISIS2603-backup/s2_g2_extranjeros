/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;

import co.edu.uniandes.csw.extranjeros.ejb.ArrendatarioLogic;
import co.edu.uniandes.csw.extranjeros.entities.ArrendatarioEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.ArrendatarioPersistence;
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
public class ArrendatarioLogicTest {
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de facturas, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ArrendatarioEntity.class.getPackage())
                .addPackage(ArrendatarioLogic.class.getPackage())
                .addPackage(ArrendatarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase UsuarioPersistence cuyos métodos se van a probar.
     */
    @Inject
    private ArrendatarioLogic arrendatarioLogic;
    
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
    }
    
    /**
     * Lista de los Usuarios que representan los datos de la Base de Datos temporal.
     */
    private List<ArrendatarioEntity> data = new ArrayList<>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ArrendatarioEntity arrendatario = factory.manufacturePojo(ArrendatarioEntity.class);
            em.persist(arrendatario);
            data.add(arrendatario);
        }
    }
    
    /**
     * Prueba para generar/crear un Arrendatario.
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException
     */
    @Test
    public void createArrendatarioTest() throws BusinessLogicException{
        
            PodamFactory factory = new PodamFactoryImpl();
            
            // Genera
            ArrendatarioEntity usuarioUno = factory.manufacturePojo(ArrendatarioEntity.class);
            ArrendatarioEntity result = arrendatarioLogic.createArrendatario(usuarioUno);
            
            // AssertNotNull
            Assert.assertNotNull(result);
            
            // Comparacion
            ArrendatarioEntity usuarioDos = em.find(ArrendatarioEntity.class, result.getId());
            
            // AssertEquals
            Assert.assertEquals(usuarioUno.getNombre(), usuarioDos.getNombre());
    }
    
    /**
     * Prueba para consultar todas los Arrendatarios en el sistema.
     */
    @Test
    public void getArrendatariosTest() {
        
        // Lista
        List<ArrendatarioEntity> usuariosAll =  arrendatarioLogic.getArrendatarios();
        
        // Assert Tamanio
        Assert.assertEquals(usuariosAll.size(), data.size());
        
        // Se encuentran todos los Usuarios
        for(ArrendatarioEntity temp : usuariosAll) {
            boolean encontrado = false;
            
            for (int i = 0; i < data.size() && !encontrado; i++) {
                if(temp.getId().equals(data.get(i).getId()))
                    encontrado = true;
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    /**
     * Prueba para consultar solo un Arrendatario.
     */
    @Test
    public void getArrendatarioTest() {
        
        // Genera
        ArrendatarioEntity usuarioUno = data.get(0);
        ArrendatarioEntity usuarioDos = arrendatarioLogic.getArrendatario(usuarioUno.getId());
        
        // NN: No nulo
        Assert.assertNotNull(usuarioDos);
        
        // Assert
        Assert.assertEquals(usuarioUno.getNombre(), usuarioDos.getNombre());
    }
    
    /**
     * Prueba para actualizar un Arrendatario.
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException
     */
    @Test
    public void updateArrendatarioTest() throws BusinessLogicException
    {
        // Obtiene Uno
        ArrendatarioEntity usuarioUno = data.get(0);
        
        // Genera Dos
        PodamFactory factory = new PodamFactoryImpl();
        ArrendatarioEntity usuarioDos = factory.manufacturePojo(ArrendatarioEntity.class);

        // Update
        usuarioDos.setId(usuarioUno.getId());
        arrendatarioLogic.updateArrendatario(usuarioDos);

        // Respuesta
        ArrendatarioEntity resp = em.find(ArrendatarioEntity.class, usuarioUno.getId());  

        // Assert
        Assert.assertEquals(usuarioDos.getNombre(), resp.getNombre());
    }
    

    /**
     * Prueba para borrar una Arrendatario.
     */
    @Test
    public void deleteArrendatarioTest() {
        Long id = data.get(0).getId();
        arrendatarioLogic.deleteArrendatario(id);
        ArrendatarioEntity usuarioUno = arrendatarioLogic.getArrendatario(id);
        Assert.assertNull(usuarioUno);
    } 
}
