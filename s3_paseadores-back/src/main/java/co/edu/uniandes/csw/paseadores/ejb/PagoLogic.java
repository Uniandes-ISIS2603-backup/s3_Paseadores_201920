/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;
 

import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.PagoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mario Hurtado
 */

@Stateless
public class PagoLogic {
    
    @Inject
    private PagoPersistence persistence;
    
    public PagoEntity createPago (PagoEntity pago) throws BusinessLogicException{
        
        if (pago.getIdPaseador()== null){
            throw new BusinessLogicException ("El id del paseador no debe ser nulo");
    }
        pago = persistence.create(pago);
        return pago;
    }
    
    
    
}
