package co.edu.uniandes.csw.paseadores.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad que representa un paseador.
 *
 * @author Daniel García
 */
@Entity
public class PaseadorEntity extends BaseEntity implements Serializable {

    /**
     * Atributos.
     */

    /**
     * Nombre del paseador.
     */
    private String nombre;

    /**
     * Correo del paseador.
     */
    private String correo;

    /**
     * Contraseña del paseador.
     */
    private String contrasena;

    /**
     * Información de contacto del paseador.
     */
    private String infoContacto;

    /**
     * Foto del paseador.
     */
    private String foto;

    /**
     * Tarifa del paseador.
     */
    private Double precio;

    /**
     * Ganancias del paseador.
     */
    private Double ganancias;

    /**
     * Información adicional del paseador.
     */
    private String infoAdicional;

    /**
     * Relaciones.
     */
    
    /**
     * Franjas ofrecidas por el paseador.
     */
    @PodamExclude
    @OneToMany(mappedBy = "paseador")
    private List<FranjaHorariaEntity> franjas = new ArrayList<>();

    /**
     * Comentarios que se le han hecho al paseador.
     */
    @PodamExclude
    @OneToMany(mappedBy = "paseador")
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    /**
     * Calificaciones que se le han hecho al paseador.
     */
    @PodamExclude
    @OneToMany(mappedBy = "paseador")
    private List<CalificacionEntity> calificaciones = new ArrayList<>();

    /**
     * Zonas en donde el paseador ofrece sus servicios.
     */
    @PodamExclude
    @ManyToMany(mappedBy = "paseadores")
    private List<ZonaEntity> zonas = new ArrayList<>();

    /**
     * Contratos que ha tenido el paseador.
     */
    @PodamExclude
    @OneToMany(mappedBy = "paseador")
    private List<ContratoEntity> contratos = new ArrayList<>();

    /**
     * Métodos.
     */
    
    /**
     * Retorna los contratos del paseador.
     *
     * @return contratos.
     */
    public List<ContratoEntity> getContratos() {
        return contratos;
    }

    /**
     * Modifica los contratos del paseador.
     *
     * @param contratos
     */
    public void setContratos(List<ContratoEntity> contratos) {
        this.contratos = contratos;
    }

    /**
     * Obtiene las zonas donde trabaja el paseador.
     *
     * @return xoans
     */
    public List<ZonaEntity> getZonas() {
        return zonas;
    }

    /**
     * Modifica el conjunto de zonas en el cual trabaja el paseador.
     *
     * @param zonas
     */
    public void setZonas(List<ZonaEntity> zonas) {
        this.zonas = zonas;
    }

    /**
     * Retorna las calififcaciones del paseador.
     *
     * @return calificaciones.
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Modifica las calificaciones.
     *
     * @param calificaciones
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * Obtiene los comentarios.
     *
     * @return comentarios.
     */
    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    /**
     * Modifica los comentarios del paseador.
     *
     * @param comentarios
     */
    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Obtiene la infomración adicional del apseador.
     *
     * @return infoAdicional.
     */
    public String getInfoAdicional() {
        return infoAdicional;
    }

    /**
     * Modifica la informacion adicional del paseador.
     *
     * @param infoAdicional
     */
    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    /**
     * Modifica la foto del paseador.
     *
     * @param pFoto
     */
    public void setFoto(String pFoto) {
        foto = pFoto;
    }

    /**
     * Retorna la foto del paseador.
     *
     * @return foto.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Modifica la tarifa del paseador.
     *
     * @param dPrecio
     */
    public void setPrecio(Double dPrecio) {
        precio = dPrecio;
    }

    /**
     * Retorna la tarifa del paseador.
     *
     * @return precio.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Modifica las ganancias del paseador.
     *
     * @param pGanancias
     */
    public void setGanancias(Double pGanancias) {
        ganancias = pGanancias;
    }

    /**
     * Obtiene las ganancias del paseador.
     *
     * @return ganancias.
     */
    public Double getGanancias() {
        return ganancias;
    }

    /**
     * Retorna el nombre del paseaodr.
     *
     * @return nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del paseador.
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el correo del paseador.
     *
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica el correo.
     *
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Retorna la contraseña del paseador.
     *
     * @return contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Modifica la contraseña del paseador
     *
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Retorna la infomración de contacto del paseador.
     *
     * @return infoContacto.
     */
    public String getInfoContacto() {
        return infoContacto;
    }

    /**
     * Modifica la infomración de contacto del paseador.
     *
     * @param infoContacto
     */
    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }

    /**
     * Obtiene las franjas ofrecidas por el paseeador.
     *
     * @return franjas
     */
    public List<FranjaHorariaEntity> getFranjas() {
        return franjas;
    }

    /**
     * Modifica las franjas ofrecidas por el paseador.
     *
     * @param franjas
     */
    public void setFranjas(List<FranjaHorariaEntity> franjas) {
        this.franjas = franjas;
    }
}
