/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

/**
 * Entidad contrato que se quiere persistir.
 * @author Nicolas Potes Garcia
 */
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;


@Entity
public class ComentarioEntity extends BaseEntity implements Serializable{
    
    
    
    //Atributos
    
    private String infoComentario;
    private String name;
    
    /**
     * Relaciones
     */
    
    @PodamExclude
    @ManyToOne
    private PaseadorEntity paseador;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    @PodamExclude
    @ManyToOne
    private ContratoEntity contrato;
    
    //Setters y getters

    public PaseadorEntity getPaseador() {
        return paseador;
    }

    public void setPaseador(PaseadorEntity paseador) {
        this.paseador = paseador;
    }
    
    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
    public ContratoEntity getContrato() {
        return contrato;
    }

    public void setContrato(ContratoEntity contrato) {
        this.contrato = contrato;
    }
    
    
    public String getInfoComentario() {
        
        return infoComentario;
        
    }
    
    public void setInfoComentario(String pInfo) {
        
        infoComentario = pInfo;
        
    }
    
    
    public String getName() {
            
            return name;
            
        }
        
        public void setName(String pName) {
            
            name = pName;
            
        }
    
}
