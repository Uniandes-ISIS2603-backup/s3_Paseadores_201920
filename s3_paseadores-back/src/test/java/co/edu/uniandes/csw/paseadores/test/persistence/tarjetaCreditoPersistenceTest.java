/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;


import co.edu.uniandes.csw.paseadores.entities.TarjetaCreditoEntity;
import co.edu.uniandes.csw.paseadores.persistence.TarjetaCreditoPersistence;

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
 * @author Juan Vergara
 */
@RunWith(Arquillian.class)
public class tarjetaCreditoPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaCreditoEntity.class.getPackage())
                .addPackage(TarjetaCreditoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml" , "beans.xml");
    }
    @Inject
    private TarjetaCreditoPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
     @Inject
    UserTransaction utx;
   
     private List<TarjetaCreditoEntity> data = new ArrayList<>();
     
     
     
      /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from TarjetaCreditoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TarjetaCreditoEntity entity = factory.manufacturePojo(TarjetaCreditoEntity.class);

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
        TarjetaCreditoEntity tarjetaCredito = factory.manufacturePojo(TarjetaCreditoEntity.class);
        TarjetaCreditoEntity result = cp.create(tarjetaCredito);
        Assert.assertNotNull(result);
        
        TarjetaCreditoEntity entity = 
           em.find(TarjetaCreditoEntity.class, result.getId());
        Assert.assertEquals(tarjetaCredito.getNumero1(), entity.getNumero1());
        
    }
    
    
    @Test
    public void findTarjetaCreditosTest() {
        List<TarjetaCreditoEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaCreditoEntity ent : list) {
            boolean found = false;
            for (TarjetaCreditoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getContratoTest() {
        TarjetaCreditoEntity entity = data.get(0);
        TarjetaCreditoEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNumero1(), newEntity.getNumero1());
        
    }
    
    
     @Test
    public void updateTarjetaCreditoTest() {
       TarjetaCreditoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaCreditoEntity newEntity = factory.manufacturePojo(TarjetaCreditoEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        TarjetaCreditoEntity resp = em.find(TarjetaCreditoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    @Test
    public void deleteTarjetaCreditoTest() {
        TarjetaCreditoEntity entity = data.get(0);
        cp.delete(entity.getId());
        TarjetaCreditoEntity deleted = em.find(TarjetaCreditoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
