package ssmc.CartaRespaldo.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Prestacion;

/**
 * IPrestacionEstablecimientoDAO
 * 
 * @author Vanessa Duno
 * @version 1.0
 * 
 */

public interface IPrestacionDAO extends
		JpaRepository<Prestacion, Integer> {

	List<Prestacion> findByTipo(int tipoPrestacion);
}
