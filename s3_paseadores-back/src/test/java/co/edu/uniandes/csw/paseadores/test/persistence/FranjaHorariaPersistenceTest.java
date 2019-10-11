/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;

import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.persistence.FranjaHorariaPersistence;
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
 * @author Santiago Bolaños Vega
 */
@RunWith(Arquillian.class)
public class FranjaHorariaPersistenceTest {
    
    @Inject
    FranjaHorariaPersistence fhp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    PaseadorEntity paseadorTest;
    
    private ArrayList<FranjaHorariaEntity> data = new ArrayList<>();
    
    @Deployment 
    public  static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FranjaHorariaEntity.class.getPackage())
                .addPackage(FranjaHorariaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml" , "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml" , "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest(){
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
        em.createQuery("delete from FranjaHorariaEntity").executeUpdate();
        em.createQuery("delete from PaseadorEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        paseadorTest = factory.manufacturePojo(PaseadorEntity.class);
        em.persist(paseadorTest);
        for (int i = 0; i < 3; i++) {
            FranjaHorariaEntity entity = factory.manufacturePojo(FranjaHorariaEntity.class);
            entity.setPaseador(paseadorTest);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una franja Horaria.
     */
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        FranjaHorariaEntity franja = factory.manufacturePojo(FranjaHorariaEntity.class);
        franja.setPaseador(paseadorTest);
        FranjaHorariaEntity result = fhp.create(franja);
        Assert.assertNotNull(result);
        
        FranjaHorariaEntity entity = em.find( FranjaHorariaEntity.class , result.getId());
        Assert.assertEquals(franja.getInicio(), entity.getInicio());
    }
      
    /**
     * Prueba para encontrar una franja.
     */
    @Test
    public void findTest(){
        FranjaHorariaEntity entity = data.get(0);
        FranjaHorariaEntity newEntity = fhp.find(paseadorTest.getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Prueba para actualizar una franja.
     */
    @Test
    public void updateTest(){
        FranjaHorariaEntity franja = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FranjaHorariaEntity entity = factory.manufacturePojo(FranjaHorariaEntity.class);
        entity.setId(franja.getId());
        fhp.update(entity);
        FranjaHorariaEntity resp = em.find(FranjaHorariaEntity.class , franja.getId());
        Assert.assertEquals(resp.getInicio(), entity.getInicio());
    }
    
    /**
     * Prueba para eliminar una franja.
     */
    @Test
    public void  deleteTest(){
        FranjaHorariaEntity franja = data.get(0);
        fhp.delete(franja.getId());
        FranjaHorariaEntity deleted = em.find(FranjaHorariaEntity.class, franja.getId());
        Assert.assertNull(deleted); 
    }
}
