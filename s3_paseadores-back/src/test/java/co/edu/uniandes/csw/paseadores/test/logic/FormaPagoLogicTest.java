/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.FormaPagoLogic;
import co.edu.uniandes.csw.paseadores.ejb.PagoLogic;
import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.FormaPagoPersistence;
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
public class FormaPagoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FormaPagoLogic formaPagoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private ArrayList<FormaPagoEntity> data = new ArrayList<FormaPagoEntity>();

    private ClienteEntity cliente;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FormaPagoEntity.class.getPackage())
                .addPackage(FormaPagoLogic.class.getPackage())
                .addPackage(FormaPagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
        em.createQuery("delete from FormaPagoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        cliente = factory.manufacturePojo(ClienteEntity.class);
        em.persist(cliente);
        for (int i = 0; i < 3; i++) {
            FormaPagoEntity formaPagoEntity = factory.manufacturePojo(FormaPagoEntity.class);
            formaPagoEntity.setCliente(cliente);
            em.persist(formaPagoEntity);
            data.add(formaPagoEntity);
        }
    }

    @Test
    public void createFormaPagoTest() throws BusinessLogicException {
        FormaPagoEntity formaPago = factory.manufacturePojo(FormaPagoEntity.class);
        FormaPagoEntity result = formaPagoLogic.createFormaPago(cliente.getId(), formaPago);
        Assert.assertNotNull(result);
        FormaPagoEntity entity = em.find(FormaPagoEntity.class, result.getId());
        Assert.assertEquals(entity.getCapacidadPago(), result.getCapacidadPago(), 1e-8);
        Assert.assertEquals(cliente.getId(), entity.getCliente().getId());
    }

    /**
     * Prueba para consultar un Pago.
     */
    @Test
    public void getFormaPagoTest() {
        FormaPagoEntity entity = data.get(0);
        FormaPagoEntity resultEntity = formaPagoLogic.getFormaPagoPorCliente(cliente.getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    @Test
    public void getFormasPagoTest() {
        List<FormaPagoEntity> list = formaPagoLogic.getFormasPago();
        Assert.assertEquals(data.size(), list.size());
        for (FormaPagoEntity entity : list) {
            boolean found = false;
            for (FormaPagoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void deleteFormaPagoTest() throws BusinessLogicException {
        FormaPagoEntity entity = data.get(0);
        formaPagoLogic.deleteFormaPago(cliente.getId(), entity.getId());
        FormaPagoEntity deleted = em.find(FormaPagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateFormaPago() throws BusinessLogicException {
        FormaPagoEntity entity = data.get(0);
        FormaPagoEntity nueva = factory.manufacturePojo(FormaPagoEntity.class);
        nueva.setId(entity.getId());
        formaPagoLogic.updateFormaPago(cliente.getId(), nueva);
        FormaPagoEntity resp = em.find(FormaPagoEntity.class, nueva.getId());
        Assert.assertNotNull(resp);
        Assert.assertEquals(nueva.getCapacidadPago(), resp.getCapacidadPago(), 1e-8);
    }
}
