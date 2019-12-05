/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Nicolas Potes Garcia
 */
public class ContratoDTO implements Serializable {

    //Atributos:
    private Double valorServicio;

    private Boolean satisfactorio;

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

            if (contratoEntity.getFranja() != null) {
                this.franja = new FranjaHorariaDTO(contratoEntity.getFranja());
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
            contratoEntity.setFranja(this.franja.toEntity());
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

    public void setPaseador(PaseadorDTO paseador) {
        this.paseador = paseador;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public void setFranja(FranjaHorariaDTO franja) {
        this.franja = franja;
    }

    public void setZona(ZonaDTO zona) {
        this.zona = zona;
    }

    public void setPago(PagoDTO pago) {
        this.pago = pago;
    }

    public void setComentario(ComentarioDTO comentario) {
        this.comentario = comentario;
    }

    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
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

    public FranjaHorariaDTO getFranja() {
        return franja;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
