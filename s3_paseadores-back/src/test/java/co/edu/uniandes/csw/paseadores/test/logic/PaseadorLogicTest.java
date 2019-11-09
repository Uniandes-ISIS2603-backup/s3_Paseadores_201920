/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.PaseadorLogic;
import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.PaseadorPersistence;
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
public class PaseadorLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PaseadorLogic paseadorLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private ArrayList<PaseadorEntity> data = new ArrayList<PaseadorEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PaseadorEntity.class.getPackage())
                .addPackage(PaseadorLogic.class.getPackage())
                .addPackage(PaseadorPersistence.class.getPackage())
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
    private void clearData() {
        em.createQuery("delete from PaseadorEntity").executeUpdate();
        em.createQuery("delete from FranjaHorariaEntity").executeUpdate();
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        for( int i = 0; i < 3 ; i++ )
        {
            PaseadorEntity paseadorEntity = factory.manufacturePojo(PaseadorEntity.class);          
            em.persist(paseadorEntity);
            /*paseadorEntity.setFranjas(new ArrayList<>());
            paseadorEntity.setComentarios(new ArrayList<>());
            paseadorEntity.setCalificaciones(new ArrayList<>());*/
            data.add(paseadorEntity);
        }
        /*PaseadorEntity paseador = data.get(0);
        FranjaHorariaEntity franjaHorariaEntity = factory.manufacturePojo(FranjaHorariaEntity.class);
        franjaHorariaEntity.setPaseador(paseador);
        em.persist(franjaHorariaEntity);
        paseador.getFranjas().add(franjaHorariaEntity);
        
        PaseadorEntity paseador2 = data.get(1);
        ComentarioEntity comentarioEntity = factory.manufacturePojo(ComentarioEntity.class);
        comentarioEntity.setPaseador(paseador2);
        em.persist(comentarioEntity);
        paseador2.getComentarios().add(comentarioEntity);
        
        PaseadorEntity paseador3 = data.get(2);
        CalificacionEntity calificacionEntity = factory.manufacturePojo(CalificacionEntity.class);
        calificacionEntity.setPaseador(paseador3);
        em.persist(calificacionEntity);
        paseador3.getCalificaciones().add(calificacionEntity);*/
    }
    
    @Test
    public void createPaseador() throws BusinessLogicException 
    {
        
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        Assert.assertNotNull(result);     
        
        PaseadorEntity entity = em.find( PaseadorEntity.class , result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
        
    }
       
       
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorNombreNull() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setNombre(null);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorCorreoNull() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setCorreo(null);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorContrasenaNull() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setContrasena(null);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorInfoContactoNull() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setInfoContacto(null);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorFotoNull() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setFoto(null);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorPrecioNull() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setPrecio(null);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorGananciasNull() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setGanancias(null);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorInfoAdicionalNull() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setInfoAdicional(null);
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorNombreVacio() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setNombre("");
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorCorreoVacio() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setCorreo("");
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorContrasenaVacio() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setContrasena("");
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorInfoContactoVacio() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setInfoContacto("");
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorFotoVacio() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setFoto("");
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorNombreNumerico() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setNombre("5");
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorCorreoNumerico() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setCorreo("5");
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createPaseadorFotoNumerico() throws BusinessLogicException{
        PaseadorEntity newEntity = factory.manufacturePojo(PaseadorEntity.class);
        newEntity.setFoto("5");
        PaseadorEntity result = paseadorLogic.createPaseador(newEntity);
        
    }
    
    /**
     * Prueba para consultar un Paseador.
     */
    @Test
    public void getPaseadorTest() throws BusinessLogicException
    {
        PaseadorEntity entity = data.get(0);
        PaseadorEntity resultEntity = paseadorLogic.getPaseador(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
    
    /**
     * Prueba para consultar la lista de Paseadors.
     */
    @Test
    public void getPaseadoresTest() 
    {
        List<PaseadorEntity> list = paseadorLogic.getPaseadores();
        Assert.assertEquals(data.size(), list.size());
        for (PaseadorEntity entity : list) {
            boolean found = false;
            for (PaseadorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para actualizar un Paseador.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @Test
    public void updatePaseadorTest() throws BusinessLogicException 
    {
        PaseadorEntity entity = data.get(0);
        PaseadorEntity pojoEntity = factory.manufacturePojo(PaseadorEntity.class);

        pojoEntity.setId(entity.getId());

        paseadorLogic.updatePaseador(pojoEntity.getId(), pojoEntity);

        PaseadorEntity resp = em.find(PaseadorEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        
    }
    /**
     * Prueba para eliminar un Paseador
     *
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @Test
    public void deletePaseadorTest() throws BusinessLogicException 
    {
        PaseadorEntity entity = data.get(0);
        paseadorLogic.deletePaseador(entity.getId());
        PaseadorEntity deleted = em.find(PaseadorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}