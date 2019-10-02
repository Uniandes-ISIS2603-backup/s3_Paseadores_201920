/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Estudiante
 */
public class ComentarioDTO implements Serializable {
    
     //Atributos
    
     private String infoComentario;
     private String name;
     
     public ComentarioDTO() {
         
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
        
         /**
     * Crea un objeto ComentarioDTO a partir de un objeto ComentarioEntity.
     *
     * @param comentarioEntity Entidad ComentarioEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public ComentarioDTO(ComentarioEntity comentarioEntity) {
        if (comentarioEntity != null) {
            this.infoComentario = comentarioEntity.getInfoComentario();
            this.name = comentarioEntity.getName();
        }
    
     }
    
    /**
     * Convierte un objeto ContratoDTO a ContratoEntity.
     *
     * @return Nuevo objeto ContratoEntity.
     *
     */
    public ComentarioEntity toEntity() {
        ComentarioEntity comentarioEntity = new ComentarioEntity();
        comentarioEntity.setInfoComentario(this.getInfoComentario());
        comentarioEntity.setName(this.getName());
        return comentarioEntity;
    }
   
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
        
 }
