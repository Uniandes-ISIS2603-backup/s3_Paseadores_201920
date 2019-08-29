/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Santiago Bolaños
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable{
    
    /**
     * Atributos
     */
    
    private String nombre;
    
    private String correo;
    
    private String contraseña;
    
    private String infoContacto;
    
    /**
     * Relaciones
     */
    
//    @OneToMany(mappedBy =  "cliente")
//    private ArrayList<ContratoEntity> contratos;

    
    /**
     * Getters
     */
    
    /**
     * Retorna el nombre del cliente
     * 
     * @return nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el correo del cliente
     * 
     * @return correo del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Retorna la contraseña del cliente
     * 
     * @return contraseña del cliente.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Retorna la infotmación de contacto del cliente
     * 
     * @return información del contacto.
     */
    public String getInfoContacto() {
        return infoContacto;
    }

//    /**
//     * Retorna la lista de contratos que ha hecho el cliente.
//     * 
//     * @return lista de contratos.
//     */
//    public ArrayList<ContratoEntity> getContratos() {
//        return contratos;
//    }

    /**
     * Setters
     */
    
    /**
     * Modifica el nombre del cliente
     * 
     * @param nombre. Nombre del cliente 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Modifica el correo del cliente.
     * 
     * @param correo . Correo del cliente
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Modifica la contraseña del cliente.
     * 
     * @param contraseña . Contraseña del cliente
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Modifica la infomración de contacto del cliente.
     * 
     * @param infoContacto Información de contacto.
     */
    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }

//    /**
//     * Modifica la lista de contratos que ha hecho el cliente.
//     * 
//     * @param lista de contratos.
//     */
//    public void setContratos(ArrayList<ContratoEntity> contratos) {
//        this.contratos = contratos;
//    }
  
}
