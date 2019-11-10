/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;

import co.edu.uniandes.csw.paseadores.dtos.ZonaDTO;
import co.edu.uniandes.csw.paseadores.ejb.ZonaLogic;
import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
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
 * @author Juan Vergara
 */

@Path("/zonas")
@Produces(MediaType.APPLICATION_JSON)      
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ZonaResource {
    
    @Inject
    private ZonaLogic zonaLogic;
    /**
     * Crea una nueva zona con la informacion recibida y regresa una copua identica de la zona
     * @param zona
     * @return zonaDTO
     * @throws BusinessLogicException 
     */
    @POST
    public ZonaDTO createZona(ZonaDTO zona) throws BusinessLogicException{
        ZonaDTO zonaDTO = new ZonaDTO(zonaLogic.createZona(zona.toEntity()));
        return zonaDTO;
    }
    /**
     * Borra la zona con el id relacionado
     * @param zonaId
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{zonaId: \\d+}")
    public void deleteZona (@PathParam("zonaId") Long zonaId) throws BusinessLogicException{
        if( zonaLogic.getZona(zonaId)==null){
           throw new WebApplicationException("El recurso /zonas/" + zonaId + " no existe.", 404);
        }
        
        zonaLogic.deleteZona(zonaId);
        
    }
    /**
     * Buscar y regresa la zona identificada con el ID recibido en la URÑ
     * @param zonaId
     * @return zonaDTO
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{zonaId: \\d+}")
    public ZonaDTO getZona(@PathParam("zonaId") Long zonaId) throws BusinessLogicException {
    	
        ZonaEntity entity = zonaLogic.getZona(zonaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/zonas/" + zonaId + " no existe.", 404);
        }
        ZonaDTO zonaDTO = new ZonaDTO(entity);
        return zonaDTO;
    }
    /**
     * Regresa todas las zonas existentes
     * @return listaDTOs
     */
    @GET
    public List<ZonaDTO> getZonas() {

        List<ZonaDTO> listaDTOs = listEntity2DTO(zonaLogic.getZonas());
        return listaDTOs;
    }
    

    /**
     * Lista de entity a DTOs
     * @param entityList
     * @return list
     */
    private List<ZonaDTO> listEntity2DTO(List<ZonaEntity> entityList) {
        List<ZonaDTO> list = new ArrayList<ZonaDTO>();
        for (ZonaEntity entity : entityList) {
            list.add(new ZonaDTO(entity));
        }
        return list;
    }
    /**
     * Actualiza la zona con la información recibida y la regresa con sus cambios aplicados
     * @param zonaId
     * @param zona
     * @return zonaDTO
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{zonaId: \\d+}")
    public ZonaDTO updateZona(@PathParam("zonaId") Long zonaId, ZonaDTO zona) throws BusinessLogicException {

        zona.setId(zonaId);
        ZonaEntity entity = zonaLogic.getZona(zonaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/zonas/" + zonaId + " no existe.", 404);

        }
        ZonaDTO zonaDTO = new ZonaDTO(zonaLogic.updateZona(zonaId, zona.toEntity()));
        return zonaDTO; 
   }
    
}
