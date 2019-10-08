/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.test.logic;

import co.edu.uniandes.csw.paseadores.ejb.ComentarioLogic;
import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
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

	private ClienteEntity clienteTest;
	private PaseadorEntity paseadorTest;
	private ContratoEntity contratoTest;

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

		ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
		clienteTest = cliente;
		em.persist(cliente);
		

		PaseadorEntity paseador = factory.manufacturePojo(PaseadorEntity.class);
		paseadorTest = paseador;
		em.persist(paseador);
		

		ContratoEntity contrato = factory.manufacturePojo(ContratoEntity.class);
		contratoTest = contrato;
		em.persist(contrato);

		for( int i = 0; i < 3 ; i++ ){
			ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);

			entity.setCliente(cliente);
			entity.setPaseador(paseador);
			entity.setContrato(contrato);

			em.persist(entity);
			data.add(entity);
		}
                
                 for (int i = 0; i < 3; i++) {
        PaseadorEntity entity = factory.manufacturePojo(PaseadorEntity.class);
        entity.setComentarios(data);
        em.persist(entity);
        dataPaseador.add(entity);
    }
	}


	@Test
	public void createComentario() throws BusinessLogicException {
		ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
		
		newEntity.setCliente(clienteTest);
		newEntity.setPaseador(paseadorTest);
		newEntity.setContrato(contratoTest);
		
		
		newEntity.getContrato().setPaseador(paseadorTest);
		newEntity.getContrato().setCliente(clienteTest);
		
		
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
	
	
	@Test( expected = BusinessLogicException.class)
	public void createComentarioClienteNull() throws BusinessLogicException{
		ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
		newEntity.setCliente(null);
		ComentarioEntity result = comentarioLogic.createComentario(newEntity);
	}
	
	
	@Test( expected = BusinessLogicException.class)
	public void createComentarioPaseadorNull() throws BusinessLogicException{
		ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
		newEntity.setPaseador(null);
		ComentarioEntity result = comentarioLogic.createComentario(newEntity);
	}
	
	@Test( expected = BusinessLogicException.class)
	public void createComentarioContratoNull() throws BusinessLogicException{
		ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
		newEntity.setContrato(null);
		ComentarioEntity result = comentarioLogic.createComentario(newEntity);
	}
	
	@Test( expected = BusinessLogicException.class)
	public void createComentarioInfoCantCaracteres() throws BusinessLogicException{
		ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
		newEntity.setInfoComentario("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.");
		ComentarioEntity result = comentarioLogic.createComentario(newEntity);
	}
	
	@Test( expected = BusinessLogicException.class)
	public void createComentarioPaseadorDist() throws BusinessLogicException{
		
		ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
		newEntity.setPaseador(paseadorTest);
		newEntity.setContrato(contratoTest);
		
		PaseadorEntity paseadorEntity = factory.manufacturePojo(PaseadorEntity.class);
		newEntity.getContrato().setPaseador(paseadorEntity);
		
		
		ComentarioEntity result = comentarioLogic.createComentario(newEntity);
	}
	
	
	@Test( expected = BusinessLogicException.class)
	public void createComentarioClienteDist() throws BusinessLogicException{
		
		ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
		newEntity.setCliente(clienteTest);
		newEntity.setContrato(contratoTest);
		
		ClienteEntity clienteEntity = factory.manufacturePojo(ClienteEntity.class);
		newEntity.getContrato().setCliente(clienteEntity);
		
		
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
	public void getComentarioTest() 
	{
		ComentarioEntity entity = data.get(0);
		ComentarioEntity resultEntity = comentarioLogic.getComentario(entity.getId());
		Assert.assertNotNull(resultEntity);
		Assert.assertEquals(entity.getId(), resultEntity.getId());
	}

//	 @Test
//	public void getComentariosTest() 
//	{
//            System.out.println(data.size());
//            System.out.println((comentarioLogic.getComentarios(dataPaseador.get(0).getId())).size());
//    	List<ComentarioEntity> list = comentarioLogic.getComentarios(dataPaseador.get(0).getId());
//		Assert.assertEquals(data.size(), list.size());
//		for (ComentarioEntity entity : list) {
//			boolean found = false;
//			for (ComentarioEntity storedEntity : data) {
//				if (entity.getId().equals(storedEntity.getId())) {
//					found = true;
//				}
//			}
//			Assert.assertTrue(found);
//		}
//	}

	/**
	 * Prueba para actualizar un Comentario.
	 */
	@Test
	public void updateComentarioTest() throws BusinessLogicException 
	{
		ComentarioEntity entity = data.get(0);
		ComentarioEntity pojoEntity = factory.manufacturePojo(ComentarioEntity.class);
		
		ClienteEntity clienteEntity = factory.manufacturePojo(ClienteEntity.class);
		PaseadorEntity paseadorEntity = factory.manufacturePojo(PaseadorEntity.class);
		ContratoEntity contratoEntity = factory.manufacturePojo(ContratoEntity.class);

		pojoEntity.setId(entity.getId());
		
		pojoEntity.setCliente(clienteTest);
		pojoEntity.setPaseador(paseadorTest);
		pojoEntity.setContrato(contratoTest);

		comentarioLogic.updateComentario(pojoEntity.getId(), pojoEntity);

		ComentarioEntity resp = em.find(ComentarioEntity.class, entity.getId());

		Assert.assertEquals(pojoEntity.getId(), resp.getId());

	}


}