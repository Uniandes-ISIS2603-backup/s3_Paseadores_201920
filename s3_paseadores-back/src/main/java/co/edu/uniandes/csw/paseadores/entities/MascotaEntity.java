/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;

/**
 *
 * @author Daniel García
 */
@Entity
public class MascotaEntity extends BaseEntity implements Serializable
{
    //Atributos propios de la clase
    private String idMascota;
    private String nombre;
    private String infoMascota;
    
    
    
    //Get nombre
    public String getNombre() 
    {
        return nombre;
    }
    //Set nombre
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getIdMascota() 
    {
        return idMascota;
    }

    public void setIdMascota(String idMascota) 
    {
        this.idMascota = idMascota;
    }

    public String getInfoMascota() 
    {
        return infoMascota;
    }

    public void setInfoMascota(String infoMascota) 
    {
        this.infoMascota = infoMascota;
    }
    
     
}
