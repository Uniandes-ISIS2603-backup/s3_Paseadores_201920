/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mario Hurtado
 */

@Stateless
public class PagoPersistence {

    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    public PagoEntity create(PagoEntity pago) {
        em.persist(pago);
        return pago;
    }

    public List<PagoEntity> findAll() {
        // Se crea un query para buscar todas las pago en la base de datos.
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de pago.
        return query.getResultList();
    }

    public PagoEntity find(Long idContrato, Long idPago) 
    {
        TypedQuery<PagoEntity> q = em.createQuery("select p from PagoEntity p where (p.contrato.id = :idContrato) and (p.id = :idPago)", PagoEntity.class);
        q.setParameter("idContrato", idContrato);
        q.setParameter("idPago", idPago);
        List<PagoEntity> results = q.getResultList();
        PagoEntity pago = null;
        if (results == null) 
        {
            pago = null;
        } 
        else if (results.isEmpty()) 
        {
            pago = null;
        } 
        else if (results.size() >= 1) 
        {
            pago = results.get(0);
        }
        return pago;
    }

    public PagoEntity update(PagoEntity pagoEntity) {
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la pago con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(pagoEntity);
    }

    public void delete(Long pagoId) {

        // Se hace uso de mismo método que esta explicado en public PagoEntity find(Long id) para obtener la pago a borrar.
        PagoEntity pagoEntity = em.find(PagoEntity.class, pagoId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from PagoEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(pagoEntity);
    }
}
