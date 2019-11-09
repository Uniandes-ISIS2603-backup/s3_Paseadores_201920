/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.PagoLogic;
import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
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
public class PagoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PagoLogic pagoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private ArrayList<PagoEntity> data = new ArrayList<PagoEntity>();

    private ArrayList<ContratoEntity> contratos = new ArrayList<>();

    private FormaPagoEntity formaPago;
    
    private ContratoEntity contratoTest;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PagoEntity.class.getPackage())
                .addPackage(PagoLogic.class.getPackage())
                .addPackage(PagoPersistence.class.getPackage())
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
        em.createQuery("delete from PagoEntity").executeUpdate();
        em.createQuery("delete from ContratoEntity").executeUpdate();
        em.createQuery("delete from FormaPagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        formaPago = factory.manufacturePojo(FormaPagoEntity.class);
        em.persist(formaPago);
        
        contratoTest = factory.manufacturePojo(ContratoEntity.class);
        em.persist(contratoTest);

        for (int i = 0; i < 3; i++) {
            ContratoEntity contrato = factory.manufacturePojo(ContratoEntity.class);
            em.persist(contrato);
            PagoEntity pagoEntity = factory.manufacturePojo(PagoEntity.class);
            pagoEntity.setContrato(contrato);
            pagoEntity.setFormaPago(formaPago);
            em.persist(pagoEntity);
            data.add(pagoEntity);
            contratos.add(contrato);
        }
    }

    @Test
    public void createPagoTest() throws BusinessLogicException {
        PagoEntity pago = factory.manufacturePojo(PagoEntity.class);
        pago.setFormaPago(formaPago);
        pago.setPagoRealizado(false);
        PagoEntity result = pagoLogic.createPago(contratoTest.getId(), pago);
        Assert.assertNotNull(result);
        PagoEntity entity = em.find(PagoEntity.class, result.getId());
        Assert.assertEquals(result.getId(), entity.getId());
    }

    @Test(expected = BusinessLogicException.class)
    public void createPagoValorServicioNegativo() throws BusinessLogicException {
        PagoEntity pago = factory.manufacturePojo(PagoEntity.class);
        pago.setValorServicio(-2000);
        PagoEntity result = pagoLogic.createPago(contratos.get(0).getId() , pago  );
    }

    /**
     * Prueba para consultar un Pago.
     */
    @Test
    public void getPagoTest() {
        PagoEntity entity = data.get(0);
        PagoEntity resultEntity = pagoLogic.getPago(contratos.get(0).getId() , entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    @Test
    public void getPagosTest() {
        List<PagoEntity> list = pagoLogic.getPagos();
        Assert.assertEquals(data.size(), list.size());
        for (PagoEntity entity : list) {
            boolean found = false;
            for (PagoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void updatePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        PagoEntity pojoEntity = factory.manufacturePojo(PagoEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setFormaPago(formaPago);
        pagoLogic.updatePago(pojoEntity.getId(), pojoEntity);
        PagoEntity resp = em.find(PagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

    @Test
    public void deletePagoTest() throws BusinessLogicException {
        PagoEntity entity = data.get(0);
        entity.setPagoRealizado(true);
        pagoLogic.updatePago(entity.getContrato().getId(), entity);
        pagoLogic.deletePago(entity.getId() , contratos.get(0).getId());
        PagoEntity deleted = em.find(PagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
