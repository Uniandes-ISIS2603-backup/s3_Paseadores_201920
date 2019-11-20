package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Métodos CRUD asociados a la entidad Mascota y su parte de persistencia.
 *
 * @author Daniel Felipe García Vargas
 */
@Stateless
public class MascotaPersistence {

    /**
     * Entity Manager.
     */
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    /**
     * Crea una nueva mascota.
     *
     * @param mascota Entidad de la mascota.
     * @return Entidad persistida.
     */
    public MascotaEntity create(MascotaEntity mascota) {
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
    public MascotaEntity find(Long idCliente, Long idMascota) {
        TypedQuery<MascotaEntity> q = em.createQuery("select p from MascotaEntity p where (p.cliente.id = :idcliente) and (p.id = :idmascota)", MascotaEntity.class);
        q.setParameter("idcliente", idCliente);
        q.setParameter("idmascota", idMascota);
        List<MascotaEntity> results = q.getResultList();
        MascotaEntity mascota = null;
        if (results != null && !results.isEmpty()) {
            mascota = results.get(0);
        }
        return mascota;
    }

    public List<MascotaEntity> findAll() {
        TypedQuery query = em.createQuery("select u from MascotaEntity u", MascotaEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza los datos de una mascota.
     *
     * @param mascotaEntity Nueva información.
     * @return Mascota actualizada.
     */
    public MascotaEntity update(MascotaEntity mascotaEntity) {
        return em.merge(mascotaEntity);
    }

    /**
     * Elimina una mascota.
     *
     * @param mascotaId Id de la mascota a elimincar.
     */
    public void delete(Long mascotaId) {
        MascotaEntity authorEntity = em.find(MascotaEntity.class, mascotaId);
        em.remove(authorEntity);
    }
}
