package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa una calificacion.
 *
 * @author Juan Vergara
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable {

    /**
     * Atributos
     */
    
    /**
     * Calificación numérica.
     */
    private Integer calificacion;

    /**
     * Relaciones
     */
    /**
     * Entidad del paseador al que se le hizo la calificación.
     */
    @PodamExclude
    @ManyToOne
    private PaseadorEntity paseador;

    /**
     * Contrato que se calificó.
     */
    @PodamExclude
    @OneToOne(mappedBy = "calificacion", fetch = FetchType.LAZY)
    private ContratoEntity contrato;

    /**
     * Metodos
     */
    
    /**
     * Retorna el paseador.
     *
     * @return paseador.
     */
    public PaseadorEntity getPaseador() {
        return paseador;
    }

    /**
     * Modifica el paseador.
     *
     * @param paseador nuevo paseador.
     */
    public void setPaseador(PaseadorEntity paseador) {
        this.paseador = paseador;
    }

    /**
     * Retorna la calificación numérica.
     *
     * @return calificacion.
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * Modifica la calificacion.
     *
     * @param calificacion nueva calificacion.
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Retorna el contrato.
     *
     * @return contrato.
     */
    public ContratoEntity getContrato() {
        return contrato;
    }

    /**
     * Modifica el contrato.
     *
     * @param contrato Nuevo contrato.
     */
    public void setContrato(ContratoEntity contrato) {
        this.contrato = contrato;
    }
}
