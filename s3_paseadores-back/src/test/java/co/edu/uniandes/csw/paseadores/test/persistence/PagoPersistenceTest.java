/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.persistence;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import co.edu.uniandes.csw.paseadores.persistence.PagoPersistence;
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
 * @author Mario Hurtado
 */
@RunWith(Arquillian.class)
public class PagoPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    PagoPersistence pp;

    @Inject
    UserTransaction utx;

    @PersistenceContext
    private EntityManager em;

    private List<PagoEntity> data = new ArrayList<>();

    private List<ContratoEntity> contratosTest = new ArrayList<>();
    
    private ContratoEntity contratoTest;

    private void clearData() {
        em.createQuery("delete from PagoEntity").executeUpdate();
        em.createQuery("delete from ContratoEntity").executeUpdate();
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

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        contratoTest = factory.manufacturePojo(ContratoEntity.class);
        em.persist(contratoTest);
        for (int i = 0; i < 3; i++) {
            PagoEntity entity = factory.manufacturePojo(PagoEntity.class);
            ContratoEntity contrato = factory.manufacturePojo(ContratoEntity.class);
            em.persist(contrato);
            entity.setContrato(contrato);
            em.persist(entity);
            data.add(entity);
            contratosTest.add(contrato);
        }
    }

    @Test
    public void createPagoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity pago = factory.manufacturePojo(PagoEntity.class);
        pago.setContrato(contratoTest);
        PagoEntity result = pp.create(pago);
        Assert.assertNotNull(result);

        PagoEntity entity = em.find(PagoEntity.class, result.getId());
        Assert.assertEquals(pago.getId(), entity.getId());
    }

    @Test
    public void updatePagoTest() {
        PagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PagoEntity newEntity = factory.manufacturePojo(PagoEntity.class);
        newEntity.setContrato(contratosTest.get(0));
        newEntity.setId(entity.getId());

        pp.update(newEntity);

        PagoEntity resp = em.find(PagoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    @Test
    public void deletePagoTest() {
        PagoEntity entity = data.get(0);
        pp.delete(entity.getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
