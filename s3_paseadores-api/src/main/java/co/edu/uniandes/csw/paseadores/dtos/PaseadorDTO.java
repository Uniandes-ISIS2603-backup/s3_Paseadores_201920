/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import java.io.Serializable;

/**
 *
 * @author Daniel Garcia
 */
public class PaseadorDTO implements Serializable {

    private Long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String infoContacto;
    private String foto;
    private Double precio;
    private Double ganancias;
    private String infoAdicional;

    /**
     * Constructor por defecto
     */
    public PaseadorDTO() {

    }

    /**
     * Constructor a partir de la entidad
     *
     * @param paseador La entidad del paseador
     */
    public PaseadorDTO(PaseadorEntity paseador) {
        if (paseador != null) {
            this.id = paseador.getId();
            this.nombre = paseador.getNombre();
            this.correo = paseador.getCorreo();
            this.contrasena = paseador.getContrasena();
            this.infoContacto = paseador.getInfoContacto();
            this.foto = paseador.getFoto();
            this.precio = paseador.getPrecio();
            this.ganancias = paseador.getGanancias();
            this.infoAdicional = paseador.getInfoAdicional();
        }
    }

    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del paseador asociado.
     */
    public PaseadorEntity toEntity() {
        PaseadorEntity nuevoPaseador = new PaseadorEntity();
        nuevoPaseador.setId(this.id);
        nuevoPaseador.setNombre(this.nombre);
        nuevoPaseador.setCorreo(this.correo);
        nuevoPaseador.setContrasena(this.contrasena);
        nuevoPaseador.setInfoContacto(this.infoContacto);
        nuevoPaseador.setFoto(this.foto);
        nuevoPaseador.setPrecio(this.precio);
        nuevoPaseador.setGanancias(this.ganancias);
        nuevoPaseador.setInfoAdicional(this.infoAdicional);

        return nuevoPaseador;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getInfoContacto() {
        return infoContacto;
    }

    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getGanancias() {
        return ganancias;
    }

    public void setGanancias(Double ganancias) {
        this.ganancias = ganancias;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

}
