/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;

import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import co.edu.uniandes.csw.paseadores.persistence.PagoPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 *
 * @author Juan Esteban Vergara 
 */
@RunWith(Arquillian.class)
public class PagoPersistenceTest {
   
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml" , "beans.xml");
    }
    @Inject
    private PagoPersistence ep;
    
    
    @PersistenceContext
    protected EntityManager em;
    
    @Test
    public void createTest(){
        //Falta crear Pago
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity pago = factory.manufacturePojo(PagoEntity.class);
        PagoEntity result = ep.create(pago);
        Assert.assertNotNull(result);
        
        PagoEntity entity = em.find(PagoEntity.class, result.getId());
        
        Assert.assertEquals(pago.getIdPaseador(),entity.getIdPaseador());
    }
    
   
    
    
    
  
   
    
}
