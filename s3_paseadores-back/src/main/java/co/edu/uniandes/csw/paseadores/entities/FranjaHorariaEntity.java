package co.edu.uniandes.csw.paseadores.entities;

import co.edu.uniandes.csw.paseadores.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 * Entidad que representa una franja horaria.
 *
 * @author Santiago Bolaños Vega
 */
@Entity
public class FranjaHorariaEntity extends BaseEntity implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Fecha y hora de inicio de la franja.
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date inicio;

    /**
     * Fecha y hora de fin de la franja.
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fin;

    /**
     * Relaciones.
     */
    
    /**
     * Paseador que ofrece la franja horaria.
     */
    @PodamExclude
    @ManyToOne
    private PaseadorEntity paseador;

    /**
     * Contrato asociado a la franja horaria.
     */
    @PodamExclude
    @OneToOne(mappedBy = "franja")
    private ContratoEntity contrato;

    /**
     * Metodos.
     */
    
    /**
     * Retorna la fecha y hora de inicio de la franja.
     *
     * @return . Inicio de la franja.
     */
    public Date getInicio() {
        return inicio;
    }

    /**
     * Retorna la fecha y hora de finalización de la franja.
     *
     * @return . Fin de la franja
     */
    public Date getFin() {
        return fin;
    }

    /**
     * Retorna el paseador asociado a la franja.
     *
     * @return . Paseador
     */
    public PaseadorEntity getPaseador() {
        return paseador;
    }

    /**
     * Retorna el contrato asociado a la franja.
     *
     * @return . Contrato.
     */
    public ContratoEntity getContrato() {
        return contrato;
    }

    /**
     * Modifica la fecha y hora de inicio de la franja.
     *
     * @param inicio. Fecha de inicio.
     */
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    /**
     * Modifica la fecha y hora de finalizacion de la franja.
     *
     * @param fin . Fecha de finalización
     */
    public void setFin(Date fin) {
        this.fin = fin;
    }

    /**
     * Modifica el paseador que ofrece la franja.
     *
     * @param paseador. paseador
     */
    public void setPaseador(PaseadorEntity paseador) {
        this.paseador = paseador;
    }

    /**
     * Modifica el contrato asociado a la franja.
     *
     * @param contrato. Contrato.
     */
    public void setContrato(ContratoEntity contrato) {
        this.contrato = contrato;
    }

}
