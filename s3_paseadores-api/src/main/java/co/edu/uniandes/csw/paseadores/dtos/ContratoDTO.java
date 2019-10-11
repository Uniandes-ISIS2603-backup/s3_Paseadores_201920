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
public class ContratoDTO implements Serializable {

    //Atributos:
    private Double valorServicio;

    private Boolean satisfactorio;

    private String name;

    private Boolean finalizado;

    private PaseadorDTO paseador;

    private ClienteDTO cliente;

    private FranjaHorariaDTO franja;

    private ZonaDTO zona;

    private PagoDTO pago;

    private ComentarioDTO comentario;

    private CalificacionDTO calificacion;

    private Long id;

    //Constructor de contrato vacío
    public ContratoDTO() {

    }

    /**
     * Crea un objeto ContratoDTO a partir de un objeto ContratoEntity.
     *
     * @param contratoEntity Entidad ContratoEntity desde la cual se va a crear
     * el nuevo objeto.
     *
     */
    public ContratoDTO(ContratoEntity contratoEntity) {
        if (contratoEntity != null) {
            this.valorServicio = contratoEntity.getValorServicio();
            this.name = contratoEntity.getName();
            this.satisfactorio = contratoEntity.getSatisfactorio();
            this.finalizado = contratoEntity.getFinalizado();
            this.id = contratoEntity.getId();
            if (contratoEntity.getPaseador() != null) {
                this.paseador = new PaseadorDTO(contratoEntity.getPaseador());
            } else {
                this.paseador = null;
            }

            if (contratoEntity.getCliente() != null) {
                this.cliente = new ClienteDTO(contratoEntity.getCliente());
            } else {
                this.cliente = null;
            }

            if (contratoEntity.getHorarios() != null) {
                this.franja = new FranjaHorariaDTO(contratoEntity.getHorarios());
            } else {
                this.franja = null;
            }

            if (contratoEntity.getZona() != null) {
                this.zona = new ZonaDTO(contratoEntity.getZona());
            } else {
                this.zona = null;
            }

            if (contratoEntity.getPago() != null) {
                this.pago = new PagoDTO(contratoEntity.getPago());
            } else {
                this.pago = null;
            }

            if (contratoEntity.getComentario() != null) {
                this.comentario = new ComentarioDTO(contratoEntity.getComentario());
            } else {
                this.comentario = null;
            }

            if (contratoEntity.getCalificacion() != null) {
                this.calificacion = new CalificacionDTO(contratoEntity.getCalificacion());
            } else {
                this.calificacion = null;
            }

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
        contratoEntity.setSatisfactorio(this.satisfactorio);
        contratoEntity.setFinalizado(this.finalizado);
        contratoEntity.setId(this.id);

        if (this.cliente != null) {
            contratoEntity.setCliente(this.cliente.toEntity());
        }

        if (this.paseador != null) {
            contratoEntity.setPaseador(this.paseador.toEntity());
        }

        if (this.franja != null) {
            contratoEntity.setHorarios(this.franja.toEntity());
        }

        if (this.zona != null) {
            contratoEntity.setZona(this.zona.toEntity());
        }

        if (this.pago != null) {
            contratoEntity.setPago(this.pago.toEntity());
        }

        if (this.comentario != null) {
            contratoEntity.setComentario(this.comentario.toEntity());
        }

        if (this.calificacion != null) {
            contratoEntity.setCalificacion(this.calificacion.toEntity());
        }

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

    public Long getId() {

        return id;

    }

    public void setId(Long pId) {

        id = pId;

    }

    //Setters y getters
    public Boolean getFinalizado() {

        return finalizado;

    }

    public void setFinalizado(Boolean pFinalizado) {

        finalizado = pFinalizado;

    }

    public Double getValorServicio() {

        return valorServicio;

    }

    public void setValorServicio(Double pValorServicio) {

        valorServicio = pValorServicio;

    }

    public Boolean getSatisfactorio() {

        return satisfactorio;

    }

    public void setSatisfactorio(Boolean pSatisfactorio) {

        satisfactorio = pSatisfactorio;

    }

    public ClienteDTO getCliente() {

        return cliente;

    }

    public PaseadorDTO getPaseador() {

        return paseador;

    }

    public ZonaDTO getZona() {

        return zona;

    }

    public PagoDTO getPago() {

        return pago;

    }

    public ComentarioDTO getComentario() {

        return comentario;

    }

    public CalificacionDTO getCalificacion() {

        return calificacion;

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
