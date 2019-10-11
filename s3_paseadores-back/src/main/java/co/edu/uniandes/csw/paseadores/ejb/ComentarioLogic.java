package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.paseadores.persistence.ContratoPersistence;
import co.edu.uniandes.csw.paseadores.persistence.PaseadorPersistence;
import java.sql.Time;
import java.util.List;
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
        
        @Inject
        private PaseadorPersistence paseadorPersistence;
        
        @Inject
        private ContratoPersistence contratoPersistence;


	public ComentarioEntity createComentario(ComentarioEntity comentario, Long contratoId) throws BusinessLogicException {

                ContratoEntity contratoEntity = null;
            
		if( comentario.getName() == null ){
			throw new BusinessLogicException("El nombre del comentario está vacío");
		}


		if( comentario.getInfoComentario() == null || comentario.getInfoComentario().length() == 0){
			throw new BusinessLogicException("El campo comentario está vacío");
		}

		if(contratoId == null) {

			throw new BusinessLogicException("El comentario no tiene un contrato asociado");

		} else {
                    
                   contratoEntity = contratoPersistence.find(contratoId);
                    
                }


		if(comentario.getInfoComentario().length() > 300) {

			throw new BusinessLogicException("La informacion del comentario no puede superar los 300 caracteres");

		}

		if(contratoEntity.getFinalizado() == false) {

			throw new BusinessLogicException("No se puede realizar un comentario acerca de un servicio que no ha terminado");

		}


                comentario.setContrato(contratoEntity);
		comentario = persistence.create(comentario);
		return comentario;

	}


	/**
	 * Obtiene los datos de una instancia de Comentario a partir de su ID.
	 *
	 * @param comentarioId Identificador de la instancia a consultar
	 * @return Instancia de ComentarioEntity
	 */
	public ComentarioEntity getComentario(Long comentarioId, Long contratoId) throws BusinessLogicException
	{
		ComentarioEntity comentarioEntity = persistence.find(comentarioId, contratoId);
                
                if( comentarioEntity == null )
                 {
                  throw new BusinessLogicException("Comentario no encontrada");
                 }
                
		return comentarioEntity;
	}
        

	  /**
     * Obtiene la lista de los registros de Comentario que pertenecen a un Paseador.
     *
     * @param paseadorId id del Paseador el cual es padre de los Comentarios.
     * @return Colección de objetos de ComentarioEntity.
     */
    public List<ComentarioEntity> getComentarios(Long paseadorId) {
    	
        PaseadorEntity paseadorEntity = paseadorPersistence.find(paseadorId);
        
        List<ComentarioEntity> lista = paseadorEntity.getComentarios();
        
        return lista;
        
    }

	/**
	 * Actualiza la información de una instancia de Comentario.
	 *
	 * @param comentarioId Identificador de la instancia a actualizar
	 * @param comentarioEntity Instancia de ComentarioEntity con los nuevos datos.
	 * @return Instancia de ComentarioEntity con los datos actualizados.
	 */
	public ComentarioEntity updateComentario(Long contratoId, ComentarioEntity comentarioEntity) throws BusinessLogicException
	{
                ContratoEntity contratoEntity = null;
                
		if(comentarioEntity.getName()==null || comentarioEntity.getName().equals("") || NumberUtils.isCreatable(comentarioEntity.getName()))
		{
			throw new BusinessLogicException("El nombre del comentario es nulo o tiene un formato incorrecto");
		}
		if(comentarioEntity.getInfoComentario() ==null || NumberUtils.isCreatable(comentarioEntity.getInfoComentario()))
		{
			throw new BusinessLogicException("La informacion del comentario es nula o tiene un formato incorrecto");
		}
		
                if(contratoId==null || contratoPersistence.find(contratoId) == null)
        {
            throw new BusinessLogicException("El contrato asociado es nulo");
        }
        else
        {
            contratoEntity = contratoPersistence.find(contratoId);
            comentarioEntity.setContrato(contratoEntity);
        }
                
        comentarioEntity=persistence.update(comentarioEntity);
        return comentarioEntity;

	}
              
        
	/**
	 * Elimina una instancia de Comentario de la base de datos.
	 *
	 * @param comentarioId Identificador de la instancia a eliminar.
	 * 
	 */
	public void deleteComentario(Long comentarioId, Long contratoId) throws BusinessLogicException 
	{
                
                ComentarioEntity old = getComentario(comentarioId, contratoId);
                
             if (old == null) 
            {
            throw new BusinessLogicException("El comentario con id = " + contratoId + " no esta asociada al contrato con id = " + contratoId);
            }
             
            persistence.delete(old.getId());
	}
        

}