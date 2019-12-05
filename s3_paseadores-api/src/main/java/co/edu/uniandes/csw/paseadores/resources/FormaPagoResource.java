/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;


import co.edu.uniandes.csw.paseadores.dtos.FormaPagoDTO;
import co.edu.uniandes.csw.paseadores.dtos.PagoDTO;
import co.edu.uniandes.csw.paseadores.ejb.FormaPagoLogic;
import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
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
public class FormaPagoResource {
    
    @Inject
    private FormaPagoLogic formaPagoLogic;
    
    
    /**
     * Crea una nueva forma de pago con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto idéntico con un id auto-generado por la
     * base de datos.
     *
     * @param formaPago {@link PagoDTO} - EL pago que se desea guardar.
     * @param clientesId - cliente
     * @return JSON {@link FormaPagoDTO} - El pago guardado con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    @POST
    public FormaPagoDTO createFormaPago(FormaPagoDTO formaPago, @PathParam("clientesId") Long clientesId) throws BusinessLogicException {
        return new FormaPagoDTO(formaPagoLogic.createFormaPago(clientesId, formaPago.toEntity()));
    }
    
      /**
     * Borra la forma de pago con el id asociado recibido en la URL.
     *
     * @param formaPagoId Identificador del pago que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @param clientesId
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el pago a borrar.
     */
    @DELETE
    @Path("{formaPagoId: \\d+}")
    public void deletePago(@PathParam("formaPagoId") Long formaPagoId, @PathParam("clientesId") Long clientesId) throws BusinessLogicException 
    {
        if (formaPagoLogic.getFormaPagoPorCliente(clientesId, formaPagoId) == null) {
            throw new WebApplicationException("El dato /formasPago/" + formaPagoId + " no se encontró.", 404);
        }
        
        formaPagoLogic.deleteFormaPago(clientesId, formaPagoId);
        
    }
    
    /**
     * Busca y devuelve la forma de pago con el ID recibido en la URL, relativa a un
     * paseador.
     *
     * @param formaPagoId
     * @param clientesId
     * @return {@link PagoDTO} - La forma de pago asociada a varias clases.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     */
    @GET
    @Path("{formaPagoId: \\d+}")
    public FormaPagoDTO getFormaPago(@PathParam("formaPagoId") Long formaPagoId, @PathParam("clientesId") Long clientesId) throws BusinessLogicException {
    	
        FormaPagoEntity entity = formaPagoLogic.getFormaPagoPorCliente(clientesId, formaPagoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso o la tupla " + "/formasPago/" + formaPagoId + " no se encuentra.", 404);
        }
        return new FormaPagoDTO(entity);
    }
    
    
    /**
     * Busca y devuelve todas las formas de pago que existen
     * @param formaPagoId El ID del paseador del cual se buscan los comentarios
     * @return JSONArray {@link FormaPagoDTO} - Los pagos encontrados en el
     * paseador. Si no hay nunguno retorna una lista vacía.
     */
    @GET
    public List<FormaPagoDTO> getFormasPago() {

        return listEntity2DTO(formaPagoLogic.getFormasPago());
    }
    
    
    
    /**
     * Lista de entidades a DTO.
     * @return la lista de contratos en forma DTO (json)
     */
    private List<FormaPagoDTO> listEntity2DTO(List<FormaPagoEntity> entityList) {
        List<FormaPagoDTO> list = new ArrayList<>();
        for (FormaPagoEntity entity : entityList) {
            list.add(new FormaPagoDTO(entity));
        }
        return list;
    }
    
    
    
     /**
     * Actualiza un contrato con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     */
    @PUT
    @Path("{formaPagoId: \\d+}")
    public FormaPagoDTO updateFormaPago(@PathParam("formaPagoId") Long formaPagoId, FormaPagoDTO formaPago, @PathParam("clientesId") Long clientesId) throws BusinessLogicException {

        if (formaPagoId.equals(formaPago.getId())) {
            throw new BusinessLogicException("Los Id´s de la forma de pago no coinciden.");
        }
        FormaPagoEntity entity = formaPagoLogic.getFormaPagoPorCliente(clientesId, formaPagoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso " + "/formasPago/" + formaPagoId + " no existe.", 404);

        }
        return new FormaPagoDTO(formaPagoLogic.updateFormaPago(formaPagoId, formaPago.toEntity()));

    }
 
    }

