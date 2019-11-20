package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa una zona.
 *
 * @author Juan Vergara
 */
@Entity
public class ZonaEntity extends BaseEntity implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Nombre de la zona.
     */
    private String infoZona;

    /**
     * Relaciones.
     */
    
    /**
     * Paseadores que trabajan en la zona.
     */
    @PodamExclude
    @ManyToMany
    private List<PaseadorEntity> paseadores = new ArrayList<>();

    /**
     * Paseos realizados en la zona.
     */
    @PodamExclude
    @OneToMany(mappedBy = "zona")
    private List<ContratoEntity> contratos = new ArrayList<>();

    /**
     * Métodos.
     */
    
    /**
     * Modifica el nombre de la zona.
     *
     * @param infoZona
     */
    public void setInfoZona(String infoZona) {
        this.infoZona = infoZona;
    }

    /**
     * Retorna el nombre de la zona.
     *
     * @return infoZona.
     */
    public String getInfoZona() {
        return infoZona;
    }

    /**
     * Retorna los paseadores de la zona.
     *
     * @return paseadores.
     */
    public List<PaseadorEntity> getPaseadores() {
        return paseadores;
    }

    /**
     * Modifica los paseadores de la zona.
     *
     * @param paseadores
     */
    public void setPaseadores(List<PaseadorEntity> paseadores) {
        this.paseadores = paseadores;
    }

    /**
     * Obtiene los paseos de la zona.
     *
     * @return contratos.
     */
    public List<ContratoEntity> getContratos() {
        return contratos;
    }

    /**
     * Actualiza los paseos de la zona.
     *
     * @param contratos
     */
    public void setContratos(List<ContratoEntity> contratos) {
        this.contratos = contratos;
    }

}
