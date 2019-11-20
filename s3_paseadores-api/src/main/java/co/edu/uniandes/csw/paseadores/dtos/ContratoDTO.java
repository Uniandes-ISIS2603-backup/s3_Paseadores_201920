package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DTO que representa un contrato.
 *
 * @author Nicolas Potes Garcia
 */
public class ContratoDTO implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Valor del servicio.
     */
    private Double valorServicio;

    /**
     * Servicio satisfacotrio.
     */
    private Boolean satisfactorio;

    /**
     * Contrato finalizado.
     */
    private Boolean finalizado;

    /**
     * Paseador.
     */
    private PaseadorDTO paseador;

    /**
     * Cliente.
     */
    private ClienteDTO cliente;

    /**
     * Franja horaria del contrato.
     */
    private FranjaHorariaDTO franja;

    /**
     * Zona del contrato.
     */
    private ZonaDTO zona;

    /**
     * Pago asociado.
     */
    private PagoDTO pago;

    /**
     * Comentario del contrato.
     */
    private ComentarioDTO comentario;

    /**
     * Calififcación del servicio.
     */
    private CalificacionDTO calificacion;

    /**
     * Id del contrato.
     */
    private Long id;

    /**
     * Constructor por defecto.
     */
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

    /**
     * Retorna el Id
     *
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id
     *
     * @param pId
     */
    public void setId(Long pId) {
        id = pId;
    }

    /**
     * Indica si fanizalizo
     *
     * @return finalizado.
     */
    public Boolean getFinalizado() {
        return finalizado;
    }

    /**
     * Modifica el paseador.
     *
     * @param paseador
     */
    public void setPaseador(PaseadorDTO paseador) {
        this.paseador = paseador;
    }

    /**
     * Modifica el cliente.
     *
     * @param cliente
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * Modifica la franfa.
     *
     * @param franja
     */
    public void setFranja(FranjaHorariaDTO franja) {
        this.franja = franja;
    }

    /**
     * Modifica la zona.
     *
     * @param zona
     */
    public void setZona(ZonaDTO zona) {
        this.zona = zona;
    }

    /**
     * Modifica el pago.
     *
     * @param pago
     */
    public void setPago(PagoDTO pago) {
        this.pago = pago;
    }

    /**
     * Modifica el comentario.
     *
     * @param comentario
     */
    public void setComentario(ComentarioDTO comentario) {
        this.comentario = comentario;
    }

    /**
     * Modifica la calificacion.
     *
     * @param calificacion
     */
    public void setCalificacion(CalificacionDTO calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Modifica el estado de finalización.
     *
     * @param pFinalizado
     */
    public void setFinalizado(Boolean pFinalizado) {
        finalizado = pFinalizado;
    }

    /**
     * Retorna el valor del servicio.
     *
     * @return valorServicio.
     */
    public Double getValorServicio() {
        return valorServicio;
    }

    /**
     * Modifica el valor del servicio.
     *
     * @param pValorServicio
     */
    public void setValorServicio(Double pValorServicio) {
        valorServicio = pValorServicio;
    }

    /**
     * Indica si fue satisfactorio el servicio.
     *
     * @return satisfactoro.
     */
    public Boolean getSatisfactorio() {
        return satisfactorio;
    }

    /**
     * Modifica satisfactorio.
     *
     * @param pSatisfactorio
     */
    public void setSatisfactorio(Boolean pSatisfactorio) {
        satisfactorio = pSatisfactorio;
    }

    /**
     * Retorna el cliente.
     *
     * @return cliente
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Retorna el paseador
     *
     * @return paseador.
     */
    public PaseadorDTO getPaseador() {
        return paseador;
    }

    /**
     * Retorna la zona.
     *
     * @return zona.
     */
    public ZonaDTO getZona() {
        return zona;
    }

    /**
     * Retorna el pago.
     *
     * @return pago.
     */
    public PagoDTO getPago() {
        return pago;
    }

    /**
     * Retorna el comentario.
     *
     * @return comentario.
     */
    public ComentarioDTO getComentario() {
        return comentario;
    }

    /**
     * Retorna la calificación.
     *
     * @return calificación.
     */
    public CalificacionDTO getCalificacion() {
        return calificacion;
    }

    /**
     * Retorna la franja.
     *
     * @return franja
     */
    public FranjaHorariaDTO getFranja() {
        return franja;
    }

    /**
     * Convierte el DTO a String
     *
     * @return
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
