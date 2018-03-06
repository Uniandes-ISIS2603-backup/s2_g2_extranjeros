/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.extranjeros.test.persistence;

import co.edu.uniandes.csw.extranjeros.entities.EstudianteEntity;
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

@RunWith (Arquillian.class)
public class EstudiantePersistenceTest {
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de servicio, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EstudianteEntity.class.getPackage())
                .addPackage(EstudiantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");              
        
    }
    
     /**
     * Inyección de la dependencia a la clase ServicioPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private EstudiantePersistence estudiantePersistence;
    
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
        em.createQuery("delete from EstudianteEntity").executeUpdate();
    }
    
    /**
     * Lista de universidades que representa los datos de la base de datos temporal.
     */
    private List<EstudianteEntity> data = new ArrayList<EstudianteEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            EstudianteEntity estudiante = factory.manufacturePojo(EstudianteEntity.class);
            em.persist(estudiante);
            data.add(estudiante);
        }
    }
    
     /**
     * Prueba para crear un Estudiante
     */
    @Test
    public void createEstudianteTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        EstudianteEntity estudiante1 = factory.manufacturePojo(EstudianteEntity.class);
        EstudianteEntity result = estudiantePersistence.create(estudiante1);
        
        Assert.assertNotNull(result);

        EstudianteEntity estudiante2 = em.find(EstudianteEntity.class, result.getId());
        
        Assert.assertEquals(estudiante1.getNombre(), estudiante2.getNombre());
        Assert.assertEquals(estudiante1.getUsuario(), estudiante2.getUsuario());
        Assert.assertEquals(estudiante1.getClave(), estudiante2.getClave());
        Assert.assertEquals(estudiante1.getCorreo(), estudiante2.getCorreo());
        Assert.assertEquals(estudiante1.getCelular(), estudiante2.getCelular());
         Assert.assertEquals(estudiante1.isEstadoArrendamiento(), estudiante2.isEstadoArrendamiento());
        Assert.assertEquals(estudiante1.getId(), estudiante2.getId());

    }
    
     /**
     * Prueba para consultar todos los estudiantes.
     */
    @Test
    public void getEstudiantesTest()
    {
        List<EstudianteEntity> estudiantesPR=estudiantePersistence.findAll();
        Assert.assertEquals(estudiantesPR.size(), data.size());
        for(EstudianteEntity temp : estudiantesPR)
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
     * Prueba para consultar una universidad.
     */
    @Test
    public void getEstudianteTest()
    {
        EstudianteEntity estudiante1=data.get(0);
        EstudianteEntity estudiante2=estudiantePersistence.find(estudiante1.getId());
        Assert.assertNotNull(estudiante2);
        
        Assert.assertEquals(estudiante1.getNombre(), estudiante2.getNombre());
        Assert.assertEquals(estudiante1.getUsuario(), estudiante2.getUsuario());
        Assert.assertEquals(estudiante1.getClave(), estudiante2.getClave());
        Assert.assertEquals(estudiante1.getCorreo(), estudiante2.getCorreo());
        Assert.assertEquals(estudiante1.getCelular(), estudiante2.getCelular());
        Assert.assertEquals(estudiante1.isEstadoArrendamiento(), estudiante2.isEstadoArrendamiento());
        Assert.assertEquals(estudiante1.getId(), estudiante2.getId());
        
          
    }
    
    /**
     * Prueba para eliminar un estudiante.
     */
    @Test
    public void deleteEstudianteTest() {
        EstudianteEntity estudiante = data.get(0);
        estudiantePersistence.delete(estudiante.getId());
        EstudianteEntity deleted = em.find(EstudianteEntity.class, estudiante.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar una providencia
     */
    @Test
    public void updateEstudianteTest() {
       EstudianteEntity estudiante1 = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EstudianteEntity estudiante2 = factory.manufacturePojo(EstudianteEntity.class);

        estudiante2.setId(estudiante1.getId());

       estudiantePersistence.update(estudiante2);

        EstudianteEntity resp = em.find(EstudianteEntity.class, estudiante1.getId());
        
        Assert.assertEquals(resp.getNombre(), estudiante2.getNombre());
        Assert.assertEquals(resp.getUsuario(), estudiante2.getUsuario());
        Assert.assertEquals(resp.getClave(), estudiante2.getClave());
        Assert.assertEquals(resp.getCorreo(), estudiante2.getCorreo());
        Assert.assertEquals(resp.getCelular(), estudiante2.getCelular());
        Assert.assertEquals(resp.isEstadoArrendamiento(), estudiante2.isEstadoArrendamiento());
        Assert.assertEquals(resp.getId(), estudiante2.getId());
        
    }
    
}
