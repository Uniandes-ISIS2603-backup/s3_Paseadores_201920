package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ComentarioPersistence;
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


	public ComentarioEntity createComentario(ComentarioEntity comentario) throws BusinessLogicException {


		if( comentario.getName() == null ){
			throw new BusinessLogicException("El nombre del comentario está vacío");
		}


		if( comentario.getInfoComentario() == null || comentario.getInfoComentario().length() == 0){
			throw new BusinessLogicException("El campo comentario está vacío");
		}

		if(comentario.getCliente() == null) {

			throw new BusinessLogicException("El comentario no tiene un cliente asociado");

		}

		if(comentario.getPaseador() == null) {

			throw new BusinessLogicException("El comentario no tiene un paseador asociado");

		}

		if(comentario.getContrato() == null) {

			throw new BusinessLogicException("El comentario no tiene un contrato asociado");

		}


		if(comentario.getInfoComentario().length() > 300) {

			throw new BusinessLogicException("La informacion del comentario no puede superar los 300 caracteres");

		}

		if(comentario.getContrato().getFinalizado() == false) {

			throw new BusinessLogicException("No se puede realizar un comentario acerca de un servicio que no ha terminado");

		}

		if(comentario.getContrato().getPaseador() != comentario.getPaseador()) {

			throw new BusinessLogicException("El paseador del contrato no coincide con el paseador del comentario");

		}

		if(comentario.getContrato().getCliente() != comentario.getCliente()) {

			throw new BusinessLogicException("El cliente del contrato no coincide con el cliente del comentario");

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
		ComentarioEntity comentarioEntity = persistence.find(comentarioId);
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

        return paseadorEntity.getComentarios();
        
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

		if(comentarioEntity.getName()==null || comentarioEntity.getName().equals("") || NumberUtils.isCreatable(comentarioEntity.getName()))
		{
			throw new BusinessLogicException("El nombre del comentario es nulo o tiene un formato incorrecto");
		}
		if(comentarioEntity.getInfoComentario() ==null || NumberUtils.isCreatable(comentarioEntity.getInfoComentario()))
		{
			throw new BusinessLogicException("La informacion del comentario es nula o tiene un formato incorrecto");
		}
		
		if(comentarioEntity.getContrato() == null)
		{
			throw new BusinessLogicException("El comentario no dispone de un contrato");
		}
		
		
		if(comentarioEntity.getCliente() == null)
		{
			throw new BusinessLogicException("El comentario no dispone de un cliente");
		}
		
		if(comentarioEntity.getPaseador() == null)
		{
			throw new BusinessLogicException("El comentario no dispone de un paseador");
		}
		

		ComentarioEntity newComentarioEntity = persistence.update(comentarioEntity);
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
		persistence.delete(comentarioId);
	}



}