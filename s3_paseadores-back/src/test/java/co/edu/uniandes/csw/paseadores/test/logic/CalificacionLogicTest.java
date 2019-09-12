/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.CalificacionLogic;
import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.CalificacionPersistence;
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
 * @author Juan Esteban Vergara
 */
@RunWith(Arquillian.class)

public class CalificacionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private CalificacionLogic calificacionLogic;
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml" , "beans.xml");
    }
     private CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);

    public void escenario1(){
        newEntity.setCalificacion(4);
    }
    public void escenarioCalificacionErronea(){
        newEntity.setCalificacion(6);
    }
    @Test
    public void  createCalificacion() throws BusinessLogicException{
        escenario1();  
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
        Assert.assertNotNull(result);
    }
    @Test (expected = BusinessLogicException.class)
   public void createCalificacionErronea() throws BusinessLogicException{
       escenarioCalificacionErronea();
       CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
   }
}
