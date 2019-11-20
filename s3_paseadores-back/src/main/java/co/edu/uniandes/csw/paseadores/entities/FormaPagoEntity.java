package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa una forma de pago.
 *
 * @author Mario Hurtado
 */
@Entity
public class FormaPagoEntity extends BaseEntity implements Serializable {

    /**
     * Relaciones.
     */
    
    /**
     * Cliente dueño de la forma de pago.
     */
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    /**
     * Pagos asociados a la fomra de pago.
     */
    @PodamExclude
    @OneToMany(mappedBy = "formaPago", cascade = CascadeType.PERSIST)
    private List<PagoEntity> pagos = new ArrayList<>();

    /**
     * Atributos.
     */
    
    /**
     * Capacidad de pago del usuario.
     */
    protected Double capacidadPago;

    /**
     * Retorna la capacidad de pago del usuario.
     *
     * @return Capacidad de pago del cliente.
     */
    public Double getCapacidadPago() {
        return capacidadPago;
    }

    /**
     * Modifica la capacidad de pago.
     *
     * @param pPago
     */
    public void setCapacidadPago(Double pPago) {
        this.capacidadPago = pPago;
    }

    /**
     * Retorna el cliente dueño.
     *
     * @return cliente.
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * Modifica el cliente dueño.
     *
     * @param cliente
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna los pagos asociados a la forma de pago.
     *
     * @return
     */
    public List<PagoEntity> getPagos() {
        return pagos;
    }

    /**
     * Modifica los pagos asociados a la forma de pago.
     *
     * @param pagos
     */
    public void setPagos(List<PagoEntity> pagos) {
        this.pagos = pagos;
    }

}
