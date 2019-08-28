/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;
import co.edu.uniandes.csw.paseadores.entities.PagoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
/**
 *
 * @author Mario Hurtado
 */

@Stateless
public class PagoPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());
    
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;
    
    public PagoEntity create (PagoEntity pago){
       LOGGER.log(Level.INFO, "Creando un autor nuevo");  
      em.persist(pago);
       LOGGER.log(Level.INFO, "Pago creado");
      return pago;
    }
    
    
        public List<PagoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los pagos");
        // Se crea un query para buscar todas las pago en la base de datos.
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de pago.
        return query.getResultList();
    }
        
        
         public PagoEntity find(Long pagoId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", pagoId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from PagoEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(PagoEntity.class, pagoId);
         }
         
         
         
         public PagoEntity update(PagoEntity pagoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el pago con id={0}", pagoEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la pago con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(pagoEntity);
    }
         
         
          public void delete(Long pagoId) {

        LOGGER.log(Level.INFO, "Borrando el pago con id={0}", pagoId);
        // Se hace uso de mismo método que esta explicado en public PagoEntity find(Long id) para obtener la pago a borrar.
        PagoEntity pagoEntity = em.find(PagoEntity.class, pagoId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from PagoEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(pagoEntity);
    }
}
