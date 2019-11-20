package co.edu.uniandes.csw.paseadores.ejb;

import co.edu.uniandes.csw.paseadores.entities.ZonaEntity;
import co.edu.uniandes.csw.paseadores.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.paseadores.persistence.ZonaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Lógiza de la zona.
 *
 * @author Juan Vergara
 */
@Stateless
public class ZonaLogic {

    /**
     * Persistencia de la zona.
     */
    @Inject
    private ZonaPersistence persistence;

    /**
     * Crea una zona.
     *
     * @param zona Zona a crear.
     * @return Zona persistida.
     * @throws BusinessLogicException Si no se cumplen las reglas de negocio.
     */
    public ZonaEntity createZona(ZonaEntity zona) throws BusinessLogicException {
        if (zona.getInfoZona() == null) {
            throw new BusinessLogicException("Información nula.");
        }
        String info = zona.getInfoZona().replace(" ", "");
        if (info.isEmpty()) {
            throw new BusinessLogicException("La información de la zona esta vacia");
        }
        return persistence.create(zona);
    }

    /**
     * Obtiene una zona.
     *
     * @param zonaId Id de la zona buscada.
     * @return Zona buscada.
     */
    public ZonaEntity getZona(Long zonaId) {
        return persistence.find(zonaId);
    }

    /**
     * Obtiene todas las zonas en el sistema.
     *
     * @return Zonas.
     */
    public List<ZonaEntity> getZonas() {
        return persistence.findAll();
    }

    /**
     * Actualiza la infomración de una zona.
     *
     * @param zona Zona a acutalizar.
     * @return Zona actualizada.
     * @throws BusinessLogicException Si no se cumplen las reglas de negocio.
     */
    public ZonaEntity updateZona(ZonaEntity zona) throws BusinessLogicException {
        if (zona.getInfoZona() == null) {
            throw new BusinessLogicException("Información nula.");
        }
        String info = zona.getInfoZona().replace(" ", "");
        if (info.isEmpty()) {
            throw new BusinessLogicException("La información de la zona esta vacia");
        }
        return persistence.update(zona);
    }

    /**
     * Elimina una zona.
     *
     * @param zonaId Id de la zona a eliminar
     * @throws BusinessLogicException Si incumple las reglas de negocio.
     */
    public void deleteZona(Long zonaId) throws BusinessLogicException {
        ZonaEntity zona = persistence.find(zonaId);
        if (zona.getContratos() != null && !zona.getContratos().isEmpty()) {
            throw new BusinessLogicException("La zona tiene contratos todavia");
        }
        if (zona.getPaseadores() != null && !zona.getPaseadores().isEmpty()) {
            throw new BusinessLogicException("La zona tiene paseadores todavia");
        }
        persistence.delete(zonaId);
    }
}
