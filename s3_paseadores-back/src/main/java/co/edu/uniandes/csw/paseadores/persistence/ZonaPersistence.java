package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Persistencia de la zona.
 *
 * @author Juan Vergara
 */
@Stateless
public class ZonaPersistence {

    /**
     * Entity Manager.
     */
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    /**
     * Crea una nueva zona.
     *
     * @param p Nueva zona
     * @return Zona persistida.
     */
    public ZonaEntity create(ZonaEntity p) {
        em.persist(p);
        return p;
    }

    /**
     * Encuentra una zona por su id.
     *
     * @param idZona Id de la zona buscada.
     * @return Zona buscada.
     */
    public ZonaEntity find(Long idZona) {
        return em.find(ZonaEntity.class, idZona);
    }

    /**
     * Retorna todas las zonas.
     *
     * @return zonas
     */
    public List<ZonaEntity> findAll() {
        TypedQuery query = em.createQuery("select u from ZonaEntity u", ZonaEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza la información de una zona.
     *
     * @param ZonaEntity zona actualizada.
     * @return Información persistida.
     */
    public ZonaEntity update(ZonaEntity ZonaEntity) {
        return em.merge(ZonaEntity);
    }

    /**
     * Elimina una zona.
     *
     * @param ZonaId Id de la zona a eliminar.
     */
    public void delete(Long ZonaId) {
        ZonaEntity authorEntity = em.find(ZonaEntity.class, ZonaId);
        em.remove(authorEntity);
    }
}
