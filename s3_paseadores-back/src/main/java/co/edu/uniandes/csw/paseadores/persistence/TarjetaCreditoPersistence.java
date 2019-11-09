/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.TarjetaCreditoEntity;
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
public class TarjetaCreditoPersistence 
{  
    @PersistenceContext (unitName = "paseadoresPU") 
    protected EntityManager em;
    
    public TarjetaCreditoEntity create(TarjetaCreditoEntity p){
        em.persist(p);
        return p;
    }
    public TarjetaCreditoEntity find (Long idTarjetaCredito){
         return em.find(TarjetaCreditoEntity.class, idTarjetaCredito);
    }
    public List<TarjetaCreditoEntity> findAll(){
        TypedQuery query = em.createQuery("select u from TarjetaCreditoEntity u", TarjetaCreditoEntity.class);
        return query.getResultList();
    }
    
    public TarjetaCreditoEntity update (TarjetaCreditoEntity tarjetaCreditoEntity){
        return em.merge(tarjetaCreditoEntity);
    }
    
    public void delete(Long tarjetaCreditoId){
        TarjetaCreditoEntity authorEntity = em.find(TarjetaCreditoEntity.class, tarjetaCreditoId);
        em.remove(authorEntity);
    }
}
