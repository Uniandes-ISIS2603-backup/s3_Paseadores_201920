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
import javax.ejb.Stateless;
import javax.inject.Inject;

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
}
