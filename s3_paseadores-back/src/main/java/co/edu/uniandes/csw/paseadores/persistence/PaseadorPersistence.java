package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Métodos CRUD asociados a la entidad Contrato y su parte de persistencia.
 *
 * @author Daniel Felipe García Vargas
 */
@Stateless
public class PaseadorPersistence {

    /**
     * Entity Manager.
     */
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    /**
     * Persiste un nuevo paseador.
     *
     * @param paseador Paseador a persistir.
     * @return Paseador persistido.
     */
    public PaseadorEntity create(PaseadorEntity paseador) {
        em.persist(paseador);
        return paseador;
    }

    /**
     * Encuentra un paseador.
     *
     * @param idPaseador Id del paseador buscado.
     * @return Paseador buscado.
     */
    public PaseadorEntity find(Long idPaseador) {
        return em.find(PaseadorEntity.class, idPaseador);
    }

    /**
     * Retorna todos los paseadores.
     *
     * @return paseadores.
     */
    public List<PaseadorEntity> findAll() {
        TypedQuery query = em.createQuery("select u from PaseadorEntity u", PaseadorEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza la infomración de un paseador.
     *
     * @param paseadorEntity Paseador a actualizar.
     * @return Paseador actualizado.
     */
    public PaseadorEntity update(PaseadorEntity paseadorEntity) {
        return em.merge(paseadorEntity);
    }

    /**
     * Elimina un paseador.
     *
     * @param paseadorId id del paseador a eliminar.
     */
    public void delete(Long paseadorId) {
        PaseadorEntity authorEntity = em.find(PaseadorEntity.class, paseadorId);
        em.remove(authorEntity);
    }
}
