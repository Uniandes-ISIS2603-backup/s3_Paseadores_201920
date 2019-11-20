package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import java.io.Serializable;

/**
 * Clase que modela el DTO para FormaPago
 *
 * @author Mario Hurtado
 */
public class FormaPagoDTO implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Capacidad de pago.
     */
    private Double capacidadPago;

    /**
     * Id de la forma de pago.
     */
    private Long id;

    /**
     * Constructor por defecto.
     */
    public FormaPagoDTO() {

    }

    /**
     * COnstructor a partir de una entidad.
     *
     * @param formaPago Entidad de la fomra de pago.
     */
    public FormaPagoDTO(FormaPagoEntity formaPago) {
        if (formaPago != null) {
            this.id = formaPago.getId();
            this.capacidadPago = formaPago.getCapacidadPago();
        }
    }

    /**
     * Retorna la capacidad de pago.
     *
     * @return the capacidadPago
     */
    public Double getCapacidadPago() {
        return capacidadPago;
    }

    /**
     * Modifica la capacidad de pago.
     *
     * @param capacidadPago the capacidadPago to set
     */
    public void setCapacidadPago(Double capacidadPago) {
        this.capacidadPago = capacidadPago;
    }

    /**
     * Retorna el Id.
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
     * Convierte el DTO en una entidad.
     *
     * @return entidad.
     */
    public FormaPagoEntity toEntity() {
        FormaPagoEntity entity = new FormaPagoEntity();
        entity.setCapacidadPago(this.capacidadPago);
        entity.setId(this.id);
        return entity;
    }
}
