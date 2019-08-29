/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.persistence.ClientePersistence;
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
 * @author Santiago Bolaños Vega
 */
@RunWith(Arquillian.class)
public class ClientePersistenceTest {
   
    @Inject
    ClientePersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ClienteEntity> data = new ArrayList<>();
    
    @Deployment 
    public  static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml" , "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml" , "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un cliente.
     */
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = cp.create(cliente);
        Assert.assertNotNull(result);
        
        ClienteEntity entity = em.find( ClienteEntity.class , result.getId());
        Assert.assertEquals(cliente.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para consultar la lista de clientes.
     */
    @Test
    public void findAllTest(){
        List<ClienteEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for( ClienteEntity cliente : list ){
            boolean found = false;
            for( ClienteEntity ent : data){
                if( cliente.getId().equals(ent.getId())){
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para encontrar un cliente.
     */
    @Test
    public void findTest(){
        ClienteEntity cliente = data.get(0);
        ClienteEntity newCliente = cp.find(cliente.getId());
        Assert.assertNotNull(newCliente);
        Assert.assertEquals(cliente.getNombre(), newCliente.getNombre());
        Assert.assertEquals(cliente.getCorreo(), newCliente.getCorreo());
    }
    
    /**
     * Prueba para actualizar un cliente.
     */
    @Test
    public void updateTest(){
        ClienteEntity cliente = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
        entity.setId(cliente.getId());
        cp.update(entity);
        ClienteEntity resp = em.find(ClienteEntity.class , cliente.getId());
        Assert.assertEquals(resp.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para eliminar un cliente.
     */
    @Test
    public void  deleteTest(){
        ClienteEntity cliente = data.get(0);
        cp.delete(cliente.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, cliente.getId());
        Assert.assertNull(deleted); 
    }
}
