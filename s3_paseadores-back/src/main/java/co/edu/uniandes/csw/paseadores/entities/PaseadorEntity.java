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
public class PaseadorEntity extends BaseEntity implements Serializable
{
    //Atributos propios de la clase
    private String foto;
    private Double precio;
    private Double ganancias;
    //private ArrayList listContactos;
    private String infoAdicional;
    
    //Atributos Heredados
    private String idUsuario;
    private String nombre;
    private String correo;
    private String contraseña;
    private String infoContacto;
    //Get ListContactos
   /* public ArrayList getListContactos() 
    {
        return listContactos;
    }
    //Set ListContactos
    public void setListContactos(ArrayList listContactos) 
    {
        this.listContactos = listContactos;
    }*/
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
    //Get idUsuario
    public String getIdUsuario() {
        return idUsuario;
    }
    //Set idUsuario
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
    public String getContraseña() {
        return contraseña;
    }
    //Set Contraseña
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    //Get infoContacto
    public String getInfoContacto() {
        return infoContacto;
    }
    //Set infoContacto
    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }  
}