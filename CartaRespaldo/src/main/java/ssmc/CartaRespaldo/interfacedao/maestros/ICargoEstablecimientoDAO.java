package ssmc.CartaRespaldo.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.CargosEstablecimiento;

/**
 * ICargoEstablecimientoDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface ICargoEstablecimientoDAO extends JpaRepository<CargosEstablecimiento, Integer> {
	
	List<CargosEstablecimiento> findByEstablecimientoId (int idEstablecimiento); 
	
}
