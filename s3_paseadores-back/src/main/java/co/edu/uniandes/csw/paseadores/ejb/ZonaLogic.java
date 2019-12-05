/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ZonaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Vergara
 */
@Stateless
public class ZonaLogic {

    @Inject
    private ZonaPersistence persistence;

    public ZonaEntity createZona(ZonaEntity zona) throws BusinessLogicException {
        if (zona.getInfoZona() == null || zona.getInfoZona().replace(" ", "").isEmpty()) {
                throw new BusinessLogicException("La información de la zona es nula");
        } 
        zona = persistence.create(zona);
        return zona;
    }

    public ZonaEntity getZona(Long zonaId) 
    {
        return persistence.find(zonaId);
    }

    public List<ZonaEntity> getZonas() 
    {
        return persistence.findAll();
    }

    public ZonaEntity updateZona(Long zonaId, ZonaEntity zona) throws BusinessLogicException {
        if (zona.getInfoZona() != null) {
            String info = zona.getInfoZona().replace(" ", "");
            if (info.isEmpty()) {
                throw new BusinessLogicException("La información de la zona esta vacia");
            }
        } else {
            throw new BusinessLogicException("La información de la zona no se encuentra");
        }
        return persistence.update(zona);
    }

    public void deleteZona(Long zonaId) throws BusinessLogicException 
    {
        ZonaEntity zona = persistence.find(zonaId);
        if (zona.getContratos() != null && !zona.getContratos().isEmpty()) 
        {
            throw new BusinessLogicException("La zona tiene contratos todavia");
        }
        if (zona.getPaseadores() != null && !zona.getPaseadores().isEmpty()) 
        {
            throw new BusinessLogicException("La zona tiene paseadores todavia");
        }
        persistence.delete(zonaId);
    }
}
