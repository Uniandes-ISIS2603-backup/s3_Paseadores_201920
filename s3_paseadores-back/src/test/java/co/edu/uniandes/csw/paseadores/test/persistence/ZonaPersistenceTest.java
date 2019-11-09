/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;

import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import co.edu.uniandes.csw.paseadores.persistence.ZonaPersistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class ZonaPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ZonaEntity.class.getPackage())
                .addPackage(ZonaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private ZonaPersistence cp;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ZonaEntity> data = new ArrayList<>();

    private PaseadorEntity paseadorTest;

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ZonaEntity").executeUpdate();
        em.createQuery("delete from PaseadorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ZonaEntity entity = factory.manufacturePojo(ZonaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
        paseadorTest = factory.manufacturePojo(PaseadorEntity.class);
        paseadorTest.setZonas(data);
        em.persist(paseadorTest);
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
        PodamFactory factory = new PodamFactoryImpl();
        ZonaEntity Zona = factory.manufacturePojo(ZonaEntity.class);
        ZonaEntity result = cp.create(Zona);
        Assert.assertNotNull(result);

        ZonaEntity entity = em.find(ZonaEntity.class, result.getId());
        Assert.assertEquals(Zona.getInfoZona(), entity.getInfoZona());
    }

    @Test
    public void findZonasTest() {
        List<ZonaEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ZonaEntity ent : list) {
            boolean found = false;
            for (ZonaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getZonaTest() {
        ZonaEntity entity = data.get(0);
        ZonaEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getInfoZona(), newEntity.getInfoZona());

    }
    
    @Test
    public void getZonas() 
    {
        List<ZonaEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(ZonaEntity z:list)
        {
            boolean found = false;
            for( ZonaEntity zona : data ){
                if( z.getId().equals(zona.getId())){
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void updateZonaTest() {
        ZonaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ZonaEntity newEntity = factory.manufacturePojo(ZonaEntity.class);
        newEntity.setPaseadores(entity.getPaseadores());
        newEntity.setId(entity.getId());

        cp.update(newEntity);

        ZonaEntity resp = em.find(ZonaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    @Test
    public void deleteZonaTest() {
        ZonaEntity entity = data.get(0);
        cp.delete(entity.getId());
        ZonaEntity deleted = em.find(ZonaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
