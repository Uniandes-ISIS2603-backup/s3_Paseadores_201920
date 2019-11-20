package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import java.io.Serializable;

/**
 * Clase que modela el DTO de Pago
 *
 * @author Mario Hurtado
 */
public class PagoDTO implements Serializable {

    /**
     * Atributos.
     */

    /**
     * Valor del servicio.
     */
    private Double valorServicio;

    /**
     * Pago realizado.
     */
    private Boolean pagoRealizado;

    /**
     * Id del pago.
     */
    private Long id;

    /**
     * Forma de pago.
     */
    private FormaPagoDTO formaPago;

    /**
     * Contructor a partir de una entidad.
     *
     * @param pago Entidad.
     */
    public PagoDTO(PagoEntity pago) {
        if (pago != null) {
            this.pagoRealizado = pago.getPagoRealizado();
            this.valorServicio = pago.getValorServicio();
            this.id = pago.getId();
            if (pago.getFormaPago() != null) {
                this.formaPago = new FormaPagoDTO(pago.getFormaPago());
            }
        }
    }

    /**
     * Constructor por defecto.
     */
    public PagoDTO() {

    }

    /**
     * Retorna el valor del servicio.
     *
     * @return the valorServicio
     */
    public Double getValorServicio() {
        return valorServicio;
    }

    /**
     * Modifica el valor del servicio.
     *
     * @param valorServicio the valorServicio to set
     */
    public void setValorServicio(Double valorServicio) {
        this.valorServicio = valorServicio;
    }

    /**
     * Indica si el pago se realizó.
     *
     * @return the pagoRealizado
     */
    public Boolean isPagoRealizado() {
        return pagoRealizado;
    }

    /**
     * Modifica pago realizado.
     *
     * @param pagoRealizado the pagoRealizado to set
     */
    public void setPagoRealizado(Boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }

    /**
     * Retorna el id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el Id.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna la forma de pago.
     *
     * @return fomraPago.
     */
    public FormaPagoDTO getFormaPago() {
        return formaPago;
    }

    /**
     * Modifica la fomra de pago.
     *
     * @param formaPago
     */
    public void setFormaPago(FormaPagoDTO formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * Convierte el DTO en una entidad.
     *
     * @return entidad.
     */
    public PagoEntity toEntity() {
        PagoEntity entity = new PagoEntity();
        entity.setPagoRealizado(this.isPagoRealizado());
        entity.setValorServicio(this.getValorServicio());
        entity.setId(this.getId());
        entity.setFormaPago(this.formaPago.toEntity());
        return entity;
    }

}
