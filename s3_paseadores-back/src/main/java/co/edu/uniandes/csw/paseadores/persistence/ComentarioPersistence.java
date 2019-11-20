package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase encargada de la persistencia de un comentario.
 *
 * @author Nicolas Potes Garcia
 */
@Stateless
public class ComentarioPersistence {

    /**
     * Entity Manager.
     */
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    /**
     * Crea un nuevo comentario.
     *
     * @param comentario
     * @return
     */
    public ComentarioEntity create(ComentarioEntity comentario) {
        em.persist(comentario);
        return comentario;
    }

    /**
     * Busca si hay alguna comentario asociado a un contrato y con un ID
     * específico
     *
     * @param idPaseador El ID del paseador con respecto al cual se busca
     * @param idComentario El ID del comentario
     * @return El comentario encontrada o null.
     */
    public ComentarioEntity findComentario(Long idPaseador, Long idComentario) {
        TypedQuery<ComentarioEntity> q = em.createQuery("select p from ComentarioEntity p where (p.paseador.id = :idPaseador) and (p.id = :idComentario)", ComentarioEntity.class);
        q.setParameter("idPaseador", idPaseador);
        q.setParameter("idComentario", idComentario);
        List<ComentarioEntity> results = q.getResultList();
        ComentarioEntity comentario = null;
        if (results == null) {
            comentario = null;
        } else if (results.isEmpty()) {
            comentario = null;
        } else if (results.size() >= 1) {
            comentario = results.get(0);
        }
        return comentario;
    }

    /**
     * Retorna todos los comentarios.
     *
     * @return comentarios.
     */
    public List<ComentarioEntity> findAll() {
        TypedQuery query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza un comentario.
     *
     * @param comentarioEntity. Nuevo comentario.
     * @return comentario actuzalizado.
     */
    public ComentarioEntity update(ComentarioEntity comentarioEntity) {
        return em.merge(comentarioEntity);
    }

    /**
     * Elimina un comentario.
     *
     * @param comentarioId Id del comentario a elimincar
     */
    public void delete(Long comentarioId) {
        ComentarioEntity comentarioEntity = em.find(ComentarioEntity.class, comentarioId);
        em.remove(comentarioEntity);
    }
}
