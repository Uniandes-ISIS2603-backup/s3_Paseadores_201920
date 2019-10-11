/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.CalificacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * clase que define la logica de la calificacion
 * @author Juan Vergara
 */
@Stateless
public class CalificacionLogic {
    @Inject
    private CalificacionPersistence persistence;
    /**
     * Crea una calificacion según la que llega por parametro y comprueba las restricciones basicas
     * @param calificacion
     * @return calificacion 
     * @throws BusinessLogicException 
     */
    public CalificacionEntity createCalificacion(CalificacionEntity calificacion) throws BusinessLogicException{
        if(calificacion.getCalificacion()>5||calificacion.getCalificacion()<0){
            throw new BusinessLogicException("La calificación esta fuera de los valores limites [0...5]");
            
        }
        calificacion = persistence.create(calificacion);
        return calificacion;
    } 
    /**
     * Busca y regresa la calificacion con el id de parametro
     * @param calificacionId
     * @return calificacionEntity
     */
    public CalificacionEntity getCalificacion(Long calificacionId) 
	{

		CalificacionEntity calificacionEntity = persistence.find(calificacionId);

		return calificacionEntity;
	}
 
    /**
     * Regresa todas las calificaciones existentes
     * @return lista
     */
	public List<CalificacionEntity> getCalificacions() {

		List<CalificacionEntity> lista = persistence.findAll();

		return lista;
	}

	/**
         * Actualiza la informacion de una calificacion y comprueba que esta no se incumpla las restricciones de una calififcacion y regresa una copia
         * @param calificacionId
         * @param calificacionEntity
         * @return newCalificacionEntitty
         * @throws BusinessLogicException 
         */
	public CalificacionEntity updateCalificacion(Long calificacionId, CalificacionEntity calificacionEntity) throws BusinessLogicException
	{

               if(calificacionEntity.getCalificacion()>5||calificacionEntity.getCalificacion()<0){
            throw new BusinessLogicException("La calificación esta fuera de los valores limites [0...5]");
            
        }
	
		CalificacionEntity newCalificacionEntity = persistence.update(calificacionEntity);

		return newCalificacionEntity;

	}


	
	
	
	
	

	/**
         * Elimina la calificacion asociada al id dado
         * @param calificacionId
         * @throws BusinessLogicException 
         */
	public void deleteCalificacion(Long calificacionId) throws BusinessLogicException 
	{

		persistence.delete(calificacionId);

	}

}
