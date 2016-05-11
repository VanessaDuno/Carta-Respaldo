package ssmc.CartaRespaldo.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.Comuna;

/**
 * IComunaDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface IComunaDAO extends JpaRepository<Comuna, Integer> {
	
	public List<Comuna> findByProvinciaId (int idProvincia); 

}
