package ssmc.CartaRespaldo.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ssmc.CartaRespaldo.modelo.maestros.CategoriaPrestacion;

/**
 * ICategoriaPrestacionDAO
 * @author Vanessa Duno
 * @version 1.0
 *
 */

public interface ICategoriaPrestacionDAO extends JpaRepository<CategoriaPrestacion, Integer> {

	public List<CategoriaPrestacion> findByPrestacionId (int id); 
}
