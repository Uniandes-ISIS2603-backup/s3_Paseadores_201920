/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ContratoPersistence;
//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.sql.Time;
import java.util.List;
//import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author Nicolas Potes Garcia
 */

@Stateless
public class ContratoLogic {

	@Inject
	private ContratoPersistence persistence;


	public ContratoEntity createContrato(ContratoEntity contrato) throws BusinessLogicException {

		if( contrato.getName() == null ){
			throw new BusinessLogicException("El nombre del contrato está vacío");
		}


		if(contrato.getValorServicio() == null || contrato.getValorServicio() < 0) {

			throw new BusinessLogicException("El valor del contrato no es válido o no se ha definido");

		}


		if(contrato.getIdPaseador() == null || contrato.getIdPaseador().isEmpty()) {

			throw new BusinessLogicException("El contrato no tiene asociado un paseador o un cliente");

		}

		if(contrato.getIdUsuario() == null) {

			throw new BusinessLogicException("El contrato no tiene asociado uno(s) cliente(s)");

		}

		if(contrato.getZona() ==null) {

			throw new BusinessLogicException("No existe una zona en el contrato");

		}         


		if(contrato.getHorarios() ==null) {

			throw new BusinessLogicException("No existe una fraja horaria para el contrato");

		}

		if(contrato.getCliente() == null) {

			throw new BusinessLogicException("El contrato no tiene asociado un cliente");

		}


		if(contrato.getPaseador() == null) {

			throw new BusinessLogicException("El contrato no tiene asociado un paseador");

		}
		
		if(contrato.getMascotas() == null || contrato.getMascotas().isEmpty()) {

			throw new BusinessLogicException("El contrato no dispone de mascota(s)");

		}
		
		if(contrato.getPago() == null) {

			throw new BusinessLogicException("El contrato no tiene creado un pago y su metodo de pago");

		}


		contrato = persistence.create(contrato);
		return contrato;

	}
        
        
//        public void createContratoMascotasIgualCliente(ContratoEntity contrato) throws BusinessLogicException{
//		
//		if(contrato.getCliente().getMascotas() == contrato.getMascotas()) {
//			
//			throw new BusinessLogicException("Las mascotas del contrato no corresponden a las del cliente");
//			
//		}
//		
//	}


	/**
	 * Obtiene los datos de una instancia de Contrato a partir de su ID.
	 *
	 * @param contratoId Identificador de la instancia a consultar
	 * @return Instancia de ContratoEntity con los datos del Paseador consultado.
	 */
	public ContratoEntity getContrato(Long contratoId) 
	{

		ContratoEntity contratoEntity = persistence.find(contratoId);

		return contratoEntity;
	}

	/**
	 * Obtiene la lista de los registros de Contrato.
	 *
	 * @return Colección de objetos de ContratoEntity.
	 */
	public List<ContratoEntity> getContratos() {

		List<ContratoEntity> lista = persistence.findAll();

		return lista;
	}

	/**
	 * Actualiza la información de una instancia de Contrato.
	 *
	 * @param contratoId Identificador de la instancia a actualizar
	 * @param contratoEntity Instancia de ContratoEntity con los nuevos datos.
	 * @return Instancia de ContratoEntity con los datos actualizados.
	 */
	public ContratoEntity updateContrato(Long contratoId, ContratoEntity contratoEntity) throws BusinessLogicException
	{

                if(contratoEntity.getName()==null || contratoEntity.getName().equals("") || NumberUtils.isCreatable(contratoEntity.getName()))
		{
			throw new BusinessLogicException("El nombre del contrato es nulo o tiene un formato incorrecto");
		}
		
		if(contratoEntity.getHorarios()==null || NumberUtils.isCreatable(contratoEntity.getHorarios().toString()))
		{
			throw new BusinessLogicException("Los horarios del contrato son nulos o tiene un formato incorrecto");
		}
			
		if(contratoEntity.getPaseador()==null)
		{
			throw new BusinessLogicException("El paseador del contrato no existe");
		}
		
		if(contratoEntity.getCliente()==null)
		{
			throw new BusinessLogicException("El cliente del contrato no existe");
		}
		
		if(contratoEntity.getPago()==null)
		{
			throw new BusinessLogicException("El pago del contrato no existe");
		}
	
		ContratoEntity newContratoEntity = persistence.update(contratoEntity);

		return newContratoEntity;

	}


	public void deleteContratoSinFinalizar(ContratoEntity contrato) throws BusinessLogicException {


		if(contrato.getFinalizado() == false) {

			throw new BusinessLogicException("No se puede eliminar el contrato porque no se ha terminado");

		}

		persistence.delete(contrato.getId());

	}
	
	
	
	public void deleteContratoSinPagar(ContratoEntity contrato) throws BusinessLogicException {
		
		if(contrato.getPago().isPagoRealizado() == false) {
			
			throw new BusinessLogicException("No se puede eliminar el contrato porque no se ha pagado");
			
		}
		
		persistence.delete(contrato.getId());
		
	}


	/**
	 * Elimina una instancia de Contrato de la base de datos.
	 *
	 * @param contratoId Identificador de la instancia a eliminar.
	 * 
	 */
	public void deleteContrato(Long contratoId) throws BusinessLogicException 
	{

		persistence.delete(contratoId);

	}



}