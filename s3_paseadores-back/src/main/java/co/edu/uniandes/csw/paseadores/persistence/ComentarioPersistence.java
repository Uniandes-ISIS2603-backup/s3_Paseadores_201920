/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
//import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;



/**
 *
 * @author Nicolas Potes Garcia
 */

@Stateless
public class ComentarioPersistence 
{
    @PersistenceContext(unitName="paseadoresPU")
    protected EntityManager em;
   
    
    public ComentarioEntity create(ComentarioEntity contrato) 
    {    
        em.persist(contrato);
        return contrato; 
    }
    
     /**
     * Buscar una comentario
     *
     * Busca si hay alguna comentario asociado a un contrato y con un ID específico
     *
     * @param idPaseador El ID del paseador con respecto al cual se busca
     * @param idComentario El ID del comentario 
     * @return El comentario encontrada o null.
     */
    public ComentarioEntity find(Long idPaseador, Long idComentario) 
    {
       TypedQuery<ComentarioEntity> q = em.createQuery("select p from ComentarioEntity p where (p.paseador.id = :idPaseador) and (p.id = :idComentario)", ComentarioEntity.class);
        q.setParameter("idPaseador", idPaseador);
        q.setParameter("idComentario", idComentario);
        List<ComentarioEntity> results = q.getResultList();
        ComentarioEntity comentario = null;
        if (results == null) 
        {
            comentario = null;
        } 
        else if (results.isEmpty()) 
        {
            comentario = null;
        } 
        else if (results.size() >= 1) 
        {
            comentario = results.get(0);
        }
        return comentario;
    }
    
    public List<ComentarioEntity> findAll() 
    {
        TypedQuery query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        return query.getResultList();       
    }
    
     public List<ComentarioEntity> findAllPorPaseador (Long idPaseador)
    {
        TypedQuery<ComentarioEntity> q = em.createQuery("select p from ComentarioEntity p where (p.paseador.id = :idPaseador)", ComentarioEntity.class);
        q.setParameter("idPaseador", idPaseador);
        return q.getResultList();
    }
    
    public ComentarioEntity update(ComentarioEntity comentarioEntity) 
    {
        return em.merge(comentarioEntity);
    }
    
    
    public void delete(Long comentarioId)
    {
        ComentarioEntity comentarioEntity = em.find(ComentarioEntity.class, comentarioId);
        em.remove(comentarioEntity);
    }
}
