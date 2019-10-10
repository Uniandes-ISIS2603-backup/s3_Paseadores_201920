/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;

import co.edu.uniandes.csw.paseadores.dtos.TarjetaCreditoDTO;
import co.edu.uniandes.csw.paseadores.ejb.TarjetaCreditoLogic;
import co.edu.uniandes.csw.paseadores.entities.TarjetaCreditoEntity;
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
@Path("Tarjetas")
@Produces(MediaType.APPLICATION_JSON)      
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TarjetaCreditoResource {
    @Inject
    private TarjetaCreditoLogic tarjetaCreditoLogic;
    
    @POST
    public TarjetaCreditoDTO createTarjetaCredito(TarjetaCreditoDTO tarjetaCredito) throws BusinessLogicException{
        TarjetaCreditoDTO tarjetaCreditoDTO = new TarjetaCreditoDTO(tarjetaCreditoLogic.createTarjetaCredito(tarjetaCredito.toEntity()));
        return tarjetaCreditoDTO;
    }
    
    @DELETE
    @Path("{tarjetaCreditoId: \\d+}")
    public void deleteTarjetaCredito (@PathParam("tarjetaCreditoId") Long tarjetaCreditoId) throws BusinessLogicException{
        if( tarjetaCreditoLogic.getTarjetaCredito(tarjetaCreditoId)==null){
           throw new WebApplicationException("El recurso /tarjetaCreditos/" + tarjetaCreditoId + " no existe.", 404);
        }
        
        tarjetaCreditoLogic.deleteTarjetaCredito(tarjetaCreditoId);
        
    }

    @GET
    @Path("{tarjetaCreditoId: \\d+}")
    public TarjetaCreditoDTO getTarjetaCredito(@PathParam("tarjetaCreditoId") Long tarjetaCreditoId) throws BusinessLogicException {
    	
        TarjetaCreditoEntity entity = tarjetaCreditoLogic.getTarjetaCredito(tarjetaCreditoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/tarjetaCreditos/" + tarjetaCreditoId + " no existe.", 404);
        }
        TarjetaCreditoDTO tarjetaCreditoDTO = new TarjetaCreditoDTO(entity);
        return tarjetaCreditoDTO;
    }

    @GET
    public List<TarjetaCreditoDTO> getTarjetaCreditos() {

        List<TarjetaCreditoDTO> listaDTOs = listEntity2DTO(tarjetaCreditoLogic.getTarjetaCreditos());
        return listaDTOs;
    }
    
 
    private List<TarjetaCreditoDTO> listEntity2DTO(List<TarjetaCreditoEntity> entityList) {
        List<TarjetaCreditoDTO> list = new ArrayList<TarjetaCreditoDTO>();
        for (TarjetaCreditoEntity entity : entityList) {
            list.add(new TarjetaCreditoDTO(entity));
        }
        return list;
    }

    @PUT
    @Path("{tarjetaCreditoId: \\d+}")
    public TarjetaCreditoDTO updateTarjetaCredito(@PathParam("tarjetaCreditoId") Long tarjetaCreditoId, TarjetaCreditoDTO tarjetaCredito) throws BusinessLogicException {

        if (tarjetaCreditoId.equals(tarjetaCredito.getId())) {
            throw new BusinessLogicException("Los ids del TarjetaCredito no coinciden.");
        }
        TarjetaCreditoEntity entity = tarjetaCreditoLogic.getTarjetaCredito(tarjetaCreditoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/tarjetaCreditos/" + tarjetaCreditoId + " no existe.", 404);

        }
        TarjetaCreditoDTO tarjetaCreditoDTO = new TarjetaCreditoDTO(tarjetaCreditoLogic.updateTarjetaCredito(tarjetaCreditoId, tarjetaCredito.toEntity()));
        return tarjetaCreditoDTO; 
   }
    
}
