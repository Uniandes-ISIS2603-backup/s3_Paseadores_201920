/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.FranjaHorariaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Santiago Bolaños Vega
 */
@Stateless
public class FranjaHorariaLogic {
    
    @Inject
    private FranjaHorariaPersistence persistence;
    
    public FranjaHorariaEntity createFranjaHoraria( FranjaHorariaEntity franja ) throws BusinessLogicException {
        if( franja.getFecha() == null ){
            throw new BusinessLogicException("La franja no tiene una fecha definida.");
        }
        franja = persistence.create(franja);
        return franja;
    }
}
