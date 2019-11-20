package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Clase que representa la persistencia de un pago.
 *
 * @author Mario Hurtado
 */
@Stateless
public class PagoPersistence {

    /**
     * Entity Manager.
     */
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    /**
     * Crea un nuevo pago.
     *
     * @param pago Nuevo pago.
     * @return Pago creado.
     */
    public PagoEntity create(PagoEntity pago) {
        em.persist(pago);
        return pago;
    }

    /**
     * Retorna todos los pagos del sistema.
     *
     * @return Pagos del sistema.
     */
    public List<PagoEntity> findAll() {
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        return query.getResultList();
    }

    /**
     * Retorna un pago por su id.
     *
     * @param idContrato Id del contrato.
     * @param idPago Id del pago.
     * @return Pago buscado.
     */
    public PagoEntity find(Long idContrato, Long idPago) {
        TypedQuery<PagoEntity> q = em.createQuery("select p from PagoEntity p where (p.id = :idPago)", PagoEntity.class);
        q.setParameter("idPago", idPago);
        List<PagoEntity> results = q.getResultList();
        PagoEntity pago = null;
        if (results == null) {
            pago = null;
        } else if (results.isEmpty()) {
            pago = null;
        } else if (results.size() >= 1) {
            pago = results.get(0);
        }
        return pago;
    }

    /**
     * Actualiza la información de un pago.
     *
     * @param pagoEntity Información actualizada.
     * @return Actualizacion persisitida.
     */
    public PagoEntity update(PagoEntity pagoEntity) {
        return em.merge(pagoEntity);
    }

    /**
     * Elimina un pago.
     *
     * @param pagoId id del pago a elimincar.
     */
    public void delete(Long pagoId) {
        PagoEntity pagoEntity = em.find(PagoEntity.class, pagoId);
        em.remove(pagoEntity);
    }
}
