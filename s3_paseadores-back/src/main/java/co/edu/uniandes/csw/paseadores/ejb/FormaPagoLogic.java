/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ClientePersistence;
import co.edu.uniandes.csw.paseadores.persistence.FormaPagoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Mario Hurtado
 */
@Stateless
public class FormaPagoLogic {

    @Inject
    private FormaPagoPersistence persistence;

    @Inject
    private ClientePersistence clientePersistence;

    public FormaPagoEntity createFormaPago(Long idCliente, FormaPagoEntity formaPago) throws BusinessLogicException {

        ClienteEntity cliente = clientePersistence.find(idCliente);
        if (cliente == null) {
            throw new BusinessLogicException("El cliente buscado no existe");
        }
        if (formaPago.getCapacidadPago() <= 0) {
            throw new BusinessLogicException(" La capacidad debe ser mayor a 0 ");
        }
        formaPago.setCliente(cliente);
        return persistence.create(formaPago);
    }

    /**
     * Obtiene los datos de una instancia de FormaPago a partir de su id.
     *
     * @param idCliente
     * @param idFormaPago
     * @return Instancia de FormaPagoEntity con los datos consultados.
     */
    public FormaPagoEntity getFormaPagoPorCliente(Long idCliente, Long idFormaPago) {
        return persistence.find(idCliente, idFormaPago);
    }

    /**
     * Obtiene la lista de los registros de las formas de Pago.
     *
     * @return Colección de objetos de FomraPagoEntity.
     */
    public List<FormaPagoEntity> getFormasPago() {
        List<FormaPagoEntity> lista = persistence.findAll();
        return lista;
    }

    /**
     * Actualiza la información de una instancia de FormaPago.
     *
     * @param idCliente
     * @param formaPago
     * @return Instancia de FormaPagoEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public FormaPagoEntity updateFormaPago(Long idCliente, FormaPagoEntity formaPago) throws BusinessLogicException {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        if (cliente == null) {
            throw new BusinessLogicException("El cliente buscado no existe");
        }
        if (formaPago.getCapacidadPago() <= 0) {
            throw new BusinessLogicException("La capacidad debe ser mayor a 0");
        }
        formaPago.setCliente(cliente);
        FormaPagoEntity nuevaFormaEntidad = persistence.update(formaPago);

        return nuevaFormaEntidad;
    }

    /**
     * Elimina una instancia FormaPago de la base de datos.
     *
     * @param idCliente
     * @param formaPagoId Identificador de la instancia a eliminar.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     *
     */
    public void deleteFormaPago(Long idCliente, Long formaPagoId) throws BusinessLogicException {
        FormaPagoEntity formaPagoAntigua = getFormaPagoPorCliente(idCliente, formaPagoId);
        if (formaPagoAntigua == null) {
            throw new BusinessLogicException("El cliente " + idCliente + " no tiene una forma de pago " + formaPagoId);
        }
        persistence.delete(formaPagoId);

    }
}
