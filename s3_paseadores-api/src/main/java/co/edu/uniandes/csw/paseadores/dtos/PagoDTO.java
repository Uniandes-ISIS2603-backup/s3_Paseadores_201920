/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.PagoEntity;

/**
 * Clase que modela el DTO de Pago
 *
 * @author Mario Hurtado
 */
public class PagoDTO {

    //Atributos
    private double valorServicio;
    private boolean pagoRealizado;
    private Long id;

    //Constructor
    public PagoDTO(PagoEntity pago) {
        this.pagoRealizado = pago.getPagoRealizado();
        this.valorServicio = pago.getValorServicio();
        this.id = pago.getId();
    }

    //Métodos
    /**
     * @return the valorServicio
     */
    public double getValorServicio() {
        return valorServicio;
    }

    /**
     * @param valorServicio the valorServicio to set
     */
    public void setValorServicio(double valorServicio) {
        this.valorServicio = valorServicio;
    }

    /**
     * @return the pagoRealizado
     */
    public boolean isPagoRealizado() {
        return pagoRealizado;
    }

    /**
     * @param pagoRealizado the pagoRealizado to set
     */
    public void setPagoRealizado(boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
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

    public PagoEntity toEntity() {
        PagoEntity entity = new PagoEntity();
        entity.setPagoRealizado(this.isPagoRealizado());
        entity.setValorServicio(this.getValorServicio());
        entity.setId(this.getId());
        return entity;
    }

}
