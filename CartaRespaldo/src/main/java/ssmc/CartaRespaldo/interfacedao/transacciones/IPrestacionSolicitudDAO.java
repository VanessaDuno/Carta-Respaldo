package ssmc.CartaRespaldo.interfacedao.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.transacciones.PrestacionSolicitud;

/**
 * IPrestacionSolicitudDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IPrestacionSolicitudDAO extends JpaRepository<PrestacionSolicitud, Integer> {
	
	List<PrestacionSolicitud> findBySolicitudTrasladoId (int id); 

}
