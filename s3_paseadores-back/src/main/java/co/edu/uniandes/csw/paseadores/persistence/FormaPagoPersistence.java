package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Persistencia de las formas de pago.
 *
 * @author Mario Hurtado
 */
@Stateless
public class FormaPagoPersistence {

    /**
     * Entity Manager.
     */
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    /**
     * Crea una nueva forma de pago.
     *
     * @param formaPago Forma de pago a persistir.
     * @return Entidad persistida.
     */
    public FormaPagoEntity create(FormaPagoEntity formaPago) {
        em.persist(formaPago);
        return formaPago;
    }

    /**
     * Retorna todas las formas de pago.
     *
     * @return formas de pago.
     */
    public List<FormaPagoEntity> findAll() {
        TypedQuery query = em.createQuery("select u from FormaPagoEntity u", FormaPagoEntity.class);
        return query.getResultList();
    }

    /**
     * Retorna una fomra de pago.
     *
     * @param idCliente id del cliente.
     * @param idFormaPago id de la forma de pago.
     * @return Forma de pago.
     */
    public FormaPagoEntity find(Long idCliente, Long idFormaPago) {
        TypedQuery<FormaPagoEntity> q = em.createQuery("select p from FormaPagoEntity p where (p.cliente.id = :idCliente) and (p.id = :idFormaPago)", FormaPagoEntity.class);
        q.setParameter("idCliente", idCliente);
        q.setParameter("idFormaPago", idFormaPago);
        List<FormaPagoEntity> results = q.getResultList();
        FormaPagoEntity formaPago = null;
        if (results != null && !results.isEmpty()) {
            formaPago = results.get(0);
        }
        return formaPago;
    }

    /**
     * Actualiza una forma de pago.
     *
     * @param formaPagoEntity Forma de pago actualizada.
     * @return Forma de pago actualizada.
     */
    public FormaPagoEntity update(FormaPagoEntity formaPagoEntity) {
        return em.merge(formaPagoEntity);
    }

    /**
     * Elimina una forma de pago.
     *
     * @param formaPagoId Id de la forma de pago a eliminar.
     */
    public void delete(Long formaPagoId) {
        FormaPagoEntity formaPagoEntity = em.find(FormaPagoEntity.class, formaPagoId);
        em.remove(formaPagoEntity);
    }
}
