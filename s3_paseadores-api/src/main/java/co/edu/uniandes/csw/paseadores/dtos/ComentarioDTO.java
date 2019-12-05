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
 * @author Nicolas Potes Garcia 
 */
public class ComentarioDTO implements Serializable {
    
     //Atributos
    
     private String infoComentario;
     private String name;
     private Long id;
     
     //Relaciones
     private ContratoDTO contrato;
     
     
     
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
        
         public Long getId() {
        	
        	return id;
        	
        }
        
        public void setId(Long pId) {
        	
        	id = pId;
        	
        }
        
        
        public ContratoDTO getContrato() {
            
            return contrato;
            
        }
        
        
        public void setContrato(ContratoDTO pContrato) {
            
            contrato = pContrato;
            
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
            this.infoComentario = comentarioEntity.getInfoComentario();
            this.id = comentarioEntity.getId();
            
            
            if (comentarioEntity.getContrato() != null) {
                this.contrato = new ContratoDTO(comentarioEntity.getContrato());
            } else {
                this.contrato = null;
            }

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
        comentarioEntity.setInfoComentario(this.getInfoComentario());
        comentarioEntity.setId(this.getId());

        if (this.contrato != null) {
            comentarioEntity.setContrato(this.contrato.toEntity());
        } 
        
        return comentarioEntity;
    }
   
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
        
 }
