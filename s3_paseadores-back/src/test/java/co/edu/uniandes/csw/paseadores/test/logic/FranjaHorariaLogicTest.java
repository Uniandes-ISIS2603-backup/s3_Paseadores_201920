/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.FranjaHorariaLogic;
import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.FranjaHorariaPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private PaseadorEntity paseadorTest;
    
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
        PaseadorEntity paseador = factory.manufacturePojo(PaseadorEntity.class);
        em.persist(paseador);
        paseadorTest = paseador;
        for( int i = 0; i < 3 ; ++i ){
            FranjaHorariaEntity entity = factory.manufacturePojo(FranjaHorariaEntity.class);
            entity.setPaseador(paseador);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createFranjaHoraria() throws BusinessLogicException {
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        Date temp;
        if( newEntity.getInicio().after(newEntity.getFin())){
            temp = newEntity.getFin();
            newEntity.setFin(newEntity.getInicio());
            newEntity.setInicio(temp);
        }
        
        newEntity.setPaseador(paseadorTest);
        FranjaHorariaEntity result = franjaLogic.createFranjaHoraria(newEntity);
        Assert.assertNotNull(result);     
        
        System.out.println(result.getInicio().toString());
        
        FranjaHorariaEntity entity = em.find( FranjaHorariaEntity.class , result.getId());
        Assert.assertEquals(entity.getInicio(), result.getInicio());
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createFranjaFechaInicioNull() throws BusinessLogicException{
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        newEntity.setInicio(null);
        newEntity.setPaseador(paseadorTest);
        FranjaHorariaEntity result = franjaLogic.createFranjaHoraria(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createFranjaFechaFinNull() throws BusinessLogicException{
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        newEntity.setFin(null);
        newEntity.setPaseador(paseadorTest);
        FranjaHorariaEntity fin = franjaLogic.createFranjaHoraria(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createFranjaPaseadorNull() throws BusinessLogicException{
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        newEntity.setPaseador(null);
        FranjaHorariaEntity fin = franjaLogic.createFranjaHoraria(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createFranjaInicioDepuesFin() throws BusinessLogicException{
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        Date temp;
        if( !newEntity.getInicio().after(newEntity.getFin())){
            temp = newEntity.getFin();
            newEntity.setFin(newEntity.getInicio());
            newEntity.setInicio(temp);
        }
        newEntity.setPaseador(null);
        FranjaHorariaEntity fin = franjaLogic.createFranjaHoraria(newEntity);
    }
    
    @Test
    public void getFranjasTest(){
        List<FranjaHorariaEntity> franjas = franjaLogic.getFranjas();
        Assert.assertEquals(data.size(), franjas.size());
        for( FranjaHorariaEntity franja : franjas ){
            boolean found = false;
            for( FranjaHorariaEntity c : data ){
                if( franja.getId().equals(c.getId())){
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getFranjaTest(){
        FranjaHorariaEntity franja = data.get(0);
        FranjaHorariaEntity result = franjaLogic.getFranja(franja.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(franja.getInicio(), result.getInicio());
        Assert.assertEquals(franja.getFin(), result.getFin());
        Assert.assertEquals(franja.getPaseador().getId(), result.getPaseador().getId());
    }
    
    @Test
    public void updateFranjaTest() throws BusinessLogicException{
        FranjaHorariaEntity franja = data.get(0);
        FranjaHorariaEntity pojoFranja = factory.manufacturePojo(FranjaHorariaEntity.class);
        pojoFranja.setId(franja.getId());
        Date temp;
        if( pojoFranja.getInicio().after(pojoFranja.getFin())){
            temp = pojoFranja.getFin();
            pojoFranja.setFin(pojoFranja.getInicio());
            pojoFranja.setInicio(temp);
        }
        pojoFranja.setPaseador(paseadorTest);
        franjaLogic.updateFranja(franja.getId(), pojoFranja);
        FranjaHorariaEntity resp = em.find(FranjaHorariaEntity.class, franja.getId());
        Assert.assertNotNull(resp);
        Assert.assertEquals(pojoFranja.getInicio(), resp.getInicio());
        Assert.assertEquals(pojoFranja.getFin(), resp.getFin());
        Assert.assertEquals(pojoFranja.getPaseador().getId(), resp.getPaseador().getId());
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void updateFranjaFechaInicioNull() throws BusinessLogicException{
        FranjaHorariaEntity franja = data.get(0);
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        newEntity.setId(franja.getId());
        newEntity.setInicio(null);
        newEntity.setPaseador(paseadorTest);
        franjaLogic.updateFranja(franja.getId() , newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void updateFranjaFechaFinNull() throws BusinessLogicException{
        FranjaHorariaEntity franja = data.get(0);
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        newEntity.setId(franja.getId());
        newEntity.setFin(null);
        newEntity.setPaseador(paseadorTest);
        franjaLogic.updateFranja(franja.getId() , newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void updateFranjaPaseadorNull() throws BusinessLogicException{
        FranjaHorariaEntity franja = data.get(0);
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        newEntity.setId(franja.getId());
        newEntity.setPaseador(null);
        franjaLogic.updateFranja(franja.getId() , newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void updateFranjaInicioDepuesFin() throws BusinessLogicException{
        FranjaHorariaEntity franja = data.get(0);
        FranjaHorariaEntity newEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        Date temp;
        if( !newEntity.getInicio().after(newEntity.getFin())){
            temp = newEntity.getFin();
            newEntity.setFin(newEntity.getInicio());
            newEntity.setInicio(temp);
        }
        newEntity.setId(franja.getId());
        newEntity.setPaseador(paseadorTest);
        franjaLogic.updateFranja(franja.getId() , newEntity);
    }
    
    @Test
    public void deleteFranjaTest(){
        FranjaHorariaEntity franja = data.get(0);
        franjaLogic.deleteFranja(franja.getId());
        FranjaHorariaEntity deleted = em.find(FranjaHorariaEntity.class, franja.getId());
        Assert.assertNull(deleted);
    }
}
