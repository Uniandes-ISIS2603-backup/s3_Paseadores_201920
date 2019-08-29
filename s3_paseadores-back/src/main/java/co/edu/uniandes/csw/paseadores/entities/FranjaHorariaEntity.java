/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

import co.edu.uniandes.csw.paseadores.podam.DateStrategy;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Santiago Bolaños Vega
 */
@Entity
public class FranjaHorariaEntity extends BaseEntity implements Serializable{
    
    /**
     * Atributos
     */
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date inicio;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fin;
    
    /**
     * Relaciones
     */
    
//    @OneToOne( mappedBy = "contrato")
//    private ContratoEntity contrato;
    
//    @OneToOne( mappedBy = "paseador")
//    private PaseadorEntity paseador;

    /**
     * Getters
     */
    
    /**
     * 
     * @return 
     */
    public Date getFecha() {
        return fecha;
    }

    public Date getInicio() {
        return inicio;
    }

    public Date getFin() {
        return fin;
    }

//    public ContratoEntity getContrato() {
//        return contrato;
//    }

//    public PaseadorEntity getPaseador() {
//        return paseador;
//    }
    
    /**
     * Setters
     */
    
    /**
     * 
     * @param fecha 
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

//    public void setContrato(ContratoEntity contrato) {
//        this.contrato = contrato;
//    }

//    public void setPaseador(PaseadorEntity paseador) {
//        this.paseador = paseador;
//    }
    
}
