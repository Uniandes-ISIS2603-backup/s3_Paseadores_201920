/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Mario Hurtado
 */

@Entity
public class FormaPagoEntity extends BaseEntity implements Serializable{
    
    @ManyToOne
    @PodamExclude
    private ClienteEntity cliente;
    
    /**
     * Capacidad de pago del usuario.
     */
    protected double capacidadPago;
    
    /**
     * Retorna la capacidad de pago del usuario.
     * @return Capacidad de pago del cliente.
     */
    public double getCapacidadPago(){
        return capacidadPago;
    }
    
    
    
    public void setCapacidadPago(double pPago){
        this.capacidadPago = pPago;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
    
    
}
