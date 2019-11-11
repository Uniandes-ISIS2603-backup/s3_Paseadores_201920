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
    private Integer Calificacion;
    private Long id;
    
    //constructor vacio
    public CalificacionDTO(){
        
    }
    //constructor que usa CalificacionEntity para llenar su informacion
    public CalificacionDTO(CalificacionEntity calificacion){
        this.Calificacion=calificacion.getCalificacion();
        this.id=calificacion.getId();
    }
    //setters de calificacion e id
    
    public void setCalificacion(Integer Calificacion) {
        this.Calificacion = Calificacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //getters de calificacion e id
    /**
    *@return Calificacion
    */
    public Integer getCalificacion() {
        return Calificacion;
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
        entity.setCalificacion(this.Calificacion);
        entity.setId(this.id);
        return entity;
    }
}
