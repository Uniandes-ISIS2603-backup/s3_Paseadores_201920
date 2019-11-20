package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.paseadores.persistence.ContratoPersistence;
import co.edu.uniandes.csw.paseadores.persistence.PaseadorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Clase encargada de la lògica de un comentario
 *
 * @author Nicolas Potes Garcia
 */
@Stateless
public class ComentarioLogic {

    /**
     * Persistencia de un comentario.
     */
    @Inject
    private ComentarioPersistence persistence;

    /**
     * Persistencia de los paseadores.
     */
    @Inject
    private PaseadorPersistence paseadorPersistence;

    /**
     * Persistencia de los contratos.
     */
    @Inject
    private ContratoPersistence contratoPersistence;

    /**
     * Crea un comentario.
     *
     * @param comentario
     * @param contratoId
     * @return Comentario creado.
     * @throws BusinessLogicException
     */
    public ComentarioEntity createComentario(ComentarioEntity comentario, Long contratoId) throws BusinessLogicException {
        ContratoEntity contratoEntity = null;
        if (comentario.getName() == null) {
            throw new BusinessLogicException("El nombre del comentario está vacío");
        }
        if (comentario.getInfoComentario() == null || comentario.getInfoComentario().length() == 0) {
            throw new BusinessLogicException("El campo comentario está vacío");
        }
        if (comentario.getInfoComentario().length() > 300) {
            throw new BusinessLogicException("La informacion del comentario no puede superar los 300 caracteres");
        }
        if (contratoId == null) {
            throw new BusinessLogicException("El comentario no tiene un contrato asociado");
        } else {
            contratoEntity = contratoPersistence.find(contratoId);
        }
        if (!contratoEntity.getFinalizado()) {
            throw new BusinessLogicException("No se puede realizar un comentario acerca de un servicio que no ha terminado");
        }

        comentario.setContrato(contratoEntity);
        comentario.setPaseador(contratoEntity.getPaseador());
        comentario = persistence.create(comentario);
        return comentario;

    }

    /**
     * Obtiene los datos de una instancia de Comentario a partir de su ID.
     *
     * @param comentarioId Identificador de la instancia a consultar
     * @param idPaseador
     * @return Instancia de ComentarioEntity
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public ComentarioEntity getComentario(Long comentarioId, Long idPaseador) throws BusinessLogicException {
        ComentarioEntity comentarioEntity = persistence.findComentario(idPaseador, comentarioId);
        if (comentarioEntity == null) {
            throw new BusinessLogicException("Comentario no encontrada");
        }
        return comentarioEntity;
    }

    /**
     * Obtiene la lista de los registros de Comentario que pertenecen a un
     * Paseador.
     *
     * @param paseadorId id del Paseador el cual es padre de los Comentarios.
     * @return Colección de objetos de ComentarioEntity.
     */
    public List<ComentarioEntity> getComentariosPorPaseador(Long paseadorId) {
        PaseadorEntity paseadorEntity = paseadorPersistence.find(paseadorId);
        return paseadorEntity.getComentarios();
    }

    /**
     * Retorna todos los comentarios.
     *
     * @return comnetarios.
     */
    public List<ComentarioEntity> getComentarios() {
        return persistence.findAll();
    }

    /**
     * Actualiza la información de una instancia de Comentario.
     *
     * @param contratoId
     * @param comentarioEntity Instancia de ComentarioEntity con los nuevos
     * datos.
     * @return Instancia de ComentarioEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public ComentarioEntity updateComentario(Long contratoId, ComentarioEntity comentarioEntity) throws BusinessLogicException {
        ContratoEntity contratoEntity;
        if (comentarioEntity.getName() == null || comentarioEntity.getName().equals("") || NumberUtils.isCreatable(comentarioEntity.getName())) {
            throw new BusinessLogicException("El nombre del comentario es nulo o tiene un formato incorrecto");
        }
        if (comentarioEntity.getInfoComentario() == null || NumberUtils.isCreatable(comentarioEntity.getInfoComentario())) {
            throw new BusinessLogicException("La informacion del comentario es nula o tiene un formato incorrecto");
        }
        if (comentarioEntity.getInfoComentario().length() > 300) {
            throw new BusinessLogicException("La informacion del comentario no puede superar los 300 caracteres");
        }
        if (contratoId == null || contratoPersistence.find(contratoId) == null) {
            throw new BusinessLogicException("El contrato asociado es nulo");
        }
        contratoEntity = contratoPersistence.find(contratoId);
        comentarioEntity.setContrato(contratoEntity);
        comentarioEntity.setPaseador(contratoEntity.getPaseador());
        comentarioEntity = persistence.update(comentarioEntity);
        return comentarioEntity;

    }

    /**
     * Elimina una instancia de Comentario de la base de datos.
     *
     * @param comentarioId Identificador de la instancia a eliminar.
     * @param paseadorId
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     *
     */
    public void deleteComentario(Long comentarioId, Long paseadorId) throws BusinessLogicException {
        ComentarioEntity old = getComentario(comentarioId, paseadorId);
        if (old == null) {
            throw new BusinessLogicException("El comentario con id = " + comentarioId + " no esta asociada al paseador con id = " + paseadorId);
        }
        persistence.delete(old.getId());
    }

}
