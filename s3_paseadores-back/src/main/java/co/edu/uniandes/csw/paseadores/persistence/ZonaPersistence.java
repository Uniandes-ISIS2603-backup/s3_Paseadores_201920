/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;


import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author Juan Vergara
 */
@Stateless
public class ZonaPersistence 
{ 
    @PersistenceContext (unitName = "paseadoresPU")
    protected EntityManager em;
    
    public ZonaEntity create(ZonaEntity p){
        em.persist(p);
        return p;
    }
    public ZonaEntity find (Long idZona){
         return em.find(ZonaEntity.class, idZona);
    }
    public List<ZonaEntity> findAll(){
        TypedQuery query = em.createQuery("select u from ZonaEntity u", ZonaEntity.class);
        return query.getResultList();
    }
    
    public ZonaEntity update (ZonaEntity zonaEntity){
        return em.merge(zonaEntity);
    }
    
    public void delete(Long zonaId){
        ZonaEntity authorEntity = em.find(ZonaEntity.class, zonaId);
        em.remove(authorEntity);
    }
}