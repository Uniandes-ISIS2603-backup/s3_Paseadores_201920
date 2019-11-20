package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa una mascota.
 *
 * @author Daniel García
 */
@Entity
public class MascotaEntity extends BaseEntity implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Nombre de la mascota.
     */
    private String nombre;

    /**
     * Información de la mascota.
     */
    private String infoMascota;

    /**
     * Relaciones.
     */
    
    /**
     * Dueño de la mascota.
     */
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    /**
     * Paseos que ha tenido la mascota.
     */
    @PodamExclude
    @ManyToMany(mappedBy = "mascotas")
    private List<ContratoEntity> contratos = new ArrayList<>();

    /**
     * Métodos.
     */
    
    /**
     * Retorna el dueño de la mascota.
     *
     * @return cleinte.
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * Modifica al dueño de la mascota.
     *
     * @param cliente
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * Retorna el nombre de la mascota.
     *
     * @return nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la mascota.
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la información de la mascota.
     *
     * @return infoMascota
     */
    public String getInfoMascota() {
        return infoMascota;
    }

    /**
     * Modifica la información de la mascota.
     *
     * @param infoMascota
     */
    public void setInfoMascota(String infoMascota) {
        this.infoMascota = infoMascota;
    }

    /**
     * Retorna los paseos que ha tenido la mascota.
     *
     * @return contratos.
     */
    public List<ContratoEntity> getContratos() {
        return contratos;
    }

    /**
     * Modifica los contratos de la mascota.
     *
     * @param contratos
     */
    public void setContratos(List<ContratoEntity> contratos) {
        this.contratos = contratos;
    }

}
