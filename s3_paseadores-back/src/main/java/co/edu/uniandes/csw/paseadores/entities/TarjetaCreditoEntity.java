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
    
    private int ccv;
    
    private int numero;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date vencimiento; 
    
    public int getCCV(){
        return ccv;
    }
    public void setCCV(int p){
        ccv=p;
    }
    public int getNumero(){
        return numero;
    }
    public void setNumero(int p){
        numero=p;
    }
    public Date getVencimiento(){
        return vencimiento;
    }
    public void setVencimiento(Date p){
        vencimiento=p;
    }
}
