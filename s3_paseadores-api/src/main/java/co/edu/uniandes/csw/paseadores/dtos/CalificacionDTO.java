/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;

/**
 *
 * @author Estudiante
 */
public class CalificacionDTO {
    private int Calificacion;
    private Long id;
    
    public CalificacionDTO(){
        
    }
    public CalificacionDTO(CalificacionEntity calificacion){
        this.Calificacion=calificacion.getCalificacion();
        this.id=calificacion.getId();
    }

    public void setCalificacion(int Calificacion) {
        this.Calificacion = Calificacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCalificacion() {
        return Calificacion;
    }

    public Long getId() {
        return id;
    }
    public CalificacionEntity toEntity(){
        CalificacionEntity entity = new CalificacionEntity();
        entity.setCalificacion(this.Calificacion);
        entity.setId(this.id);
        return entity;
    }
}
