/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.PaseadorLogic;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.PaseadorPersistence;
import java.util.ArrayList;
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
 * @author Daniel García
 */
@RunWith(Arquillian.class)
public class PaseadorLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PaseadorLogic paseadorLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private ArrayList<PaseadorEntity> data = new ArrayList<PaseadorEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaseadorEntity.class.getPackage())
                .addPackage(PaseadorLogic.class.getPackage())
                .addPackage(PaseadorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
              .addAsManifestResource("META-INF/beans.xml" , "beans.xml");
    }
    
    @Before
    public void configTest(){
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
    
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PaseadorEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for( int i = 0; i < 3 ; ++i ){
            PaseadorEntity entity = factory.manufacturePojo(PaseadorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createPaseador() throws BusinessLogicException {
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        Assert.assertNotNull(result);     
        
        PaseadorEntity entity = em.find( PaseadorEntity.class , result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorNombreNull() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setNombre(null);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
    }
    
}