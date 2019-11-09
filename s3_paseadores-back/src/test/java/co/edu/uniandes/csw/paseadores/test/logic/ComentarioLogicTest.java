/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.ComentarioLogic;
import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ComentarioPersistence;
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
 * @author Nicolas Potes Garcia
 */
@RunWith(Arquillian.class)
public class ComentarioLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ComentarioLogic comentarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private ArrayList<ComentarioEntity> data = new ArrayList<>();

    private ArrayList<ContratoEntity> contratos = new ArrayList<>();

    private ArrayList<PaseadorEntity> paseadores = new ArrayList<>();

    private ArrayList<ClienteEntity> clientes = new ArrayList<>();
    
    private ContratoEntity contratoTest;

    private PaseadorEntity paseadorTest;

    private ClienteEntity clienteTest;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioLogic.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        em.createQuery("delete from ContratoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
        em.createQuery("delete from PaseadorEntity").executeUpdate();
    }

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
            ComentarioEntity comentario = factory.manufacturePojo(ComentarioEntity.class);
            comentario.setContrato(contrato);
            comentario.setPaseador(paseador);
            em.persist(comentario);
            data.add(comentario);
            clientes.add(cliente);
            contratos.add(contrato);
            paseadores.add(paseador);
        }
    }

    @Test
    public void createComentario() throws BusinessLogicException {
        ComentarioEntity comentario = factory.manufacturePojo(ComentarioEntity.class);
        ComentarioEntity result = comentarioLogic.createComentario(comentario, contratoTest.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getPaseador().getId(), comentario.getPaseador().getId());
    }

    @Test(expected = BusinessLogicException.class)
    public void createComentarioInfoNull() throws BusinessLogicException {
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        newEntity.setInfoComentario(null);
        ComentarioEntity result = comentarioLogic.createComentario(newEntity, contratos.get(0).getId());
    }

    @Test(expected = BusinessLogicException.class)
    public void createComentarioContratoNull() throws BusinessLogicException {

        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        newEntity.setContrato(null);
        ComentarioEntity result = comentarioLogic.createComentario(newEntity, null);
    }

    @Test(expected = BusinessLogicException.class)
    public void createComentarioInfoCantCaracteres() throws BusinessLogicException {

        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        newEntity.setInfoComentario("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.");
        ComentarioEntity result = comentarioLogic.createComentario(newEntity, contratos.get(0).getId());
    }

    /**
     * Prueba para eliminar un Comentario
     */
    @Test
    public void deleteComentarioTest() throws BusinessLogicException {

        ComentarioEntity entity = data.get(0);
        comentarioLogic.deleteComentario(entity.getId(), entity.getPaseador().getId());
        ComentarioEntity deleted = em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para consultar un Comentario.
     */
    @Test
    public void getComentarioTest() throws BusinessLogicException {

        ComentarioEntity entity = data.get(0);
        ComentarioEntity resultEntity = comentarioLogic.getComentario(entity.getId(), entity.getPaseador().getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getInfoComentario(), resultEntity.getInfoComentario());
    }

    @Test
    public void getComentariosTest() throws BusinessLogicException {
        List<ComentarioEntity> list = comentarioLogic.getComentarios();
        Assert.assertEquals(data.size(), list.size());
        for (ComentarioEntity entity : list) {
            boolean found = false;
            for (ComentarioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para actualizar un Comentario.
     */
    @Test
    public void updateComentarioTest() throws BusinessLogicException {

        ComentarioEntity entity = data.get(0);
        ComentarioEntity pojoEntity = factory.manufacturePojo(ComentarioEntity.class);
        pojoEntity.setId(data.get(0).getId());
        comentarioLogic.updateComentario(contratos.get(0).getId(), pojoEntity);
        ComentarioEntity resp = em.find(ComentarioEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getInfoComentario(), resp.getInfoComentario());
    }

}
