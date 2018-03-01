/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;

import co.edu.uniandes.csw.extranjeros.ejb.UsuarioLogic;
import co.edu.uniandes.csw.extranjeros.entities.UsuarioEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class UsuarioLogicTest {
    
   /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de facturas, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase UsuarioPersistence cuyos métodos se van a probar.
     */
    @Inject
    private UsuarioLogic usuarioLogic;
    
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    /**
     * Lista de los Usuarios que representan los datos de la Base de Datos temporal.
     */
    private List<UsuarioEntity> data = new ArrayList<>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(usuario);
            data.add(usuario);
        }
    }
    
    /**
     * Prueba para generar/crear un Usuario.
     */
    @Test
    public void createUsuarioTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        
        // Genera
        UsuarioEntity usuarioUno = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result;
        try {
            result = usuarioLogic.createUsuario(usuarioUno);
            
            // AssertNotNull
            Assert.assertNotNull(result);

            // Comparacion
            UsuarioEntity usuarioDos = em.find(UsuarioEntity.class, result.getId());

            // AssertEquals
            Assert.assertEquals(usuarioUno.getUsuario(), usuarioDos.getUsuario());
            Assert.assertEquals(usuarioUno.getClave(), usuarioDos.getClave());
            Assert.assertEquals(usuarioUno.getCorreo(), usuarioDos.getCorreo());
            Assert.assertEquals(usuarioUno.getCelular(), usuarioDos.getCelular());
            
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para consultar todas los Usuarios en el sistema.
     */
    @Test
    public void getUsuariosTest() {
        // Lista
        List<UsuarioEntity> usuariosAll =  usuarioLogic.getUsuarios();
        
        // Assert Tamanio
        Assert.assertEquals(usuariosAll.size(), data.size());
        
        // Se encuentran todos los Usuarios
        for(UsuarioEntity temp : usuariosAll) {
            boolean encontrado = false;
            
            for (int i = 0; i < data.size() && !encontrado; i++) {
                if(temp.getId().equals(data.get(i).getId()))
                    encontrado = true;
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    /**
     * Prueba para consultar solo un Usuario.
     */
    @Test
    public void getUsuarioTest() {
        
        // Genera
        UsuarioEntity usuarioUno = data.get(0);
        UsuarioEntity usuarioDos = usuarioLogic.getUsuario(usuarioUno.getId());
        
        // NN: No nulo
        Assert.assertNotNull(usuarioDos);
        
        // Assert
        Assert.assertEquals(usuarioUno.getUsuario(), usuarioDos.getUsuario());
        Assert.assertEquals(usuarioUno.getClave(), usuarioDos.getClave());
        Assert.assertEquals(usuarioUno.getCorreo(), usuarioDos.getCorreo());
        Assert.assertEquals(usuarioUno.getCelular(), usuarioDos.getCelular());
    }
    
    /**
     * Prueba para actualizar un Usuario.
     */
    @Test
    public void updateUsuarioTest()
    {
        // Obtiene Uno
        UsuarioEntity usuarioUno = data.get(0);
        
        // Genera Dos
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity usuarioDos = factory.manufacturePojo(UsuarioEntity.class);

        // Update
        usuarioDos.setId(usuarioUno.getId());
        usuarioLogic.updateUsuario(usuarioDos);

        // Respuesta
        UsuarioEntity resp = em.find(UsuarioEntity.class, usuarioUno.getId());  

        // Assert
        Assert.assertEquals(usuarioDos.getUsuario(), resp.getUsuario());
        Assert.assertEquals(usuarioDos.getClave(), resp.getClave());
        Assert.assertEquals(usuarioDos.getCorreo(), resp.getCorreo());
        Assert.assertEquals(usuarioDos.getCelular(), resp.getCelular());
    }
    
    /**
     * Prueba para borrar una Usuario.
     */
    @Test
    public void deleteUsuarioTest() {
        Long id = data.get(0).getId();
        usuarioLogic.deleteUsuario(id);
        UsuarioEntity usuarioUno = usuarioLogic.getUsuario(id);
        Assert.assertNull(usuarioUno);
    } 
    
}
