package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Persistencia de la calificacion
 *
 * @author Juan Vergara.
 */
@Stateless
public class CalificacionPersistence {

    /**
     * Entity Manager.
     */
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    /**
     * Crea una nueva calificacion.
     *
     * @param p. Entidad a persistir.
     * @return entidad persistida.
     */
    public CalificacionEntity create(CalificacionEntity p) {
        em.persist(p);
        return p;
    }

    /**
     * Encuentra una calificacion.
     *
     * @param idPaseador Id del paseador.
     * @param idCalificacion Id de la calificacion.
     * @return calificacion.
     */
    public CalificacionEntity findCalificacion(Long idPaseador, Long idCalificacion) {

        TypedQuery<CalificacionEntity> q = em.createQuery("select p from CalificacionEntity p where (p.id = :idCalificacion ) and ( p.paseador.id = :idPaseador )", CalificacionEntity.class);
        q.setParameter("idCalificacion", idCalificacion);
        q.setParameter("idPaseador", idPaseador);

        List<CalificacionEntity> results = q.getResultList();
        CalificacionEntity calificacion = null;
        if ( results != null && !results.isEmpty()) {
            calificacion = results.get(0);
        }
        return calificacion;
    }

    /**
     * Retorna todas las calificaciones en el sistema.
     *
     * @return Todas las calificaciones.
     */
    public List<CalificacionEntity> findAll() {
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza una calificacion.
     *
     * @param calificacionEntity nueva calificacion.
     * @return calificacion actualizada.
     */
    public CalificacionEntity update(CalificacionEntity calificacionEntity) {
        return em.merge(calificacionEntity);
    }

    /**
     * Elimina una calificación.
     *
     * @param calificacionId Id de la calificacion.
     */
    public void delete(Long calificacionId) {
        CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionId);
        em.remove(calificacionEntity);
    }
}
