/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.MascotaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel García
 */
@Stateless
public class MascotaLogic 
{
    @Inject
    private MascotaPersistence persistence;
    
    public MascotaEntity createMascota(MascotaEntity mascota) throws BusinessLogicException
    {
        if(mascota.getNombre()==null)
        {
            throw new BusinessLogicException("El nombre de la mascota es nulo");
        }
        mascota=persistence.create(mascota);
        return mascota;
    }
}
