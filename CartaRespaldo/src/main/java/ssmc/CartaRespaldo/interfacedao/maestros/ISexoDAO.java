package ssmc.CartaRespaldo.interfacedao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Sexo;

/**
 * ISexoDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface ISexoDAO extends JpaRepository<Sexo, Integer> {

}
