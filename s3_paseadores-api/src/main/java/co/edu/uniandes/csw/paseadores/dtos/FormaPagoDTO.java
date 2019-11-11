/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import java.io.Serializable;

/**
 * Clase que modela el DTO para FormaPago
 * @author Mario Hurtado
 */
public class FormaPagoDTO implements Serializable
{
    
    //Atributos 
    private Double capacidadPago;
    private Long id;
    
    public FormaPagoDTO(){
        
    }
    
    public FormaPagoDTO(FormaPagoEntity formaPago)
    {
        if( formaPago != null ){
                this.id = formaPago.getId();
                this.capacidadPago = formaPago.getCapacidadPago();
        }
    }

    /**
     * @return the capacidadPago
     */
    public Double getCapacidadPago() {
        return capacidadPago;
    }

    /**
     * @param capacidadPago the capacidadPago to set
     */
    public void setCapacidadPago(Double capacidadPago) {
        this.capacidadPago = capacidadPago;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    public FormaPagoEntity toEntity()
    {
        FormaPagoEntity entity = new FormaPagoEntity();
        entity.setCapacidadPago(this.capacidadPago);
        entity.setId(this.id);
        return entity;
    }
    
}
