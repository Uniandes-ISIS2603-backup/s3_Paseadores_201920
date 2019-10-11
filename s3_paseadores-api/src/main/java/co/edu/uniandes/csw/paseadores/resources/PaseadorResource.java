/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.resources;

import co.edu.uniandes.csw.paseadores.dtos.PaseadorDTO;
import co.edu.uniandes.csw.paseadores.dtos.PaseadorDetailDTO;
import co.edu.uniandes.csw.paseadores.ejb.PaseadorLogic;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
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
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Daniel Garcia
 */
@Path("/paseadores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PaseadorResource 
{
    private static final Logger LOGGER = Logger.getLogger(PaseadorResource.class.getName());

    @Inject
    private PaseadorLogic paseadorLogic;

    /**
     * Crea un nuevo autor con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param paseador {@link PaseadorDTO} - EL paseador que se desea guardar.
     * @return JSON {@link PaseadorDTO} - El paseador guardado con el atributo id
     * autogenerado.
     */
    @POST
    public PaseadorDTO createPaseador(PaseadorDTO paseador) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "AuthorResource createPaseador: input: {0}", paseador);
        PaseadorDTO paseadorDTO = new PaseadorDTO(paseadorLogic.createPaseador(paseador.toEntity()));;
        LOGGER.log(Level.INFO, "AuthorResource createPaseador: output: {0}", paseadorDTO);
        return paseadorDTO;
    }
     /**
     * Busca y devuelve todos los paseadores que existen en la aplicacion.
     *
     * @return JSONArray {@link PaseadorDetailDTO} - Los paseadores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PaseadorDetailDTO> getPaseadores() 
    {
        LOGGER.info("PaseadorResource getPaseadores: input: void");
        List<PaseadorDetailDTO> listaPaseadores = listEntity2DTO(paseadorLogic.getPaseadores());
        LOGGER.log(Level.INFO, "PaseadorResource getPaseadores: output: {0}", listaPaseadores);
        return listaPaseadores;
    }
    /**
     * Busca el paseador con el id asociado recibido en la URL y lo devuelve.
     *
     * @param paseadoresId Identificador del paseador que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link PaseadorDetailDTO} - El paseador buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{paseadoresId: \\d+}")
    public PaseadorDetailDTO getPaseador(@PathParam("paseadoresId") Long paseadoresId) 
    {
        LOGGER.log(Level.INFO, "PaseadorResource getPaseador: input: {0}", paseadoresId);
        PaseadorEntity paseadorEntity = paseadorLogic.getPaseador(paseadoresId);
        if (paseadorEntity == null) {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId + " no existe.", 404);
        }
        PaseadorDetailDTO detailDTO = new PaseadorDetailDTO(paseadorEntity);
        LOGGER.log(Level.INFO, "PaseadorResource getPaseador: output: {0}", detailDTO);
        return detailDTO;
    }
    /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param paseadoresId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param paseador {@link AuthorDetailDTO} El autor que se desea guardar.
     * @return JSON {@link AuthorDetailDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{paseadoresId: \\d+}")
    public PaseadorDetailDTO updateAuthor(@PathParam("paseadoresId") Long paseadoresId, PaseadorDetailDTO paseador) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "PaseadorResource updatePaseador: input: paseadoresId: {0} , paseador: {1}", new Object[]{paseadoresId, paseador});
        paseador.setId(paseadoresId);
        if (paseadorLogic.getPaseador(paseadoresId) == null) {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId + " no existe.", 404);
        }
        PaseadorDetailDTO detailDTO = new PaseadorDetailDTO(paseadorLogic.updatePaseador(paseadoresId, paseador.toEntity()));
        LOGGER.log(Level.INFO, "PaseadorResource updatePaseador: output: {0}", detailDTO);
        return detailDTO;
    }
     /**
     * Borra el autor con el id asociado recibido en la URL.
     *
     * @param paseadoresId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{paseadoresId: \\d+}")
    public void deletePaseador(@PathParam("paseadoresId") Long paseadoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PaseadorResource deletePaseador: input: {0}", paseadoresId);
        if (paseadorLogic.getPaseador(paseadoresId) == null) {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId + " no existe.", 404);
        }
        paseadorLogic.deletePaseador(paseadoresId);
        LOGGER.info("PaseadorResource deletePaseador: output: void");
    }
    /**
     * Conexión con el servicio de calificaciones para un paseador.
     * {@link CalificacionResource}
     *
     * Este método conecta la ruta de /paseadores con las rutas de /calificaciones que
     * dependen del paseador, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las calificaciones.
     *
     * @param paseadoresId El ID del paseador con respecto al cual se accede al
     * servicio.
     * @return El servicio de calificaciones para ese paseador en particular.
     */
    @Path("{paseadoresId: \\d+}/calificaciones")
    public Class<CalificacionResource> getCalificacionResource(@PathParam("paseadoresId") Long paseadoresId) 
    {
        if (paseadorLogic.getPaseador(paseadoresId) == null) 
        {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId + " no existe.", 404);
        }
        return CalificacionResource.class;
    }
    /**
     * Conexión con el servicio de comentarios para un paseador.
     * {@link ComentarioResource}
     *
     * Este método conecta la ruta de /paseadores con las rutas de /comentarios que
     * dependen del paseador, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las comentarios.
     *
     * @param paseadoresId El ID del paseador con respecto al cual se accede al
     * servicio.
     * @return El servicio de comentarios para ese paseador en particular.
     */
    @Path("{paseadoresId: \\d+}/comentarios")
    public Class<ComentarioResource> getComentarioResource(@PathParam("paseadoresId") Long paseadoresId) 
    {
        if (paseadorLogic.getPaseador(paseadoresId) == null) 
        {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId + " no existe.", 404);
        }
        return ComentarioResource.class;
    }
     /**
     * Conexión con el servicio de contratos para un paseador.
     * {@link ContratoResource}
     *
     * Este método conecta la ruta de /paseadores con las rutas de /contratos que
     * dependen del paseador, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las contratos.
     *
     * @param paseadoresId El ID del paseador con respecto al cual se accede al
     * servicio.
     * @return El servicio de contratos para ese paseador en particular.
     */
    @Path("{paseadoresId: \\d+}/contratos")
    public Class<ContratoResource> getContratoResource(@PathParam("paseadoresId") Long paseadoresId) 
    {
        if (paseadorLogic.getPaseador(paseadoresId) == null) 
        {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId + " no existe.", 404);
        }
        return ContratoResource.class;
    }
     /**
     * Conexión con el servicio de franjas para un paseador.
     * {@link FranjaHorariaResource}
     *
     * Este método conecta la ruta de /paseadores con las rutas de /franjas que
     * dependen del paseador, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las franjas.
     *
     * @param paseadoresId El ID del paseador con respecto al cual se accede al
     * servicio.
     * @return El servicio de franjas para ese paseador en particular.
     */
    @Path("{paseadoresId: \\d+}/franjas")
    public Class<FranjaHorariaResource> getFranjaResource(@PathParam("paseadoresId") Long paseadoresId) 
    {
        if (paseadorLogic.getPaseador(paseadoresId) == null) 
        {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId + " no existe.", 404);
        }
        return FranjaHorariaResource.class;
    }
     /**
     * Conexión con el servicio de zonas para un paseador.
     * {@link ZonaResource}
     *
     * Este método conecta la ruta de /paseadores con las rutas de /zonas que
     * dependen del paseador, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las zonas.
     *
     * @param paseadoresId El ID del paseador con respecto al cual se accede al
     * servicio.
     * @return El servicio de zonas para ese paseador en particular.
     */
    @Path("{paseadoresId: \\d+}/zonas")
    public Class<ZonaResource> getZonaResource(@PathParam("paseadoresId") Long paseadoresId) 
    {
        if (paseadorLogic.getPaseador(paseadoresId) == null) 
        {
            throw new WebApplicationException("El recurso /paseadores/" + paseadoresId + " no existe.", 404);
        }
        return ZonaResource.class;
    }
    /**
     * Convierte una lista de PaseadorEntity a una lista de PaseadorDetailDTO.
     *
     * @param entityList Lista de PaseadorEntity a convertir.
     * @return Lista de PaseadorDetailDTO convertida.
     */
    private List<PaseadorDetailDTO> listEntity2DTO(List<PaseadorEntity> entityList) 
    {
        List<PaseadorDetailDTO> list = new ArrayList<>();
        for (PaseadorEntity entity : entityList) {
            list.add(new PaseadorDetailDTO(entity));
        }
        return list;
    }
}
