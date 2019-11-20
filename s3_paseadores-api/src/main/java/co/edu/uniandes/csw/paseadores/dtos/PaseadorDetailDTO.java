package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO detallado del paseador.
 *
 * @author Daniel Garcia
 */
public class PaseadorDetailDTO extends PaseadorDTO implements Serializable {

    /**
     * Atributos.
     */

    /**
     * Caliicaciones.
     */
    private List<CalificacionDTO> calificaciones;

    /**
     * Comentarios.
     */
    private List<ComentarioDTO> comentarios;

    /**
     * Contratos.
     */
    private List<ContratoDTO> contratos;

    /**
     * Franjas ofrecidas.
     */
    private List<FranjaHorariaDTO> franjas;

    /**
     * Zonas de servicio.
     */
    private List<ZonaDTO> zonas;

    /**
     * Constructor por defecto.
     */
    public PaseadorDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param paseadorEntity La entidad de la cual se construye el DTO
     */
    public PaseadorDetailDTO(PaseadorEntity paseadorEntity) {
        super(paseadorEntity);
        if (paseadorEntity.getCalificaciones() != null) {
            calificaciones = new ArrayList<>();
            for (CalificacionEntity calificacion : paseadorEntity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(calificacion));
            }
        }
        if (paseadorEntity.getComentarios() != null) {
            comentarios = new ArrayList<>();
            for (ComentarioEntity comentario : paseadorEntity.getComentarios()) {
                comentarios.add(new ComentarioDTO(comentario));
            }
        }
        if (paseadorEntity.getContratos() != null) {
            contratos = new ArrayList<>();
            for (ContratoEntity contrato : paseadorEntity.getContratos()) {
                contratos.add(new ContratoDTO(contrato));
            }
        }
        if (paseadorEntity.getFranjas() != null) {
            franjas = new ArrayList<>();
            for (FranjaHorariaEntity franja : paseadorEntity.getFranjas()) {
                franjas.add(new FranjaHorariaDTO(franja));
            }
        }
        if (paseadorEntity.getZonas() != null) {
            zonas = new ArrayList<>();
            for (ZonaEntity zona : paseadorEntity.getZonas()) {
                zonas.add(new ZonaDTO(zona));
            }
        }
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public PaseadorEntity toEntity() {
        PaseadorEntity paseadorEntity = super.toEntity();
        if (calificaciones != null) {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO dtoCalificacion : getCalificaciones()) {
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            paseadorEntity.setCalificaciones(calificacionesEntity);
        }
        if (comentarios != null) {
            List<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for (ComentarioDTO dtoComentario : getComentarios()) {
                comentariosEntity.add(dtoComentario.toEntity());
            }
            paseadorEntity.setComentarios(comentariosEntity);
        }
        if (contratos != null) {
            List<ContratoEntity> contratosEntity = new ArrayList<>();
            for (ContratoDTO dtoContrato : getContratos()) {
                contratosEntity.add(dtoContrato.toEntity());
            }
            paseadorEntity.setContratos(contratosEntity);
        }
        if (franjas != null) {
            List<FranjaHorariaEntity> franjasEntity = new ArrayList<>();
            for (FranjaHorariaDTO dtoFranja : getFranjas()) {
                franjasEntity.add(dtoFranja.toEntity());
            }
            paseadorEntity.setFranjas(franjasEntity);
        }
        if (zonas != null) {
            List<ZonaEntity> zonasEntity = new ArrayList<>();
            for (ZonaDTO dtoZona : getZonas()) {
                zonasEntity.add(dtoZona.toEntity());
            }
            paseadorEntity.setZonas(zonasEntity);
        }
        return paseadorEntity;
    }

    /**
     * Retorna las calificaciones.
     *
     * @return calificaciones.
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Asigna las calificaciones.
     *
     * @param calificaciones
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * Retorna los comentarios.
     *
     * @return comentarios.
     */
    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    /**
     * Modifica los comentarios
     *
     * @param comentarios
     */
    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Retorna los contratos.
     *
     * @return contratos.
     */
    public List<ContratoDTO> getContratos() {
        return contratos;
    }

    /**
     * Modifica los contratos.
     *
     * @param contratos
     */
    public void setContratos(List<ContratoDTO> contratos) {
        this.contratos = contratos;
    }

    /**
     * Retorna las franjas.
     *
     * @return franjas.
     */
    public List<FranjaHorariaDTO> getFranjas() {
        return franjas;
    }

    /**
     * Modifica las franjas.
     *
     * @param franjas
     */
    public void setFranjas(List<FranjaHorariaDTO> franjas) {
        this.franjas = franjas;
    }

    /**
     * Retorna las zonas.
     *
     * @return zonas.
     */
    public List<ZonaDTO> getZonas() {
        return zonas;
    }

    /**
     * Modifica las zonas.
     *
     * @param zonas
     */
    public void setZonas(List<ZonaDTO> zonas) {
        this.zonas = zonas;
    }
}
