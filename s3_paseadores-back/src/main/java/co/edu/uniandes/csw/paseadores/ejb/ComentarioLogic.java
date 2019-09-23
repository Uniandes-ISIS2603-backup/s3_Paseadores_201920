/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ComentarioPersistence;
//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.sql.Time;
import java.util.List;
//import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author Nicolas Potes Garcia
 */

@Stateless
public class ComentarioLogic {
    
    @Inject
    private ComentarioPersistence persistence;
    
    
    public ComentarioEntity createComentario(ComentarioEntity comentario) throws BusinessLogicException {
        
        
        if( comentario.getName() == null ){
            throw new BusinessLogicException("El nombre del comentario está vacío");
        }
        
        
        if( comentario.getInfoComentario() == null ){
            throw new BusinessLogicException("El campo comentario está vacío");
        }
        
        
        comentario = persistence.create(comentario);
        return comentario;
        
    }
    
    
     /**
     * Obtiene los datos de una instancia de Comentario a partir de su ID.
     *
     * @param comentarioId Identificador de la instancia a consultar
     * @return Instancia de ComentarioEntity
     */
    public ComentarioEntity getComentario(Long comentarioId) 
    {
       // LOGGER.log(Level.SEVERE, "Inicia el proceso de consultar el comentario con el id = {0}", comentarioId);
        ComentarioEntity comentarioEntity = persistence.find(comentarioId);
        if (comentarioEntity == null) {
            //LOGGER.log(Level.SEVERE, "El contrato con el id = {0} no existe", comentarioId);
        }
        //LOGGER.log(Level.INFO, "Termina proceso de consultar el contrato con id = {0}", comentarioId);
        return comentarioEntity;
    }
    
    /**
     * Obtiene la lista de los registros del Comentario.
     *
     * @return Colección de objetos de ComentarioEntity.
     */
    public List<ComentarioEntity> getComentarios() {
        //LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los comentarios");
        List<ComentarioEntity> lista = persistence.findAll();
        //LOGGER.log(Level.INFO, "Termina proceso de consultar todos los comentarios");
        return lista;
    }
    
    /**
     * Actualiza la información de una instancia de Comentario.
     *
     * @param comentarioId Identificador de la instancia a actualizar
     * @param comentarioEntity Instancia de ComentarioEntity con los nuevos datos.
     * @return Instancia de ComentarioEntity con los datos actualizados.
     */
    public ComentarioEntity updateComentario(Long comentarioId, ComentarioEntity comentarioEntity) throws BusinessLogicException
    {
        
        //LOGGER.log(Level.INFO, "Inicia proceso de actualizar el comentario con id = {0}", comentarioId);
        if(comentarioEntity.getName()==null || comentarioEntity.getName().equals("") || NumberUtils.isCreatable(comentarioEntity.getName()))
        {
            throw new BusinessLogicException("El nombre del comentario es nulo o tiene un formato incorrecto");
        }
        if(comentarioEntity.getInfoComentario() ==null || NumberUtils.isCreatable(comentarioEntity.getInfoComentario()))
        {
            throw new BusinessLogicException("La informacion del comentario es nula o tiene un formato incorrecto");
        }
        
        ComentarioEntity newComentarioEntity = persistence.update(comentarioEntity);
        //LOGGER.log(Level.INFO, "Termina proceso de actualizar el comentario con id = {0}", comentarioId);
        return newComentarioEntity;
        
    }
    
    
    
    /**
     * Elimina una instancia de Comentario de la base de datos.
     *
     * @param comentarioId Identificador de la instancia a eliminar.
     * 
     */
    public void deleteComentario(Long comentarioId) throws BusinessLogicException 
    {
        //LOGGER.log(Level.INFO, "Inicia proceso de borrar el comentario con id = {0}", comentarioId);
        persistence.delete(comentarioId);
        //LOGGER.log(Level.INFO, "Termina proceso de borrar el comentario con id = {0}", comentarioId);
    }
    
    
    
}