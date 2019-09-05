/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.FranjaHorariaLogic;
import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.FranjaHorariaPersistence;
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
 * @author Santiago Bolaños Vega
 */
@RunWith(Arquillian.class)
public class FranjaHorariaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private FranjaHorariaLogic franjaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private ArrayList<FranjaHorariaEntity> data = new ArrayList<FranjaHorariaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FranjaHorariaEntity.class.getPackage())
                .addPackage(FranjaHorariaLogic.class.getPackage())
                .addPackage(FranjaHorariaPersistence.class.getPackage())
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
        em.createQuery("delete from FranjaHorariaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for( int i = 0; i < 3 ; ++i ){
            FranjaHorariaEntity entity = factory.manufacturePojo(FranjaHorariaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createCliente() throws BusinessLogicException {
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        FranjaHorariaEntity result = franjaLogic.createFranjaHoraria(newEntity);
        Assert.assertNotNull(result);     
        
        FranjaHorariaEntity entity = em.find( FranjaHorariaEntity.class , result.getId());
        Assert.assertEquals(entity.getFecha(), result.getFecha());
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createEstudianteNombreNull() throws BusinessLogicException{
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        newEntity.setFecha(null);
        FranjaHorariaEntity result = franjaLogic.createFranjaHoraria(newEntity);
    }
    
}
