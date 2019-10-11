/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;
import co.edu.uniandes.csw.paseadores.dtos.ComentarioDTO;
import co.edu.uniandes.csw.paseadores.dtos.ContratoDTO;
import co.edu.uniandes.csw.paseadores.ejb.ComentarioLogic;
import co.edu.uniandes.csw.paseadores.ejb.ContratoLogic;
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
//import static javax.ws.rs.HttpMethod.POST;
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
@Path("/comentarios")
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
     * @return JSON {@link ComentarioDTO} - El comentario guardado con el atributo id
     * autogenerado.
     */
     @POST
    public ComentarioDTO createComentario(ComentarioDTO comentario,@PathParam("contratoId") Long contratoId) throws BusinessLogicException {
        ComentarioDTO comentarioDTO = new ComentarioDTO(comentarioLogic.createComentario(comentario.toEntity(), contratoId));
        return comentarioDTO;
    }
    
    
    /**
     * Borra el comentario con el id asociado recibido en la URL.
     *
     * @param comentarioId Identificador del comentario que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @param contratoId Identificador del contrato que tiene asociado el comentario.
     *
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el comentario a borrar.
     */
    @DELETE
    @Path("{comentarioId: \\d+}")
    public void deleteComentario(@PathParam("comentarioId") Long comentarioId, @PathParam("contratoId") Long contratoId) throws BusinessLogicException {
    	
        if (comentarioLogic.getComentario(comentarioId, contratoId) == null) {
            throw new WebApplicationException("El recurso /contratos/" + contratoId + "/comentarios/" + comentarioId + " no existe.", 404);
        }
        
        comentarioLogic.deleteComentario(comentarioId, contratoId);
        
    }
    
     /**
     * Busca y devuelve el comentario con el ID recibido en la URL, relativa a un
     * paseador.
     *
     * @return {@link ComentarioDTO} - El comentario asociado a un paseador y contrato.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comentario.
     */
    @GET
    @Path("{comentarioId: \\d+}")
    public ComentarioDTO getComentario(@PathParam("comentarioId") Long comentarioId, @PathParam("contratoId") Long contratoId) throws BusinessLogicException {
    	
        ComentarioEntity entity = comentarioLogic.getComentario(comentarioId, contratoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /contratos/" + contratoId + "/comentarios/" + comentarioId + " no existe.", 404);
        }
        ComentarioDTO comentarioDTO = new ComentarioDTO(entity);
        return comentarioDTO;
    }
    
    
    
    /**
     * Busca y devuelve todas los comentarios que existen asociados a un paseador
     * @param paseadorId El ID del paseador del cual se buscan los comentarios
     * @return JSONArray {@link ComentarioDTO} - Los comentarios encontrados en el
     * paseador. Si no hay nunguno retorna una lista vacía.
     */
    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("paseadorId") Long paseadorId) {

        List<ComentarioDTO> listaDTOs = listEntity2DTO(comentarioLogic.getComentarios(paseadorId));
        return listaDTOs;
    }
    
    
    
    /**
     * Lista de entidades a DTO.
     * @return la lista de comentarios en forma DTO (json)
     */
    private List<ComentarioDTO> listEntity2DTO(List<ComentarioEntity> entityList) {
        List<ComentarioDTO> list = new ArrayList<ComentarioDTO>();
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
    public ComentarioDTO updateComentario(@PathParam("contratoId") Long contratoId, @PathParam("comentarioId") Long comentarioId, ComentarioDTO comentario) throws BusinessLogicException {

        if (comentarioId.equals(comentario.getId())) {
            throw new BusinessLogicException("Los ids del Comentario no coinciden.");
        }
        ComentarioEntity entity = comentarioLogic.getComentario(comentarioId, contratoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /contratos/" + contratoId + "/comentarios/" + comentarioId + " no existe.", 404);

        }
        ComentarioDTO comentarioDTO = new ComentarioDTO(comentarioLogic.updateComentario(comentarioId, comentario.toEntity()));
        return comentarioDTO;

    }
    
}

