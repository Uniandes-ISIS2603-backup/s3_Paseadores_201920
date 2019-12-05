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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Vergara
 */
@Entity
public class ZonaEntity extends BaseEntity implements Serializable{
    
    private String infoZona;

     /**
     * Relaciones
     */
    
    @PodamExclude
    @ManyToMany
    private List<PaseadorEntity> paseadores = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "zona")
    private List<ContratoEntity> contratos = new ArrayList<>();
    
    public void setInfoZona(String infoZona) {
        this.infoZona = infoZona;
    }

    public String getInfoZona() {
        return infoZona;
    }

    public List<PaseadorEntity> getPaseadores() {
        return paseadores;
    }

    public void setPaseadores(List<PaseadorEntity> paseadores) {
        this.paseadores = paseadores;
    }

    public List<ContratoEntity> getContratos() {
        return contratos;
    }

    public void setContratos(List<ContratoEntity> contratos) {
        this.contratos = contratos;
    }

   
}
