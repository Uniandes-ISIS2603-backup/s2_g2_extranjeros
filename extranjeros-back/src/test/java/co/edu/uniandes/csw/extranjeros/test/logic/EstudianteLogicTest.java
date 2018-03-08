/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.logic;

import co.edu.uniandes.csw.extranjeros.ejb.EstudianteLogic;
import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
import co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.extranjeros.persistence.EstudiantePersistence;
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
 * @author am.quintero12
 */
@RunWith(Arquillian.class)
public class EstudianteLogicTest {
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de facturas, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstudianteEntity.class.getPackage())
                .addPackage(EstudianteLogic.class.getPackage())
                .addPackage(EstudiantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyección de la dependencia a la clase UsuarioPersistence cuyos métodos se van a probar.
     */
    @Inject
    private EstudianteLogic estudianteLogic;
    
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
        em.createQuery("delete from EstudianteEntity").executeUpdate();
    }
    
     /**
     * Lista de los Usuarios que representan los datos de la Base de Datos temporal.
     */
    private List<EstudianteEntity> data = new ArrayList<>();
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EstudianteEntity estudiante = factory.manufacturePojo(EstudianteEntity.class);
            em.persist(estudiante);
            data.add(estudiante);
        }
    }
    
    /**
     * Prueba para generar/crear un estudiante.
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException
     */
    @Test
    public void createEstudianteTest() throws BusinessLogicException{
        
            PodamFactory factory = new PodamFactoryImpl();
            
            
            EstudianteEntity estudiante = factory.manufacturePojo(EstudianteEntity.class);
            EstudianteEntity result = estudianteLogic.createEstudiante(estudiante);
            
           
            Assert.assertNotNull(result);
            
            EstudianteEntity usuarioDos = em.find(EstudianteEntity.class, result.getId());
            
            Assert.assertEquals(estudiante.getNombre(), usuarioDos.getNombre());
    }
    
     /**
     * Prueba para consultar todas los estudiantes 
     */
    @Test
    public void getArrendatariosTest() {
        
        // Lista
        List<EstudianteEntity> usuariosAll =  estudianteLogic.getEstudiantes();
        
        // Assert Tamanio
        Assert.assertEquals(usuariosAll.size(), data.size());
        
        // Se encuentran todos los Usuarios
        for(EstudianteEntity temp : usuariosAll) {
            boolean encontrado = false;
            
            for (int i = 0; i < data.size() && !encontrado; i++) {
                if(temp.getId().equals(data.get(i).getId()))
                    encontrado = true;
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    /**
     * Prueba para consultar  un Estudiante.
     */
    @Test
    public void getArrendatarioTest() {
        
        EstudianteEntity usuario1 = data.get(0);
        EstudianteEntity usuario2 = estudianteLogic.getEstudiante(usuario1.getId());
        Assert.assertNotNull(usuario2);
        Assert.assertEquals(usuario1.getNombre(), usuario2.getNombre());
    }
    
     /**
     * Prueba para actualizar un Arrendatario.
     * @throws co.edu.uniandes.csw.extranjeros.exceptions.BusinessLogicException
     */
    @Test
    public void updateArrendatarioTest() throws BusinessLogicException
    {
        
        EstudianteEntity estudiante1 = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EstudianteEntity estudiante2 = factory.manufacturePojo(EstudianteEntity.class);

        estudiante2.setId(estudiante1.getId());
        estudianteLogic.updateEstudiante(estudiante2);

        EstudianteEntity resp = em.find(EstudianteEntity.class, estudiante1.getId());  
        
        Assert.assertEquals(estudiante2.getNombre(), resp.getNombre());
    }
    
    
    /**
     * Prueba para borrar una Arrendatario.
     */
    @Test
    public void deleteArrendatarioTest() {
        Long id = data.get(0).getId();
        estudianteLogic.deleteEstudiante(id);
        EstudianteEntity estudiante1 = estudianteLogic.getEstudiante(id);
        Assert.assertNull(estudiante1);
    } 

    
    
}
