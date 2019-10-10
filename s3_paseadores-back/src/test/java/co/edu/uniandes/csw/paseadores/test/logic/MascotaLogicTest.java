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
    private void clearData() 
    {
        em.createQuery("delete from MascotaEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        clienteTest = factory.manufacturePojo(ClienteEntity.class);;
        em.persist(clienteTest);
        for( int i = 0; i < 3 ; ++i )
        {
            MascotaEntity entity = factory.manufacturePojo(MascotaEntity.class);
            entity.setCliente(clienteTest);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una mascota
     *
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @Test
    public void createMascota() throws BusinessLogicException 
    {
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        newEntity.setCliente(clienteTest);
        MascotaEntity result = mascotaLogic.createMascota(clienteTest.getId(),newEntity);
        Assert.assertNotNull(result);     
        
        MascotaEntity entity = em.find( MascotaEntity.class , result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
       
    }
    
    /**
     * Prueba para crear una mascota con nombre null
     *
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @Test( expected = BusinessLogicException.class)
    public void createMascotaNombreNull() throws BusinessLogicException
    {
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        newEntity.setNombre(null);
        newEntity.setCliente(clienteTest);
        MascotaEntity result = mascotaLogic.createMascota(clienteTest.getId(),newEntity);
    }
    
    /**
     * Prueba para crear una mascota con informacion null
     *
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @Test( expected = BusinessLogicException.class)
    public void createMascotaInformacionNull() throws BusinessLogicException
    {
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        newEntity.setInfoMascota(null);
        newEntity.setCliente(clienteTest);
        MascotaEntity result = mascotaLogic.createMascota(clienteTest.getId(),newEntity);
    }
    
    /**
     * Prueba para crear una mascota con cliente null
     *
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @Test( expected = BusinessLogicException.class)
    public void createMascotaClienteNull() throws BusinessLogicException
    {
        MascotaEntity newEntity = factory.manufacturePojo(MascotaEntity.class);
        newEntity.setCliente(null);
        MascotaEntity result = mascotaLogic.createMascota(null,newEntity);
    }
    
    
     /**
     * Prueba para consultar la lista de mascotas.
     *
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @Test
    public void getMascotasTest() throws BusinessLogicException 
    {
        List<MascotaEntity> list = mascotaLogic.getMascotas(clienteTest.getId());
        Assert.assertEquals(data.size(), list.size());
        for (MascotaEntity entity : list) 
        {
            boolean found = false;
            for (MascotaEntity storedEntity : data) 
            {
                if (entity.getId().equals(storedEntity.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar una mascota.
     */
    @Test
    public void getMascotaTest() throws BusinessLogicException 
    {
        MascotaEntity entity = data.get(0);
        MascotaEntity resultEntity = mascotaLogic.getMascota(clienteTest.getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para actualizar una mascota.
     */
    @Test
    public void updateMascotaTest() throws BusinessLogicException 
    {
        MascotaEntity entity = data.get(0);
        MascotaEntity pojoEntity = factory.manufacturePojo(MascotaEntity.class);

        pojoEntity.setId(entity.getId());

        mascotaLogic.updateMascota(clienteTest.getId(), pojoEntity);

        MascotaEntity resp = em.find(MascotaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
     /**
     * Prueba para eliminar una mascota
     *
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @Test
    public void deleteMascotaTest() throws BusinessLogicException 
    {
        MascotaEntity entity = data.get(0);
        mascotaLogic.deleteMascota(clienteTest.getId(), entity.getId());
        MascotaEntity deleted = em.find(MascotaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para eliminarle una mascota a un cliente al cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteMascotaConClienteNoAsociadoTest() throws BusinessLogicException 
    {
        MascotaEntity entity = data.get(0);
        mascotaLogic.deleteMascota(clienteTest.getId()+1, entity.getId());
    }
}