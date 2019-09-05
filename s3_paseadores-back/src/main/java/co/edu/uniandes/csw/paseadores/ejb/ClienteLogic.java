/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ClientePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Santiaago Bolaños Vega   
 */
@Stateless
public class ClienteLogic {
    
    @Inject
    private ClientePersistence persistence;
    
    public ClienteEntity createCliente( ClienteEntity cliente ) throws BusinessLogicException{
        if( cliente.getNombre() == null ){
            throw new BusinessLogicException("El nombre del cliente está vacío.");
        }
        cliente = persistence.create(cliente);
        return cliente;
    }
}
