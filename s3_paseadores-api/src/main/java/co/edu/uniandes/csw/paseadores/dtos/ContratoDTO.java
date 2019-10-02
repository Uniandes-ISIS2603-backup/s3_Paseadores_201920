/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.adapters.DateAdapter;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Nicolas Potes Garcia
 */
public class ContratoDTO implements Serializable{

	
	//Atributos:

	private Double valorServicio;


	//private FranjaHorariaEntity horarios;

	private String idPaseador;

	private String idMascota;

	private String idUsuario;

	private Boolean satisfactorio;

	private String name;

	private Boolean finalizado = false;
	

	//Constructor de contrato vacío
	public ContratoDTO() {

	}

	/**
	 * Crea un objeto ContratoDTO a partir de un objeto ContratoEntity.
	 *
	 * @param contratoEntity Entidad ContratoEntity desde la cual se va a crear el
	 * nuevo objeto.
	 *
	 */
	public ContratoDTO(ContratoEntity contratoEntity) {
		if (contratoEntity != null) {
			this.valorServicio = contratoEntity.getValorServicio();
			this.name = contratoEntity.getName();
			this.idPaseador = contratoEntity.getIdPaseador();
			this.idUsuario = contratoEntity.getIdUsuario();
			this.satisfactorio = contratoEntity.getSatisfactorio();
			this.finalizado = contratoEntity.getFinalizado();

		}
	}


	/**
	 * Convierte un objeto ContratoDTO a ContratoEntity.
	 *
	 * @return Nuevo objeto ContratoEntity.
	 *
	 */
	public ContratoEntity toEntity() {
		ContratoEntity contratoEntity = new ContratoEntity();
		contratoEntity.setValorServicio(this.getValorServicio());
		contratoEntity.setName(this.getName());
		contratoEntity.setIdPaseador(this.idPaseador);
		contratoEntity.setIdPaseador(this.idPaseador);
		contratoEntity.setIdUsuario(this.idUsuario);
		contratoEntity.setSatisfactorio(this.satisfactorio);
		contratoEntity.setFinalizado(this.finalizado);
		return contratoEntity;
	}



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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
