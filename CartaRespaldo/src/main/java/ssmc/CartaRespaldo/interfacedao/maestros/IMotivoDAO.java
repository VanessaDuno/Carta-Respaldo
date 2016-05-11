package ssmc.CartaRespaldo.interfacedao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Motivo;

/**
 * IMotivoDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IMotivoDAO extends JpaRepository<Motivo, Integer> {

}
