/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ZonaPersistence;
import javax.inject.Inject;

/**
 *
 * @author Juan Vergara
 */
public class ZonaLogic {
    @Inject
    private ZonaPersistence persistence;
    
    public ZonaEntity createZona(ZonaEntity zona) throws BusinessLogicException{
        
        if(zona.getInfoZona()!=null){
            String info = zona.getInfoZona().replace(" ", "");
            if(info.isEmpty()){
                throw new BusinessLogicException("LA información de la zona esta vacia");
            }
        }
        else{
            throw new BusinessLogicException("LA información de la zona esta vacia");
        }
        zona = persistence.create(zona);
        return zona;
    }
}
