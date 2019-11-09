/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;


import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.persistence.PaseadorPersistence;
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
 * @author Daniel Felipe Garcia Vargas
 */
@RunWith(Arquillian.class)
public class PaseadorPersistenceTest 
{
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
              .addPackage(PaseadorEntity.class.getPackage())
              .addPackage(PaseadorPersistence.class.getPackage())
              .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml", "beans.xml" );
    }
    
    @Inject
    PaseadorPersistence pp;
    
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;
    
    @Inject
    UserTransaction utx;
   
    private List<PaseadorEntity> data = new ArrayList<PaseadorEntity>();
    
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() 
    {
        em.createQuery("delete from PaseadorEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PaseadorEntity entity = factory.manufacturePojo(PaseadorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
     /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
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
    
    @Test
    public void createTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        PaseadorEntity paseador = factory.manufacturePojo(PaseadorEntity.class);
        PaseadorEntity result = pp.create(paseador);
        Assert.assertNotNull(result);
        
        PaseadorEntity entity = em.find(PaseadorEntity.class, result.getId());
        Assert.assertEquals(paseador.getId(),entity.getId());
    }
    
    @Test
    public void findPaseadoresTest() 
    {
        List<PaseadorEntity> list = pp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PaseadorEntity ent : list) {
            boolean found = false;
            for (PaseadorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getPaseadorTest() {
        PaseadorEntity entity = data.get(0);
        PaseadorEntity newEntity = pp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
     @Test
    public void updatePaseadorTest() 
    {
        PaseadorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);

        newEntity.setId(entity.getId());

        pp.update(newEntity);
        PaseadorEntity resp = em.find(PaseadorEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    @Test
    public void deletePaseadorTest() {
        PaseadorEntity entity = data.get(0);
        pp.delete(entity.getId());
        PaseadorEntity deleted = em.find(PaseadorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
