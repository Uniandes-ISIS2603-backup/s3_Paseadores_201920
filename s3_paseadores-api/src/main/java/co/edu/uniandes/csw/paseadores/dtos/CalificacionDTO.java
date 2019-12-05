/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import java.io.Serializable;

/**
 * Clase que modela el DTO para Calificacion
 * @author Juan Esteban Vergara
 */


public class CalificacionDTO implements Serializable
{
    //atributos
    private Integer calificacion;
    private Long id;
    
    //constructor vacio
    public CalificacionDTO(){
        
    }
    //constructor que usa CalificacionEntity para llenar su informacion
    public CalificacionDTO(CalificacionEntity calificacion){
        this.calificacion=calificacion.getCalificacion();
        this.id=calificacion.getId();
    }
    //setters de calificacion e id
    
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //getters de calificacion e id
    /**
    *@return Calificacion
    */
    public Integer getCalificacion() {
        return calificacion;
    }
    /**
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }
    //metodo toEntity que crea un Entity en base al DTO
    /**
     * 
     * @return entity 
     */
    public CalificacionEntity toEntity(){
        CalificacionEntity entity = new CalificacionEntity();
        entity.setCalificacion(this.calificacion);
        entity.setId(this.id);
        return entity;
    }
}
