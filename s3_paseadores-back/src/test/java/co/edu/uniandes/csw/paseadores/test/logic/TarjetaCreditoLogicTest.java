/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.TarjetaCreditoLogic;
import co.edu.uniandes.csw.paseadores.entities.TarjetaCreditoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.TarjetaCreditoPersistence;
import javax.inject.Inject;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Vergara
 */
@RunWith(Arquillian.class)
public class TarjetaCreditoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private TarjetaCreditoLogic tarjetaCreditoLogic;
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaCreditoEntity.class.getPackage())
                .addPackage(TarjetaCreditoLogic.class.getPackage())
                .addPackage(TarjetaCreditoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml" , "beans.xml");
    }
    private TarjetaCreditoEntity newEntity = factory.manufacturePojo(TarjetaCreditoEntity.class);
    private void escenarioBase(){
        newEntity.setCCV(234);
        newEntity.setNumero1(1234);
        newEntity.setNumero2(1234);
        newEntity.setNumero3(1234);
        newEntity.setNumero4(1234);
    }private void escenarioErrorCVV(){
        newEntity.setCCV(34);
        newEntity.setNumero1(1234);
        newEntity.setNumero2(1234);
        newEntity.setNumero3(1234);
        newEntity.setNumero4(1234);
    }
    private void escenarioErrorNum1(){
        newEntity.setCCV(234);
        newEntity.setNumero1(234);
        newEntity.setNumero2(1234);
        newEntity.setNumero3(1234);
        newEntity.setNumero4(1234);
    }
    private void escenarioErrorNum2(){
        newEntity.setCCV(234);
        newEntity.setNumero1(1234);
        newEntity.setNumero2(234);
        newEntity.setNumero3(1234);
        newEntity.setNumero4(1234);
    }
    private void escenarioErrorNum3(){
        newEntity.setCCV(234);
        newEntity.setNumero1(1234);
        newEntity.setNumero2(1234);
        newEntity.setNumero3(234);
        newEntity.setNumero4(1234);
    }
    private void escenarioErrorNum4(){
        newEntity.setCCV(234);
        newEntity.setNumero1(1234);
        newEntity.setNumero2(1234);
        newEntity.setNumero3(1234);
        newEntity.setNumero4(234);
    }
    @Test 
    public void createTarjetaCredito() throws BusinessLogicException{
        escenarioBase();
        
        TarjetaCreditoEntity result = tarjetaCreditoLogic.createTarjetaCredito(newEntity);
        Assert.assertNotNull(result);
    }
    @Test (expected = BusinessLogicException.class)
    public void createTarjetaCreditoErrorCVV() throws BusinessLogicException{
        escenarioErrorCVV();
        TarjetaCreditoEntity result = tarjetaCreditoLogic.createTarjetaCredito(newEntity);
    }
    @Test (expected = BusinessLogicException.class)
    public void createTarjetaCreditoErrorNum1() throws BusinessLogicException{
        escenarioErrorNum1();
        TarjetaCreditoEntity result = tarjetaCreditoLogic.createTarjetaCredito(newEntity);
    }
    @Test (expected = BusinessLogicException.class)
    public void createTarjetaCreditoErrorNum2() throws BusinessLogicException{
        escenarioErrorNum2();
        TarjetaCreditoEntity result = tarjetaCreditoLogic.createTarjetaCredito(newEntity);
    }
    @Test (expected = BusinessLogicException.class)
    public void createTarjetaCreditoErrorNum3() throws BusinessLogicException{
        escenarioErrorNum3();
        TarjetaCreditoEntity result = tarjetaCreditoLogic.createTarjetaCredito(newEntity);
    }
    @Test (expected = BusinessLogicException.class)
    public void createTarjetaCreditoErrorNum4() throws BusinessLogicException{
        escenarioErrorNum4();
        TarjetaCreditoEntity result = tarjetaCreditoLogic.createTarjetaCredito(newEntity);
    }
}
