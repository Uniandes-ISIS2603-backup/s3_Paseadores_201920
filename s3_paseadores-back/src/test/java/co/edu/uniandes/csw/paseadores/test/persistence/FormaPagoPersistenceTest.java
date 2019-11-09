/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import co.edu.uniandes.csw.paseadores.persistence.FormaPagoPersistence;
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
 * @author Mario Hurtado
 */
@RunWith(Arquillian.class)
public class FormaPagoPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FormaPagoEntity.class.getPackage())
                .addPackage(FormaPagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    FormaPagoPersistence fpp;

    @Inject
    UserTransaction utx;

    private List<FormaPagoEntity> data = new ArrayList<>();

    private List<ClienteEntity> clientesTest = new ArrayList<>();

    @PersistenceContext
    private EntityManager em;

    private void clearData() {
        em.createQuery("delete from FormaPagoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            FormaPagoEntity entity = factory.manufacturePojo(FormaPagoEntity.class);
            ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class); 
            em.persist(cliente);
            entity.setCliente(cliente);
            em.persist(entity);
            data.add(entity);
            clientesTest.add(cliente);
        }
    }
    
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
    public void createFormaPagoTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        FormaPagoEntity formaPago = factory.manufacturePojo(FormaPagoEntity.class);
        formaPago.setCliente(clientesTest.get(0));
        FormaPagoEntity result = fpp.create(formaPago);
        Assert.assertNotNull(result);

        FormaPagoEntity entity = em.find(FormaPagoEntity.class, result.getId());
        Assert.assertEquals(formaPago.getCapacidadPago(), entity.getCapacidadPago(), 0);
    }

    @Test
    public void findFormasPagoTest() 
    {
        List<FormaPagoEntity> list = fpp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FormaPagoEntity ent : list) {
            boolean found = false;
            for (FormaPagoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getFormaPagoPorClienteTest() 
    {
        FormaPagoEntity entity = data.get(0);
        FormaPagoEntity newEntity = fpp.find(clientesTest.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());  
    }
    
    @Test
    public void updatePagoTest() 
    {
        FormaPagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FormaPagoEntity newEntity = factory.manufacturePojo(FormaPagoEntity.class);

        newEntity.setId(entity.getId());
        newEntity.setCliente(clientesTest.get(0));
        fpp.update(newEntity);

        FormaPagoEntity resp = em.find(FormaPagoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId(), 0);
    }

    @Test
    public void deletePagoTest() 
    {
        FormaPagoEntity entity = data.get(0);
        fpp.delete(entity.getId());
        FormaPagoEntity deleted = em.find(FormaPagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
