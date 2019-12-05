/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;


import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Estudiante
 */
@Stateless
public class CalificacionPersistence 
{
    @PersistenceContext (unitName = "paseadoresPU")
    protected EntityManager em;
    
    @Inject
    private PaseadorPersistence paseadorPersistence;
    
    public CalificacionEntity create(CalificacionEntity p)
    {
        em.persist(p);
        return p;
    }
    
    public CalificacionEntity findCalificacion (Long idPaseador, Long idCalificacion)
    {
        TypedQuery<CalificacionEntity> q = em.createQuery("select p from CalificacionEntity p where (p.id = :idCalificacion)", CalificacionEntity.class);
        q.setParameter("idCalificacion", idCalificacion);
        List<CalificacionEntity> results = q.getResultList();
        CalificacionEntity calificacion = null;
        if (results != null && !results.isEmpty()) 
        {
            calificacion = results.get(0);
        }
        return calificacion;
    }
    
    public List<CalificacionEntity> findAllPorPaseador (Long idPaseador)
    {
        PaseadorEntity paseador = paseadorPersistence.find(idPaseador);
        if( paseador != null ){
            return paseador.getCalificaciones();
        }
        else{
            return new ArrayList<>();
        }
    }
    
    public List<CalificacionEntity> findAll()
    {
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return query.getResultList();
    }
    
    public CalificacionEntity update (CalificacionEntity calificacionEntity)
    {
        return em.merge(calificacionEntity);
    }
    
    public void delete(Long calificacionId)
    {
        CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionId);
        em.remove(calificacionEntity);
    }
}
