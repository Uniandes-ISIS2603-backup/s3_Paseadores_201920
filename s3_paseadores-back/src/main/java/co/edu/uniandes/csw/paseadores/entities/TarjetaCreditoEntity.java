/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

import co.edu.uniandes.csw.paseadores.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Juan Vergara
 */
@Entity
public class TarjetaCreditoEntity extends BaseEntity implements Serializable{
    
    private int cvv;
    
    private int numero1;
    
    private int numero2;
    
    private int numero3;

    private int numero4;

    public int getNumero2() {
        return numero2;
    }

    public int getNumero3() {
        return numero3;
    }

    public int getNumero4() {
        return numero4;
    }
    
    
    
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date vencimiento; 
    
    public int getCCV(){
        return cvv;
    }
    public void setCCV(int p){
        cvv=p;
    }
    public int getNumero1(){
        return numero1;
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
    public void setNumero1(int p){
        numero1=p;
    }
    public Date getVencimiento(){
        return vencimiento;
    }
    public void setVencimiento(Date p){
        vencimiento=p;
    }
}
