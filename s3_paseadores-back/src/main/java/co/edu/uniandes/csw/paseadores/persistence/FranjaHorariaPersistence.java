/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Santiago Bolaños Vega
 */
@Stateless
public class FranjaHorariaPersistence {
    
    @PersistenceContext( unitName = "paseadoresPU")
    protected EntityManager em;
    
    /**
     * Crea una franja horaria en la base de datos.
     * 
     * @param franja. objeto franja que se creará en la base de datos.
     * @return la entidad franjaHoraria creada con un id dado por la base de datos.
     */
    public FranjaHorariaEntity create(FranjaHorariaEntity franja ){
        em.persist(franja);
        return franja;
    }
    
    /**
     * Buscar una franja horaria
     *
     * Busca si hay alguna franja asociada a un paseador y con un ID específico
     *
     * @param paseadorId El ID del pasador con respecto al cual se busca
     * @param franjaId El ID de la franja buscada
     * @return La franja encontrada o null
     */
    public FranjaHorariaEntity find(Long paseadorId, Long franjaId) {
        TypedQuery<FranjaHorariaEntity> q = em.createQuery("select p from FranjaHorariaEntity p where (p.paseador.id = :paseadorid) and (p.id = :franjaId)", FranjaHorariaEntity.class);
        q.setParameter("paseadorid", paseadorId);
        q.setParameter("franjaId", franjaId);
        List<FranjaHorariaEntity> results = q.getResultList();
        FranjaHorariaEntity franja = null;
        if (results == null) {
            franja = null;
        } else if (results.isEmpty()) {
            franja = null;
        } else if (results.size() >= 1) {
            franja = results.get(0);
        }
        return franja;
    }
    
    /**
     * Actualiza una franja horaria.
     * 
     * @param franja La entidad de la frnaja horaria con los nuevos datos.l
     * @return Franja actualizada.
     */
    public FranjaHorariaEntity update(FranjaHorariaEntity franja){
        return em.merge(franja);
    }
    
    /**
     * Elimina una franja de la base de datos dado su id.
     * 
     * @param franjaId id de la franja a eliminar.
     */
    public void delete( Long franjaId){
        FranjaHorariaEntity cliente = em.find(FranjaHorariaEntity.class , franjaId);
        em.remove(cliente);
    }
}
