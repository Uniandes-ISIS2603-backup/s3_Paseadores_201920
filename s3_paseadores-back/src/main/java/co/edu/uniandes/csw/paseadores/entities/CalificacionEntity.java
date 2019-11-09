/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Estudiante
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable{
    private Integer calificacion;

    /**
     * Relaciones
     */
    
    @PodamExclude
    @ManyToOne
    private PaseadorEntity paseador;

    @PodamExclude
    @OneToOne
    private ContratoEntity contrato;
    
    public PaseadorEntity getPaseador() {
        return paseador;
    }

    public void setPaseador(PaseadorEntity paseador) {
        this.paseador = paseador;
    }
    
    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public ContratoEntity getContrato() {
        return contrato;
    }

    public void setContrato(ContratoEntity contrato) {
        this.contrato = contrato;
    }
    
    
}
