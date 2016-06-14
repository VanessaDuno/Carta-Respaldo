package ssmc.CartaRespaldo.interfacedao.maestros;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ssmc.CartaRespaldo.modelo.maestros.Establecimiento;


/**
 * IHospitalDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IEstablecimientoDAO extends JpaRepository<Establecimiento, Integer> {

	List<Establecimiento> findByRegionIdAndIsDestinoOrderByNombreAsc(int region, boolean destino);
	
	@Query (value = "select * from establecimiento where isdestino = true and id_establecimiento between 1 and 3;", nativeQuery=true)
	List<Establecimiento> findByIsDestino (); 
	
}