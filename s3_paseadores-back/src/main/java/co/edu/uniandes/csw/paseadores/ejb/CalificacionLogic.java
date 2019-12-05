/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.CalificacionEntity;
import co.edu.uniandes.csw.paseadores.entities.ContratoEntity;
import co.edu.uniandes.csw.paseadores.entities.PaseadorEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.paseadores.persistence.ContratoPersistence;
import co.edu.uniandes.csw.paseadores.persistence.PaseadorPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * clase que define la logica de la calificacion
 *
 * @author Juan Vergara
 */
@Stateless
public class CalificacionLogic {

    @Inject
    private CalificacionPersistence persistence;

    @Inject
    private ContratoPersistence contratoPersistence;
    
    @Inject
    private PaseadorPersistence paseadorPersistence;

    /**
     * Crea una calificacion según la que llega por parametro y comprueba las
     * restricciones basicas
     *
     * @param idContrato
     * @param calificacion
     * @return calificacion
     * @throws BusinessLogicException
     */
    public CalificacionEntity createCalificacion(Long idContrato, CalificacionEntity calificacion) throws BusinessLogicException {
        if (calificacion.getCalificacion() > 5 || calificacion.getCalificacion() < 0) {
            throw new BusinessLogicException("La calificación esta fuera de los valores limites [0...5]");

        }
        ContratoEntity contrato = contratoPersistence.find(idContrato);
        if (contrato.getCalificacion() != null) {
            throw new BusinessLogicException("El contrato ya tiene una calificación.");
        }
        calificacion = persistence.create(calificacion);
        contrato.setCalificacion(calificacion);
        PaseadorEntity paseador = contrato.getPaseador();
        List<CalificacionEntity> calificaciones = paseador.getCalificaciones();
        if( calificaciones == null ){
            calificaciones = new ArrayList<>();
        }
        calificaciones.add(calificacion);
        paseador.setCalificaciones(calificaciones);
        contratoPersistence.update(contrato);
        paseadorPersistence.update(paseador);
        return calificacion;
    }

    /**
     * Busca y regresa la calificacion con el id de parametro
     *
     * @param idPaseador
     * @param calificacionId
     * @return calificacionEntity
     * @throws co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException
     */
    public CalificacionEntity getCalificacion(Long idPaseador, Long calificacionId) throws BusinessLogicException {
        CalificacionEntity calificacionEntity = persistence.findCalificacion(idPaseador, calificacionId);
        if (calificacionEntity == null) {
            throw new BusinessLogicException("calificacion no encontrada");
        }
        return calificacionEntity;
    }

    /**
     * Regresa todas las calificaciones existentes
     *
     * @return lista
     */
    public List<CalificacionEntity> getCalificaciones() {
        return persistence.findAll();
    }

    /**
     * Regresa todas las calificaciones existentes
     *
     * @param idPaseador
     * @return lista
     */
    public List<CalificacionEntity> getCalificacionesPorPaseador(Long idPaseador) {
        return persistence.findAllPorPaseador(idPaseador);
    }

    /**
     * Actualiza la informacion de una calificacion y comprueba que esta no se
     * incumpla las restricciones de una calififcacion y regresa una copia
     *
     * @param idContrato
     * @param calificacionEntity
     * @return newCalificacionEntitty
     * @throws BusinessLogicException
     */
    public CalificacionEntity updateCalificacion(CalificacionEntity calificacionEntity) throws BusinessLogicException {

        if (calificacionEntity.getCalificacion() > 5 || calificacionEntity.getCalificacion() < 0) {
            throw new BusinessLogicException("La calificación esta fuera de los valores limites [0...5]");
        }
        return persistence.update(calificacionEntity);
    }

    /**
     * Elimina la calificacion asociada al id dado
     *
     * @param idContrato
     * @param calificacionId
     * @throws BusinessLogicException
     */
    public void deleteCalificacion(Long idPaseador, Long calificacionId) throws BusinessLogicException {
        CalificacionEntity antigua = getCalificacion(idPaseador, calificacionId);
        if (antigua == null) {
            throw new BusinessLogicException("La calificacion con el id " + calificacionId + " no esta asociada al paseador con id " + idPaseador);
        }
        persistence.delete(calificacionId);
    }

}
