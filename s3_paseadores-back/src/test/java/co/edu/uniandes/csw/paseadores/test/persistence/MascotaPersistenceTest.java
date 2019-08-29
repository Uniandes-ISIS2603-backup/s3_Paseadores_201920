/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;

import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import co.edu.uniandes.csw.paseadores.persistence.MascotaPersistence;
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
 * @author Daniel Felipe Garcia Vargas
 */
@RunWith(Arquillian.class)
public class MascotaPersistenceTest 
{
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
              .addClass(MascotaEntity.class)
              .addClass(MascotaPersistence.class)
              .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml", "beans.xml" );
    }
    
    @Inject
    MascotaPersistence ep;
    
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;
    
    @Test
    public void createTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEntity mascota = factory.manufacturePojo(MascotaEntity.class);
        MascotaEntity result = ep.create(mascota);
        Assert.assertNotNull(result);
        
        MascotaEntity entity = em.find(MascotaEntity.class, result.getId());

        Assert.assertEquals(mascota.getNombre(),entity.getNombre());

    }
}
