package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import java.io.Serializable;

/**
 * DTO que representa una mascota.
 *
 * @author Daniel Garcia
 */
public class MascotaDTO implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Id.
     */
    private Long id;

    /**
     * Nombre de la mascota.
     */
    private String nombre;

    /**
     * Información adicional.
     */
    private String infoMascota;

    /**
     * Dueño.
     */
    private ClienteDTO cliente;

    /**
     * Constructor a partir de una entidad
     *
     * @param mascotaEntity La entidad de la cual se construye el DTO
     */
    public MascotaDTO(MascotaEntity mascotaEntity) {
        this.id = mascotaEntity.getId();
        this.nombre = mascotaEntity.getNombre();
        this.infoMascota = mascotaEntity.getInfoMascota();

        if (mascotaEntity.getCliente() != null) {
            this.cliente = new ClienteDTO(mascotaEntity.getCliente());
        } else {
            this.cliente = null;
        }
    }

    /**
     * Constructor por defecto.
     */
    public MascotaDTO() {

    }

    /**
     * Método para transformar del DTO a una entidad.
     *
     * @return La entidad de esta mascota.
     */
    public MascotaEntity toEntity() {
        MascotaEntity mascotaEntity = new MascotaEntity();
        mascotaEntity.setId(this.id);
        mascotaEntity.setNombre(this.nombre);
        mascotaEntity.setInfoMascota(this.infoMascota);

        if (this.cliente != null) {
            mascotaEntity.setCliente(this.cliente.toEntity());
        }
        return mascotaEntity;
    }

    /**
     * Retorna el Id.
     *
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el Id.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el nombre.
     *
     * @return nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la información adicional.
     *
     * @return infoMacota.
     */
    public String getInfoMascota() {
        return infoMascota;
    }

    /**
     * Modifica la información adicional.
     *
     * @param infoMascota
     */
    public void setInfoMascota(String infoMascota) {
        this.infoMascota = infoMascota;
    }

    /**
     * Retorna el dueño.
     *
     * @return cliente.
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Modifica al cliente.
     *
     * @param cliente
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

}
