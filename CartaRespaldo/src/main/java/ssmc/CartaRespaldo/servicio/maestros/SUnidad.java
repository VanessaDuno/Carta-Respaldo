package ssmc.CartaRespaldo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssmc.CartaRespaldo.interfacedao.maestros.IUnidadDAO;
import ssmc.CartaRespaldo.modelo.maestros.Unidad;

/**
 * SUnidad
 * @author Vanessa Duno
 * @version 1.0
 *
 */

@Service("SUnidad")
public class SUnidad {

	@Autowired
	private IUnidadDAO iUnidadDAO;

	/**
	 * buscarTodos: Servicio que busca en todos las unidades por establecimiento
	 * almacenados
	 * 
	 * @param Recibe un int idEstablecimiento
	 * @return Retorna una lista de unidades 
	 * @throws No dispara ninguna excepción.
	 * 
	 */
	public List<Unidad> buscarPorEstablecimiento(int idEstablecimiento) {
		return iUnidadDAO.findByEstablecimientoId(idEstablecimiento); 
	}
	
}
