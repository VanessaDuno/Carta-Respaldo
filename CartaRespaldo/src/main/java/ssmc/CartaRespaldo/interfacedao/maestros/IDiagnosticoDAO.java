package ssmc.CartaRespaldo.interfacedao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Diagnostico;

/**
 * IDiagnosticoDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IDiagnosticoDAO extends JpaRepository<Diagnostico, Long> {
	
	public Diagnostico findByNombre (String nombre); 

}
