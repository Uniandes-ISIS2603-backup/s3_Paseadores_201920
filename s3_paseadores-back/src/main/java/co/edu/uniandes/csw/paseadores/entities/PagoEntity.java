/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author Mario Hurtado
 */

@Entity
public class PagoEntity extends BaseEntity implements Serializable {
    
    //Atributos
  
    /**
     * Costo del servicio que presta el paseador.
     */
    private double valorServicio;
    
    /**
     * Boolean que indica si ya se pagó el servicio.
     */
    private  boolean pagoRealizado;
    
    @PodamExclude
    @ManyToOne
    private FormaPagoEntity formaPago;
    
    @PodamExclude
    @OneToOne
    private ContratoEntity contrato;
    
    //Métodos

    public double getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(double valorServicio) {
        this.valorServicio = valorServicio;
    }

    public boolean getPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }

    public FormaPagoEntity getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPagoEntity formaPago) {
        this.formaPago = formaPago;
    }

    public ContratoEntity getContrato() {
        return contrato;
    }

    public void setContrato(ContratoEntity contrato) {
        this.contrato = contrato;
    }
    
    
            
}
