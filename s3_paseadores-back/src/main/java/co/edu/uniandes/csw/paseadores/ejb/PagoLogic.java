/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ContratoPersistence;
import co.edu.uniandes.csw.paseadores.persistence.PagoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author Mario Hurtado
 */
@Stateless
public class PagoLogic {

    /**
     * Inyección de la persistencia de pago
     */
    @Inject
    private PagoPersistence persistence;

    @Inject
    private ContratoPersistence contratoPersistence;

    //Métodos 
    /**
     * Crea el pago a partir de una instacia de PagoEntity
     *
     * @param idContrato
     * @param pago El pago a crear
     * @return el pago creado
     * @throws BusinessLogicException si al validar la información hay errores
     */
    public PagoEntity createPago(Long idContrato, PagoEntity pago) throws BusinessLogicException {
        if (pago.getValorServicio() <= 0) {
            throw new BusinessLogicException("El valor del servicio no es válido");
        }
        if (pago.getPagoRealizado()) {
            throw new BusinessLogicException("El pago no debe estar realizado");
        }
        if (pago.getFormaPago() == null) {
            throw new BusinessLogicException("El pago debe tener una forma de pago");
        }
        ContratoEntity contrato = contratoPersistence.find(idContrato);
        if (contrato.getPago() != null) {
            throw new BusinessLogicException("El contrato ya tiene un pago asociado");
        }
        pago = persistence.create(pago);
        contrato.setPago(pago);
        contratoPersistence.update(contrato);
        return pago;
    }

    /**
     * Obtiene los datos de una instancia de Pago a partir de su id.
     *
     * @param idContrato
     * @param pagoId Identificador de la instancia a consultar
     * @return Instancia de PagoEntity con los datos consultados.
     */
    public PagoEntity getPago(Long idContrato, Long pagoId) {
        PagoEntity pagoEntity = persistence.find(idContrato, pagoId);
        return pagoEntity;
    }

    /**
     * Obtiene la lista de los registros de Pago.
     *
     * @return Colección de objetos de PagoEntity.
     */
    public List<PagoEntity> getPagos() {

        List<PagoEntity> lista = persistence.findAll();

        return lista;
    }

    /**
     * Actualiza la información de una instancia de Pago.
     *
     * @param idContrato
     * @param pagoEntity Instancia de PagoEntity con los nuevos datos.
     * @return Instancia de PagoEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public PagoEntity updatePago(Long idContrato, PagoEntity pagoEntity) throws BusinessLogicException {
        if (pagoEntity.getValorServicio() <= 0) {
            throw new BusinessLogicException("El valor del servicio no es válido");
        }
        if (pagoEntity.getFormaPago() == null) {
            throw new BusinessLogicException("El pago debe tener una forma de pago");
        }
        PagoEntity nuevaEntidad = persistence.update(pagoEntity);
        return nuevaEntidad;
    }

    /**
     * Elimina una instancia de Pago de la base de datos.
     *
     * @param pagoId Identificador de la instancia a eliminar.
     * @param idContrato
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     *
     */
    public void deletePago(Long pagoId , Long idContrato) throws BusinessLogicException {
        PagoEntity pago = getPago(idContrato, pagoId);
        if (pago==null) {
            throw new BusinessLogicException("El pago asociado no existe");
        }
        if (pago.getPagoRealizado() != true) {
            throw new BusinessLogicException("No se puede eliminar el pago porque no se ha realizado");
        }
        persistence.delete(pagoId);

    }

}