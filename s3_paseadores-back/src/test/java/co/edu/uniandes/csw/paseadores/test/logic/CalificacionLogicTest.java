/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.CalificacionLogic;
import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.CalificacionPersistence;
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
 * @author Juan Esteban Vergara
 */
@RunWith(Arquillian.class)

public class CalificacionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CalificacionLogic calificacionLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private ArrayList<CalificacionEntity> data = new ArrayList<>();
    
    private ArrayList<ContratoEntity> contratos = new ArrayList<>();
    
    private ArrayList<PaseadorEntity> paseadores = new ArrayList<>();
    
    private ArrayList<ClienteEntity> clientes = new ArrayList<>();
    
    private ContratoEntity contratoTest;
    
    private PaseadorEntity paseadorTest;
    
    private ClienteEntity clienteTest;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from CalificacionEntity").executeUpdate();
        em.createQuery("delete from ContratoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
        em.createQuery("delete from PaseadorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        clienteTest = factory.manufacturePojo(ClienteEntity.class);
        em.persist(clienteTest);
        paseadorTest = factory.manufacturePojo(PaseadorEntity.class);
        em.persist(paseadorTest);
        contratoTest = factory.manufacturePojo(ContratoEntity.class);
        contratoTest.setPaseador(paseadorTest);
        contratoTest.setCliente(clienteTest);
        em.persist(contratoTest);
        
        for (int i = 0; i < 3; ++i) {
            ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
            em.persist(cliente);
            PaseadorEntity paseador = factory.manufacturePojo(PaseadorEntity.class);
            em.persist(paseador);
            ContratoEntity contrato = factory.manufacturePojo(ContratoEntity.class);
            contrato.setCliente(cliente);
            contrato.setPaseador(paseador);
            em.persist(contrato);
            CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
            calificacion.setContrato(contrato);
            calificacion.setPaseador(paseador);
            em.persist(calificacion);
            data.add(calificacion);
            clientes.add(cliente);
            contratos.add(contrato);
            paseadores.add(paseador);
        }
    }
    
    @Test
    public void createCalificacion() throws BusinessLogicException {
        CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
        calificacion.setCalificacion(4);
        CalificacionEntity result = calificacionLogic.createCalificacion(contratoTest.getId(), calificacion);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getCalificacion(), calificacion.getCalificacion());
    }
    
    @Test
    public void getCalificacionTest() throws BusinessLogicException {
        CalificacionEntity calificacion = data.get(0);
        CalificacionEntity result = calificacionLogic.getCalificacion(calificacion.getPaseador().getId(), calificacion.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(calificacion.getCalificacion(), result.getCalificacion());
        Assert.assertEquals(calificacion.getContrato().getId(), calificacion.getContrato().getId());
        Assert.assertEquals(calificacion.getPaseador().getId(), result.getPaseador().getId());
    }
    
    @Test
    public void getCalificacionesTest() {
        List<CalificacionEntity> calificaciones = calificacionLogic.getCalificaciones();
        Assert.assertEquals(data.size(), calificaciones.size());
        for (CalificacionEntity calificacion : calificaciones) {
            boolean found = false;
            for (CalificacionEntity c : data) {
                if (calificacion.getId().equals(c.getId())) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void updateCalificacionTest() throws BusinessLogicException {
        CalificacionEntity calificacion = data.get(0);
        CalificacionEntity pojoCalificacion = factory.manufacturePojo(CalificacionEntity.class);
        pojoCalificacion.setId(calificacion.getId());
        pojoCalificacion.setCalificacion(3);
        calificacionLogic.updateCalificacion(calificacion.getContrato().getId(), pojoCalificacion);
        CalificacionEntity resp = em.find(CalificacionEntity.class, calificacion.getId());
        Assert.assertNotNull(resp);
        Assert.assertEquals(pojoCalificacion.getCalificacion(), resp.getCalificacion());
    }
    
    @Test
    public void deleteCalificacionTest() throws BusinessLogicException {
        CalificacionEntity calificacion = data.get(0);
        calificacionLogic.deleteCalificacion(calificacion.getPaseador().getId(), calificacion.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, calificacion.getId());
        Assert.assertNull(deleted);
    }
}
