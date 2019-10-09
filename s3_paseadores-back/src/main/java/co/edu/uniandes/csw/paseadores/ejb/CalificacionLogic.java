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
 *
 * @author Juan Vergara
 */
@Stateless
public class CalificacionLogic {
    @Inject
    private CalificacionPersistence persistence;
    
    public CalificacionEntity createCalificacion(CalificacionEntity calificacion) throws BusinessLogicException{
        if(calificacion.getCalificacion()>5||calificacion.getCalificacion()<0){
            throw new BusinessLogicException("La calificación esta fuera de los valores limites [0...5]");
            
        }
        calificacion = persistence.create(calificacion);
        return calificacion;
    } 
    public CalificacionEntity getCalificacion(Long calificacionId) 
	{

		CalificacionEntity calificacionEntity = persistence.find(calificacionId);

		return calificacionEntity;
	}
  
	public List<CalificacionEntity> getCalificacions() {

		List<CalificacionEntity> lista = persistence.findAll();

		return lista;
	}

	
	public CalificacionEntity updateCalificacion(Long calificacionId, CalificacionEntity calificacionEntity) throws BusinessLogicException
	{

               if(calificacionEntity.getCalificacion()>5||calificacionEntity.getCalificacion()<0){
            throw new BusinessLogicException("La calificación esta fuera de los valores limites [0...5]");
            
        }
	
		CalificacionEntity newCalificacionEntity = persistence.update(calificacionEntity);

		return newCalificacionEntity;

	}


	
	
	
	
	

	
	public void deleteCalificacion(Long calificacionId) throws BusinessLogicException 
	{

		persistence.delete(calificacionId);

	}

}
