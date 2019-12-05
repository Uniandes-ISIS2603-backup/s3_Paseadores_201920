/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;

import co.edu.uniandes.csw.paseadores.dtos.MascotaDTO;
import co.edu.uniandes.csw.paseadores.ejb.MascotaLogic;
import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Daniel Garcia
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class MascotaResource {

    private static final Logger LOGGER = Logger.getLogger(MascotaResource.class.getName());

    @Inject
    private MascotaLogic mascotaLogic;

    /**
     * Crea una nueva mascota con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param mascota {@link MascotaDTO} - EL mascota que se desea guardar.
     * @param clienteId id del cliente
     * @return JSON {@link MascotaDTO} - El mascota guardado con el atributo id
     * autogenerado.
     */
    @POST
    public MascotaDTO createMascota(MascotaDTO mascota, @PathParam("clientesId") Long clienteId) throws BusinessLogicException {
        MascotaEntity entidad = mascotaLogic.createMascota(clienteId, mascota.toEntity());
        if (entidad == null) {
        }
        MascotaDTO mascotaDTO = new MascotaDTO(entidad);
        return mascotaDTO;
    }

    /**
     * Busca y devuelve todas las mascotas que existen en la aplicacion.
     *
     * @param clienteId id del cliente
     * @return JSONArray {@link MascotaDetailDTO} - Los mascotaes encontrados en
     * la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<MascotaDTO> getMascotas(@PathParam("clientesId") Long clienteId) {
        LOGGER.info("MascotaResource getMascotas: input: void");
        List<MascotaDTO> listaMascotas = listEntity2DTO(mascotaLogic.getMascotas(clienteId));
        LOGGER.log(Level.INFO, "MascotaResource getMascotas: output: {0}", listaMascotas);
        return listaMascotas;
    }

    /**
     * Busca el mascota con el id asociado recibido en la URL y lo devuelve.
     *
     * @param clienteId id del cliente
     * @param mascotasId id de la mascota
     * @return JSON {@link MascotaDetailDTO} - El mascota buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{mascotasId: \\d+}")
    public MascotaDTO getMascota(@PathParam("clientesId") Long clienteId, @PathParam("mascotasId") Long mascotasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MascotaResource getMascota: input: {0}", mascotasId);
        MascotaEntity mascotaEntity = mascotaLogic.getMascota(clienteId, mascotasId);
        if (mascotaEntity == null) {
            throw new WebApplicationException("El recurso /mascotas/" + mascotasId + " no existe.", 404);
        }
        MascotaDTO detailDTO = new MascotaDTO(mascotaEntity);
        LOGGER.log(Level.INFO, "MascotaResource getMascota: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param clienteId id del cliente
     * @param mascotasId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param mascota {@link AuthorDetailDTO} El autor que se desea guardar.
     * @return JSON {@link AuthorDetailDTO} - El autor guardado.
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{mascotasId: \\d+}")
    public MascotaDTO updateMascota(@PathParam("clientesId") Long clienteId, @PathParam("mascotasId") Long mascotasId, MascotaDTO mascota) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MascotaResource updateMascota: input: mascotaesId: {0} , mascota: {1}", new Object[]{mascotasId, mascota});
        if (mascotasId.equals(mascota.getId())) {
            throw new BusinessLogicException("Los ids del Comentario no coinciden.");
        }
        MascotaEntity entity = mascotaLogic.getMascota(clienteId, mascotasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /clientes/" + clienteId + "/comentarios/" + mascotasId + " no existe.", 404);

        }
        MascotaDTO mascotaDTO = new MascotaDTO(mascotaLogic.updateMascota(mascotasId, mascota.toEntity()));
        LOGGER.log(Level.INFO, "MascotaResource updateMascota: output: {0}", mascotaDTO);
        return mascotaDTO;
    }

    /**
     * Borra el autor con el id asociado recibido en la URL.
     *
     * @param mascotasId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @param clienteId id del cliente
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{mascotasId: \\d+}")
    public void deleteMascota(@PathParam("mascotasId") Long mascotasId, @PathParam("clientesId") Long clienteId) throws BusinessLogicException {
        if (mascotaLogic.getMascota(clienteId, mascotasId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clienteId + "/mascotas/" + mascotasId + " no existe.", 404);
        }
        mascotaLogic.deleteMascota(clienteId, mascotasId);
    }

    /**
     * Convierte una lista de MascotaEntity a una lista de MascotaDetailDTO.
     *
     * @param entityList Lista de MascotaEntity a convertir.
     * @return Lista de MascotaDetailDTO convertida.
     */
    private List<MascotaDTO> listEntity2DTO(List<MascotaEntity> entityList) {
        List<MascotaDTO> list = new ArrayList<>();
        for (MascotaEntity entity : entityList) {
            list.add(new MascotaDTO(entity));
        }
        return list;
    }
}
