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
 * Logica de una forma de pago.
 *
 * @author Mario Hurtado
 */
@Stateless
public class FormaPagoLogic {

    /**
     * Persistencia de la forma de pago.
     */
    @Inject
    private FormaPagoPersistence persistence;

    /**
     * Persistencia de los clientes.
     */
    @Inject
    private ClientePersistence clientePersistence;

    /**
     * Crea una nueva forma de apgo.
     *
     * @param idCliente. Id del cliente.
     * @param formaPago. Informaci[on de la forma de pago.
     * @return Forma de pago creada.
     * @throws BusinessLogicException Si la forma de pago no cumple con las
     * reglas de negocio.
     */
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
    public FormaPagoEntity getFormaPago(Long idCliente, Long idFormaPago) {
        return persistence.find(idCliente, idFormaPago);
    }

    /**
     * Retorna todas las formas de pago asociada a un cliente partícular.
     *
     * @param idCliente Id del cliente.
     * @return Formas de pago.
     * @throws BusinessLogicException Si el cliente no existe.
     */
    public List<FormaPagoEntity> getFormasPagoCliente(Long idCliente) throws BusinessLogicException {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        if (cliente == null) {
            throw new BusinessLogicException("No hay un cliente con el id " + idCliente);
        }
        return cliente.getFormasPago();
    }

    /**
     * Obtiene la lista de los registros de las formas de Pago.
     *
     * @return Colección de objetos de FomraPagoEntity.
     */
    public List<FormaPagoEntity> getFormasPago() {
        return persistence.findAll();
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
        return persistence.update(formaPago);
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
        FormaPagoEntity formaPagoAntigua = getFormaPago(idCliente, formaPagoId);
        if (formaPagoAntigua == null) {
            throw new BusinessLogicException("El cliente " + idCliente + " no tiene una forma de pago " + formaPagoId);
        }
        persistence.delete(formaPagoId);

    }
}
