package ssmc.CartaRespaldo.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Responsable;

/**
 * IResponsableDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IResponsableDAO extends JpaRepository<Responsable, Integer> {
	
	public List<Responsable> findByEstablecimientoIdAndCargoId (int idEstablecimiento, int idCargo); 

}
