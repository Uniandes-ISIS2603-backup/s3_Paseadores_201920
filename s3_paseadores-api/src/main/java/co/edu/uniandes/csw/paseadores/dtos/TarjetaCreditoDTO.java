/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.TarjetaCreditoEntity;
import java.io.Serializable;

/**
 * Clase que modela el DT0 para TarjetaCredito
 * @author Juan Vergara
 */
public class TarjetaCreditoDTO implements Serializable{
    // Atributos
    private int cvv;
    
    private int numero1;
    
    private int numero2;
    
    private int numero3;

    private int numero4;
    
    private Long id;
    //Constructor vacio
    public TarjetaCreditoDTO(){
        
    }
    /**
     * Contructor que crea el DTO en base al parametro entity
     * @param tarjeta 
     */
    public TarjetaCreditoDTO(TarjetaCreditoEntity tarjeta){
        this.cvv=tarjeta.getCCV();
        this.id=tarjeta.getId();
        this.numero1=tarjeta.getNumero1();
        this.numero2=tarjeta.getNumero2();
        this.numero3=tarjeta.getNumero3();
        this.numero4=tarjeta.getNumero4();
    }
//setters de cvv, y los 4 grupos de números que componen el número de tarjeta de credito
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public void setNumero2(int numero2) {
        this.numero2 = numero2;
    }

    public void setNumero3(int numero3) {
        this.numero3 = numero3;
    }

    public void setNumero4(int numero4) {
        this.numero4 = numero4;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //getters del cvv y los 4 grupos de números que componen el número de tarjeta de credito
    /**
     * 
     * @return cvv
     */
    public int getCvv() {
        return cvv;
    }
/**
 * 
 * @return numero1
 */
    public int getNumero1() {
        return numero1;
    }
    /**
     * 
     * @return numero2 
     */
    public int getNumero2() {
        return numero2;
    }
    /**
     * 
     * @return numero 3 
     */
    public int getNumero3() {
        return numero3;
    }

    public int getNumero4() {
        return numero4;
    }

    public Long getId() {
        return id;
    }
    public TarjetaCreditoEntity toEntity(){
        TarjetaCreditoEntity entity = new TarjetaCreditoEntity();
        entity.setCCV(this.cvv);
        entity.setNumero1(this.numero1);
        entity.setNumero2(this.numero2);
        entity.setNumero3(this.numero3);
        entity.setNumero4(this.numero4);
        entity.setId(this.id);
        return entity;
    }
    
}
