/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.FranjaHorariaPersistence;
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
    
    /**
     * Se encarga de crear una nueva franja horaria en la base de datos.
     * 
     * @param franja. Objeto FranjaHorariaEntity a crear.
     * @return Objeto ClienteEntity creado y con su Id.
     * @throws BusinessLogicException Si alguno de los parámetros es inválido.
     */
    public FranjaHorariaEntity createFranjaHoraria( FranjaHorariaEntity franja ) throws BusinessLogicException {
        if( franja.getInicio() == null ){
            throw new BusinessLogicException("La franja no tiene una fecha inicial definida.");
        }
        if( franja.getFin() == null ){
            throw new BusinessLogicException("La franja no tiene una fecha final definida.");
        }
        if( franja.getPaseador() == null ){
            throw new BusinessLogicException("La franja no tiene un paseador asociado");
        }
        if( franja.getFin().before(franja.getInicio())){
            throw new BusinessLogicException("La hora final debe ser posterior a la hora inicial.");
        }
                   
        franja = persistence.create(franja);
        return franja;
    }
    
    /**
     * Retorna todos las franjas horarias en la base de datos.
     * 
     * @return Lista de entidades tipo FranjaHoraria.
     */
    public List<FranjaHorariaEntity> getFranjas(){
        List<FranjaHorariaEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Busca ua franja por su Id
     * 
     * @param franjaId El Id de la franja a buscar.
     * @return FranjaHoraria buscada. null si no lo encuentra.
     */
    public FranjaHorariaEntity getFranja( Long franjaId ){
        FranjaHorariaEntity franja = persistence.find(franjaId);
        if( franja == null ){
            
        }
        return franja;
    }
    
    /**
     * Actaliza la informaión de una franja por su Id.
     * 
     * @param franjaId. Id de la franja a actualizar.
     * @param franja. Información de la franja actualizada.
     * @return La entidad del cliente actualizada.
     * @throws BusinessLogicException 
     */
    public FranjaHorariaEntity updateFranja(Long franjaId , FranjaHorariaEntity franja ) throws BusinessLogicException{
        if( franja.getInicio() == null ){
            throw new BusinessLogicException("La franja no tiene una fecha inicial definida.");
        }
        if( franja.getFin() == null ){
            throw new BusinessLogicException("La franja no tiene una fecha final definida.");
        }
        if( franja.getPaseador() == null ){
            throw new BusinessLogicException("La franja no tiene un paseador asociado");
        }
        if( franja.getFin().before(franja.getInicio())){
            throw new BusinessLogicException("La hora final debe ser posterior a la hora inicial.");
        }
        
        FranjaHorariaEntity entity = persistence.update(franja);
        return entity;
    }
    
    /**
     * Elimina una franja horaria por su Id.
     * 
     * @param franjaId . Id de la franja a eliminar
     */
    public void deleteFranja( Long franjaId ){
        persistence.delete(franjaId);
    }
}
