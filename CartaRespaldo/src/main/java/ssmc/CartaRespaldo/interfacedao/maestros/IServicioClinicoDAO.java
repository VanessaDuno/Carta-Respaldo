package ssmc.CartaRespaldo.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.ServicioClinico;

/**
 * IServicioClinicoDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IServicioClinicoDAO extends JpaRepository<ServicioClinico, Integer> {
	
	List<ServicioClinico> findByUnidadId(int  idUnidad); 

}
