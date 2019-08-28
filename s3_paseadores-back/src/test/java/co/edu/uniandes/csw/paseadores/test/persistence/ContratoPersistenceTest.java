/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.persistence.ContratoPersistence;
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
 * Pruebas para la persistencia de la entidad contrato.
 * @author Nicolas Potes Garcia
 */

@RunWith(Arquillian.class)
public class ContratoPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
      return ShrinkWrap.create(JavaArchive.class)
             .addPackage(ContratoEntity.class.getPackage())
              .addPackage(ContratoPersistence.class.getPackage())
              .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml" , "beans.xml");
    }
    
    @Inject
    private ContratoPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
     @Inject
    UserTransaction utx;
   
     private List<ContratoEntity> data = new ArrayList<>();
     
     
     
      /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ContratoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ContratoEntity entity = factory.manufacturePojo(ContratoEntity.class);

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
    public void createTest() {
        //Falta crear contrato
        
        PodamFactory factory = new PodamFactoryImpl();
        ContratoEntity contrato = factory.manufacturePojo(ContratoEntity.class);
        ContratoEntity result = cp.create(contrato);
        Assert.assertNotNull(result);
        
        ContratoEntity entity = 
           em.find(ContratoEntity.class, result.getId());
        Assert.assertEquals(contrato.getName(), entity.getName());
        
    }
    
    
    @Test
    public void findContratosTest() {
        List<ContratoEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ContratoEntity ent : list) {
            boolean found = false;
            for (ContratoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getContratoTest() {
        ContratoEntity entity = data.get(0);
        ContratoEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        
    }
    
    
     @Test
    public void updateContratoTest() {
        ContratoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ContratoEntity newEntity = factory.manufacturePojo(ContratoEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        ContratoEntity resp = em.find(ContratoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    @Test
    public void deleteContratoTest() {
        ContratoEntity entity = data.get(0);
        cp.delete(entity.getId());
        ContratoEntity deleted = em.find(ContratoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
}
