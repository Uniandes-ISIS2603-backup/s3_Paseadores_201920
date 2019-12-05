/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
import co.edu.uniandes.csw.paseadores.entities.MascotaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  Clase que extiende de ClienteDTO para manejar las relaciones de cardinalidad
 *  multiple de los clientes.
 * 
 * @author Santiago Bolaños
 */
public class ClienteDetailDTO extends ClienteDTO implements Serializable{
    
    //===================================================
    // Relaciones
    //===================================================
    
    /**
     * Contratos del cliente
     */
    private List<ContratoDTO> contratos;
    
    /**
     * Mascotas del cliente.
     */
    private List<MascotaDTO> mascotas;
    
    /**
     * Formas de pago del cliente
     */
    private List<FormaPagoDTO> formasPago;
    
    //===================================================
    // Constructores DTO y Entidad
    //===================================================
    
    /**
     * Constructor por defecto.
     */
    public ClienteDetailDTO(){
        super();
    }

    /**
     * Constructor a partir de una entidad.
     * 
     * @param cliente. La entidad del cliente.
     */
    public ClienteDetailDTO(ClienteEntity cliente) {
        super(cliente);
        List<ContratoEntity> contratosEntity = cliente.getContratos();
        if( contratosEntity != null ){
            contratos = new ArrayList<>();
            
            for( ContratoEntity contrato : contratosEntity ){
                contratos.add(new ContratoDTO((contrato)));
            }
        }
        List<MascotaEntity> mascotasEntity = cliente.getMascotas();
        if( mascotasEntity != null ){
            mascotas = new ArrayList<>();
            for( MascotaEntity mascota : mascotasEntity ){
                mascotas.add(new MascotaDTO((mascota)));
            }
        }
        List<FormaPagoEntity> formasPagoEntity = cliente.getFormasPago();
        if( formasPagoEntity != null ){
            formasPago = new ArrayList<>();
            for( FormaPagoEntity formaPago : formasPagoEntity ){
                formasPago.add(new FormaPagoDTO((formaPago)));
            }
        }
    }
    
    /**
     * Método para transformar el DTO en una entidad.
     * 
     * @return 
     * @return. La entidad del cliente.
     */
    public ClienteEntity toEntity(){
        ClienteEntity cliente = super.toEntity();
        if(contratos != null ){
            List<ContratoEntity> contratosEntity = new ArrayList<>();
            for( ContratoDTO contrato : contratos ){
                contratosEntity.add(contrato.toEntity());
            }
            cliente.setContratos(contratosEntity);
        }
        if( mascotas != null ){
            List<MascotaEntity> mascotasEntity = new ArrayList<>();
            for( MascotaDTO mascota : mascotas ){
                mascotasEntity.add(mascota.toEntity());
            }
            cliente.setMascotas(mascotasEntity);
        }
        if( formasPago != null ){
            List<FormaPagoEntity> formasPagoEntity = new ArrayList<>();
            for( FormaPagoDTO formaPago : formasPago ){
                formasPagoEntity.add(formaPago.toEntity());
            }
            cliente.setFormasPago(formasPagoEntity);
        }
        return cliente;
    }
    
    //===================================================
    // GETTERS  
    //===================================================

    /**
     * Retorna los contratos del cliente
     * 
     * @return Contratos del cliente
     */
    public List<ContratoDTO> getContratos() {
        return contratos;
    }

    /**
     * Retorna las mascotas del cliente 
     * 
     * @return Mascotas del cliente
     */
    public List<MascotaDTO> getMascotas() {
        return mascotas;
    }
    
    /**
     * Retorna las formas de pago del cliente
     * 
     * @return Formas de pago del cliente
     */
    public List<FormaPagoDTO> getFormasPago(){
        return formasPago;
    }
    
    //===================================================
    // SETTERS
    //===================================================

    /**
     * Modifica los contratos del cliente
     * 
     * @param contratos Lista de Contratos
     */
    public void setContratos(List<ContratoDTO> contratos) {
        this.contratos = contratos;
    }

    /**
     * Modifica las formas de pago del cliente
     * 
     * @param mascotas
     */
    public void setMascotas(List<MascotaDTO> mascotas) {
        this.mascotas = mascotas;
    }
    
    /**
     * Modifica las formas de pago del cliente
     * 
     * @param formasPago Lista de formas de pago
     */
    public void setFormasPago(List<FormaPagoDTO> formasPago) {
        this.formasPago = formasPago;
    }
    
    
    
    
}
