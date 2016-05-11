package ssmc.CartaRespaldo.interfacedao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.transacciones.Bitacora;

/**
 * IBitacoraDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IBitacoraDAO extends JpaRepository<Bitacora, Integer> {

}
