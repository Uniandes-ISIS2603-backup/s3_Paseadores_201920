/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.PaseadorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author Daniel García
 */
@Stateless
public class PaseadorLogic 
{
     private static final Logger LOGGER = Logger.getLogger(PaseadorLogic.class.getName());

    @Inject
    private PaseadorPersistence persistence;

    /**
     * Se encarga de crear un Paseador en la base de datos.
     *
     * @param paseadorEntity Objeto de PaseadorEntity con los datos nuevos
     * @return Objeto de PaseadorEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public PaseadorEntity createPaseador(PaseadorEntity paseadorEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del paseador");
        if(paseadorEntity.getNombre()==null || paseadorEntity.getNombre().equals("") || NumberUtils.isCreatable(paseadorEntity.getNombre()))
        {
            throw new BusinessLogicException("El nombre del paseador es nulo o tiene un formato incorrecto");
        }
        if(paseadorEntity.getCorreo()==null || paseadorEntity.getCorreo().equals("") || NumberUtils.isCreatable(paseadorEntity.getCorreo()))
        {
            throw new BusinessLogicException("El correo del paseador es nulo o tiene un formato incorrecto");
        }
        if(paseadorEntity.getContrasena()==null || paseadorEntity.getContrasena().equals(""))
        {
            throw new BusinessLogicException("La contrasena del paseador es nulo o tiene un formato incorrecto");
        }
        if(paseadorEntity.getFoto()==null || paseadorEntity.getFoto().equals("") || NumberUtils.isCreatable(paseadorEntity.getFoto()))
        {
            throw new BusinessLogicException("La foto del paseador es nulo o tiene un formato incorrecto");
        }
        if(paseadorEntity.getInfoContacto()==null || paseadorEntity.getInfoContacto().equals(""))
        {
            throw new BusinessLogicException("La informacion de contacto del paseador es nulo o tiene un formato incorrecto");
        }
        if(paseadorEntity.getPrecio()==null)
        {
            throw new BusinessLogicException("El precio del paseador es nulo");
        }
        if(paseadorEntity.getGanancias()==null)
        {
            throw new BusinessLogicException("Las ganancias del paseador son nulo");
        }
        if(paseadorEntity.getInfoAdicional()==null)
        {
            throw new BusinessLogicException("La informacion adicional del paseador es nulo");
        }
        paseadorEntity=persistence.create(paseadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del paseador");
        return paseadorEntity;
    }
    
    /**
     * Obtiene los datos de una instancia de Paseador a partir de su ID.
     *
     * @param paseadorId Identificador de la instancia a consultar
     * @return Instancia de PaseadorEntity con los datos del Paseador consultado.
     */
    public PaseadorEntity getPaseador(Long paseadorId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el paseador con id = {0}", paseadorId);
        PaseadorEntity paseadorEntity = persistence.find(paseadorId);
        if (paseadorEntity == null) {
            LOGGER.log(Level.SEVERE, "El paseador con el id = {0} no existe", paseadorId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el paseador con id = {0}", paseadorId);
        return paseadorEntity;
    }
    
    /**
     * Obtiene la lista de los registros de Paseador.
     *
     * @return Colección de objetos de PaseadorEntity.
     */
    public List<PaseadorEntity> getPaseadores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los paseadores");
        List<PaseadorEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los paseadores");
        return lista;
    }
   
    /**
     * Actualiza la información de una instancia de Paseador.
     *
     * @param paseadorId Identificador de la instancia a actualizar
     * @param paseadorEntity Instancia de PaseadorEntity con los nuevos datos.
     * @return Instancia de PaseadorEntity con los datos actualizados.
     */
    public PaseadorEntity updatePaseador(Long paseadorId, PaseadorEntity paseadorEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el paseador con id = {0}", paseadorId);
        if(paseadorEntity.getNombre()==null || paseadorEntity.getNombre().equals("") || NumberUtils.isCreatable(paseadorEntity.getNombre()))
        {
            throw new BusinessLogicException("El nombre del paseador es nulo o tiene un formato incorrecto");
        }
        if(paseadorEntity.getCorreo()==null || paseadorEntity.getCorreo().equals("") || NumberUtils.isCreatable(paseadorEntity.getCorreo()))
        {
            throw new BusinessLogicException("El correo del paseador es nulo o tiene un formato incorrecto");
        }
        if(paseadorEntity.getContrasena()==null || paseadorEntity.getContrasena().equals("") )
        {
            throw new BusinessLogicException("La contrasena del paseador es nulo o tiene un formato incorrecto");
        }
        if(paseadorEntity.getFoto()==null || paseadorEntity.getFoto().equals("") || NumberUtils.isCreatable(paseadorEntity.getFoto()))
        {
            throw new BusinessLogicException("La foto del paseador es nulo o tiene un formato incorrecto");
        }
        if(paseadorEntity.getInfoContacto()==null || paseadorEntity.getInfoContacto().equals("") )
        {
            throw new BusinessLogicException("La informacion de contacto del paseador es nulo o tiene un formato incorrecto");
        }
        if(paseadorEntity.getPrecio()==null)
        {
            throw new BusinessLogicException("El precio del paseador es nulo");
        }
        if(paseadorEntity.getGanancias()==null)
        {
            throw new BusinessLogicException("Las ganancias del paseador son nulo");
        }
        if(paseadorEntity.getInfoAdicional()==null)
        {
            throw new BusinessLogicException("La informacion adicional del paseador es nulo");
        }
        PaseadorEntity newPaseadorEntity = persistence.update(paseadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el paseador con id = {0}", paseadorId);
        return newPaseadorEntity;
    }
    
    /**
     * Elimina una instancia de Paseador de la base de datos.
     *
     * @param paseadorId Identificador de la instancia a eliminar.
     * 
     */
    public void deletePaseador(Long paseadorId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el paseador con id = {0}", paseadorId);
        persistence.delete(paseadorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el paseador con id = {0}", paseadorId);
    }
}
