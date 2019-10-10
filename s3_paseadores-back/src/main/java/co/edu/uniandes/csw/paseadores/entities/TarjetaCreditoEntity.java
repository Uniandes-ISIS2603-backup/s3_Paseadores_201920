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
    
    private Integer cvv;
    
    private Integer numero1;
    
    private Integer numero2;
    
    private Integer numero3;

    private Integer numero4;

    public Integer getNumero2() {
        return numero2;
    }

    public Integer getNumero3() {
        return numero3;
    }

    public Integer getNumero4() {
        return numero4;
    }
    
    
    
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date vencimiento; 
    
    public Integer getCCV(){
        return cvv;
    }
    public void setCCV(int p){
        cvv=p;
    }
    public Integer getNumero1(){
        return numero1;
    }

    public void setNumero2(Integer numero2) {
        this.numero2 = numero2;
    }

    public void setNumero3(Integer numero3) {
        this.numero3 = numero3;
    }

    public void setNumero4(Integer numero4) {
        this.numero4 = numero4;
    }
    public void setNumero1(Integer p){
        numero1=p;
    }
    public Date getVencimiento(){
        return vencimiento;
    }
    public void setVencimiento(Date p){
        vencimiento=p;
    }
}
