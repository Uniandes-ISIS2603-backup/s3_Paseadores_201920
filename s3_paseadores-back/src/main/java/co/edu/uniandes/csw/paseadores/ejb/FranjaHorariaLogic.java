/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.FranjaHorariaPersistence;
import co.edu.uniandes.csw.paseadores.persistence.PaseadorPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    @Inject
    private PaseadorPersistence paseadorPersistence;
    
    /**
     * Se encarga de crear una nueva franja horaria en la base de datos.
     * 
     * @param paseadorId. Id del paseador.
     * @param franja. Objeto FranjaHorariaEntity a crear.
     * @return Objeto ClienteEntity creado y con su Id.
     * @throws BusinessLogicException Si alguno de los parámetros es inválido.
     */
    public FranjaHorariaEntity createFranjaHoraria( Long paseadorId , FranjaHorariaEntity franja ) throws BusinessLogicException {
        if(paseadorId == null ){
            throw new BusinessLogicException("Debe enviarse un Id de paseador válido.");
        }
        PaseadorEntity paseador = paseadorPersistence.find(paseadorId);
        if( paseador == null ){
            throw new BusinessLogicException("Se quiere agregar la franja a un paseador que no está en la base de datos.");
        }
        if( franja.getInicio() == null ){
            throw new BusinessLogicException("La franja no tiene una fecha inicial definida.");
        }
        if( franja.getFin() == null ){
            throw new BusinessLogicException("La franja no tiene una fecha final definida.");
        }
        if( franja.getFin().before(franja.getInicio())){
            throw new BusinessLogicException("La hora final debe ser posterior a la hora inicial.");
        }
        
        //Asocia a la franja el paseador
        franja.setPaseador(paseador);  
        franja = persistence.create(franja);
        return franja;
    }
    
    /**
     * Retorna todos las franjas horarias en la base de datos.
     * 
     * @param paseadorId. Id del paseador.
     * @return Lista de entidades tipo FranjaHoraria.
     */
    public List<FranjaHorariaEntity> getFranjas(Long paseadorId){
        PaseadorEntity paseador = paseadorPersistence.find(paseadorId);
        return paseador.getFranjas();
    }
    
    /**
     * Busca ua franja por su Id
     * 
     * @param franjaId El Id de la franja a buscar.
     * @return FranjaHoraria buscada. null si no lo encuentra.
     */
    public FranjaHorariaEntity getFranja( Long paseadorId , Long franjaId ){
        return persistence.find(paseadorId, franjaId);
    }
    
    /**
     * Actaliza la informaión de una franja por su Id.
     * 
     * @param franjaId. Id de la franja a actualizar.
     * @param franja. Información de la franja actualizada.
     * @return La entidad del cliente actualizada.
     * @throws BusinessLogicException 
     */
    public FranjaHorariaEntity updateFranja(Long paseadorId , FranjaHorariaEntity franja ) throws BusinessLogicException{
        if(paseadorId == null ){
            throw new BusinessLogicException("Debe enviarse un Id de paseador válido.");
        }
        PaseadorEntity paseador = paseadorPersistence.find(paseadorId);
        if( paseador == null ){
            throw new BusinessLogicException("El paseador no se encuentra en la base de datos.");
        }
        if( franja.getInicio() == null ){
            throw new BusinessLogicException("La franja no tiene una fecha inicial definida.");
        }
        if( franja.getFin() == null ){
            throw new BusinessLogicException("La franja no tiene una fecha final definida.");
        }
        
        if( franja.getFin().before(franja.getInicio())){
            throw new BusinessLogicException("La hora final debe ser posterior a la hora inicial.");
        }
        franja.setPaseador(paseador);
        FranjaHorariaEntity entity = persistence.update(franja);
        return entity;
    }
    
    /**
     * Elimina una franja horaria por su Id.
     * 
     * @param paseadorId. Id del paseador
     * @param franjaId . Id de la franja a eliminar
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public void deleteFranja( Long paseadorId , Long franjaId ) throws BusinessLogicException{
        FranjaHorariaEntity old = getFranja(paseadorId, franjaId);
        if (old == null) {
            throw new BusinessLogicException("La franja con id = " + franjaId + " no esta asociado al paseador con id = " + paseadorId);
        }
        persistence.delete(franjaId);
    }
}
