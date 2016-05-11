package ssmc.CartaRespaldo.interfacedao.maestros;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Establecimiento;


/**
 * IHospitalDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IEstablecimientoDAO extends JpaRepository<Establecimiento, Integer> {

	List<Establecimiento> findByRegionIdOrderByNombreAsc(int region);
	
}