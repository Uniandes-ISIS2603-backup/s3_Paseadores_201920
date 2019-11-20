/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que modela el DTO para Zona
 * @author Juan Vergara
 */

public class ZonaDTO  implements Serializable{
    //atributos
    private String infoZona;
    private Long id;
    
    //Constructor vacio
    public ZonaDTO(){
        
    }
    /**
     * Constructor que usa un entity para crear el DTO
     * @param zona 
     */
    public ZonaDTO(ZonaEntity zona){
        this.infoZona=zona.getInfoZona();
        
        this.id=zona.getId();
        
        
    }
//setters y getters de infoZona, id y paseador
    
  
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
    /**
     * Metodo que crea un entity en base al DTO
     * @return entity
     */
    public ZonaEntity toEntity(){
        ZonaEntity entity = new ZonaEntity();
        entity.setInfoZona(this.infoZona);
        entity.setId(this.id);
       
        return entity;
    }
    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
    
}
