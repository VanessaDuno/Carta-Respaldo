package ssmc.CartaRespaldo.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Provincia;

/**
 * IProvincia
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IProvincia extends JpaRepository<Provincia, Integer> {
	
	public List<Provincia> findByRegionId (int idRegion); 

}
