/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;
import javax.persistence.Entity;
import java.io.Serializable;
/**
 *
 * @author Mario Hurtado
 */

@Entity
public class PagoEntity extends BaseEntity implements Serializable {
    
    //Atributos
    
    /**
     * Identificador del usuario.
     */
    private String idUsuario;
    
    /**
     * Identificador del paseador.
     */
    private String idPaseador;
    
    /**
     * Costo del servicio que presta el paseador.
     */
    private double valorServicio;
    
    /**
     * Boolean que indica si ya se pagó el servicio.
     */
    private  boolean pagoRealizado;
    
    
    //Métodos

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdPaseador() {
        return idPaseador;
    }

    public void setIdPaseador(String idPaseador) {
        this.idPaseador = idPaseador;
    }

    public double getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(double valorServicio) {
        this.valorServicio = valorServicio;
    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }
    
    public void pagar(){
        return;
    }
            
}
