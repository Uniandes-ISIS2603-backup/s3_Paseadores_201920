package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import java.io.Serializable;

/**
 * DTO que representa un paseador.
 *
 * @author Daniel Garcia
 */
public class PaseadorDTO implements Serializable {

    /**
     * Atributos.
     */
    
    /**
     * Id del paseador.
     */
    private Long id;

    /**
     * Nombre del paseador.
     */
    private String nombre;

    /**
     * Correo del paseador.
     */
    private String correo;

    /**
     * Contraseña.
     */
    private String contrasena;

    /**
     * Información de contacot.
     */
    private String infoContacto;

    /**
     * Foto.
     */
    private String foto;

    /**
     * Tarifa establecida.
     */
    private Double precio;

    /**
     * Ganacias obtenidas.
     */
    private Double ganancias;

    /**
     * Información adicional.
     */
    private String infoAdicional;

    /**
     * Constructor por defecto.
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

    /**
     * Retorna el Id.
     *
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el id.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el nombre.
     *
     * @return nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre.
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el correo.
     *
     * @return correo.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Asigna el correo.
     *
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Retorna la contraseña.
     *
     * @return contrasena.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Modifica la contraseña.
     *
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Retorna la información de contacto.
     *
     * @return infoContacto.
     */
    public String getInfoContacto() {
        return infoContacto;
    }

    /**
     * Modifica la infomración de contacto.
     *
     * @param infoContacto
     */
    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }

    /**
     * Retorna la foto.
     *
     * @return foto.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Asgina la foto.
     *
     * @param foto
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Retorna el precio.
     *
     * @return precio.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Asigna la tarifa.
     *
     * @param precio
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Retorna las ganancias
     *
     * @return ganancias.
     */
    public Double getGanancias() {
        return ganancias;
    }

    /**
     * Asigna las ganancias.
     *
     * @param ganancias
     */
    public void setGanancias(Double ganancias) {
        this.ganancias = ganancias;
    }

    /**
     * Retorna la información adicional.
     *
     * @return infoAdicional.
     */
    public String getInfoAdicional() {
        return infoAdicional;
    }

    /**
     * Asigna la informaación adicional.
     *
     * @param infoAdicional
     */
    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

}
