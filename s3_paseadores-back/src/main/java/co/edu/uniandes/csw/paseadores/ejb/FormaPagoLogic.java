/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;
 

import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.FormaPagoPersistence;
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
public class FormaPagoLogic {
    
    @Inject
    private FormaPagoPersistence persistence;
    
    public FormaPagoEntity createFormaPago (FormaPagoEntity formaPago) throws BusinessLogicException{
        
        if (formaPago.getCapacidadPago()== 0){
            throw new BusinessLogicException ("La capacidad debe ser mayor a 0");
    }
        formaPago = persistence.create(formaPago);
        return formaPago;
    }
    
         /**
	 * Obtiene los datos de una instancia de FormaPago a partir de su id.
	 *
	 * @param formaPagoId Identificador de la instancia a consultar
	 * @return Instancia de FormaPagoEntity con los datos consultados.
	 */
    public FormaPagoEntity getFormaPago(Long formaPagoId) 
	{

		FormaPagoEntity formaPagoEntity = persistence.find(formaPagoId);

		return formaPagoEntity;
	}
    
    
     /**
	 * Obtiene la lista de los registros de las formas de Pago.
	 *
	 * @return Colección de objetos de FomraPagoEntity.
	 */
	public List<FormaPagoEntity> getFormasPago() {

		List<FormaPagoEntity> lista = persistence.findAll();

		return lista;
	}
        
        
          /**
	 * Actualiza la información de una instancia de FormaPago.
	 *
	 * @param formaPagoId Identificador de la instancia a actualizar
	 * @param formaPagoEntity Instancia de FormaPagoEntity con los nuevos datos.
	 * @return Instancia de FormaPagoEntity con los datos actualizados.
	 */
	public FormaPagoEntity updateFormaPago(Long formaPagoId, FormaPagoEntity formaPagoEntity) throws BusinessLogicException
	{
		if(formaPagoEntity.getCliente() == null || NumberUtils.isCreatable(formaPagoEntity.getCliente().toString()))
		{
			throw new BusinessLogicException("El cliente es nulo o tiene un formato incorrecto");
		}
		
		FormaPagoEntity nuevaFormaEntidad = persistence.update(formaPagoEntity);

		return nuevaFormaEntidad;
	}
        
        
        
         /**
	 * Elimina una instancia FormaPago de la base de datos.
	 *
	 * @param formaPagoId Identificador de la instancia a eliminar.
	 * 
	 */
	public void deleteFormaPago(Long formaPagoId) throws BusinessLogicException 
	{

		persistence.delete(formaPagoId);

	}
}
