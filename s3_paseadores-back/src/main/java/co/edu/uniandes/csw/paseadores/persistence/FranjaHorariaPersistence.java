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
     * Retorna todas las franjas horarias de la base de datos.
     * 
     * @return una lista con todos las franjas horarias de la base de datos.
     * "select u from FranjaHorariaEntity u" es como un "select * from FranjaHorariaEntity;" -
     * "SELECT * FROM table_name" en SQL
     */
    public List<FranjaHorariaEntity> findAll(){
        TypedQuery query = em.createQuery("Select u from FranjaHorariaEntity u",FranjaHorariaEntity.class);
        return query.getResultList();
    }
    
    /**
     * Busca si hay una franja con el id que se envía por parámetro
     * 
     * @param franjaId. Id de la franja
     * @return Franja buscada.
     */
    public FranjaHorariaEntity find( Long franjaId ){
        return em.find(FranjaHorariaEntity.class, franjaId);
    }
    
    /**
     * Actualiza una franja horaria.
     * 
     * @param franja La entidad de la frnaja horaria con los nuevos datos.l
     * @return Franja actualizada.
     */
    public FranjaHorariaEntity update(FranjaHorariaEntity cliente){
        return em.merge(cliente);
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
