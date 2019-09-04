/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.PagoLogic;
import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.PagoPersistence;
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
 * @author Mario Hurtado
 */

@RunWith (Arquillian.class)
public class PagoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PagoLogic pagoLogic;
    
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoLogic.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
 @PersistenceContext
 private EntityManager em; 
    
    
@Test
public void createPagoTest() throws BusinessLogicException{
    PagoEntity pago = factory.manufacturePojo(PagoEntity.class);
    PagoEntity result = pagoLogic.createPago(pago);
    Assert.assertNotNull(result);
    
    PagoEntity entity = em.find(PagoEntity.class, result.getId());
    Assert.assertEquals(entity.getIdPaseador(), result.getIdPaseador());
}
   
@Test (expected = BusinessLogicException.class)
public void createPagoIdPaseadorNull() throws BusinessLogicException{
    PagoEntity pago = factory.manufacturePojo(PagoEntity.class);
    pago.setIdPaseador(null);
    PagoEntity result = pagoLogic.createPago(pago);
}
    
}
