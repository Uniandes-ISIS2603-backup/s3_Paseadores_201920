/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;

import co.edu.uniandes.csw.paseadores.dtos.CalificacionDTO;
import co.edu.uniandes.csw.paseadores.ejb.CalificacionLogic;
import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
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
 * @version 1.0
 */
@Produces(MediaType.APPLICATION_JSON)      
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
        
public class CalificacionResource {
    
    @Inject
    private CalificacionLogic calificacionLogic;
   
    /**
     * 
     * @param calificacion {@link calificacionDTO} - La calificacion que desea guardar.
     * @return JSON {@link calificacionDTO} - La calificacion guardada con su id autogenerado
     * @throws BusinessLogicException 
     */
    @POST
    public CalificacionDTO createCalificacion(@PathParam("contratoId")Long contratoId, CalificacionDTO calificacion) throws BusinessLogicException{
        return new CalificacionDTO(calificacionLogic.createCalificacion(contratoId, calificacion.toEntity()));
    }
    /**
     * Borra la calificacion con el id recibido en la URL
     * @param calificacionId
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{calificacionId: \\d+}")
    public void deleteCalificacion (@PathParam("calificacionId") Long calificacionId, @PathParam("paseadoresId") Long paseadoresId) throws BusinessLogicException{
        if( calificacionLogic.getCalificacion(paseadoresId, calificacionId)==null){
           throw new WebApplicationException("El recurso /calificacions/" + calificacionId + " no existe.", 404);
        }        
        calificacionLogic.deleteCalificacion(paseadoresId, calificacionId);        
    }
    /**
     * Busca y deuelve la calificacion con el ID recibido en la URL
     * @param calificacionId
     * @return calificacionDTO
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("calificacionId") Long calificacionId, @PathParam("paseadoresId") Long paseadoresId) throws BusinessLogicException {
    	
        CalificacionEntity entity = calificacionLogic.getCalificacion(paseadoresId, calificacionId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/calificacions/" + calificacionId + " no existe.", 404);
        }
        return new CalificacionDTO(entity);
    }
    /**
     * Devuelve todas las calificaciones que existen
     * @return listaDTOs
     */
    @GET
    public List<CalificacionDTO> getCalificaciones(@PathParam("paseadoresId") Long paseadoresId) {
        return listEntity2DTO(calificacionLogic.getCalificaciones());
    }
    

    /**
     * Lista de entidades a DTOs
     * @param entityList
     * @return list
     */
    private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }
    /**
     * Actualiza una calificacion con la informacion recibida
     * @param calificacionId
     * @param calificacion
     * @return calificacionDTO
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("calificacionId") Long calificacionId, CalificacionDTO calificacion, @PathParam("paseadoresId") Long paseadoresId) throws BusinessLogicException {

        calificacion.setId(paseadoresId);
        CalificacionEntity entity = calificacionLogic.getCalificacion(paseadoresId, calificacionId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/calificacions/" + calificacionId + " no existe.", 404);

        }
        return new CalificacionDTO(calificacionLogic.updateCalificacion(calificacionId, calificacion.toEntity()));
   }
    
}
