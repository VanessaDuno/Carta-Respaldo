package ssmc.CartaRespaldo.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Unidad;

/**
 * IUnidadDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IUnidadDAO extends JpaRepository<Unidad, Integer> {

	List<Unidad> findByEstablecimientoId(int idEstablecimiento); 
}
