/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.FormaPagoLogic;
import co.edu.uniandes.csw.paseadores.ejb.PagoLogic;
import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.FormaPagoPersistence;
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
public class FormaPagoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private FormaPagoLogic formaPagoLogic;
    
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FormaPagoEntity.class.getPackage())
                .addPackage(FormaPagoLogic.class.getPackage())
                .addPackage(FormaPagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
 @PersistenceContext
 private EntityManager em; 
    
    
@Test
public void createFormaPagoTest() throws BusinessLogicException{
    FormaPagoEntity formaPago = factory.manufacturePojo(FormaPagoEntity.class);
    FormaPagoEntity result = formaPagoLogic.createFormaPago(formaPago);
    Assert.assertNotEquals(0, result);
    
    FormaPagoEntity entity = em.find(FormaPagoEntity.class, result.getId());
    Assert.assertEquals(entity.getCapacidadPago(), result.getCapacidadPago(), 1e-8);
}
   
@Test (expected = BusinessLogicException.class)
public void createFormaPagoCapacidadPagoCero() throws BusinessLogicException{
    FormaPagoEntity formaPago = factory.manufacturePojo(FormaPagoEntity.class);
    formaPago.setCapacidadPago(0);
    FormaPagoEntity result = formaPagoLogic.createFormaPago(formaPago);
}
    
}
