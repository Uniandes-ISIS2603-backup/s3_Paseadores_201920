/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Mario Hurtado
 */

@Entity
public class FormaPagoEntity extends BaseEntity implements Serializable{
    
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    @PodamExclude
    @OneToMany(mappedBy="formaPago" , cascade = CascadeType.PERSIST)
    private List<PagoEntity> pagos = new ArrayList<PagoEntity>();
    
    /**
     * Capacidad de pago del usuario.
     */
    protected Double capacidadPago;
    
    /**
     * Retorna la capacidad de pago del usuario.
     * @return Capacidad de pago del cliente.
     */
    public Double getCapacidadPago(){
        return capacidadPago;
    }
    
    
    
    public void setCapacidadPago(Double pPago){
        this.capacidadPago = pPago;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<PagoEntity> getPagos() {
        return pagos;
    }

    public void setPagos(List<PagoEntity> pagos) {
        this.pagos = pagos;
    }
    
    
    
}
