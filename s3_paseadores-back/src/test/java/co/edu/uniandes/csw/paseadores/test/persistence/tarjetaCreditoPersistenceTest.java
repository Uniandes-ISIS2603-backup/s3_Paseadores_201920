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
    private TarjetaCreditoPersistence ep;
    
    @PersistenceContext
    protected EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<TarjetaCreditoEntity> data = new ArrayList<>();
    
    private void clearData(){
        em.createQuery("borrar de tarjetaCreditoEntity").executeUpdate();
    }
    
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for (int i=0;i<3;i++){
            TarjetaCreditoEntity entity = factory.manufacturePojo(TarjetaCreditoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Before
    public void configTest(){
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }
    }
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaCreditoEntity  tarjetaCredito = factory.manufacturePojo(TarjetaCreditoEntity.class);
        TarjetaCreditoEntity result = ep.create(tarjetaCredito);
        Assert.assertNotNull(result);
        
        TarjetaCreditoEntity entity = em.find(TarjetaCreditoEntity.class, result.getId());
        
        Assert.assertEquals(tarjetaCredito.getCCV(), entity.getCCV());
    }
    @Test void findTarjetasCreditoTest(){
        List<TarjetaCreditoEntity> list = ep.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(TarjetaCreditoEntity ent : list){
            boolean found = false;
            for (TarjetaCreditoEntity entity : data){
                if (ent.getId().equals(entity.getId())){
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    
}
