package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import java.io.Serializable;

/**
 * Clase que modela el DTO para Calificacion
 *
 * @author Juan Esteban Vergara
 */
public class CalificacionDTO implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Calificación.
     */
    private Integer calificacion;

    /**
     * Id.
     */
    private Long id;

    /**
     * Constructor por defecto.
     */
    public CalificacionDTO() {

    }

    /**
     * Constructor a partir de una Entidad.
     *
     * @param calificacion Entidad.
     */
    public CalificacionDTO(CalificacionEntity calificacion) {
        this.calificacion = calificacion.getCalificacion();
        this.id = calificacion.getId();
    }

    /**
     * Asigna una calififcacion.
     *
     * @param calificacion
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Asigna el Id.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna la caliificacion.
     *
     * @return Calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * Retorna el Id de la calificación.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Transforma el DTO en una entidad.
     *
     * @return entity
     */
    public CalificacionEntity toEntity() {
        CalificacionEntity entity = new CalificacionEntity();
        entity.setCalificacion(this.calificacion);
        entity.setId(this.id);
        return entity;
    }
}
