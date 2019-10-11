/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;
 

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.PagoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author Mario Hurtado
 */

@Stateless
public class PagoLogic {
    
    /**
     * Inyección de la persistencia de pago
     */
    @Inject
    private PagoPersistence persistence;
    
    
    //Métodos 
    
    /**
     * Crea el pago a partir de una instacia de PagoEntity
     * @param pago El pago a crear
     * @return el pago creado
     * @throws BusinessLogicException si al validar la información hay errores
     */
    public PagoEntity createPago (PagoEntity pago) throws BusinessLogicException{
        
        if (pago.getIdPaseador()== null){
            throw new BusinessLogicException ("El id del paseador no debe ser nulo");
    }
        if (pago.getIdUsuario()== null){
            throw new BusinessLogicException ("El id del usuario no debe ser nulo");
    }
        if (pago.getValorServicio()<= 0){
            throw new BusinessLogicException ("El valor del servicio no es válido");
    }
        pago = persistence.create(pago);
        return pago;   
    }
  
    
         /**
	 * Obtiene los datos de una instancia de Pago a partir de su id.
	 *
	 * @param pagoId Identificador de la instancia a consultar
	 * @return Instancia de PagoEntity con los datos consultados.
	 */
    public PagoEntity getPago(Long pagoId) 
	{

		PagoEntity pagoEntity = persistence.find(pagoId);

		return pagoEntity;
	}
    
    
    /**
	 * Obtiene la lista de los registros de Pago.
	 *
	 * @return Colección de objetos de PagoEntity.
	 */
	public List<PagoEntity> getPagos() {

		List<PagoEntity> lista = persistence.findAll();

		return lista;
	}
        
        /**
	 * Actualiza la información de una instancia de Pago.
	 *
	 * @param pagoId Identificador de la instancia a actualizar
	 * @param pagoEntity Instancia de PagoEntity con los nuevos datos.
	 * @return Instancia de PagoEntity con los datos actualizados.
	 */
	public PagoEntity updatePago(Long pagoId, PagoEntity pagoEntity) throws BusinessLogicException
	{

                if(pagoEntity.getIdPaseador()==null || pagoEntity.getIdPaseador().equals("") || NumberUtils.isCreatable(pagoEntity.getIdPaseador()))
		{
			throw new BusinessLogicException("El id del paseador es nulo o tiene un formato incorrecto");
		}
		
		if(pagoEntity.getIdUsuario()==null || NumberUtils.isCreatable(pagoEntity.getIdUsuario().toString()))
		{
			throw new BusinessLogicException("El id del usuario es nulo o tiene un formato incorrecto");
		}
			
		if(pagoEntity.getValorServicio() <= 0)
		{
			throw new BusinessLogicException("El valor del servicio es inválido");
		}
		
		PagoEntity nuevaEntidad = persistence.update(pagoEntity);

		return nuevaEntidad;
	}
        
        
	public void deletePagoFinalizado(PagoEntity pago) throws BusinessLogicException {


		if(!(pago.isPagoRealizado())) {

			throw new BusinessLogicException("No se puede eliminar el pago porque no se ha realizado");

		}

		persistence.delete(pago.getId());
	}
        
        
	public void deletePagoSinFinalizar(PagoEntity pago) throws BusinessLogicException {
		
		if(pago.isPagoRealizado() == true) {
			
			throw new BusinessLogicException("No se puede eliminar el pago porque ya se realizó");
			
		}
		
		persistence.delete(pago.getId());
	}
        
        /**
	 * Elimina una instancia de Pago de la base de datos.
	 *
	 * @param pagoId Identificador de la instancia a eliminar.
	 * 
	 */
	public void deletePago(Long pagoId) throws BusinessLogicException 
	{

		persistence.delete(pagoId);

	}

}
