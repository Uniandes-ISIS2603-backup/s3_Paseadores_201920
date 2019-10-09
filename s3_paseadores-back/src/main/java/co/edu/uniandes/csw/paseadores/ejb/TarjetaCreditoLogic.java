/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.TarjetaCreditoEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.TarjetaCreditoPersistence;
import javax.inject.Inject;

/**
 *
 * @author Juan Vergara
 */
public class TarjetaCreditoLogic {
    @Inject
    private TarjetaCreditoPersistence persistence;
    
    public TarjetaCreditoEntity createTarjetaCredito(TarjetaCreditoEntity tarjetaCredito) throws BusinessLogicException{
        if(tarjetaCredito.getCCV()>999||tarjetaCredito.getCCV()<100){
            throw new BusinessLogicException("Número cvv fuera de rango (100-999)");
        }
        if(tarjetaCredito.getNumero1()<1000||tarjetaCredito.getNumero1()>9999){
            throw new BusinessLogicException("Primeros 4 digitos del número fuera de rango fuera de rango (1000-9999)");
        }
        if(tarjetaCredito.getNumero2()<1000||tarjetaCredito.getNumero2()>9999){
            throw new BusinessLogicException("Segundos 4 digitos del número fuera de rango fuera de rango (1000-9999)");
        }
        if(tarjetaCredito.getNumero3()<1000||tarjetaCredito.getNumero3()>9999){
            throw new BusinessLogicException("Terceros 4 digitos del número fuera de rango fuera de rango (1000-9999)");
        }
        if(tarjetaCredito.getNumero4()<1000||tarjetaCredito.getNumero4()>9999){
            throw new BusinessLogicException("Ultimos 4 digitos del número fuera de rango fuera de rango (1000-9999)");
        }
        tarjetaCredito = persistence.create(tarjetaCredito);
        return tarjetaCredito;
    }
    
}
