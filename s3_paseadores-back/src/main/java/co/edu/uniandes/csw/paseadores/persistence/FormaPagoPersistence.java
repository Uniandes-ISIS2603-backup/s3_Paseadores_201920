/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mario Hurtado
 */
@Stateless
public class FormaPagoPersistence {

    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    public FormaPagoEntity create(FormaPagoEntity formaPago) {
        em.persist(formaPago);
        return formaPago;
    }

    public List<FormaPagoEntity> findAll() {
        // Se crea un query para buscar todas las pago en la base de datos.
        TypedQuery query = em.createQuery("select u from FormaPagoEntity u", FormaPagoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de pago.
        return query.getResultList();
    }

    public FormaPagoEntity find(Long idCliente, Long idFormaPago) {
        TypedQuery<FormaPagoEntity> q = em.createQuery("select p from FormaPagoEntity p where (p.cliente.id = :idCliente) and (p.id = :idFormaPago)", FormaPagoEntity.class);
        q.setParameter("idCliente", idCliente);
        q.setParameter("idFormaPago", idFormaPago);
        List<FormaPagoEntity> results = q.getResultList();
        FormaPagoEntity formaPago = null;
        if (results != null && !results.isEmpty() ){
            formaPago = results.get(0);
        }
        return formaPago;
    }

    public FormaPagoEntity update(FormaPagoEntity formaPagoEntity) {
        return em.merge(formaPagoEntity);
    }

    public void delete(Long formaPagoId) {
        FormaPagoEntity formaPagoEntity = em.find(FormaPagoEntity.class, formaPagoId);
        em.remove(formaPagoEntity);
    }
}
