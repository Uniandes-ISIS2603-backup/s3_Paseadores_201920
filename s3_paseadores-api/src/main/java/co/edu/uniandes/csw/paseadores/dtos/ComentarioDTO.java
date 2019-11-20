package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DTO que representa un comentario.
 *
 * @author Nicolas Potes Garcia
 */
public class ComentarioDTO implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Información del comentario.
     */
    private String infoComentario;

    /**
     * Título del comentario.
     */
    private String name;

    /**
     * Id del comentario.
     */
    private Long id;

    /**
     * Relaciones.
     */
    /**
     * Contrato.
     */
    private ContratoDTO contrato;

    /**
     * Constructor por defecto.
     */
    public ComentarioDTO() {

    }

    /**
     * Crea un objeto ComentarioDTO a partir de un objeto ComentarioEntity.
     *
     * @param comentarioEntity Entidad ComentarioEntity desde la cual se va a
     * crear el nuevo objeto.
     */
    public ComentarioDTO(ComentarioEntity comentarioEntity) {
        if (comentarioEntity != null) {
            this.infoComentario = comentarioEntity.getInfoComentario();
            this.name = comentarioEntity.getName();
            this.infoComentario = comentarioEntity.getInfoComentario();
            this.id = comentarioEntity.getId();

            if (comentarioEntity.getContrato() != null) {
                this.contrato = new ContratoDTO(comentarioEntity.getContrato());
            } else {
                this.contrato = null;
            }
        }
    }

    /**
     * Retorna la información del comentario.
     *
     * @return infoComentario.
     */
    public String getInfoComentario() {
        return infoComentario;
    }

    /**
     * Modifica la información del comentario.
     *
     * @param pInfo
     */
    public void setInfoComentario(String pInfo) {
        infoComentario = pInfo;
    }

    /**
     * Retorna el título del comentario.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el título del comentario.
     *
     * @param pName
     */
    public void setName(String pName) {
        name = pName;
    }

    /**
     * Retorna el Id del comentario.
     *
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id del comentario.
     *
     * @param pId
     */
    public void setId(Long pId) {
        id = pId;
    }

    /**
     * Retorna el contrato del comentario.
     *
     * @return contrato.
     */
    public ContratoDTO getContrato() {
        return contrato;
    }

    /**
     * Asigna un contrato.
     *
     * @param pContrato
     */
    public void setContrato(ContratoDTO pContrato) {
        contrato = pContrato;
    }

    /**
     * Convierte un objeto ContratoDTO a ContratoEntity.
     *
     * @return Nuevo objeto ContratoEntity.
     *
     */
    public ComentarioEntity toEntity() {
        ComentarioEntity comentarioEntity = new ComentarioEntity();
        comentarioEntity.setInfoComentario(this.getInfoComentario());
        comentarioEntity.setName(this.getName());
        comentarioEntity.setInfoComentario(this.getInfoComentario());
        comentarioEntity.setId(this.getId());

        if (this.contrato != null) {
            comentarioEntity.setContrato(this.contrato.toEntity());
        }

        return comentarioEntity;
    }

    /**
     * Convierte el DTO en String.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
