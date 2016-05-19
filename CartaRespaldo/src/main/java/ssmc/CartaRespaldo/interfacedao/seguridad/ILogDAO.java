package ssmc.CartaRespaldo.interfacedao.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.controlador.seguridad.LogActividad;

/**
 * ILogDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface ILogDAO extends JpaRepository<LogActividad, Integer>{

}
