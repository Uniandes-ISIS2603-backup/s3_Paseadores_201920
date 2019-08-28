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

@Entity
public class ContratoEntity extends BaseEntity implements Serializable{

	
	//Atributos:
	
	private Double valorServicio;
	
	private Date[] horarios;
	
	private String idPaseador;
	
	private String idMascota;
	
	private String idUsuario;
	
	private Boolean satisfactorio;
        
        private String name;
	
	
	//Metodos
        
        //Mirar si se deja o elimina
        public String getName() {
            
            return name;
            
        }
        
        public void setName(String pName) {
            
            name = pName;
            
        }
	
	public void calificarServicio(Integer pCalificacion) {
		
		
	}
	
	public void realizarComentario(String pComentario) {
		
		
	}
	
	public void realizarPago() {
		
		
	}
	
	
	//Setters y getters
	
	
	public Double getValorServicio() {
		
		return valorServicio;
		
	}
	
	public void setValorServicio(Double pValorServicio) {
		
		valorServicio = pValorServicio;
		
	}
	
	public Date[] getHorarios() { 
		
		return horarios;
		
	}
	
	public void setHorarios(Date[] pHorarios) {
		
		horarios = pHorarios;
		
	}
	
	public String getIdPaseador() {
		
		return idPaseador;
		
	}
	
	public String getIdMascota() {
		
		return idMascota;
		
	}
	
	public String getIdUsuario() {
		
		return idUsuario;
		
	}
	
	public Boolean getSatisfactorio() {
		
		return satisfactorio;
		
	}
	
	
	public void setSatisfactorio(Boolean pSatisfactorio) {
		
		satisfactorio = pSatisfactorio;
		
	}

}
