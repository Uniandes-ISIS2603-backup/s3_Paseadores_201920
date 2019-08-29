/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;

import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import co.edu.uniandes.csw.paseadores.persistence.MascotaPersistence;
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
    MascotaPersistence mp;
    
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;
    
    @Inject
    UserTransaction utx;
   
    private List<MascotaEntity> data = new ArrayList<>();
    
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() 
    {
        em.createQuery("delete from MascotaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MascotaEntity entity = factory.manufacturePojo(MascotaEntity.class);

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
    public void createTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEntity mascota = factory.manufacturePojo(MascotaEntity.class);
        MascotaEntity result = mp.create(mascota);
        Assert.assertNotNull(result);
        
        MascotaEntity entity = em.find(MascotaEntity.class, result.getId());

        Assert.assertEquals(mascota.getNombre(),entity.getNombre());

    }
    
    @Test
    public void findMascotasTest() 
    {
        List<MascotaEntity> list = mp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MascotaEntity ent : list) {
            boolean found = false;
            for (MascotaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getMascotaTest() {
        MascotaEntity entity = data.get(0);
        MascotaEntity newEntity = mp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
     @Test
    public void updateMascotaTest() 
    {
        MascotaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);

        newEntity.setId(entity.getId());

        mp.update(newEntity);

        MascotaEntity resp = em.find(MascotaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    @Test
    public void deleteMascotaTest() {
        MascotaEntity entity = data.get(0);
        mp.delete(entity.getId());
        MascotaEntity deleted = em.find(MascotaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
