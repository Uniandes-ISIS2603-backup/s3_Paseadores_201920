package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Métodos CRUD asociados a la entidad Contrato y su parte de persistencia.
 *
 * @author Nicolas Potes Garcia
 */
@Stateless
public class ContratoPersistence {

    /**
     * Entity Manager.
     */
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;

    /**
     * Crea un contrato.
     *
     * @param contrato Contrato nuevo.
     * @return Contrato creado.
     */
    public ContratoEntity create(ContratoEntity contrato) {
        em.persist(contrato);
        return contrato;
    }

    /**
     * Encuentra un contrato por su id.
     *
     * @param idContrato. Id de contrato.
     * @return Contrato buscado.
     */
    public ContratoEntity find(Long idContrato) {
        return em.find(ContratoEntity.class, idContrato);
    }

    /**
     * Devuelve todos los contratos.
     *
     * @return Contratos.
     */
    public List<ContratoEntity> findAll() {
        TypedQuery query = em.createQuery("select u from ContratoEntity u", ContratoEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza un contrato.
     *
     * @param contratoEntity Contrato a actualizar.
     * @return Contrato actualizado.
     */
    public ContratoEntity update(ContratoEntity contratoEntity) {
        return em.merge(contratoEntity);
    }

    /**
     * Elimina un contrato.
     *
     * @param contratoId Id del contrato a eliminar.
     */
    public void delete(Long contratoId) {
        ContratoEntity contratoEntity = em.find(ContratoEntity.class, contratoId);
        em.remove(contratoEntity);
    }
}
