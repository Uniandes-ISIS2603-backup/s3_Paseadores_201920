package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DTO detallado del contrato.
 *
 * @author Nicolas Potes Garcia
 */
public class ContratoDetailDTO extends ContratoDTO implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Mascotas.
     */
    private List<MascotaDTO> mascotas;

    /**
     * Constructor por defecto.
     */
    public ContratoDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param contratoEntity La entidad de la cual se construye el DTO
     */
    public ContratoDetailDTO(ContratoEntity contratoEntity) {
        super(contratoEntity);
        if (contratoEntity.getMascotas() != null) {
            mascotas = new ArrayList<>();
            for (MascotaEntity mascota : contratoEntity.getMascotas()) {
                mascotas.add(new MascotaDTO(mascota));
            }
        }
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el contrato.
     */
    @Override
    public ContratoEntity toEntity() {
        ContratoEntity contratoEntity = super.toEntity();
        if (mascotas != null) {
            List<MascotaEntity> mascotasEntity = new ArrayList<>();
            for (MascotaDTO dtoMascota : getMascotas()) {
                mascotasEntity.add(dtoMascota.toEntity());
            }
            contratoEntity.setMascotas(mascotasEntity);
        }

        return contratoEntity;
    }

    /**
     * Retorna las mascotas
     *
     * @return mascotas.
     */
    public List<MascotaDTO> getMascotas() {
        return mascotas;
    }

    /**
     * Modifica las mascotas.
     *
     * @param pMascotas
     */
    public void setMascotas(List<MascotaDTO> pMascotas) {
        mascotas = pMascotas;
    }

    /**
     * Convierte el DTO en String
     *
     * @return
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
