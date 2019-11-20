package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ClientePersistence;
import co.edu.uniandes.csw.paseadores.persistence.ContratoPersistence;
import co.edu.uniandes.csw.paseadores.persistence.PagoPersistence;
import co.edu.uniandes.csw.paseadores.persistence.PaseadorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Lògica de los contratos.
 *
 * @author Nicolas Potes Garcia
 */
@Stateless
public class ContratoLogic {

    /**
     * Persistencia del contrato.
     */
    @Inject
    private ContratoPersistence persistence;

    /**
     * Persisrencia de los paseadores.
     */
    @Inject
    private PaseadorPersistence paseadorPersistence;

    /**
     * Persistencia de los clientes.
     */
    @Inject
    private ClientePersistence clientePersistence;

    /**
     * Crea un contrato.
     *
     * @param contrato Conttrato a persistir.
     * @return Contrato persistido.
     * @throws BusinessLogicException Si no se cumplen las reglas de negocio.
     */
    public ContratoEntity createContrato(ContratoEntity contrato) throws BusinessLogicException {

        if (contrato.getValorServicio() == null || contrato.getValorServicio() <= 0) {
            throw new BusinessLogicException("El valor del contrato no es válido o no se ha definido");
        }
        if (contrato.getZona() == null) {
            throw new BusinessLogicException("No existe una zona en el contrato");
        }
        if (contrato.getFranja() == null) {
            throw new BusinessLogicException("No existe una fraja horaria para el contrato");
        }
        if (contrato.getCliente() == null) {
            throw new BusinessLogicException("El contrato no tiene asociado un cliente");
        }
        if (contrato.getPaseador() == null) {
            throw new BusinessLogicException("El contrato no tiene asociado un paseador");
        }
        if (contrato.getFinalizado()) {
            throw new BusinessLogicException("El contrato no se puede crear como finalizado");
        }
        contrato = persistence.create(contrato);
        return contrato;
    }

    /**
     * Obtiene los datos de una instancia de Contrato a partir de su ID.
     *
     * @param contratoId Identificador de la instancia a consultar
     * @return Instancia de ContratoEntity con los datos del Paseador
     * consultado.
     */
    public ContratoEntity getContrato(Long contratoId) {
        return persistence.find(contratoId);
    }

    /**
     * Obtiene la lista de los registros de Contrato.
     *
     * @return Colección de objetos de ContratoEntity.
     */
    public List<ContratoEntity> getContratos() {
        return persistence.findAll();
    }

    /**
     * Obtiene todos los contratos de un cliente.
     *
     * @param idCliente Id del cliente.
     * @return Contratos del cliente.
     * @throws BusinessLogicException Si el cliente no existe.
     */
    public List<ContratoEntity> getContratosPorCliente(Long idCliente) throws BusinessLogicException {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        if (cliente == null) {
            throw new BusinessLogicException("No existe un cliente con el id " + idCliente);
        }
        return cliente.getContratos();
    }

    /**
     * Obtiene todos los contratos de un paseador.
     *
     * @param idPaseador Id del paseador.
     * @return Contratos del cliente.
     * @throws BusinessLogicException Si el cliente no existe.
     */
    public List<ContratoEntity> getContratosPorPaseador(Long idPaseador) throws BusinessLogicException {
        PaseadorEntity paseador = paseadorPersistence.find(idPaseador);
        if (paseador == null) {
            throw new BusinessLogicException("No existe un paseador con el id " + idPaseador);
        }
        return paseador.getContratos();
    }

    /**
     * Actualiza la información de una instancia de Contrato.
     *
     * @param contratoId Identificador de la instancia a actualizar
     * @param contrato
     * @return Instancia de ContratoEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public ContratoEntity updateContrato(Long contratoId, ContratoEntity contrato) throws BusinessLogicException {
        ContratoEntity contratoAntiguo = getContrato(contratoId);
        if (contratoAntiguo != null && contratoAntiguo.getPago() != null) {
            contrato.setPago(contratoAntiguo.getPago());
        }
        if (contrato.getValorServicio() == null || contrato.getValorServicio() <= 0) {
            throw new BusinessLogicException("El valor del contrato no es válido o no se ha definido");
        }
        if (contrato.getCliente() == null) {
            throw new BusinessLogicException("El contrato no tiene asociado un cliente");
        }
        if (contrato.getPaseador() == null) {
            throw new BusinessLogicException("El contrato no tiene asociado un paseador");
        }
        if (contrato.getZona() == null) {
            throw new BusinessLogicException("No existe una zona en el contrato");
        }
        if (contrato.getFranja() == null) {
            throw new BusinessLogicException("No existe una fraja horaria para el contrato");
        }
        if (contrato.getMascotas() == null || contrato.getMascotas().isEmpty()) {
            throw new BusinessLogicException("El contrato no dispone de mascota(s)");
        }

        return persistence.update(contrato);
    }

    /**
     * Elimina una instancia de Contrato de la base de datos.
     *
     * @param contratoId Identificador de la instancia a eliminar.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     *
     */
    public void deleteContrato(Long contratoId) throws BusinessLogicException {
        ContratoEntity contrato = getContrato(contratoId);
        if (!contrato.getPago().getPagoRealizado()) {
            throw new BusinessLogicException("No se puede eliminar el contrato porque no se ha pagado");
        }
        if (!contrato.getFinalizado()) {
            throw new BusinessLogicException("No se puede eliminar el contrato porque no se ha finalizado");
        }
        persistence.delete(contratoId);
    }
}
