/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;


import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Estudiante
 */
@Stateless
public class CalificacionPersistence {
    @PersistenceContext (unitName = "paseadoresPU")
    protected EntityManager em;
    
    public CalificacionEntity create(CalificacionEntity p){
        em.persist(p);
        return p;
    }
    public CalificacionEntity find (Long idCalificacion){
         return em.find(CalificacionEntity.class, idCalificacion);
    }
    public List<CalificacionEntity> findAll(){
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return query.getResultList();
    }
    
    public CalificacionEntity update (CalificacionEntity CalificacionEntity){
        return em.merge(CalificacionEntity);
    }
    
    public void delete(Long CalificacionId){
        CalificacionEntity authorEntity = em.find(CalificacionEntity.class, CalificacionId);
        em.remove(authorEntity);
    }
}
