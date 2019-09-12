/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.ZonaLogic;
import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ZonaPersistence;
import javax.inject.Inject;
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
 * @author Juan Vergara
 */
@RunWith(Arquillian.class)
public class ZonaLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private ZonaLogic zonaLogic;
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ZonaEntity.class.getPackage())
                .addPackage(ZonaLogic.class.getPackage())
                .addPackage(ZonaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml" , "beans.xml");
    }
     private ZonaEntity newEntity = factory.manufacturePojo(ZonaEntity.class);
    public void escenarioBase(){
        newEntity.setInfoZona("hola mundo");
    }
    public void escenarioInfoNull(){
        newEntity.setInfoZona(null);
    }
    public void escenarioInfoVacia(){
        newEntity.setInfoZona("     ");
    }
    @Test
    public void createZona() throws BusinessLogicException{
        escenarioBase();
        
        ZonaEntity result = zonaLogic.createZona(newEntity);
        Assert.assertNotNull(result);
        
    }
    @Test (expected = BusinessLogicException.class)
   public void createZonaInfoNull() throws BusinessLogicException{
       escenarioInfoNull();
       ZonaEntity result = zonaLogic.createZona(newEntity);
   }
   @Test (expected = BusinessLogicException.class)
   public void createZonaInfoVacia() throws BusinessLogicException{
       escenarioInfoVacia();
       ZonaEntity result = zonaLogic.createZona(newEntity);
   }
    
}
