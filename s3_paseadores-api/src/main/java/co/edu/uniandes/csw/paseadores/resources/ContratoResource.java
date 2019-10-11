/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;


import co.edu.uniandes.csw.paseadores.dtos.ComentarioDTO;
import co.edu.uniandes.csw.paseadores.dtos.ContratoDTO;
import co.edu.uniandes.csw.paseadores.ejb.ContratoLogic;
import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
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
 * @author Nicolas Potes Garcia
 * @version 1.0
 */
@Path("/contratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ContratoResource {
    
    @Inject
    private ContratoLogic contratoLogic;
    
    
    /**
     * Crea un nuevo contrato con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param contrato {@link ContratoDTO} - EL contrato que se desea guardar.
     * @return JSON {@link ContratoDTO} - El contrato guardado con el atributo id
     * autogenerado.
     */
    @POST
    public ContratoDTO createContrato(ContratoDTO contrato) throws BusinessLogicException {
        ContratoDTO contratoDTO = new ContratoDTO(contratoLogic.createContrato(contrato.toEntity()));
        return contratoDTO;
    }
    
      /**
     * Borra el contrato con el id asociado recibido en la URL.
     *
     * @param contratoId Identificador del contrato que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el contrato a borrar.
     */
    @DELETE
    @Path("{contratoId: \\d+}")
    public void deletContrato(@PathParam("contratoId") Long contratoId) throws BusinessLogicException {
    	
        if (contratoLogic.getContrato(contratoId) == null) {
            throw new WebApplicationException("El recurso /contratos/" + contratoId + " no existe.", 404);
        }
        
        contratoLogic.deleteContrato(contratoId);
        
    }
    
    /**
     * Busca y devuelve el contrato con el ID recibido en la URL, relativa a un
     * paseador.
     *
     * @return {@link ContratoDTO} - El contrato asociado a varias clases.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el contrato.
     */
    @GET
    @Path("{contratoId: \\d+}")
    public ContratoDTO getContrato(@PathParam("contratoId") Long contratoId) throws BusinessLogicException {
    	
        ContratoEntity entity = contratoLogic.getContrato(contratoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/contratos/" + contratoId + " no existe.", 404);
        }
        ContratoDTO contratoDTO = new ContratoDTO(entity);
        return contratoDTO;
    }
    
    
    /**
     * Busca y devuelve todas los contratos que existen
     * @param paseadorId El ID del paseador del cual se buscan los comentarios
     * @return JSONArray {@link ContratoDTO} - Los contratos encontrados en el
     * paseador. Si no hay nunguno retorna una lista vacía.
     */
    @GET
    public List<ContratoDTO> getContratos() {

        List<ContratoDTO> listaDTOs = listEntity2DTO(contratoLogic.getContratos());
        return listaDTOs;
    }
    
    
    
    /**
     * Lista de entidades a DTO.
     * @return la lista de contratos en forma DTO (json)
     */
    private List<ContratoDTO> listEntity2DTO(List<ContratoEntity> entityList) {
        List<ContratoDTO> list = new ArrayList<ContratoDTO>();
        for (ContratoEntity entity : entityList) {
            list.add(new ContratoDTO(entity));
        }
        return list;
    }
    
    
    
     /**
     * Actualiza un contrato con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     */
    @PUT
    @Path("{contratoId: \\d+}")
    public ContratoDTO updateContrato(@PathParam("contratoId") Long contratoId, ContratoDTO contrato) throws BusinessLogicException {

        if (contratoId == (contrato.toEntity().getId())) {
            throw new BusinessLogicException("Los ids del Contrato no coinciden.");
        }
        ContratoEntity entity = contratoLogic.getContrato(contratoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/contratos/" + contratoId + " no existe.", 404);

        }
        ContratoDTO contratoDTO = new ContratoDTO(contratoLogic.updateContrato(contratoId, contrato.toEntity()));
        return contratoDTO;

    }
    
    
    @Path("{contratoId: \\d+}/comentarios")
    public Class<ComentarioResource> getComentarioResource(@PathParam("contratoId") Long contratoId) {
        if (contratoLogic.getContrato(contratoId) == null) {
            throw new WebApplicationException("El recurso /contratos/" + contratoId + " no existe.", 404);
        }
        return ComentarioResource.class;
    }
    
    
    
    }
