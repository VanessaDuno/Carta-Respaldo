package ssmc.CartaRespaldo.interfacedao.transacciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ssmc.CartaRespaldo.modelo.transacciones.SolicitudTraslado;

/**
 * ISolicitudTrasladoDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface ISolicitudTrasladoDAO extends JpaRepository<SolicitudTraslado, Integer> {
	
	@Query("select max(id) from SolicitudTraslado")
	public Integer buscarMaxId();

}
