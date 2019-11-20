package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad contrato que se quiere persistir.
 *
 * @author Nicolas Potes Garcia
 */
@Entity
public class ContratoEntity extends BaseEntity implements Serializable {

    /**
     * Relaciones.
     */
    /**
     * Franja horaria del contrato.
     */
    @PodamExclude
    @OneToOne
    private FranjaHorariaEntity franja;

    /**
     * Cliente del contrato.
     */
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    /**
     * Zona del codntrato.
     */
    @PodamExclude
    @ManyToOne
    private ZonaEntity zona;

    /**
     * Paseador del contrato.
     */
    @PodamExclude
    @ManyToOne
    private PaseadorEntity paseador;

    /**
     * Pago asociado al contrato.
     */
    @PodamExclude
    @OneToOne
    private PagoEntity pago;

    /**
     * Comentario asociado al contrato.
     */
    @PodamExclude
    @OneToOne
    private ComentarioEntity comentario;

    /**
     * Calificacion asociada al contrato.
     */
    @PodamExclude
    @OneToOne
    private CalificacionEntity calificacion;

    /**
     * Mascotas asociadas al contrato.
     */
    @PodamExclude
    @ManyToMany
    private List<MascotaEntity> mascotas = new ArrayList<>();

    /**
     * Atributos.
     */
    /**
     * Valor del contrato.
     */
    private Double valorServicio;

    /**
     * Contrato satisfactorio.
     */
    private Boolean satisfactorio;

    /**
     * Contrato finalizado.
     */
    private Boolean finalizado = false;

    /**
     * Metodos.
     */
    /**
     * Determina si el contrato está finalizado.
     *
     * @return finalizado.
     */
    public Boolean getFinalizado() {
        return finalizado;
    }

    /**
     * Modifica el estado de finalización del contrato.
     *
     * @param pFinalizado
     */
    public void setFinalizado(Boolean pFinalizado) {
        finalizado = pFinalizado;
    }

    /**
     * Retorna el valor de servicio del cocontrato.
     *
     * @return valorServicio
     */
    public Double getValorServicio() {
        return valorServicio;
    }

    /**
     * Modifica el valor de servicio del contrato.
     *
     * @param pValorServicio
     */
    public void setValorServicio(Double pValorServicio) {
        valorServicio = pValorServicio;
    }

    /**
     * Retorna la franja horaria del contrato.
     *
     * @return franja
     */
    public FranjaHorariaEntity getFranja() {
        return franja;
    }

    /**
     * Modifica la franja horaria del contrato.
     *
     * @param pHorarios
     */
    public void setFranja(FranjaHorariaEntity pHorarios) {
        franja = pHorarios;
    }

    /**
     * Determina si el servicio fue satisfactorio.
     *
     * @return satisfactorio
     */
    public Boolean getSatisfactorio() {
        return satisfactorio;
    }

    /**
     * Modifica el estado satisfactorio del contrato.
     *
     * @param pSatisfactorio
     */
    public void setSatisfactorio(Boolean pSatisfactorio) {
        satisfactorio = pSatisfactorio;
    }

    /**
     * Retorna el cliente del contrato.
     *
     * @return cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * Modifica el cliente asociado al contrato.
     *
     * @param pCliente
     */
    public void setCliente(ClienteEntity pCliente) {
        cliente = pCliente;
    }

    /**
     * Retorna el paseador del contrato.
     *
     * @return paseador
     */
    public PaseadorEntity getPaseador() {
        return paseador;
    }

    /**
     * Modifica el paseador asociado al contrato.
     *
     * @param pPaseador
     */
    public void setPaseador(PaseadorEntity pPaseador) {
        paseador = pPaseador;
    }

    /**
     * Retorna el pago asociado al contrato.
     *
     * @return pago
     */
    public PagoEntity getPago() {
        return pago;
    }

    /**
     * Modifica el pago asociado al contrato.
     *
     * @param pPago
     */
    public void setPago(PagoEntity pPago) {
        pago = pPago;
    }

    /**
     * Retorna la zona asociada al contrato.
     *
     * @return zona
     */
    public ZonaEntity getZona() {
        return zona;
    }

    /**
     * Modifica la zona del paseo.
     *
     * @param pZona
     */
    public void setZona(ZonaEntity pZona) {
        zona = pZona;
    }

    /**
     * Retorna las mascotas paseadas.
     *
     * @return mascotas
     */
    public List<MascotaEntity> getMascotas() {
        return mascotas;
    }

    /**
     * Modifica las mascotas paseadas.
     *
     * @param pMascotas
     */
    public void setMascotas(List<MascotaEntity> pMascotas) {
        mascotas = pMascotas;
    }

    /**
     * Retorna el comentario del contrato.
     *
     * @return comentario.
     */
    public ComentarioEntity getComentario() {
        return comentario;
    }

    /**
     * Modifica el comentario del contrato.
     *
     * @param pComentario
     */
    public void setComentario(ComentarioEntity pComentario) {
        comentario = pComentario;
    }

    /**
     * Retorna la calificacion del contrato.
     *
     * @return calificacion
     */
    public CalificacionEntity getCalificacion() {
        return calificacion;
    }

    /**
     * Modifica la calificacion
     *
     * @param pCalificacion
     */
    public void setCalificacion(CalificacionEntity pCalificacion) {
        calificacion = pCalificacion;
    }
}
