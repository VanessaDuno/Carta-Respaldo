package ssmc.CartaRespaldo.interfacedao.maestros;


import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Region;

/**
 * IRegionDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IRegionDAO extends JpaRepository<Region, Integer> {

	Region findByCodigo (int id); 
}