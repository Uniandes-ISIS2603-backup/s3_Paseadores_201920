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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class ContratoEntity extends BaseEntity implements Serializable{
    
    
    
        //Relaciones
        
            @PodamExclude
            @ManyToOne
            private ClienteEntity cliente;
        
            @PodamExclude
            @ManyToOne
            private PaseadorEntity paseador;
//        
//            @PodamExclude
//            @OneToOne
//            private PagoEntity pago;
//        
            @PodamExclude
            @OneToOne
            private ZonaEntity zona;
//    
//            @PodamExclude
//            @OneToOne
//            private ComentarioEntity comentario;
//    
            @PodamExclude
            @OneToOne
            private FranjaHorariaEntity franja;
//    
//            @PodamExclude
//            @OneToOne
//            private CalificacionEntity calificacion;

	
	//Atributos:
	
	private Double valorServicio;
	
	private Date[] horarios;
	
	private String idPaseador;
	
	private String idMascota;
	
	private String idUsuario;
	
	private Boolean satisfactorio;
        
        private String name;
        
        private Boolean finalizado = false;
	
	
	//Metodos
        
        //Mirar si se deja o elimina
        public String getName() {
            
            return name;
            
        }
        
        public void setName(String pName) {
            
            name = pName;
            
        }
	
	
	//Setters y getters
        
        
        public Boolean getFinalizado() {
            
            return finalizado;
            
        }
        
        public void setFinalizado(Boolean pFinalizado){
    
            finalizado = pFinalizado;
    
        }
	
	
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
        
        public void setIdPaseador(String pIdPaseador) {

		idPaseador = pIdPaseador;

	}


	public void setIdCliente(String pIdCliente) {

		idUsuario = pIdCliente;

	}
	
	public void setIdMascota(String pIdMascota) {

		idMascota = pIdMascota;

	}
	
	public Boolean getSatisfactorio() {
		
		return satisfactorio;
		
	}
	
	
	public void setSatisfactorio(Boolean pSatisfactorio) {
		
		satisfactorio = pSatisfactorio;
		
	}
        
        
        public ClienteEntity getCliente() {
            
            return cliente;
            
        }
        
        
        public void setCliente(ClienteEntity pCliente) {
            
            cliente = pCliente;
            
        }
//        
//        public PaseadorEntity getPaseador() {
//            
//            return paseador;
//            
//        }
//        
//        
//        public void setPaseador(PaseadorEntity pPaseador) {
//            
//            paseador = pPaseador;
//            
//        }
//        
//        
//        public PagoEntity getPago() {
//            
//            return pago;
//            
//        }
//        
//        
//        public void setPago(PagoEntity pPago) {
//            
//            pago = pPago;
//            
//        }
//        
        public ZonaEntity getZona() {
            
            return zona;
            
        }
//        
        
        public void setZona(ZonaEntity pZona) {
            
            zona = pZona;
            
        } 
//        
//        public ComentarioEntity getComentario() {
//            
//            return comentario;
//            
//        }
//        
//        
//        public void setComentario(ComentarioEntity pComentario) {
//            
//            comentario = pComentario;
//            
//        }
//        
        public FranjaHorariaEntity getFranja() {
            
            return franja;
            
        }
        
        
        public void setFranja(FranjaHorariaEntity pFranja) {
            
            franja = pFranja;
            
        }
//        
//        public CalificacionEntity getCalificacion() {
//            
//            return calificacion;
//            
//        }
        
        
//        public void setCalificacion(CalificacionEntity pCalificacion) {
//            
//            calificacion = pCalificacion;
//            
//        }

}
