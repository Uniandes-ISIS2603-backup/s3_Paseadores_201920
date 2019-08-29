/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseadores.persistence;
import co.edu.uniandes.csw.paseadores.entities.FormaPagoEntity;
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
public class FormaPagoPersistence {
     private static final Logger LOGGER = Logger.getLogger(FormaPagoPersistence.class.getName());
    
    @PersistenceContext(unitName = "paseadoresPU")
    protected EntityManager em;
    
    public FormaPagoEntity create (FormaPagoEntity formaPago){
       LOGGER.log(Level.INFO, "Creando un autor nuevo");  
      em.persist(formaPago);
       LOGGER.log(Level.INFO, "Pago creado");
      return formaPago;
    }
    
    
        public List<FormaPagoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los pagos");
        // Se crea un query para buscar todas las pago en la base de datos.
        TypedQuery query = em.createQuery("select u from FormaPagoEntity u", FormaPagoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de pago.
        return query.getResultList();
    }
        
        
         public FormaPagoEntity find(Long formaPagoId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", formaPagoId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from PagoEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(FormaPagoEntity.class, formaPagoId);
         }
         
         
         
         public FormaPagoEntity update(FormaPagoEntity formaPagoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el pago con id={0}", formaPagoEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la pago con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(formaPagoEntity);
    }
         
         
          public void delete(Long formaPagoId) {

        LOGGER.log(Level.INFO, "Borrando el pago con id={0}", formaPagoId);
        // Se hace uso de mismo método que esta explicado en public PagoEntity find(Long id) para obtener la pago a borrar.
        FormaPagoEntity formaPagoEntity = em.find(FormaPagoEntity.class, formaPagoId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from PagoEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(formaPagoEntity);
    }
}
