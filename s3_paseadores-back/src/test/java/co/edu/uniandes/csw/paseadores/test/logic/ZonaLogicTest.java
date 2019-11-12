/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.ZonaLogic;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ZonaPersistence;
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
 * @author Juan Vergara
 */
@RunWith(Arquillian.class)
public class ZonaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ZonaLogic zonaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private ArrayList<ZonaEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ZonaEntity.class.getPackage())
                .addPackage(ZonaLogic.class.getPackage())
                .addPackage(ZonaPersistence.class.getPackage())
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
        em.createQuery("delete from ZonaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; ++i) {
            ZonaEntity entity = factory.manufacturePojo(ZonaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createZonaTest() throws BusinessLogicException{
        ZonaEntity zona = factory.manufacturePojo(ZonaEntity.class);
        ZonaEntity result = zonaLogic.createZona(zona);
        Assert.assertNotNull(result);
        //Zona coverage
        zona.setContratos(new ArrayList());
        List<ContratoEntity> nuevosContratos = zona.getContratos();
        zona.setContratos(nuevosContratos);
        //Fin zona coverage
        ZonaEntity entity = em.find(ZonaEntity.class, result.getId());
        Assert.assertEquals(result.getInfoZona(), entity.getInfoZona());
    }
    
    @Test
    public void getZonasTest(){
        List<ZonaEntity> zonas = zonaLogic.getZonas();
        Assert.assertEquals(data.size(), zonas.size());
        for( ZonaEntity zona : zonas ){
            boolean found = false;
            for( ZonaEntity z : data ){
                if( zona.getId().equals(z.getId())){
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getZonaTest() throws BusinessLogicException{
        ZonaEntity zona = data.get(0);
        ZonaEntity result = zonaLogic.getZona(zona.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(zona.getInfoZona(), result.getInfoZona());
    }
    
    @Test
    public void updateZonaTest() throws BusinessLogicException{
        ZonaEntity zona = data.get(0);
        ZonaEntity pojoEntity = factory.manufacturePojo(ZonaEntity.class);
        pojoEntity.setId(zona.getId());
        zonaLogic.updateZona(pojoEntity.getId(), pojoEntity);
        ZonaEntity resp = em.find(ZonaEntity.class, zona.getId());
        Assert.assertEquals(pojoEntity.getInfoZona(), resp.getInfoZona());
    }
    
    @Test
    public void deleteZonaTest() throws BusinessLogicException{
        ZonaEntity zona = data.get(0);
        zonaLogic.deleteZona(zona.getId());
        ZonaEntity deleted = em.find(ZonaEntity.class, zona.getId());
        Assert.assertNull(deleted);
    }
}
