/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.MascotaLogic;
import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.MascotaPersistence;
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
public class MascotaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MascotaLogic mascotaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private ArrayList<MascotaEntity> data = new ArrayList<MascotaEntity>();
    
    private ClienteEntity clienteTest;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEntity.class.getPackage())
                .addPackage(MascotaLogic.class.getPackage())
                .addPackage(MascotaPersistence.class.getPackage())
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
        em.createQuery("delete from MascotaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
        clienteTest = cliente;
        em.persist(cliente);
        for( int i = 0; i < 3 ; ++i ){
            MascotaEntity entity = factory.manufacturePojo(MascotaEntity.class);
            entity.setCliente(cliente);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createMascota() throws BusinessLogicException {
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        newEntity.setCliente(clienteTest);
        MascotaEntity result = mascotaLogic.createMascota(newEntity);
        Assert.assertNotNull(result);     
        
        MascotaEntity entity = em.find( MascotaEntity.class , result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createMascotaNombreNull() throws BusinessLogicException{
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        newEntity.setNombre(null);
        MascotaEntity result = mascotaLogic.createMascota(newEntity);
    }
    
}