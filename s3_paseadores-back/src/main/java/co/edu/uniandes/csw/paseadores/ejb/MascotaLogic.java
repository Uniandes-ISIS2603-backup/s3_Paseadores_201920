/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ClientePersistence;
import co.edu.uniandes.csw.paseadores.persistence.MascotaPersistence;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
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
     @Inject
    private ClientePersistence clientePersistence;
    
    private static final Logger LOGGER = Logger.getLogger(MascotaLogic.class.getName());
     /**
     * Se encarga de crear un Review en la base de datos.
     *
     * @param mascota Objeto de MascotaEntity con los datos nuevos
     * @param clienteId id del cliente el cual sera padre de la nueva mascota.
     * @return Objeto de MascotaEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si clienteId no es el mismo que tiene el
     * entity.
     *
     */
    public MascotaEntity createMascota(Long clienteId, MascotaEntity mascota) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de crear mascota");
        ClienteEntity cliente;
        if(mascota.getNombre()==null)
        {
            throw new BusinessLogicException("El nombre de la mascota es nulo");
        }
        if(mascota.getInfoMascota()==null)
        {
            throw new BusinessLogicException("La informacion de la mascota es nulo");
        }
        if(clienteId==null || clientePersistence.find(clienteId)==null)
        {
            throw new BusinessLogicException("El cliente es nulo");
        }
        else
        {
            cliente = clientePersistence.find(clienteId);
            mascota.setCliente(cliente);
        }
        mascota=persistence.create(mascota);
        LOGGER.log(Level.INFO, "Termina proceso de creación de mascota");
        return mascota;
    }
    
     /**
     * Retorna todos las mascotas en la base de datos.
     * 
     * @param clienteId id del cliente
     * @return Lista de entidades tipo Mascota.
     */
    public List<MascotaEntity> getMascotas(Long clienteId)
    {
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        List<MascotaEntity> lista = clienteEntity.getMascotas();
        return lista;
    }
    
    /**
     * Busca una mascota por su Id
     * 
     * @param clienteId el id del cliente
     * @param mascotaId El Id de la mascota a buscar.
     * @return mascota buscada. null si no lo encuentra.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public MascotaEntity getMascota(Long clienteId, Long mascotaId ) throws BusinessLogicException
    {
        MascotaEntity mascota = persistence.find(clienteId, mascotaId);
        if( mascota == null )
        {
             throw new BusinessLogicException("Mascota no encontrada");
        }
        return mascota;
    }
    
    /**
     * Actualiza la información de una instancia de Paseador.
     *
     * @param idCliente id del cliente asociado a la mascota
     * @param mascotaEntity Instancia de MascotaEntity con los nuevos datos.
     * @return Instancia de PaseadorEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public MascotaEntity updateMascota(Long idCliente, MascotaEntity mascotaEntity) throws BusinessLogicException
    {
        
        ClienteEntity clienteEntity;
       
        if(mascotaEntity.getNombre()==null)
        {
            throw new BusinessLogicException("El nombre de la mascota es nulo");
        }
        if(mascotaEntity.getInfoMascota()==null)
        {
            throw new BusinessLogicException("La informacion de la mascota es nulo");
        }
        if(idCliente==null || clientePersistence.find(idCliente) == null)
        {
            throw new BusinessLogicException("El cliente asociado es nulo");
        }
        else
        {
            clienteEntity = clientePersistence.find(idCliente);
            mascotaEntity.setCliente(clienteEntity);
        }
        mascotaEntity=persistence.update(mascotaEntity);
        return mascotaEntity;
    }
    
    /**
     * Elimina una mascota por su Id.
     * 
     * @param idCliente id del cliente asociado
     * @param mascotaId Id de la mascota a eliminar
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public void deleteMascota(Long idCliente, Long mascotaId ) throws BusinessLogicException
    {
        MascotaEntity old = getMascota(idCliente, mascotaId);
        if (old == null) 
        {
            throw new BusinessLogicException("La mascota con id = " + mascotaId + " no esta asociada al cliente con id = " + idCliente);
        }
        persistence.delete(old.getId());
    }
}
