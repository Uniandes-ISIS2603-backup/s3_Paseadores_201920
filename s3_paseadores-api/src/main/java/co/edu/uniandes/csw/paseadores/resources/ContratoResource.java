/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;


import co.edu.uniandes.csw.paseadores.dtos.ContratoDTO;
import co.edu.uniandes.csw.paseadores.ejb.ContratoLogic;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
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
@Path("contratos")
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
//    @DELETE
//    @Path("{contratoId: \\d+}")
//    public void deleteContrato(@PathParam("contratoId") Long contratoId) throws BusinessLogicException {
//    	
//        if (contratoLogic.getContrato(contratoId) == null) {
//            throw new WebApplicationException("El recurso /contratos/" + contratoId + " no existe.", 404);
//        }
//        
//        contratoLogic.deleteContrato(contratoId);
//        
//    }
    
    
    }
