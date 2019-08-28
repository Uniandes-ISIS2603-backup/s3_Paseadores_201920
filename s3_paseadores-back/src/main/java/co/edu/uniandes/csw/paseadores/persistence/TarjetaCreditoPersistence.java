/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.TarjetaCreditoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Vergara
 */
@Stateless
public class TarjetaCreditoPersistence {
    
    @PersistenceContext (unitName = "paseadoresPU")
    protected EntityManager em;
    
    public TarjetaCreditoEntity create(TarjetaCreditoEntity p){
        em.persist(p);
        return p;
    }
}
