/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.ClienteLogic;
import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ClientePersistence;
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
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class ClienteLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ClienteLogic clienteLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private ArrayList<ClienteEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for( int i = 0; i < 3 ; ++i ){
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createClienteTest() throws BusinessLogicException {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setCorreo("hola@hotmail.com");
        ClienteEntity result = clienteLogic.createCliente(newEntity);
        Assert.assertNotNull(result);     
        
        ClienteEntity entity = em.find( ClienteEntity.class , result.getId());
        Assert.assertEquals(result.getNombre(), entity.getNombre());
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteNombreNullTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setNombre(null);
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteNombreVacioTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setNombre("");
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteNombreNumericoTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setNombre("135151");
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteCorreoNullTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setCorreo(null);
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteCorreoVacioTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setCorreo("");
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteCorreoNumericoTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setCorreo("135151");
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteCorreoSinArrobaTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setCorreo("hotmail.com");
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteContrasenaNullTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setContrasena(null);
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteContrasenaVacioTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setContrasena("");
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteInfoContactoNullTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setInfoContacto(null);
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createClienteInfoContactoVacioTest() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setInfoContacto("");
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
    @Test
    public void getClientesTest(){
        List<ClienteEntity> clientes = clienteLogic.getClientes();
        Assert.assertEquals(data.size(), clientes.size());
        for( ClienteEntity cliente : clientes ){
            boolean found = false;
            for( ClienteEntity c : data ){
                if( cliente.getId().equals(c.getId())){
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getClienteTest(){
        ClienteEntity cliente = data.get(0);
        ClienteEntity result = clienteLogic.getCliente(cliente.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(cliente.getNombre(), result.getNombre());
        Assert.assertEquals(cliente.getCorreo(), result.getCorreo());
        Assert.assertEquals(cliente.getContrasena() , result.getContrasena());
        Assert.assertEquals(cliente.getInfoContacto(), result.getInfoContacto());
    }
    
    @Test
    public void updateClienteTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(cliente.getId());
        pojoEntity.setCorreo("hola@hotmail.com");
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
        ClienteEntity resp = em.find(ClienteEntity.class, cliente.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getCorreo(), resp.getCorreo());
        Assert.assertEquals(pojoEntity.getContrasena() , resp.getContrasena());
        Assert.assertEquals(pojoEntity.getInfoContacto(), resp.getInfoContacto());
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteNombreNullTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setNombre(null);
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteNombreVacioTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setNombre("");
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteNombreNumericoTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setNombre("123");
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteCorreoNullTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setCorreo(null);
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteCorreoVacioTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setCorreo("");
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteCorreoNumericoTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setCorreo("123");
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteCorreoSinArrobaTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setCorreo("holaaa");
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteContrasenaNullTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setContrasena(null);
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteContrasenaVacioTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setContrasena("");
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteInfoContactoNullTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setInfoContacto(null);
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test(expected = BusinessLogicException.class )
    public void updateClienteInfoContactoVacioTest() throws BusinessLogicException{
        ClienteEntity cliente = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setInfoContacto("");
        pojoEntity.setId(cliente.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    @Test
    public void deleteClienteTest(){
        ClienteEntity cliente = data.get(0);
        clienteLogic.deleteCliente(cliente.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, cliente.getId());
        Assert.assertNull(deleted);
    }
}
