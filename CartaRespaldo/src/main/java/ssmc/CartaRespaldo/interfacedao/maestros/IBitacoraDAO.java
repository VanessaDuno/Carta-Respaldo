package ssmc.CartaRespaldo.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ssmc.CartaRespaldo.modelo.transacciones.Bitacora;

/**
 * IBitacoraDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IBitacoraDAO extends JpaRepository<Bitacora, Integer> {
	
	List<Bitacora> findByEstatusOrderByFechaAsc (String estatus);
	
	List<Bitacora> findByTrasladoUnidadEstablecimientoIdAndActivoOrderByFechaAsc (int id, boolean activo);
		
	@Query("select b from Bitacora b where b.traslado.id = ?1 and b.activo = ?2")
	Bitacora findByTrasladoIdAndActivo (int idTraslado, boolean activo);
	
	Bitacora findByTrasladoIdAndEstatus (int idTraslado, String estado); 

}
