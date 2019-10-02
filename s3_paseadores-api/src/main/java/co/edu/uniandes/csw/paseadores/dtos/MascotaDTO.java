/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import java.io.Serializable;

/**
 *
 * @author Daniel Garcia
 */
public class MascotaDTO implements Serializable
{
    private Long id;
    private String nombre;
    private String infoMascota;
    //Relacion con el cliente
    private ClienteDTO cliente;

     /**
     * Constructor a partir de una entidad
     *
     * @param mascotaEntity La entidad de la cual se construye el DTO
     */
    public MascotaDTO(MascotaEntity mascotaEntity) 
    {
        if (mascotaEntity != null) 
        {
            this.id = mascotaEntity.getId();
            this.nombre = mascotaEntity.getNombre();
            this.infoMascota = mascotaEntity.getInfoMascota();
           
            if (mascotaEntity.getCliente() != null) 
            {
                this.cliente = new ClienteDTO(mascotaEntity.getCliente());
            } else 
            {
                this.cliente = null;
            }
        }
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
    
        if (this.cliente != null) 
        {
            mascotaEntity.setCliente(this.cliente.toEntity());
        }
        return mascotaEntity;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfoMascota() {
        return infoMascota;
    }

    public void setInfoMascota(String infoMascota) {
        this.infoMascota = infoMascota;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
    
    
    
    
}
