package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que modela el DTO para Zona
 *
 * @author Juan Vergara
 */
public class ZonaDTO implements Serializable {

    /**
     * Atributos.
     */
    /**
     * Nombre de la zona.
     */
    private String infoZona;

    /**
     * Id de la zona.
     */
    private Long id;

    /**
     * Constructor por defecto.
     */
    public ZonaDTO() {

    }

    /**
     * Constructor que usa un entity para crear el DTO
     *
     * @param zona
     */
    public ZonaDTO(ZonaEntity zona) {
        this.infoZona = zona.getInfoZona();
        this.id = zona.getId();
    }

    /**
     * Retorna el id.
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id.
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el nombre de la zona.
     * @return infoZona.
     */
    public String getInfoZona() {
        return infoZona;
    }

    /**
     * Modifica el nombre.
     * @param infoZona 
     */
    public void setInfoZona(String infoZona) {
        this.infoZona = infoZona;
    }

    /**
     * Metodo que crea un entity en base al DTO
     *
     * @return entity
     */
    public ZonaEntity toEntity() {
        ZonaEntity entity = new ZonaEntity();
        entity.setInfoZona(this.infoZona);
        entity.setId(this.id);

        return entity;
    }

    /**
     * Convierte el DTO en String.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
