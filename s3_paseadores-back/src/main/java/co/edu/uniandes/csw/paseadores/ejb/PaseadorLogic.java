/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.PaseadorPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel García
 */
@Stateless
public class PaseadorLogic 
{
    @Inject
    private PaseadorPersistence persistence;
    
    public PaseadorEntity createPaseador(PaseadorEntity paseador) throws BusinessLogicException
    {
        if(paseador.getNombre()==null)
        {
            throw new BusinessLogicException("El nombre del paseador es nulo");
        }
        paseador=persistence.create(paseador);
        return paseador;
    }
}
