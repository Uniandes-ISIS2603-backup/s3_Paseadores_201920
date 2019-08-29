/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import java.util.List;
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

    public MascotaEntity find(Long idMascota)
    {

           return em.find(MascotaEntity.class, idMascota);

    }
    
    public List<MascotaEntity> findAll()
    {
        
        TypedQuery query = em.createQuery("select u from MascotaEntity u", MascotaEntity.class);
        return query.getResultList();
        
    }
    
    public MascotaEntity update(MascotaEntity mascotaEntity) {
       // LOGGER.log(Level.INFO, "Actualizando el author con id={0}", contratoEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la author con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(mascotaEntity);
    }
    
     public void delete(Long mascotaId) {

//        LOGGER.log(Level.INFO, "Borrando el author con id={0}", contratoId);
      
        MascotaEntity authorEntity = em.find(MascotaEntity.class, mascotaId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from AuthorEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(authorEntity);
    }
}
