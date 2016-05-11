package ssmc.CartaRespaldo.interfacedao.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.transacciones.ResponsableSolicitud;

/**
 * IResponsableSolicitudDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IResponsableSolicitudDAO extends JpaRepository<ResponsableSolicitud, Integer> {
	
	List<ResponsableSolicitud> findBySolicitudTrasladoId (int idSolicitud); 
	
}
