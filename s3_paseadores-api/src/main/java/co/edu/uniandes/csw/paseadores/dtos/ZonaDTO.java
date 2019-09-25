/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Juan Vergara
 */

public class ZonaDTO  {

    private String infoZona;
    private Long id;
    
    public ZonaDTO(){
        
    }
    public ZonaDTO(ZonaEntity zona){
        this.infoZona=zona.getInfoZona();
        this.id=zona.getId();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfoZona() {
        return infoZona;
    }

    public void setInfoZona(String infoZona) {
        this.infoZona = infoZona;
    }

    public ZonaEntity toEntity(){
        ZonaEntity entity = new ZonaEntity();
        entity.setInfoZona(this.infoZona);
        entity.setId(this.id);
        return entity;
    }
    
}
