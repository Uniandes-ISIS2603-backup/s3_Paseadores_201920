/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

/**
 * Entidad contrato que se quiere persistir.
 *
 * @author Nicolas Potes Garcia
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class ContratoEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @OneToOne
    private FranjaHorariaEntity franja;

    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    @PodamExclude
    @ManyToOne
    private ZonaEntity zona;

    @PodamExclude
    @ManyToOne
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
    @ManyToMany(mappedBy = "contratos")
    private List<MascotaEntity> mascotas = new ArrayList<MascotaEntity>();

    private Double valorServicio;

    private Boolean satisfactorio;

    private Boolean finalizado = false;

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

    public FranjaHorariaEntity getFranja() {
        return franja;
    }

    public void setFranja(FranjaHorariaEntity pHorarios) {
        franja = pHorarios;
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
