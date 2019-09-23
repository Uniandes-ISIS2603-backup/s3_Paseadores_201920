/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.ComentarioLogic;
import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
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
    
    @Inject
    UserTransaction utx;
    
    @PersistenceContext
    private EntityManager em;
   
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
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for( int i = 0; i < 3 ; ++i ){
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    @Test
    public void createComentario() throws BusinessLogicException {
    	ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        ComentarioEntity result = comentarioLogic.createComentario(newEntity);
        Assert.assertNotNull(result);     
        
        ComentarioEntity entity = em.find( ComentarioEntity.class , result.getId());
        Assert.assertEquals(result.getName(), entity.getName());
        
    }
    
    
            @Test( expected = BusinessLogicException.class)
            public void createComentarioInfoNull() throws BusinessLogicException{
            	ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
                newEntity.setInfoComentario(null);
                ComentarioEntity result = comentarioLogic.createComentario(newEntity);
            }
            
            
            /**
     * Prueba para eliminar un Comentario
     */
    @Test
    public void deleteComentarioTest() throws BusinessLogicException 
    {
    	ComentarioEntity entity = data.get(0);
        comentarioLogic.deleteComentario(entity.getId());
        ComentarioEntity deleted = em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
            
    
    /**
     * Prueba para consultar un Comentario.
     */
    @Test
    public void getContratoTest() 
    {
    	ComentarioEntity entity = data.get(0);
    	ComentarioEntity resultEntity = comentarioLogic.getComentario(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para consultar la lista de Comentarios.
     */
    @Test
    public void getComentariosTest() 
    {
        List<ComentarioEntity> list = comentarioLogic.getComentarios();
        Assert.assertEquals(data.size(), list.size());
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
    public void updateComentarioTest() throws BusinessLogicException 
    {
    	ComentarioEntity entity = data.get(0);
    	ComentarioEntity pojoEntity = factory.manufacturePojo(ComentarioEntity.class);

        pojoEntity.setId(entity.getId());

        comentarioLogic.updateComentario(pojoEntity.getId(), pojoEntity);

        ComentarioEntity resp = em.find(ComentarioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        
    }

    
}