/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Juan Vergara
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable{
    
    private String idUsuario;
    
    private String idPaseador;
    
    private Double valorServicio;
    
    private Boolean pagoRealizado;
    
    public String getIdUsuario(){
       return idUsuario;
    }
    public void setIdUsuario(String p){
        this.idUsuario=p;
    }
    public String getIdPaseador(){
       return idPaseador;
    }
    public void setIdPaseador(String p){
        this.idUsuario=p;
    }
    public Double getValorServicio(){
       return valorServicio;
    }
    public void setValorServicio(Double p){
       this. valorServicio=p;
    }
    public Boolean getPagoRealizado(){
       return pagoRealizado;
    }
    public void setPagoRealizado(Boolean p){
        this.pagoRealizado=p;
    }
   
}

