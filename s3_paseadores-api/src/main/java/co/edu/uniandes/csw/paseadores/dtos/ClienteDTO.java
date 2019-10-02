/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import java.io.Serializable;

/**
 * Clase de transferencia de datos de Clientes. 
 * 
 * @author Santiago Bolaños
 */
class ClienteDTO implements Serializable
{
    //===================================================
    // Atributos
    //===================================================
    
    /**
     * Id del cliente
     */
    private Long id;
    
    /**
     * Nombre del cliente
     */
    private String nombre;
    
    /**
     * Correo del cliente
     */
    private String correo;
    
    /**
     * Contraseña del cliente
     */
    private String contrasena;
    
    /**
     * Información de contacto del cliente
     */
    private String infoContacto;
    
    //===================================================
    // Constructores DTO y Entidad
    //===================================================
    
    /**
     * Constructor por defecto.
     */
    public ClienteDTO(){
        
    }

    /**
     * Constructor a partir de una entidad.
     * 
     * @param cliente. La entidad del cliente.
     */
    public ClienteDTO(ClienteEntity cliente) 
    {
        if( cliente != null ){
                this.id = cliente.getId();
                this.nombre = cliente.getNombre();
                this.contrasena = cliente.getContrasena();
                this.correo = cliente.getCorreo();
                this.infoContacto = cliente.getInfoContacto();
        }
    }
    
    /**
     * Método para transformar el DTO en una entidad.
     * 
     * @return. La entidad del cliente.
     */
    public ClienteEntity toEntity()
    {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(id);
        entity.setNombre(nombre);
        entity.setCorreo(correo);
        entity.setContrasena(contrasena);
        entity.setInfoContacto(infoContacto);
        return entity;
    }
    
    //===================================================
    // GETTERS  
    //===================================================

    /**
     * Retorna el Id del cliente.
     * 
     * @return. Id del CLiente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Retorna el nombre del cliente.}
     * 
     * @return. Nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el correo del cliente.
     * 
     * @return. Correo del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Retorna la contraseña del cliente.
     * 
     * @return. Contraseña del cliente-
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Retorna la infotmación de contacto del cliente
     * 
     * @return información del contacto.
     */
    public String getInfoContacto() {
        return infoContacto;
    }
    
    //===================================================
    // SETTERS
    //===================================================

    /**
     * Modifica el id del cliente
     * 
     * @param id. id del cliente
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * @param contrasena . Contraseña del cliente
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Modifica la infomración de contacto del cliente.
     * 
     * @param infoContacto Información de contacto.
     */
    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }
    
    
}
