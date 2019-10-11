/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;


import co.edu.uniandes.csw.paseadores.dtos.ComentarioDTO;
import co.edu.uniandes.csw.paseadores.dtos.ContratoDTO;
import co.edu.uniandes.csw.paseadores.dtos.PagoDTO;
import co.edu.uniandes.csw.paseadores.ejb.ContratoLogic;
import co.edu.uniandes.csw.paseadores.ejb.PagoLogic;
import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
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
 * @author Mario Hurtado
 * @version 1.0
 */
@Path("pagos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PagoResource {
    
    @Inject
    private PagoLogic pagoLogic;
    
    
    /**
     * Crea un nuevo pago con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto idéntico con un id auto-generado por la
     * base de datos.
     *
     * @param pago {@link PagoDTO} - EL pago que se desea guardar.
     * @return JSON {@link PagoDTO} - El pago guardado con el atributo id
     * autogenerado.
     */
    @POST
    public PagoDTO createPago(PagoDTO pago) throws BusinessLogicException {
        PagoDTO pagoDTO = new PagoDTO(pagoLogic.createPago(pago.toEntity()));
        return pagoDTO;
    }
    
      /**
     * Borra el pago con el id asociado recibido en la URL.
     *
     * @param pagoId Identificador del pago que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el pago a borrar.
     */
    @DELETE
    @Path("{pagoId: \\d+}")
    public void deletePago(@PathParam("pagoId") Long pagoId) throws BusinessLogicException {
    	
        if (pagoLogic.getPago(pagoId) == null) {
            throw new WebApplicationException("El recurso /pagos/" + pagoId + " no existe.", 404);
        }
        
        pagoLogic.deletePago(pagoId);
        
    }
    
    /**
     * Busca y devuelve el pago con el ID recibido en la URL, relativa a un
     * paseador.
     *
     * @return {@link PagoDTO} - El pago asociado a varias clases.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     */
    @GET
    @Path("{pagoId: \\d+}")
    public PagoDTO getPago(@PathParam("pagoId") Long pagoId) throws BusinessLogicException {
    	
        PagoEntity entity = pagoLogic.getPago(pagoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/pagos/" + pagoId + " no existe.", 404);
        }
        PagoDTO pagoDTO = new PagoDTO(entity);
        return pagoDTO;
    }
    
    
    /**
     * Busca y devuelve todas los pagos que existen
     * @param pagoId El ID del paseador del cual se buscan los comentarios
     * @return JSONArray {@link PagoDTO} - Los pagos encontrados. Si no hay nunguno retorna una lista vacía.
     */
    @GET
    public List<PagoDTO> getPagos() {

        List<PagoDTO> listaDTOs = listEntity2DTO(pagoLogic.getPagos());
        return listaDTOs;
    }
    
    
    
    /**
     * Lista de entidades a DTO.
     * @return la lista de contratos en forma DTO (json)
     */
    private List<PagoDTO> listEntity2DTO(List<PagoEntity> entityList) {
        List<PagoDTO> list = new ArrayList<PagoDTO>();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDTO(entity));
        }
        return list;
    }
    
    
    
     /**
     * Actualiza un contrato con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     */
    @PUT
    @Path("{pagoId: \\d+}")
    public PagoDTO updatePago(@PathParam("pagoId") Long pagoId, PagoDTO pago) throws BusinessLogicException {

        if (pagoId.equals(pago.getId())) {
            throw new BusinessLogicException("Los Id´s del pago no coinciden.");
        }
        PagoEntity entity = pagoLogic.getPago(pagoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/pagos/" + pagoId + " no existe.", 404);

        }
        PagoDTO pagoDTO = new PagoDTO(pagoLogic.updatePago(pagoId, pago.toEntity()));
        return pagoDTO;

    }
 
    }
