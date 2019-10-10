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
    private ClienteDTO cliente;
    private double capacidadPago;
    private Long id;
    
    public FormaPagoDTO(FormaPagoEntity formaPago){
        this.capacidadPago = formaPago.getCapacidadPago();
        if (formaPago.getCliente()!= null) {
				this.cliente = new ClienteDTO(formaPago.getCliente());
			} else {
				this.cliente = null;
			}
        
        this.id = formaPago.getId();
    }

    /**
     * @return the cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
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
    
    public FormaPagoEntity toEntity(){
        FormaPagoEntity entity = new FormaPagoEntity();
        entity.setCapacidadPago(this.capacidadPago);
        if (this.cliente != null) {
			entity.setCliente(this.cliente.toEntity());
		}
        entity.setId(this.id);
        return entity;
    }
    
}
