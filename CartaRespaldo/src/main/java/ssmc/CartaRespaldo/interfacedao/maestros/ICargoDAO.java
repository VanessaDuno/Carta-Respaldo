package ssmc.CartaRespaldo.interfacedao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Cargo;

/**
 * ICargoDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface ICargoDAO extends JpaRepository<Cargo, Integer> {

}
