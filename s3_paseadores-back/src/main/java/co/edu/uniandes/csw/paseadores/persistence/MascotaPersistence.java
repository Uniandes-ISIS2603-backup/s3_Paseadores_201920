/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Métodos CRUD asociados a la entidad Mascota y su parte de persistencia.
 * @author Daniel Felipe García Vargas
 */
@Stateless
public class MascotaPersistence 
{
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    public MascotaEntity create(MascotaEntity mascota) 
    {
        em.persist(mascota);
        return mascota;
    }

    /**
     * Buscar una mascota
     *
     * Busca si hay alguna mascota asociada a un libro y con un ID específico
     *
     * @param idCliente El ID del cliente con respecto al cual se busca
     * @param idMascota El ID de la mascota buscada
     * @return La mascota encontrada o null. Nota: Si existe una o más msacota
     * devuelve siempre la primera que encuentra
     */
    public MascotaEntity find(Long idCliente, Long idMascota)
    {
        TypedQuery<MascotaEntity> q = em.createQuery("select p from MascotaEntity p where (p.cliente.id = :idcliente) and (p.id = :idmascota)", MascotaEntity.class);
        q.setParameter("idcliente", idCliente);
        q.setParameter("idmascota", idMascota);
        List<MascotaEntity> results = q.getResultList();
        MascotaEntity mascota = null;
        if (results == null) 
        {
            mascota = null;
        } else if (results.isEmpty()) 
        {
            mascota = null;
        } else if (results.size() >= 1) 
        {
            mascota = results.get(0);
        }
        return mascota;
    }
    
    public List<MascotaEntity> findAll()
    {       
        TypedQuery query = em.createQuery("select u from MascotaEntity u", MascotaEntity.class);
        return query.getResultList();      
    }
    
    public MascotaEntity update(MascotaEntity mascotaEntity) 
    { 
        return em.merge(mascotaEntity);
    }
    
     public void delete(Long mascotaId) 
     {
        MascotaEntity authorEntity = em.find(MascotaEntity.class, mascotaId);
        em.remove(authorEntity);
    }
}
