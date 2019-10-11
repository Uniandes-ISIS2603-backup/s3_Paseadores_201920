/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;

import co.edu.uniandes.csw.paseadores.dtos.FranjaHorariaDTO;
import co.edu.uniandes.csw.paseadores.ejb.FranjaHorariaLogic;
import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
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
 * @author Santiago Bolaños Vega
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class FranjaHorariaResource {
    
    @Inject
    private FranjaHorariaLogic franjaLogic;
    
    /**
     * Crea una franja horaria
     *
     * @param paseadoresId El ID del paseador del cual se le agrega la reseña
     * @param franja {@link FranjaHorariaDTO} - La franja que se desea guardar.
     * @return JSON {@link FranjaHorariaDTO} - La franja guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     */
    @POST
    public FranjaHorariaDTO createFranjaHoraria(@PathParam("paseadoresId") Long paseadoresId, FranjaHorariaDTO franja) throws BusinessLogicException {
       FranjaHorariaDTO nuevaFranjaDTO = new FranjaHorariaDTO(franjaLogic.createFranjaHoraria(paseadoresId, franja.toEntity()));
        return nuevaFranjaDTO;
    }
    
    /**
     * Busca y devuelve todas las franjas que existen en un libro.
     *
     * @param paseadoresId El ID del paseador del cual se buscan las franjas
     * @return JSONArray {@link FranjaHorariaDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<FranjaHorariaDTO> getFranjas(@PathParam("paseadoresId") Long paseadoresId) {
        List<FranjaHorariaDTO> listaDTOs = listEntity2DTO(franjaLogic.getFranjas(paseadoresId));
        return listaDTOs;
    }
    
    /**
     * Busca y devuelve la franja con el ID recibido en la URL, relativa a un
     * paseador.
     *
     * @param paseadoresId El ID del libro del cual se buscan las reseñas
     * @param franjasId El ID de la reseña que se busca
     * @return {@link FranjaHorariaDTO} - La franja encontradas en el paseador.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el paseador.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la franja.
     */
    @GET
    @Path("{franjasId: \\d+}")
    public FranjaHorariaDTO getFranjas(@PathParam("paseadoresId") Long paseadoresId, @PathParam("franjasId") Long franjasId) throws BusinessLogicException {
        FranjaHorariaEntity entity = franjaLogic.getFranja(paseadoresId, franjasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId+ "/franjas/" + franjasId + " no existe.", 404);
        }
        FranjaHorariaDTO franjaDTO = new FranjaHorariaDTO(entity);
        return franjaDTO;
    }
    
    /**
     * Actualiza una franja con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param paseadoresId El ID del paseador del cual se guarda la franja
     * @param franjasId El ID de la franja que se va a actualizar
     * @param franja {@link FranjaHorariaDTO} - La franja que se desea guardar.
     * @return JSON {@link FranjaHorariaDTO} - La franja actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la franja.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la franja.
     */
    @PUT
    @Path("{franjasId: \\d+}")
    public FranjaHorariaDTO updatFranja(@PathParam("paseadoresId") Long paseadoresId, @PathParam("franjasId") Long franjasId, FranjaHorariaDTO franja) throws BusinessLogicException {
        if (franjasId.equals(franja.getId())) {
            throw new BusinessLogicException("Los ids delas franjas no coinciden.");
        }
        FranjaHorariaEntity entity = franjaLogic.getFranja(paseadoresId, franjasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId + "/franjas/" + franjasId + " no existe.", 404);

        }
        FranjaHorariaDTO franjaDTO = new FranjaHorariaDTO(franjaLogic.updateFranja(paseadoresId, franja.toEntity()));
        return franjaDTO;

    }
    
    /**
     * Borra la franja con el id asociado recibido en la URL.
     *
     * @param paseadoresId El ID del paseador del cual se va a eliminar la franja.
     * @param franjasId El ID de la franja que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la franja.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la franja.
     */
    @DELETE
    @Path("{franjasId: \\d+}")
    public void deleteFranja(@PathParam("paseadoresId") Long paseadoresId, @PathParam("franjasId") Long franjasId) throws BusinessLogicException {
        FranjaHorariaEntity entity = franjaLogic.getFranja(paseadoresId, franjasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId + "/franjas/" + franjasId + " no existe.", 404);
        }
        franjaLogic.deleteFranja(paseadoresId, franjasId);
    }
    
    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos FranjaHorariaEntity a una lista de
     * objetos FranjaHorariaDTO (json)
     *
     * @param entityList corresponde a la lista de franjas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de franjas en forma DTO (json)
     */
    private List<FranjaHorariaDTO> listEntity2DTO(List<FranjaHorariaEntity> entityList) {
        List<FranjaHorariaDTO> list = new ArrayList<FranjaHorariaDTO>();
        for (FranjaHorariaEntity entity : entityList) {
            list.add(new FranjaHorariaDTO(entity));
        }
        return list;
    }
    
}
