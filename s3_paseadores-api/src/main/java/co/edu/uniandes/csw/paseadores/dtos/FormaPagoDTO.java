/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;

/**
 * Clase que modela el DTO para FormaPago
 * @author Mario Hurtado
 */
public class FormaPagoDTO {
    
    //Atributos 
    private double capacidadPago;
    private Long id;
    
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
    public double getCapacidadPago() {
        return capacidadPago;
    }

    /**
     * @param capacidadPago the capacidadPago to set
     */
    public void setCapacidadPago(double capacidadPago) {
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
