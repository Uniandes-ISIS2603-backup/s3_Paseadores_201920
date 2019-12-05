/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;
import co.edu.uniandes.csw.paseadores.dtos.ComentarioDTO;
import co.edu.uniandes.csw.paseadores.ejb.ComentarioLogic;
import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Nicolas Potes Garcia
 * @version 1.0
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ComentarioResource {
    
    @Inject
    private ComentarioLogic comentarioLogic;
    
    
    /**
     * Crea un nuevo comentario con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param comentario {@link ComentarioDTO} - EL comentario que se desea guardar.
     * @param contratoId
     * @return JSON {@link ComentarioDTO} - El comentario guardado con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
     @POST
    public ComentarioDTO createComentario(ComentarioDTO comentario,@PathParam("contratoId") Long contratoId) throws BusinessLogicException {
        return new ComentarioDTO(comentarioLogic.createComentario(comentario.toEntity(), contratoId));
    }
    
    
    /**
     * Borra el comentario con el id asociado recibido en la URL.
     *
     * @param comentarioId Identificador del comentario que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @param paseadoresId
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     *
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el comentario a borrar.
     */
    @DELETE
    @Path("{comentarioId: \\d+}")
    public void deleteComentario(@PathParam("comentarioId") Long comentarioId, @PathParam("paseadoresId") Long paseadoresId) throws BusinessLogicException {
    	
        if (comentarioLogic.getComentario(comentarioId, paseadoresId) == null) {
            throw new WebApplicationException("El dato /paseador/" + paseadoresId + "/comentarios/" + comentarioId + " no esta disponible.", 404);
        }
        
        comentarioLogic.deleteComentario(comentarioId, paseadoresId);
        
    }
    
     /**
     * Busca y devuelve el comentario con el ID recibido en la URL, relativa a un
     * paseador.
     *
     * @param comentarioId
     * @param paseadoresId
     * @return {@link ComentarioDTO} - El comentario asociado a un paseador y contrato.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comentario.
     */
    @GET
    @Path("{comentarioId: \\d+}")
    public ComentarioDTO getComentario(@PathParam("comentarioId") Long comentarioId, @PathParam("paseadoresId") Long paseadoresId) throws BusinessLogicException {
    	
        ComentarioEntity entity = comentarioLogic.getComentario(comentarioId, paseadoresId);
        if (entity == null) {
            throw new WebApplicationException("El registro /paseador/" + paseadoresId + "/comentario/" + comentarioId + " no se encuentra.", 404);
        }
        return new ComentarioDTO(entity);
    }
    
    
    
    /**
     * Busca y devuelve todas los comentarios que existen asociados a un paseador
     * @param paseadorId El ID del paseador del cual se buscan los comentarios
     * @return JSONArray {@link ComentarioDTO} - Los comentarios encontrados en el
     * paseador. Si no hay nunguno retorna una lista vacía.
     */
    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("paseadorId") Long paseadorId) {

        return listEntity2DTO(comentarioLogic.getComentariosPorPaseador(paseadorId));
    }
    
    
    
    /**
     * Lista de entidades a DTO.
     * @return la lista de comentarios en forma DTO (json)
     */
    private List<ComentarioDTO> listEntity2DTO(List<ComentarioEntity> entityList) {
        List<ComentarioDTO> list = new ArrayList<>();
        for (ComentarioEntity entity : entityList) {
            list.add(new ComentarioDTO(entity));
        }
        return list;
    }
    
       /**
     * Actualiza un comentario con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     */
    @PUT
    @Path("{comentarioId: \\d+}")
    public ComentarioDTO updateComentario(@PathParam("paseadoresId") Long paseadoresId, @PathParam("comentarioId") Long comentarioId, ComentarioDTO comentario) throws BusinessLogicException {

        if (!comentarioId.equals(comentario.getId())) {
            throw new BusinessLogicException("Los ids del Comentario no coinciden.");
        }
        ComentarioEntity entity = comentarioLogic.getComentario(comentarioId, paseadoresId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /paseador/" + paseadoresId + "/coment/" + comentarioId + " no existe.", 404);

        }
        return new ComentarioDTO(comentarioLogic.updateComentario(comentarioId, comentario.toEntity()));

    }
    
}

