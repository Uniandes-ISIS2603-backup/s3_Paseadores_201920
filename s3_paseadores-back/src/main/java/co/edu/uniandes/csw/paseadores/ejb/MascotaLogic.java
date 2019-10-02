/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.MascotaPersistence;
import java.util.List;
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
        if(mascota.getInfoMascota()==null)
        {
            throw new BusinessLogicException("La informacion de la mascota es nulo");
        }
        if(mascota.getCliente()==null)
        {
            throw new BusinessLogicException("La mascota no tiene cliente asociado");
        }
        mascota=persistence.create(mascota);
        return mascota;
    }
    
     /**
     * Retorna todos las mascotas en la base de datos.
     * 
     * @return Lista de entidades tipo Mascota.
     */
    public List<MascotaEntity> getMascotas(){
        List<MascotaEntity> lista = persistence.findAll();
        return lista;
    }
    
    /**
     * Busca una mascota por su Id
     * 
     * @param mascotaId El Id de la mascota a buscar.
     * @return Cliente buscado. null si no lo encuentra.
     */
    public MascotaEntity getMascota( Long mascotaId ) throws BusinessLogicException{
        MascotaEntity mascota = persistence.find(mascotaId);
        if( mascota == null )
        {
             throw new BusinessLogicException("Mascota no encontrada");
        }
        return mascota;
    }
    
    /**
     * Actualiza la información de una instancia de Paseador.
     *
     * @param mascotaId Identificador de la instancia a actualizar
     * @param mascotaEntity Instancia de PaseadorEntity con los nuevos datos.
     * @return Instancia de PaseadorEntity con los datos actualizados.
     */
    public MascotaEntity updateMascota(Long mascotaId, MascotaEntity mascotaEntity) throws BusinessLogicException
    {
        if(mascotaEntity.getNombre()==null)
        {
            throw new BusinessLogicException("El nombre de la mascota es nulo");
        }
        if(mascotaEntity.getInfoMascota()==null)
        {
            throw new BusinessLogicException("La informacion de la mascota es nulo");
        }
        if(mascotaEntity.getCliente()==null)
        {
            throw new BusinessLogicException("La mascota no tiene cliente asociado");
        }
        mascotaEntity=persistence.update(mascotaEntity);
        return mascotaEntity;
    }
    
    /**
     * Elimina una mascota por su Id.
     * 
     * @param mascotaId . Id del cliente a eliminar
     */
    public void deleteMascota( Long mascotaId ){
        persistence.delete(mascotaId);
    }
}
