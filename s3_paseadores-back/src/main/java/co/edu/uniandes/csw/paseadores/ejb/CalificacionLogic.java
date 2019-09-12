/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.CalificacionPersistence;
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
    
}
