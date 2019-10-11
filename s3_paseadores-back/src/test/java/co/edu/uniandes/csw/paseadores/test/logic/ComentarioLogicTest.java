/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.ComentarioLogic;
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

    private List<PaseadorEntity> dataPaseador = new ArrayList<PaseadorEntity>();

    @Inject
    UserTransaction utx;

    @PersistenceContext
    private EntityManager em;

    private ContratoEntity contratoTest;

    private ContratoEntity contratoTest2;

    private ContratoEntity contratoTest3;

    private PaseadorEntity paseadorTest;
    
    private PaseadorEntity x;

    private ArrayList<ComentarioEntity> data = new ArrayList<ComentarioEntity>();

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
        em.createQuery("delete from PaseadorEntity").executeUpdate();
    }

    
    private void insertData() {

        paseadorTest = factory.manufacturePojo(PaseadorEntity.class);
        

        contratoTest = factory.manufacturePojo(ContratoEntity.class);
        contratoTest.setPaseador(paseadorTest);

        contratoTest2 = factory.manufacturePojo(ContratoEntity.class);
        contratoTest2.setPaseador(paseadorTest);

        contratoTest3 = factory.manufacturePojo(ContratoEntity.class);
        contratoTest3.setPaseador(paseadorTest);

        ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);
        entity.setContrato(contratoTest);

        data.add(entity);

        ComentarioEntity entity2 = factory.manufacturePojo(ComentarioEntity.class);
        entity2.setContrato(contratoTest2);

        data.add(entity2);

        ComentarioEntity entity3 = factory.manufacturePojo(ComentarioEntity.class);
        entity3.setContrato(contratoTest3);

        data.add(entity3);

        em.persist(contratoTest);
        em.persist(contratoTest2);
        em.persist(contratoTest3);

        em.persist(entity);
        em.persist(entity2);
        em.persist(entity3);

        paseadorTest.setComentarios(data);

        em.persist(paseadorTest);
        
        x = em.find(PaseadorEntity.class, paseadorTest.getId());

    }
    
     

    @Test
    public void createComentario() throws BusinessLogicException {
        
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);

        newEntity.setContrato(contratoTest);

        ComentarioEntity result = comentarioLogic.createComentario(newEntity, contratoTest.getId());
        Assert.assertNotNull(result);

        ComentarioEntity entity = em.find(ComentarioEntity.class, result.getId());
        Assert.assertEquals(result.getName(), entity.getName());

    }

    @Test(expected = BusinessLogicException.class)
    public void createComentarioInfoNull() throws BusinessLogicException {
        
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        newEntity.setInfoComentario(null);
        ComentarioEntity result = comentarioLogic.createComentario(newEntity, contratoTest.getId());
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
        ComentarioEntity result = comentarioLogic.createComentario(newEntity, contratoTest.getId());
    }

    /**
     * Prueba para eliminar un Comentario
     */
    @Test
    public void deleteComentarioTest() throws BusinessLogicException {
        
        ComentarioEntity entity = data.get(0);
        comentarioLogic.deleteComentario(entity.getId(), contratoTest.getId());
        ComentarioEntity deleted = em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para consultar un Comentario.
     */
    @Test
    public void getComentarioTest() throws BusinessLogicException {

        ComentarioEntity entity = data.get(0);
        ComentarioEntity resultEntity = comentarioLogic.getComentario(entity.getId(), contratoTest.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    @Test
    public void getComentariosTest() throws BusinessLogicException {

        
        List<ComentarioEntity> list = comentarioLogic.getComentarios(paseadorTest.getId());
        
        //Assert.assertEquals(data.size(), list.size());
        for (ComentarioEntity entity : list) {
            boolean found = false;
            for (ComentarioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
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

        System.out.println(pojoEntity.getId());

        System.out.println(data.get(0).getId());

        pojoEntity.setId(data.get(0).getId());

        comentarioLogic.updateComentario(contratoTest.getId(), pojoEntity);

        ComentarioEntity resp = em.find(ComentarioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());

    }

}
