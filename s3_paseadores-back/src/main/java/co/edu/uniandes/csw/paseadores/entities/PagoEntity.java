package co.edu.uniandes.csw.paseadores.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa un pago.
 *
 * @author Mario Hurtado
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Costo del servicio que presta el paseador.
     */
    private Double valorServicio;

    /**
     * Boolean que indica si ya se pagó el servicio.
     */
    private Boolean pagoRealizado;

    /**
     * Relaciones.
     */
    
    /**
     * Forma de pago asociada.
     */
    @PodamExclude
    @ManyToOne
    private FormaPagoEntity formaPago;

    /**
     * Contrato del pago.
     */
    @PodamExclude
    @OneToOne(mappedBy = "pago")
    private ContratoEntity contrato;

    /**
     * Métodos.
     */
    
    /**
     * Retorna el valor del paseo.
     *
     * @return valorServicio
     */
    public Double getValorServicio() {
        return valorServicio;
    }

    /**
     * Modifica el valor del apseo.
     *
     * @param valorServicio
     */
    public void setValorServicio(Double valorServicio) {
        this.valorServicio = valorServicio;
    }

    /**
     * Indica si el pago ha sido realizado.
     *
     * @return pagoRealizado.
     */
    public Boolean getPagoRealizado() {
        return pagoRealizado;
    }

    /**
     * Modifica el valor de pago realizado.
     *
     * @param pagoRealizado
     */
    public void setPagoRealizado(Boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }

    /**
     * Retorna la fomra de pago asociada.
     *
     * @return formaPago
     */
    public FormaPagoEntity getFormaPago() {
        return formaPago;
    }

    /**
     * Asigna una forma de pago al pago.
     *
     * @param formaPago
     */
    public void setFormaPago(FormaPagoEntity formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * Retorna el contrato asociado.
     *
     * @return contrato.
     */
    public ContratoEntity getContrato() {
        return contrato;
    }

    /**
     * Modifica el contrato asociado.
     *
     * @param contrato
     */
    public void setContrato(ContratoEntity contrato) {
        this.contrato = contrato;
    }
}
