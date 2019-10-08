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
import co.edu.uniandes.csw.paseadores.podam.DateStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

@Entity
public class ContratoEntity extends BaseEntity implements Serializable{



	//Relaciones

	@PodamExclude
	@OneToOne
	private FranjaHorariaEntity franja;

	@PodamExclude
	@OneToOne
	private ClienteEntity cliente;

	@PodamExclude
	@OneToOne
	private ZonaEntity zona;

	@PodamExclude
	@OneToOne
	private PaseadorEntity paseador;

	@PodamExclude
	@OneToOne
	private PagoEntity pago;

	@PodamExclude
	@OneToOne
	private ComentarioEntity comentario;

	@PodamExclude
	@OneToOne
	private CalificacionEntity calificacion;

	@PodamExclude
	@OneToMany
	private List<MascotaEntity> mascotas = new ArrayList<MascotaEntity>();

    
    
	//Atributos:

	private Double valorServicio;

	private String idPaseador;

	private String idUsuario;

	private Boolean satisfactorio;

	private String name;

	private Boolean finalizado = false;
        
        private String idContrato;


	//Metodos
        
        public String getIdContrato() {
            
            return idContrato;
            
        }
        
        public void setIdContrato(String pIdContrato) {
            
            idContrato = pIdContrato;
            
        }

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

	public FranjaHorariaEntity getHorarios() { 

		return franja;

	}

	public void setHorarios(FranjaHorariaEntity pHorarios) {

		franja = pHorarios;

	}

	public String getIdPaseador() {

		return idPaseador;

	}

	public String getIdUsuario() {

		return idUsuario;

	}

	public void setIdPaseador(String pIdPaseador) {

		idPaseador = pIdPaseador;

	}


	public void setIdUsuario(String pIdCliente) {

		idUsuario = pIdCliente;

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

	public PaseadorEntity getPaseador() {

		return paseador;

	}


	public void setPaseador(PaseadorEntity pPaseador) {

		paseador = pPaseador;

	}
	        
	        
	        public PagoEntity getPago() {
	            
	            return pago;
	            
	        }
	        
	        
	        public void setPago(PagoEntity pPago) {
	            
	            pago = pPago;
	            
	        }
	        
	public ZonaEntity getZona() {

		return zona;

	}       

	public void setZona(ZonaEntity pZona) {

		zona = pZona;

	} 


	public List<MascotaEntity> getMascotas() {

		return mascotas;

	}

	public void setMascotas(List<MascotaEntity> pMascotas) {

		mascotas = pMascotas;

	}


	       
	        public ComentarioEntity getComentario() {
	            
	            return comentario;
	            
	        }
	        
	        
	        public void setComentario(ComentarioEntity pComentario) {
	            
	            comentario = pComentario;
	            
	        }
	        

	        
	        public CalificacionEntity getCalificacion() {
	            
	            return calificacion;
	            
	        }


	        public void setCalificacion(CalificacionEntity pCalificacion) {
	            
	            calificacion = pCalificacion;
	            
	        }

}
