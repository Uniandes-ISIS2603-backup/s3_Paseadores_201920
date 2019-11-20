package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad contrato que se quiere persistir.
 *
 * @author Nicolas Potes Garcia
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Informaciòn del comentario.
     */
    private String infoComentario;

    /**
     * Titulo del comentario.
     */
    private String name;

    /**
     * Relaciones.
     */
    
    /**
     * Paseador al que se le hizo el comentario.
     */
    @PodamExclude
    @ManyToOne
    private PaseadorEntity paseador;

    /**
     * Contrato sobre el que se hizo el comentario.
     */
    @PodamExclude
    @OneToOne(mappedBy = "comentario", fetch = FetchType.LAZY)
    private ContratoEntity contrato;

    /**
     * Mètodos.
     */
    
    /**
     * Retorna el paseador.
     *
     * @return paseador.
     */
    public PaseadorEntity getPaseador() {
        return paseador;
    }

    /**
     * Modifica el paseador.
     *
     * @param paseador
     */
    public void setPaseador(PaseadorEntity paseador) {
        this.paseador = paseador;
    }

    /**
     * Retorna el contrato.
     *
     * @return contrato.
     */
    public ContratoEntity getContrato() {
        return contrato;
    }

    /**
     * Modifica el contrato.
     *
     * @param contrato
     */
    public void setContrato(ContratoEntity contrato) {
        this.contrato = contrato;
    }

    /**
     * Retorna la informaciòn del comentario.
     *
     * @return infoComentario.
     */
    public String getInfoComentario() {
        return infoComentario;
    }

    /**
     * Modifica el contenido del comentario.
     *
     * @param pInfo
     */
    public void setInfoComentario(String pInfo) {
        infoComentario = pInfo;
    }

    /**
     * Retorna el tìtulo del cometnario.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Mofidica el tìtulo del comentario.
     *
     * @param pName
     */
    public void setName(String pName) {
        name = pName;
    }

}
