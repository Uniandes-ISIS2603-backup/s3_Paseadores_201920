/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel Felipe García Vargas
 */
@Stateless
public class PaseadorPersistence 
{
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    public PaseadorEntity create(PaseadorEntity paseador) 
    {
        em.persist(paseador);

        return paseador;
    }

}
