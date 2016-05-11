package ssmc.CartaRespaldo.interfacedao.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.seguridad.Grupo;

/**
 * IGrupoDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IGrupoDAO extends JpaRepository<Grupo, Integer>{
	
	public List<Grupo> findByEstado (boolean estado); 

}
