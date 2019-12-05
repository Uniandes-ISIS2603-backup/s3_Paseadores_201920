/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;



import co.edu.uniandes.csw.paseadores.dtos.PagoDTO;
import co.edu.uniandes.csw.paseadores.ejb.PagoLogic;
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
     * @param contratoId
     * @param idContrato
     * @return JSON {@link PagoDTO} - El pago guardado con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @POST
    public PagoDTO createPago(PagoDTO pago, @PathParam("contratoId") Long contratoId) throws BusinessLogicException {
        return new PagoDTO(pagoLogic.createPago(contratoId, pago.toEntity()));
    }
    
      /**
     * Borra el pago con el id asociado recibido en la URL.
     *
     * @param pagoId Identificador del pago que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @param contratoId
     * @param idContrato
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el pago a borrar.
     */
    @DELETE
    @Path("{pagoId: \\d+}")
    public void deletePago(@PathParam("pagoId") Long pagoId, @PathParam("contratoId") Long contratoId) throws BusinessLogicException {
    	
        if (pagoLogic.getPago(contratoId, pagoId) == null) {
            throw new WebApplicationException("El recurso o tupla /pagos/" + pagoId + " no esta en la base de datos.", 404);
        }
        
        pagoLogic.deletePago(pagoId, contratoId);
        
    }
    
    /**
     * Busca y devuelve el pago con el ID recibido en la URL, relativa a un
     * paseador.
     *
     * @param pagoId
     * @param contratoId
     * @return {@link PagoDTO} - El pago asociado a varias clases.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     */
    @GET
    @Path("{pagoId: \\d+}")
    public PagoDTO getPago(@PathParam("pagoId") Long pagoId, @PathParam("contratoId") Long contratoId) throws BusinessLogicException {
    	
        PagoEntity entity = pagoLogic.getPago(contratoId, pagoId);
        if (entity == null) {
            throw new WebApplicationException("El dato " + "/pagos/" + pagoId + " no se encuentra.", 404);
        }
        return new PagoDTO(entity);
    }
    
    
    /**
     * Busca y devuelve todas los pagos que existen
     * @return JSONArray {@link PagoDTO} - Los pagos encontrados. Si no hay nunguno retorna una lista vacía.
     */
    @GET
    public List<PagoDTO> getPagos() {

        return listEntity2DTO(pagoLogic.getPagos());
    }
    
    
    
    /**
     * Lista de entidades a DTO.
     * @return la lista de contratos en forma DTO (json)
     */
    private List<PagoDTO> listEntity2DTO(List<PagoEntity> entityList) {
        List<PagoDTO> list = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDTO(entity));
        }
        return list;
    }
    
    
    
     /**
     * Actualiza un contrato con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     * @param pagoId
     * @param pago
     * @param contratoId
     * @return 
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @PUT
    @Path("{pagoId: \\d+}")
    public PagoDTO updatePago(@PathParam("pagoId") Long pagoId, PagoDTO pago, @PathParam("contratoId") Long contratoId) throws BusinessLogicException {

        pago.setId(pagoId);
        PagoEntity entity = pagoLogic.getPago(contratoId,pagoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/pagos/" + pagoId + " no se encontro.", 404);

        }
        return new PagoDTO(pagoLogic.updatePago(contratoId, pago.toEntity()));

    }
 
    }
