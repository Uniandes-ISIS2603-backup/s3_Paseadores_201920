/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ClientePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author Santiago Bolaños Vega
 */
@Stateless
public class ClienteLogic {

    @Inject
    private ClientePersistence persistence;

    /**
     * Se encarga de crear un nuevo cliente en la base de datos.
     *
     * @param cliente. Objeto ClienteEntity a crear.
     * @return Objeto ClienteEntity creado y con su Id.
     * @throws BusinessLogicException Si alguno de los parámetros es inválido.
     */
    public ClienteEntity createCliente(ClienteEntity cliente) throws BusinessLogicException {
        if (cliente.getNombre() == null || cliente.getNombre().equals("") || NumberUtils.isCreatable(cliente.getNombre())) {
            throw new BusinessLogicException("El nombre del cliente es nulo o tiene un formato incorrecto");
        }
        if (cliente.getCorreo() == null || cliente.getCorreo().equals("") || NumberUtils.isCreatable(cliente.getCorreo()) || !cliente.getCorreo().contains("@")) {
            throw new BusinessLogicException("El correo del cliente es nulo o tiene un formato incorrecto");
        }
        if (cliente.getContrasena() == null || cliente.getContrasena().equals("")) {
            throw new BusinessLogicException("La contrasena del cliente es nula o tiene un formato incorrecto");
        }
        if (cliente.getInfoContacto() == null || cliente.getInfoContacto().equals("")) {
            throw new BusinessLogicException("La informacion de contacto delcliente es nula o tiene un formato incorrecto");
        }
        cliente = persistence.create(cliente);
        return cliente;
    }

    /**
     * Retorna todos los clientes en la base de datos.
     *
     * @return Lista de entidades tipo Cliente.
     */
    public List<ClienteEntity> getClientes() {
        return persistence.findAll();
    }

    /**
     * Busca un cliente por su Id
     *
     * @param clienteId El Id del cliente a buscar.
     * @return Cliente buscado. null si no lo encuentra.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public ClienteEntity getCliente(Long clienteId) throws BusinessLogicException {
        ClienteEntity cliente = persistence.find(clienteId);
        if (cliente == null) {
            throw new BusinessLogicException("El cliente no existe");
        }
        return cliente;
    }

    /**
     * Actaliza la informaión de un cliente por su Id.
     *
     * @param clienteId. Id del cliente a actualizar.
     * @param cliente. Información del cliente actualizada.
     * @return La entidad del cliente actualizada.
     * @throws BusinessLogicException
     */
    public ClienteEntity updateCliente(Long clienteId, ClienteEntity cliente) throws BusinessLogicException {
        if (cliente.getNombre() == null || cliente.getNombre().equals("") || NumberUtils.isCreatable(cliente.getNombre())) {
            throw new BusinessLogicException("El nombre del cliente es nulo o tiene un formato incorrecto");
        }
        if (cliente.getCorreo() == null || cliente.getCorreo().equals("") || NumberUtils.isCreatable(cliente.getCorreo()) || !cliente.getCorreo().contains("@")) {
            throw new BusinessLogicException("El correo del cliente es nulo o tiene un formato incorrecto");
        }
        if (cliente.getContrasena() == null || cliente.getContrasena().equals("")) {
            throw new BusinessLogicException("La contrasena del cliente es nula o tiene un formato incorrecto");
        }
        if (cliente.getInfoContacto() == null || cliente.getInfoContacto().equals("")) {
            throw new BusinessLogicException("La informacion de contacto delcliente es nula o tiene un formato incorrecto");
        }
        return persistence.update(cliente);
    }

    /**
     * Elimina un cliente por su Id.
     *
     * @param clienteId . Id del cliente a eliminar
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public void deleteCliente(Long clienteId) throws BusinessLogicException {
        ClienteEntity cliente = persistence.find(clienteId);
        if (cliente.getContratos() != null && !cliente.getContratos().isEmpty()) 
        {
            throw new BusinessLogicException("El cliente todavia tiene contratos");
        }
        if (cliente.getMascotas() != null && !cliente.getMascotas().isEmpty()) 
        {
            throw new BusinessLogicException("El cliente todavia tiene mascotas");
        }
        if (cliente.getFormasPago() != null && !cliente.getFormasPago().isEmpty()) 
        {
            throw new BusinessLogicException("El cliente todavia tiene formas de pago");
        }
        persistence.delete(clienteId);
    }
}
