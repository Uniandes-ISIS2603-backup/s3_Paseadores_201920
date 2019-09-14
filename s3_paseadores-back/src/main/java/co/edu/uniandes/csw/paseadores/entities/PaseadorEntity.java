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
 *
 * @author Daniel García
 */
@Entity
public class PaseadorEntity extends BaseEntity implements Serializable
{
    //Atributos propios de la clase
    
    private String foto;
    
    private Double precio;
    
    private Double ganancias;
        
    private String infoAdicional;
    
    //Atributos Heredados
    
    private String nombre;
    
    private String correo;
    
    private String contrasena;
    
    private String infoContacto;
    
    
    //Relaciones
            
    //Relacion Paseadores - FranjasHorarias
    @PodamExclude
    @OneToMany(mappedBy = "paseador")
    private List<FranjaHorariaEntity> franjas = new ArrayList<FranjaHorariaEntity>();
    
    //Relacion Paseadores - Comentarios
    @PodamExclude
    @OneToMany(mappedBy = "paseador")
    private List<ComentarioEntity> comentarios = new ArrayList<ComentarioEntity>();
    
    //Relacion Paseadores - Calificaciones
    @PodamExclude
    @OneToMany(mappedBy = "paseador")
    private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();

    //Relacion Paseadores - Zonas
    @PodamExclude
    @OneToMany(mappedBy = "paseador")
    private List<ZonaEntity> zonas = new ArrayList<ZonaEntity>();

    //Get Zonas
    public List<ZonaEntity> getZonas() {
        return zonas;
    }
    //Set Zonas
    public void setZonas(List<ZonaEntity> zonas) {
        this.zonas = zonas;
    }
    
    //Get Calificaciones
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }
    //Set Calificaciones
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    //Get Comentarios
    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }
    //Set Comentarios
    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }
    
    //Get InfoAdicional
    public String getInfoAdicional() 
    {
        return infoAdicional;
    }
    //Set InfoAdicional
    public void setInfoAdicional(String infoAdicional) 
    {
        this.infoAdicional = infoAdicional;
    }
    //Set Foto
    public void setFoto(String pFoto)
    {
        foto=pFoto;
    }
    //Get Foto
    public String getFoto()
    {
        return foto;
    }
    //Set Precio
    public void setPrecio(Double dPrecio)
    {
        precio=dPrecio;
    }
    //Get Precio
    public Double getPrecio()
    {
        return precio;
    }
     //Set Ganancias
    public void setGanancias(Double pGanancias)
    {
        ganancias=pGanancias;
    }
    //Get Ganancias
    public Double getGanancias()
    {
        return ganancias;
    }    
    //Get nombre
    public String getNombre() {
        return nombre;
    }
    //Set nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //Get Correo
    public String getCorreo() {
        return correo;
    }
    //Set Correo
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    //Get Contraseña
    public String getContrasena() {
        return contrasena;
    }
    //Set Contraseña
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    //Get infoContacto
    public String getInfoContacto() {
        return infoContacto;
    }
    //Set infoContacto
    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }
    
    public List<FranjaHorariaEntity> getFranjas(){
        return franjas;
    }
    
    public void setFranjas(List<FranjaHorariaEntity> franjas ){
        this.franjas = franjas;
    }
}
