/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;

import co.edu.uniandes.csw.paseadores.dtos.ClienteDTO;
import co.edu.uniandes.csw.paseadores.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.paseadores.ejb.ClienteLogic;
import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Santiago Bolaños
 */
@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteResource {
    
    @Inject
    private ClienteLogic clienteLogic;
    
    @POST
    public ClienteDTO createCliente(ClienteDTO cliente ) throws BusinessLogicException{
        ClienteEntity entity = clienteLogic.createCliente(cliente.toEntity());
        ClienteDTO dto = new ClienteDTO(entity);
        return dto;
    }
    
    @GET
    public List<ClienteDetailDTO> getClientes(){
        List<ClienteDetailDTO> lista = listEntity2DTO(clienteLogic.getClientes());
        return lista;
    }
    
    @GET
    @Path("{clientesId: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("clientesId") Long clientesId) 
    {
        ClienteEntity entity = clienteLogic.getCliente(clientesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDetailDTO dto = new ClienteDetailDTO(entity);
        return dto;
    }
    
    @PUT
    @Path("{clientesId: \\d+}")
    public ClienteDetailDTO updateCliente(@PathParam("clientesId") Long clientesId, ClienteDetailDTO cliente) throws BusinessLogicException {
        
        cliente.setId(clientesId);
        if( clienteLogic.getCliente(clientesId) == null ){
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDetailDTO dto = new ClienteDetailDTO(clienteLogic.updateCliente(clientesId, cliente.toEntity()));
        return dto;
    }
    
    @DELETE
    @Path("{clientesId: \\d+}")
    public void deleteCliente(@PathParam("clientesId") Long clientesId) throws BusinessLogicException{
        if( clienteLogic.getCliente(clientesId) == null ){
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        clienteLogic.deleteCliente(clientesId);
    }
    
    private List<ClienteDetailDTO> listEntity2DTO(List<ClienteEntity> entityList) 
    {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : entityList) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }
}