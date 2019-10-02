/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.dtos;

import co.edu.uniandes.csw.paseadores.entities.ClienteEntity;
import java.io.Serializable;

/**
 *
 * @author Estudiante
 */
class ClienteDTO implements Serializable
{

    ClienteDTO(ClienteEntity cliente) 
    {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public ClienteEntity toEntity()
    {
        //TODO
        return new ClienteEntity();
    }
}
