/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.PagoEntity;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Juan Esteban Vergara
 */
@Stateless
public class PagoPersistence {
    
    @PersistenceContext (unitName = "paseadoresPU")
    protected EntityManager em;
    
    public PagoEntity create(PagoEntity p){
        em.persist(p);
        return p;
    }
    
}
