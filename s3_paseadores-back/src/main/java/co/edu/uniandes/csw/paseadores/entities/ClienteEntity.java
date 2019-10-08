/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa un cliente
 * @author Santiago Bolaños
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable{
    
    //===================================================
    // Atributos
    //===================================================
    
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
     * Información de contacto del cliente.
     */
    private String infoContacto;
    
    //===================================================
    // Relaciones
    //===================================================
    
    /**
     * Mascotas del cliente.
     */
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<MascotaEntity> mascotas = new ArrayList<MascotaEntity>();
    
    /**
     * Contratos del cliente
     */
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<ContratoEntity> contratos = new ArrayList<ContratoEntity>();
    
    /**
     * Formas de pago del cliente
     */
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<FormaPagoEntity> formasPago = new ArrayList<FormaPagoEntity>();

    
    ///===================================================
    // GETTERS
    //===================================================
    
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
    
    /**
     * Retorna las mascotas del cliente 
     * 
     * @return Mascotas del cliente
     */
    public List<MascotaEntity> getMascotas() {
        return mascotas;
    }
    
    /**
     * Retorna los contratos del cliente
     * 
     * @return Contratos del cliente
     */
    public List<ContratoEntity> getContratos(){
        return contratos;
    }
    
    /**
     * Retorna las formas de pago del cliente
     * 
     * @return Formas de pago del cliente
     */
    public List<FormaPagoEntity> getFormasPago(){
        return formasPago;
    }

    //===================================================
    // SETTERS
    //===================================================
    
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
    
    /**
     * Modifica las mascotas del cliente
     * 
     * @param mascotas Lista de mascotas.
     */
    public void setMascotas(List<MascotaEntity> mascotas) {
        this.mascotas = mascotas;
    }
    
    /**
     * Modifica los contratos del cliente
     * 
     * @param contratos Lista de Contratos
     */
    public void setContratos(List<ContratoEntity> contratos) {
        this.contratos = contratos;
    }
    
    /**
     * Modifica las formas de pago del cliente
     * 
     * @param formasPago Lista de formas de pago
     */
    public void setFormasPago(List<FormaPagoEntity> formasPago) {
        this.formasPago = formasPago;
    }
}
