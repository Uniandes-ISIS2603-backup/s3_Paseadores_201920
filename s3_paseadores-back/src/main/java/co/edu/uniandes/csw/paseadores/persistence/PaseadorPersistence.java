/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Métodos CRUD asociados a la entidad Contrato y su parte de persistencia.
 *
 * @author Daniel Felipe García Vargas
 */
@Stateless
public class PaseadorPersistence {

    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    public PaseadorEntity create(PaseadorEntity paseador) {
        em.persist(paseador);
        return paseador;
    }

    public PaseadorEntity find(Long idPaseador) {
        return em.find(PaseadorEntity.class, idPaseador);
    }

    public List<PaseadorEntity> findAll() {
        TypedQuery query = em.createQuery("select u from PaseadorEntity u", PaseadorEntity.class);
        return query.getResultList();
    }

    public PaseadorEntity update(PaseadorEntity paseadorEntity) {
        return em.merge(paseadorEntity);
    }

    public void delete(Long paseadorId) {
        PaseadorEntity authorEntity = em.find(PaseadorEntity.class, paseadorId);
        em.remove(authorEntity);
    }
}
