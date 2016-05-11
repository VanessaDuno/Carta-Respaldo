package ssmc.CartaRespaldo.interfacedao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ssmc.CartaRespaldo.modelo.maestros.DetallePrestacion;

/**
 * IDetallePrestacionDAO
 * 
 * @author Vanessa Duno
 * @version 1.0
 * 
 */

public interface IDetallePrestacionDAO extends
		JpaRepository<DetallePrestacion, Integer> {

	List<DetallePrestacion> findByPrestacionId(int idPrestacion);
	
	@Query ("select p from DetallePrestacion p where p.codigo like CONCAT(?1,'%')")
	List<DetallePrestacion> buscarPorCategoria(String parametro);
	
	@Query ("select p from DetallePrestacion p where codigo between ?1 and ?2")
	List<DetallePrestacion> buscarPorCategoriaTraumatologia (String inicio,String fin); 
}
