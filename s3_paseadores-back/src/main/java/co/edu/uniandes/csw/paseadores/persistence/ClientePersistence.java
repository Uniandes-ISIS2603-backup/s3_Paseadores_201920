/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *Clase que maneja la persistencia para el cliente.
 * 
 * @author Santiago Bolaños 
 */
@Stateless
public class ClientePersistence {
    
    @PersistenceContext( unitName = "paseadoresPU")
    protected EntityManager em;
    
    /**
     * Crea un cliente en la base de datos.
     * 
     * @param cliente. objeto cliente que se creará en la base de datos.
     * @return la entidad cliente creada con un id dado por la base de datos.
     */
    public ClienteEntity create ( ClienteEntity cliente ){
        em.persist(cliente);
        return cliente;
    }
    
    /**
     * Retorna todos los clientes de la base de datos.
     * 
     * @return una lista con todos los clientes de la base de datos.
     * "select u from ClienteEntity u" es como un "select * from ClienteEntity;" -
     * "SELECT * FROM table_name" en SQL
     */
    public List<ClienteEntity> findAll(){
        TypedQuery query = em.createQuery("Select u from ClienteEntity u",ClienteEntity.class);
        return query.getResultList();
    }
    
    /**
     * Busca si hay un cliente con el id que de envía por parámetro
     * 
     * @param clienteId. Id del cliente
     * @return Cliente buscado.
     */
    public ClienteEntity find( Long clienteId ){
        return em.find(ClienteEntity.class, clienteId);
    }
    
    /**
     * Actualiza un cliente.
     * 
     * @param cliente La entidad del cliente con los nuevos datos.l
     * @return Cliente actualizado.
     */
    public ClienteEntity update(ClienteEntity cliente){
        return em.merge(cliente);
    }
    
    /**
     * Elimina un cliente de la base de datos dado su id.
     * 
     * @param clienteId id del cliente a eliminar.
     */
    public void delete( Long clienteId){
        ClienteEntity cliente = em.find(ClienteEntity.class , clienteId);
        em.remove(cliente);
    }
}