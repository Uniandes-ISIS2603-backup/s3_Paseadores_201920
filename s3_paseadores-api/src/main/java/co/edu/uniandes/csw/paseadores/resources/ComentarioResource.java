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
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
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
@Path("comentarios")
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
    public ComentarioDTO createComentario(ComentarioDTO comentario) throws BusinessLogicException {
        ComentarioDTO comentarioDTO = new ComentarioDTO(comentarioLogic.createComentario(comentario.toEntity()));
        return comentarioDTO;
    }
    
    
    /**
     * Borra el comentario con el id asociado recibido en la URL.
     *
     * @param contratoId Identificador del comentario que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el comentario a borrar.
     */
    @DELETE
    @Path("{comentarioId: \\d+}")
    public void deleteComentario(@PathParam("comentarioId") Long comentarioId) throws BusinessLogicException {
    	
        if (comentarioLogic.getComentario(comentarioId) == null) {
            throw new WebApplicationException("El recurso /comentarios/" + comentarioId + " no existe.", 404);
        }
        
        comentarioLogic.deleteComentario(comentarioId);
        
    }
    
    
    
}