/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ContratoPersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author Nicolas Potes Garcia
 */

@Stateless
public class ContratoLogic {
    
    @Inject
    private ContratoPersistence persistence;
    
    
    public ContratoEntity createContrato(ContratoEntity contrato) throws BusinessLogicException {
        
        
        if(contrato.getHorarios() == null) {
            
            throw new BusinessLogicException("No se han definido los horarios del contrato");
            
        }
        
        if( contrato.getName() == null ){
            throw new BusinessLogicException("El nombre del contrato está vacío");
        }
        
        
        if(contrato.getValorServicio() == null || contrato.getValorServicio() < 0) {
            
            throw new BusinessLogicException("El valor del contrato no es válido o no se ha definido");
            
        }
        
        
        if(contrato.getIdPaseador() == null || contrato.getIdPaseador().isEmpty()) {
            
            throw new BusinessLogicException("El contrato no tiene asociado un paseador o un cliente");
            
        }
        
        if(contrato.getIdMascota() == null || contrato.getIdMascota().isEmpty()) {
            
            throw new BusinessLogicException("El contrato no tiene asociado una(s) mascota(s)");
            
        }
        
        
//        if(contrato.getIdUsuario() == null || contrato.getIdUsuario().isEmpty()) {
//            
//            throw new BusinessLogicException("El contrato no tiene asociado una(s) cliente(s)");
//            
//        }
        
        
        
//        if(contrato.getZona() ==null) {
//            
//            throw new BusinessLogicException("Las zonas del contrato están mal definidas");
//            
//        }
        
        
        
//        if(contrato.getZona() !=null && contrato.getZona().getInfoZona().split("/").length != 4 ) {
//            
//            throw new BusinessLogicException("Las zonas del contrato están mal definidas");
//            
//        }
        
        
        contrato = persistence.create(contrato);
        return contrato;
        
    }
    
    
     /**
     * Obtiene los datos de una instancia de Contrato a partir de su ID.
     *
     * @param contratoId Identificador de la instancia a consultar
     * @return Instancia de ContratoEntity con los datos del Paseador consultado.
     */
    public ContratoEntity getContrato(Long contratoId) 
    {
        LOGGER.log(Level.SEVERE, "Inicia el proceso de consultar el contrato con el id = {0}", contratoId);
        ContratoEntity contratoEntity = persistence.find(contratoId);
        if (contratoEntity == null) {
            LOGGER.log(Level.SEVERE, "El contrato con el id = {0} no existe", contratoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el contrato con id = {0}", contratoId);
        return contratoEntity;
    }
    
    /**
     * Obtiene la lista de los registros de Contrato.
     *
     * @return Colección de objetos de ContratoEntity.
     */
    public List<ContratoEntity> getContratos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los contratos");
        List<ContratoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los contratos");
        return lista;
    }
    
    /**
     * Actualiza la información de una instancia de Contrato.
     *
     * @param contratoId Identificador de la instancia a actualizar
     * @param contratoEntity Instancia de ContratoEntity con los nuevos datos.
     * @return Instancia de ContratoEntity con los datos actualizados.
     */
    public ContratoEntity updateContrato(Long contratoId, ContratoEntity contratoEntity) throws BusinessLogicException
    {
        
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el contrato con id = {0}", contratoId);
        if(contratoEntity.getName()==null || contratoEntity.getName().equals("") || NumberUtils.isCreatable(contratoEntity.getName()))
        {
            throw new BusinessLogicException("El nombre del contrato es nulo o tiene un formato incorrecto");
        }
        if(contratoEntity.getHorarios()==null || NumberUtils.isCreatable(contratoEntity.getHorarios().toString()))
        {
            throw new BusinessLogicException("Los horarios del contrato son nulos o tiene un formato incorrecto");
        }
        if(contratoEntity.getIdPaseador()==null || contratoEntity.getIdPaseador().equals("") || NumberUtils.isCreatable(contratoEntity.getIdPaseador()))
        {
            throw new BusinessLogicException("El id del paseador del contrato es nulo o tiene un formato incorrecto");
        }
//        if(contratoEntity.getIdUsuario()==null || contratoEntity.getIdUsuario().equals("") || NumberUtils.isCreatable(contratoEntity.getIdUsuario()))
//        {
//            throw new BusinessLogicException("El id del cliente del contrato es nulo o tiene un formato incorrecto");
//        }
        if(contratoEntity.getIdMascota()==null || contratoEntity.getIdMascota().equals("") || NumberUtils.isCreatable(contratoEntity.getIdMascota()))
        {
            throw new BusinessLogicException("El id de la mascota(s) del contrato es nulo o tiene un formato incorrecto");
        }
        
        ContratoEntity newContratoEntity = persistence.update(contratoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el contrato con id = {0}", contratoId);
        return newContratoEntity;
        
    }
    
    
    public void deleteContratoSinFinalizar(ContratoEntity contrato) throws BusinessLogicException {
        
        
        if(contrato.getFinalizado() == false) {
            
            throw new BusinessLogicException("No se puede eliminar el contrato porque no se ha terminado");
            
        }
         
        persistence.delete(contrato.getId());
        
    }
    
    
    /**
     * Elimina una instancia de Contrato de la base de datos.
     *
     * @param contratoId Identificador de la instancia a eliminar.
     * 
     */
    public void deleteContrato(Long contratoId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el paseador con id = {0}", contratoId);
        persistence.delete(contratoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el paseador con id = {0}", contratoId);
    }
    
    
    
}