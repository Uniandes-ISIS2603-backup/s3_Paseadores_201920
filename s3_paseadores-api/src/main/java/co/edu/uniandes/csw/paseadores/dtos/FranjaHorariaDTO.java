/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import java.util.Date;

/**
 * Clase de transferencia de datos de Franjas horarias. 
 * 
 * @author Santiago Bolaños
 */
public class FranjaHorariaDTO {
    
    //===================================================
    // Atributos
    //===================================================
    
    /**
     * Id de la franja
     */
    private Long id;
    
    /**
     * Fecha y hora de inicio de la franja
     */
    private Date inicio;
    
    /**
     * Fecha y hora de fin de la franja
     */
    private Date fin;
    
    //===================================================
    // Relaciones
    //===================================================
    
    /**
     * Contrato de la franja horaria.
     */
    private ContratoDTO contrato;
    
    //===================================================
    // Constructores DTO y Entidad
    //===================================================

    /**
     * Constructor por defecto.
     */
    public FranjaHorariaDTO() {
        
    }
    
    /**
     * Constructor a partir de una entidad.
     * 
     * @param franja. La entidad de la franja.
     */
    public FranjaHorariaDTO(FranjaHorariaEntity franja)
    {
        this.id = franja.getId();
        this.inicio = franja.getInicio();
        this.fin = franja.getFin();
        if( franja.getContrato() != null ){
            this.contrato = new ContratoDTO(franja.getContrato());
        }
    }
    
    /**
     * Método para transformar el DTO en una entidad.
     * 
     * @return. La entidad de la franja.
     */
    public FranjaHorariaEntity toEntity(){
        FranjaHorariaEntity franjaEntity = new FranjaHorariaEntity();
        franjaEntity.setId(id);
        franjaEntity.setInicio(inicio);
        franjaEntity.setFin(fin);
        if( contrato != null ){
            franjaEntity.setContrato(contrato.toEntity());
        }
        return franjaEntity;
    }
    
    //===================================================
    // GETTERS
    //===================================================

    /**
     * Retorna el Id de la franja.
     * 
     * @return id.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Retorna la fecha y hora de inicio de la franja.
     * 
     * @return . Inicio de la franja.
     */
    public Date getInicio() {
        return inicio;
    }

    /**
     * Retorna la fecha y hora de finalización de la franja.
     * 
     * @return . Fin de la franja
     */
    public Date getFin() {
        return fin;
    }

    /**
     * Retorna el contrato asociado a la franja.
     * 
     * @return . Contrato.
     */
    public ContratoDTO getContrato() {
        return contrato;
    }
    
    //===================================================
    // SETTERS
    //===================================================

    /**
     * Modifica la fecha y hora de inicio de la franja.
     * 
     * @param inicio. Fecha de inicio.
     */
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    /**
     * Modifica la fecha y hora de finalizacion de la franja.
     * 
     * @param fin . Fecha de finalización
     */
    public void setFin(Date fin) {
        this.fin = fin;
    }

    /**
     * Modifica el contrato asociado a la franja.
     * 
     * @param contrato. Contrato.
     */
    public void setContrato(ContratoDTO contrato) {
        this.contrato = contrato;
    }

    /**
     * Modifica el Id de la franja.
     * 
     * @param id. id.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
}
