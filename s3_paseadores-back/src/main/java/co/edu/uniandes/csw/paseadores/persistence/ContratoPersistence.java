/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;return

import java.util.List;
//import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Métodos CRUD asociados a la entidad Contrato y su parte de persistencia.
 * @author Nicolas Potes Garcia
 */

@Stateless
public class ContratoPersistence 
{    
    @PersistenceContext(unitName="paseadoresPU")
    protected EntityManager em;
      
    public ContratoEntity create(ContratoEntity contrato) 
    {
        em.persist(contrato);   
        return contrato; 
    }
    
    public ContratoEntity find(Long idContrato) 
    {
       return em.find(ContratoEntity.class, idContrato);        
    }
    
    public List<ContratoEntity> findAllPorPaseador(Long idPaseador) 
    {
        TypedQuery q = em.createQuery("select p from ContratoEntity p where (p.paseador.id = :idPaseador)", ContratoEntity.class);
        q.setParameter("idPaseador", idPaseador);
        return q.getResultList();
    }
    
    public List<ContratoEntity> findAllPorCliente(Long idCliente) 
    {
        TypedQuery q = em.createQuery("select p from ContratoEntity p where (p.cliente.id = :idCliente)", ContratoEntity.class);
        q.setParameter("idCliente", idCliente);
        return q.getResultList();
    }
    
    public List<ContratoEntity> findAll() 
    {
        TypedQuery query = em.createQuery("select u from ContratoEntity u", ContratoEntity.class);
        return query.getResultList();
    }
    
    public ContratoEntity update(ContratoEntity contratoEntity) 
    {
        return em.merge(contratoEntity);
    }
    
    public void delete(Long contratoId) 
    {
        ContratoEntity contratoEntity = em.find(ContratoEntity.class, contratoId);
        em.remove(contratoEntity);
    }  
}

