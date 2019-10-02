/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseadores.entities.ComentarioEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.FranjaHorariaEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Garcia
 */
public class PaseadorDetailDTO extends PaseadorDTO implements Serializable
{
    // relación  cero o muchas calificaciones 
    private List<CalificacionDTO> calificaciones;
    
    // relación  cero o muchos comentarios
    private List<ComentarioDTO> comentarios;

    // relación  cero o muchos contratos
    private List<ContratoDTO> contratos;
    
    // relación  cero o muchas franjasHorarias 
    private List<FranjaHorariaDTO> franjas;
    
    // relación  cero o muchas zonas 
    private List<ZonaDTO> zonas;

    public PaseadorDetailDTO() {
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param paseadorEntity La entidad de la cual se construye el DTO
     */
    public PaseadorDetailDTO(PaseadorEntity paseadorEntity) {
        super(paseadorEntity);
        if (paseadorEntity.getCalificaciones() != null) 
        {
            calificaciones = new ArrayList<>();
            for (CalificacionEntity calificacion : paseadorEntity.getCalificaciones()) 
            {
                calificaciones.add(new CalificacionDTO(calificacion));
            }
        }
        if (paseadorEntity.getComentarios() != null) 
        {
            comentarios = new ArrayList<>();
            for (ComentarioEntity comentario : paseadorEntity.getComentarios()) 
            {
                comentarios.add(new ComentarioDTO(comentario));
            }
        }
        if (paseadorEntity.getContratos() != null) 
        {
            contratos = new ArrayList<>();
            for (ContratoEntity contrato : paseadorEntity.getContratos()) 
            {
                contratos.add(new ContratoDTO(contrato));
            }
        }
        if (paseadorEntity.getFranjas() != null) 
        {
            franjas = new ArrayList<>();
            for (FranjaHorariaEntity franja : paseadorEntity.getFranjas()) 
            {
                franjas.add(new FranjaHorariaDTO(franja));
            }
        }
        if (paseadorEntity.getZonas() != null) 
        {
            zonas = new ArrayList<>();
            for (ZonaEntity zona : paseadorEntity.getZonas()) 
            {
                zonas.add(new ZonaDTO(zona));
            }
        }
    }
    
    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public PaseadorEntity toEntity() 
    {
        PaseadorEntity paseadorEntity = super.toEntity();
        if (calificaciones != null) 
        {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO dtoCalificacion : getCalificaciones()) 
            {
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            paseadorEntity.setCalificaciones(calificacionesEntity);
        }
        if (comentarios != null) 
        {
            List<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for (ComentarioDTO dtoComentario : getComentarios()) 
            {
                comentariosEntity.add(dtoComentario.toEntity());
            }
            paseadorEntity.setComentarios(comentariosEntity);
        }
        if (contratos != null) 
        {
            List<ContratoEntity> contratosEntity = new ArrayList<>();
            for (ContratoDTO dtoContrato : getContratos()) 
            {
                contratosEntity.add(dtoContrato.toEntity());
            }
            paseadorEntity.setContratos(contratosEntity);
        }
        if (franjas != null) 
        {
            List<FranjaHorariaEntity> franjasEntity = new ArrayList<>();
            for (FranjaHorariaDTO dtoFranja : getFranjas()) 
            {
                franjasEntity.add(dtoFranja.toEntity());
            }
            paseadorEntity.setFranjas(franjasEntity);
        }
        if (zonas != null) 
        {
            List<ZonaEntity> zonasEntity = new ArrayList<>();
            for (ZonaDTO dtoZona : getZonas()) 
            {
                zonasEntity.add(dtoZona.toEntity());
            }
            paseadorEntity.setZonas(zonasEntity);
        }
        return paseadorEntity;
    }
    
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public List<ContratoDTO> getContratos() {
        return contratos;
    }

    public void setContratos(List<ContratoDTO> contratos) {
        this.contratos = contratos;
    }

    public List<FranjaHorariaDTO> getFranjas() {
        return franjas;
    }

    public void setFranjas(List<FranjaHorariaDTO> franjas) {
        this.franjas = franjas;
    }

    public List<ZonaDTO> getZonas() {
        return zonas;
    }

    public void setZonas(List<ZonaDTO> zonas) {
        this.zonas = zonas;
    }
    
    
    
    
}
