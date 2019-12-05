/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ContratoPersistence;
import co.edu.uniandes.csw.paseadores.persistence.PagoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas Potes Garcia
 */
@Stateless
public class ContratoLogic {

    @Inject
    private ContratoPersistence persistence;

    @Inject
    private PagoPersistence pagoPersistence;

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

    public List<ContratoEntity> getContratosPorCliente(Long idCliente) {
        return persistence.findAllPorCliente(idCliente);
    }

    public List<ContratoEntity> getContratosPorPaseador(Long idPaseador) {
        return persistence.findAllPorCliente(idPaseador);
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
        if( contratoAntiguo != null && contratoAntiguo.getPago() != null ){
            contrato.setPago(contratoAntiguo.getPago());
        }
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
