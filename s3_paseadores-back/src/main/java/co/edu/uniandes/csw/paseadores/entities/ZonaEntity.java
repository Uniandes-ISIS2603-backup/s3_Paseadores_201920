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
public class ZonaEntity extends BaseEntity implements Serializable{
    
    private String infoZona;

    public void setInfoZona(String infoZona) {
        this.infoZona = infoZona;
    }

    public String getInfoZona() {
        return infoZona;
    }
    
}
